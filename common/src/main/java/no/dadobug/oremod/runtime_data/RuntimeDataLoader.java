package no.dadobug.oremod.runtime_data;


import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import no.dadobug.oremod.EntryModule;
import pers.solid.brrp.v1.RRPEventHelper;
import pers.solid.brrp.v1.api.LanguageProvider;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.model.ModelJsonBuilder;
import pers.solid.brrp.v1.tag.IdentifiedTagBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class RuntimeDataLoader {

    private static final ResourceLocation en_us = ResourceLocation.tryBuild(EntryModule.modid, "en_us");

    private static final List<Pair<ResourceLocation, String>> itemLocalizationList = new ArrayList<>();
    private static final List<Pair<ResourceLocation, String>> blockLocalizationList = new ArrayList<>();
    private static final Set<IdentifiedTagBuilder<?>> tagSet = new HashSet<>();
    private static final List<Runnable> denseGenList = new ArrayList<>();
    private static final List<Runnable> blockStateList = new ArrayList<>();
    private static boolean blockStatesAdded = false;

    public static RuntimeResourcePack provider = RuntimeResourcePack.create(new ResourceLocation(EntryModule.modid, "dynamic_resources"));


    public static void addLootTable(ResourceLocation id, LootTable.Builder table){
        provider.addLootTable(id.brrp_prefixed("blocks/"), table);
    }
    public static void addBlockTag(ResourceLocation tag, ResourceLocation block){
        if(tagSet.stream().noneMatch((builder) -> {
            if(!builder.identifier.equals(tag) || builder.registry != Registry.BLOCK)return false;
            builder.add(block);
            return true;
        })) tagSet.add(IdentifiedTagBuilder.createBlock(tag).add(block));
    }
    public static void addStandardTexture(ResourceLocation block, Supplier<Block> blockSupplier){
        ResourceLocation newBlock = block.brrp_prefixed("block/");
        provider.addModel(newBlock, ModelJsonBuilder.create("block/cube_all").addTexture("all", newBlock));
        blockStateList.add(() -> provider.addBlockState(block, BlockModelGenerators.createSimpleBlock(blockSupplier.get(), newBlock)));
        provider.addModel(block.brrp_prefixed("item/"), ModelJsonBuilder.create(newBlock));
    }

    public static void addBlockLocalization(ResourceLocation block, String name){
        blockLocalizationList.add(new Pair<>(block, name));
    }

    public static void addItemLocalization(ResourceLocation item, String name){
        itemLocalizationList.add(new Pair<>(item, name));
    }

    public static void addModelItemTexture(ResourceLocation item, String model) {
        provider.addModel(item.brrp_prefixed("item/"), ModelJsonBuilder.create(model));
    }

    public static void addBlockTexture(ResourceLocation block, String model, Supplier<Block> blockSupplier) {
        ResourceLocation newBlock = block.brrp_prefixed("block/");
        provider.addModel(newBlock, ModelJsonBuilder.create("block/cube_all").addTexture("all", model));
        blockStateList.add(() -> provider.addBlockState(block, BlockModelGenerators.createSimpleBlock(blockSupplier.get(), newBlock)));
        provider.addModel(block.brrp_prefixed("item/"), ModelJsonBuilder.create(newBlock));
    }

    public static void addImageItemTexture(ResourceLocation item, String texture) {
        provider.addModel(item.brrp_prefixed("item/"), ModelJsonBuilder.create("item/generated").addTexture("layer0", texture));
    }
    public static void addItemTag(ResourceLocation tag, ResourceLocation item) {
        if(tagSet.stream().noneMatch((builder) -> {
            if(!builder.identifier.equals(tag) || builder.registry != Registry.ITEM)return false;
            builder.add(item);
            return true;
        })) tagSet.add(IdentifiedTagBuilder.createItem(tag).add(item));
    }
    public static void addDenseGen(Runnable runnable) {
        denseGenList.add(runnable);
    }



    public static void init(){
        genRequired();
        tagSet.forEach((tag) -> provider.addTag(tag));



        RRPEventHelper.BEFORE_VANILLA.registerPack((packType) -> {
            if(packType.equals(PackType.CLIENT_RESOURCES) && !blockStatesAdded) {
                blockStateList.forEach(Runnable::run);
                genLang();
                blockStatesAdded = true;
            }
            return provider;
        });
    }
    public static void denseGenInit(){
        denseGenList.forEach(Runnable::run);
    }
    private static void genRequired(){
        ResourceLocation hollow = EntryModule.BEDROCK_HOLLOW.getId();
        addStandardTexture(hollow, EntryModule.BEDROCK_HOLLOW);

        addBlockTag(EntryModule.HOLLOW_TAG.location(), hollow);
        addBlockTag(EntryModule.REGEN_TAG.location(), hollow);
        addBlockTag(BlockTags.WITHER_IMMUNE.location(), hollow);
        addBlockTag(BlockTags.DRAGON_IMMUNE.location(), hollow);
        addBlockTag(BlockTags.MINEABLE_WITH_PICKAXE.location(), hollow);
        addBlockTag(ResourceLocation.tryBuild("fabric", "needs_tool_level_4"), hollow);
        addBlockTag(ResourceLocation.tryBuild("forge", "needs_netherite_tool"), hollow);


        ResourceLocation fractured = EntryModule.BEDROCK_FRACTURED.ore().getId();
        addStandardTexture(fractured, EntryModule.BEDROCK_FRACTURED.ore());

        addBlockTag(EntryModule.FRACTURE_SOURCE_TAG.location(), fractured);
        addBlockTag(EntryModule.CORE_TAG.location(), fractured);
        addBlockTag(EntryModule.REGEN_TAG.location(), fractured);
        addBlockTag(BlockTags.WITHER_IMMUNE.location(), fractured);
        addBlockTag(BlockTags.DRAGON_IMMUNE.location(), fractured);
        addBlockTag(BlockTags.MINEABLE_WITH_PICKAXE.location(), fractured);
        addBlockTag(ResourceLocation.tryBuild("fabric", "needs_tool_level_4"), fractured);
        addBlockTag(ResourceLocation.tryBuild("forge", "needs_netherite_tool"), fractured);

        ResourceLocation fractured_core = EntryModule.BEDROCK_FRACTURED.core().getId();
        addImageItemTexture(fractured_core, "dadobugores:item/regenerative_fractured");
        addItemTag(EntryModule.IS_CORE_TAG.location(), fractured_core);

    }


    private static void genLang(){
        LanguageProvider builder = LanguageProvider.create() ;

        blockLocalizationList.forEach((pair) -> builder.add(Registry.BLOCK.get(pair.getFirst()), pair.getSecond()));

        itemLocalizationList.forEach((pair) -> builder.add(Registry.ITEM.get(pair.getFirst()), pair.getSecond()));

        builder.add("enchantment.dadobugores.arcane_extraction","Arcane Extraction");
        builder.add("enchantment.dadobugores.curse_of_fracturing","Curse of Fracturing");
        builder.add("enchantment.dadobugores.curse_of_shattering","Curse of Shattering");
        builder.add("enchantment.dadobugores.extraction","Extraction");
        builder.add("enchantment.dadobugores.gentle_mining","Gentle Mining");
        builder.add("enchantment.dadobugores.shattering","Shattering");

        builder.add("enchantment.dadobugores.arcane_extraction.desc","Picks up bedrock ores in block form.");
        builder.add("enchantment.dadobugores.curse_of_fracturing.desc","Irreversibly damages bedrock ores.");
        builder.add("enchantment.dadobugores.curse_of_shattering.desc","Damages or destroys regenerative ores.");
        builder.add("enchantment.dadobugores.extraction.desc","Extracts the regenerative core of bedrock ores.");
        builder.add("enchantment.dadobugores.gentle_mining.desc","Greatly increases efficiency for finite regenerative ores.");
        builder.add("enchantment.dadobugores.shattering.desc","Greatly increases the yield of regenerative ores, at the cost of damaging or destroying the ore.");

        builder.add("item.dadobugores.durability.tooltip","Remaining ore durability: ");
        builder.add("item.dadobugores.regen_broken.tooltip","It hums with uncontained power, and lightly burns to the touch");
        builder.add("item.dadobugores.regen_fluid.tooltip","It hums with great power, slowly dripping");
        builder.add("item.dadobugores.regen_joke_fluid_one.tooltip","If only you had some cookies to go with this...");
        builder.add("item.dadobugores.regen_joke_item_one.tooltip","If only you had some milk to go with this...");
        builder.add("item.dadobugores.regen_need_host.tooltip","Place in Hollow Bedrock to activate");
        builder.add("item.dadobugores.regen_power.tooltip","It hums with great power");
        builder.add("item.dadobugores.regen_xp.tooltip","It hums with great magical potential");

        builder.add("itemGroup.dadobugores.item_group","Bedrock Ores");

        builder.add("block.dadobugores.bedrock_hollow","Hollow Bedrock");

        builder.add("block.dadobugores.bedrock_fractured","Fractured Bedrock");
        builder.add("item.dadobugores.regenerative_fractured","Fractured Regenerative Cluster");

        provider.addLang(new ResourceLocation(EntryModule.modid, "en_us"), builder);
    }


}

package no.dadobug.oremod.runtime_data;


import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.Registry;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;
import no.dadobug.oremod.EntryModule;
import no.dadobug.oremod.advancements.BreakRegenerativeBlockTrigger;
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
                genAdvancements();
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

        builder.add("advancement.dadobugores.it_came_back.title","It Came Back?");
        builder.add("advancement.dadobugores.it_came_back.description","Break a regenerative block");
        builder.add("advancement.dadobugores.no_end_in_sight.title","No End in Sight");
        builder.add("advancement.dadobugores.no_end_in_sight.description","Mine a bedrock ore");
        builder.add("advancement.dadobugores.hyperdensity.title","Hyperdensity");
        builder.add("advancement.dadobugores.hyperdensity.description","Mine a Dense ore");
        builder.add("advancement.dadobugores.unbreaking_two.title","Unbreaking 2.0");
        builder.add("advancement.dadobugores.unbreaking_two.description","Preserve the durability of a regenerative block with Gentle Mining");
        builder.add("advancement.dadobugores.yoink.title","Yoink!");
        builder.add("advancement.dadobugores.yoink.description","Grab a regenerative block with silk touch");
        builder.add("advancement.dadobugores.fortune_two.title","Fortune 2.0");
        builder.add("advancement.dadobugores.fortune_two.description","Shatter the durability of a regenerative block for quick riches");
        builder.add("advancement.dadobugores.native_bounty.title","Native Bounty");
        builder.add("advancement.dadobugores.native_bounty.description","Mine a native ore");
        builder.add("advancement.dadobugores.magic_plumber.title","Know Any Magic Plumbers?");
        builder.add("advancement.dadobugores.magic_plumber.description","Mine an xp leak");
        builder.add("advancement.dadobugores.fractured.title","I Hope That Wasn't Important...");
        builder.add("advancement.dadobugores.fractured.description","Fracture a bedrock ore. Be careful around it!");
        builder.add("advancement.dadobugores.gone_for_good.title","Gone For Good");
        builder.add("advancement.dadobugores.gone_for_good.description","Shatter a bedrock ore, granting you fast resources for a price...");
        builder.add("advancement.dadobugores.hollow.title","Hollowing It Out");
        builder.add("advancement.dadobugores.hollow.description","Extract a regenerative core from the bedrock");
        builder.add("advancement.dadobugores.regen_relocate.title","Regenerative Relocation");
        builder.add("advancement.dadobugores.regen_relocate.description","Place a regenerative core in a suitable host block");

        builder.add("itemGroup.dadobugores.item_group","Bedrock Ores");

        builder.add("block.dadobugores.bedrock_hollow","Hollow Bedrock");

        builder.add("block.dadobugores.bedrock_fractured","Fractured Bedrock");
        builder.add("item.dadobugores.regenerative_fractured","Fractured Regenerative Cluster");

        provider.addLang(new ResourceLocation(EntryModule.modid, "en_us"), builder);
    }

    private static void genAdvancements() {
        provider.addAdvancement(new ResourceLocation(EntryModule.modid, "it_came_back"), Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(Registry.BLOCK.get(new ResourceLocation(EntryModule.modid, "bedrock_diamond_ore"))),
                        MutableComponent.create(new TranslatableContents("advancement.dadobugores.it_came_back.title")),
                        MutableComponent.create(new TranslatableContents("advancement.dadobugores.it_came_back.description")),
                        new ResourceLocation(EntryModule.modid, "textures/block/native_iron_ore.png"),
                        FrameType.GOAL,
                        true, true, false
                ))
                .addCriterion("break", new Criterion(BreakRegenerativeBlockTrigger.TriggerInstance.breakRegenerativeBlock())));


        provider.addAdvancement(new ResourceLocation(EntryModule.modid, "no_end_in_sight"), Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(Registry.BLOCK.get(new ResourceLocation(EntryModule.modid, "bedrock_gold_ore"))),
                        MutableComponent.create(new TranslatableContents("advancement.dadobugores.no_end_in_sight.title")),
                        MutableComponent.create(new TranslatableContents("advancement.dadobugores.no_end_in_sight.description")),
                        null,
                        FrameType.TASK,
                        true, true, false
                ))
                .addCriterion("break", new Criterion(BreakRegenerativeBlockTrigger.TriggerInstance.breakRegenerativeBlock(EntryModule.CORE_TAG)))
                .parent(new ResourceLocation(EntryModule.modid, "it_came_back")));


        provider.addAdvancement(new ResourceLocation(EntryModule.modid, "hyperdensity"), Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(Registry.BLOCK.get(new ResourceLocation(EntryModule.modid, "dense_diamond_ore"))),
                        MutableComponent.create(new TranslatableContents("advancement.dadobugores.hyperdensity.title")),
                        MutableComponent.create(new TranslatableContents("advancement.dadobugores.hyperdensity.description")),
                        null,
                        FrameType.TASK,
                        true, true, false
                ))
                .addCriterion("break", new Criterion(BreakRegenerativeBlockTrigger.TriggerInstance.breakRegenerativeBlock(EntryModule.DENSE_TAG)))
                .parent(new ResourceLocation(EntryModule.modid, "it_came_back")));

        ItemStack gentlepick = Items.DIAMOND_PICKAXE.getDefaultInstance();
        gentlepick.enchant(EntryModule.GENTLE_MINING.get(), 5);
        provider.addAdvancement(new ResourceLocation(EntryModule.modid, "unbreaking_two"), Advancement.Builder.advancement()
                .display(new DisplayInfo(gentlepick,
                        MutableComponent.create(new TranslatableContents("advancement.dadobugores.unbreaking_two.title")),
                        MutableComponent.create(new TranslatableContents("advancement.dadobugores.unbreaking_two.description")),
                        null,
                        FrameType.TASK,
                        true, true, false
                ))
                .addCriterion("break", new Criterion(BreakRegenerativeBlockTrigger.TriggerInstance.Builder.builder().isInfinite(false).setTool(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(EntryModule.GENTLE_MINING.get(), MinMaxBounds.Ints.ANY)).build()).build()))
                .parent(new ResourceLocation(EntryModule.modid, "hyperdensity")));

        ItemStack shatterpick = Items.NETHERITE_PICKAXE.getDefaultInstance();
        shatterpick.enchant(EntryModule.SHATTERING.get(), 5);
        provider.addAdvancement(new ResourceLocation(EntryModule.modid, "fortune_two"), Advancement.Builder.advancement()
                .display(new DisplayInfo(shatterpick,
                        MutableComponent.create(new TranslatableContents("advancement.dadobugores.fortune_two.title")),
                        MutableComponent.create(new TranslatableContents("advancement.dadobugores.fortune_two.description")),
                        null,
                        FrameType.TASK,
                        true, true, false
                ))
                .addCriterion("break", new Criterion(BreakRegenerativeBlockTrigger.TriggerInstance.Builder.builder().isInfinite(false).setTool(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(EntryModule.SHATTERING.get(), MinMaxBounds.Ints.ANY)).build()).build()))
                .parent(new ResourceLocation(EntryModule.modid, "hyperdensity")));


        provider.addAdvancement(new ResourceLocation(EntryModule.modid, "yoink"), Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(Registry.BLOCK.get(new ResourceLocation(EntryModule.modid, "dense_emerald_ore"))),
                        MutableComponent.create(new TranslatableContents("advancement.dadobugores.yoink.title")),
                        MutableComponent.create(new TranslatableContents("advancement.dadobugores.yoink.description")),
                        null,
                        FrameType.TASK,
                        true, true, false
                ))
                .addCriterion("break", new Criterion(BreakRegenerativeBlockTrigger.TriggerInstance.Builder.builder().isSilkable(true).setTool(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.ANY)).build()).build()))
                .parent(new ResourceLocation(EntryModule.modid, "hyperdensity")));


        provider.addAdvancement(new ResourceLocation(EntryModule.modid, "native_bounty"), Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(Registry.BLOCK.get(new ResourceLocation(EntryModule.modid, "native_gold_ore"))),
                        MutableComponent.create(new TranslatableContents("advancement.dadobugores.native_bounty.title")),
                        MutableComponent.create(new TranslatableContents("advancement.dadobugores.native_bounty.description")),
                        null,
                        FrameType.TASK,
                        true, true, false
                ))
                .addCriterion("break", new Criterion(BreakRegenerativeBlockTrigger.TriggerInstance.breakRegenerativeBlock(Registry.BLOCK.get(new ResourceLocation(EntryModule.modid, "native_gold_ore")), Registry.BLOCK.get(new ResourceLocation(EntryModule.modid, "native_iron_ore")))))
                .parent(new ResourceLocation(EntryModule.modid, "it_came_back")));


        provider.addAdvancement(new ResourceLocation(EntryModule.modid, "magic_plumber"), Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(Registry.BLOCK.get(new ResourceLocation(EntryModule.modid, "bedrock_xp_leak"))),
                        MutableComponent.create(new TranslatableContents("advancement.dadobugores.magic_plumber.title")),
                        MutableComponent.create(new TranslatableContents("advancement.dadobugores.magic_plumber.description")),
                        null,
                        FrameType.TASK,
                        true, true, false
                ))
                .addCriterion("break", new Criterion(BreakRegenerativeBlockTrigger.TriggerInstance.breakRegenerativeBlock(Registry.BLOCK.get(new ResourceLocation(EntryModule.modid, "bedrock_xp_leak")))))
                .parent(new ResourceLocation(EntryModule.modid, "no_end_in_sight")));



        provider.addAdvancement(new ResourceLocation(EntryModule.modid, "fractured"), Advancement.Builder.advancement()
                .display(new DisplayInfo(EntryModule.BEDROCK_FRACTURED.oreItem().get().getDefaultInstance(),
                        MutableComponent.create(new TranslatableContents("advancement.dadobugores.fractured.title")),
                        MutableComponent.create(new TranslatableContents("advancement.dadobugores.fractured.description")),
                        null,
                        FrameType.TASK,
                        true, true, false
                ))
                .addCriterion("break", new Criterion(BreakRegenerativeBlockTrigger.TriggerInstance.breakRegenerativeBlock(EntryModule.CURSE_OF_FRACTURING.get(), EntryModule.CORE_TAG)))
                .parent(new ResourceLocation(EntryModule.modid, "no_end_in_sight")));



        provider.addAdvancement(new ResourceLocation(EntryModule.modid, "gone_for_good"), Advancement.Builder.advancement()
                .display(new DisplayInfo(Blocks.BEDROCK.asItem().getDefaultInstance(),
                        MutableComponent.create(new TranslatableContents("advancement.dadobugores.gone_for_good.title")),
                        MutableComponent.create(new TranslatableContents("advancement.dadobugores.gone_for_good.description")),
                        null,
                        FrameType.TASK,
                        true, true, false
                ))
                .addCriterion("break", new Criterion(BreakRegenerativeBlockTrigger.TriggerInstance.breakRegenerativeBlock(EntryModule.SHATTERING.get(), EntryModule.CORE_TAG)))
                .parent(new ResourceLocation(EntryModule.modid, "no_end_in_sight")));

        provider.addAdvancement(new ResourceLocation(EntryModule.modid, "hollow"), Advancement.Builder.advancement()
                .display(new DisplayInfo(EntryModule.BEDROCK_HOLLOW_ITEM.get().getDefaultInstance(),
                        MutableComponent.create(new TranslatableContents("advancement.dadobugores.hollow.title")),
                        MutableComponent.create(new TranslatableContents("advancement.dadobugores.hollow.description")),
                        null,
                        FrameType.TASK,
                        true, true, false
                ))
                .addCriterion("break", new Criterion(BreakRegenerativeBlockTrigger.TriggerInstance.breakRegenerativeBlock(EntryModule.EXTRACTION.get(), EntryModule.CORE_TAG)))
                .parent(new ResourceLocation(EntryModule.modid, "no_end_in_sight")));

        provider.addAdvancement(new ResourceLocation(EntryModule.modid, "regen_relocate"), Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(Registry.ITEM.get(new ResourceLocation(EntryModule.modid, "regenerative_nether_quartz"))),
                        MutableComponent.create(new TranslatableContents("advancement.dadobugores.regen_relocate.title")),
                        MutableComponent.create(new TranslatableContents("advancement.dadobugores.regen_relocate.description")),
                        null,
                        FrameType.GOAL,
                        true, true, false
                ))
                .addCriterion("break", new Criterion(new PlacedBlockTrigger.TriggerInstance(EntityPredicate.Composite.ANY, null, StatePropertiesPredicate.ANY, LocationPredicate.ANY, ItemPredicate.Builder.item().of(EntryModule.IS_CORE_TAG).build())))
                .parent(new ResourceLocation(EntryModule.modid, "hollow")));
    }


}

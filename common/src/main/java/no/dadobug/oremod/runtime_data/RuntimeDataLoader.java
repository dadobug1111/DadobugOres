package no.dadobug.oremod.runtime_data;

import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicTexturePack;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import no.dadobug.oremod.EntryModule;
import no.dadobug.oremod.json_configs.RegenerativeOreLootConfig;

import java.util.*;

public class RuntimeDataLoader {



    private static final Map<ResourceLocation, Set<LootPool.Builder>> lootMap = new HashMap<>();
    private static final Map<ResourceLocation, Set<ResourceLocation>> blockTagMap = new HashMap<>();
    private static final Map<ResourceLocation, Set<ResourceLocation>> itemTagMap = new HashMap<>();
    private static final List<Runnable> denseGenList = new ArrayList<>();
    //private static final List<ResourceLocation> standardTextureList = new ArrayList<>();

    private static final DynamicDataPack data = new DynamicDataPack(new ResourceLocation(EntryModule.modid, "dynamic_data"));
    //private static final DynamicTexturePack resource = new DynamicTexturePack(new ResourceLocation(EntryModule.modid, "dynamic_resources"));

    public static DynClientProvider provider = new DynClientProvider(new DynamicTexturePack(new ResourceLocation(EntryModule.modid, "dynamic_resources")));


    public static boolean addLootTable(ResourceLocation id, LootPool.Builder table){
        if(!lootMap.containsKey(id)){
            lootMap.put(id, new HashSet<>());
        }

        return lootMap.get(id).add(table);
    }
    public static boolean addBlockTag(ResourceLocation tag, ResourceLocation block){
        if(!blockTagMap.containsKey(tag)){
            blockTagMap.put(tag, new HashSet<>());
        }

        return blockTagMap.get(tag).add(block);
    }
    public static boolean addStandardTexture(ResourceLocation block){
        return provider.addStandardTexture(block);
    }

    public static boolean addBlockLocalization(ResourceLocation block, String name){
        return provider.addBlockLocalization(block, name);
    }

    public static boolean addItemLocalization(ResourceLocation item, String name){
        return provider.addItemLocalization(item, name);
    }

    public static boolean addModelItemTexture(ResourceLocation item, String model) {
        return provider.addModelItemTexture(item, model);
    }

    public static boolean addBlockTexture(ResourceLocation block, String model) {
        return provider.addBlockTexture(block, model);
    }

    public static boolean addImageItemTexture(ResourceLocation item, String texture) {
        return provider.addImageItemTexture(item, texture);
    }
    public static boolean addItemTag(ResourceLocation tag, ResourceLocation item) {
        if(!itemTagMap.containsKey(tag)){
            itemTagMap.put(tag, new HashSet<>());
        }

        return itemTagMap.get(tag).add(item);
    }
    public static boolean addDenseGen(Runnable runnable) {
        return denseGenList.add(runnable);
    }


    public static void init(){
        denseGenList.forEach(Runnable::run);
        ResourceLocation fractured = EntryModule.BEDROCK_FRACTURED.ore().getId();
        ResourceLocation hollow = EntryModule.BEDROCK_HOLLOW.getId();

        new RegenerativeOreLootConfig().setCoreId(EntryModule.BEDROCK_FRACTURED.core().getId().toString()).addLoot(EntryModule.registryBox, fractured.toString());

        lootMap.keySet().forEach((key) -> {
            LootTable.Builder Table = new LootTable.Builder();
            lootMap.get(key).forEach(Table::withPool);
            data.addJson(ResourceLocation.tryBuild(key.getNamespace(), key.getPath()) , LootTables.serialize(Table.build()), ResType.BLOCK_LOOT_TABLES);
        });


        addBlockTag(EntryModule.HOLLOW_TAG.location(), hollow);
        addBlockTag(BlockTags.WITHER_IMMUNE.location(), hollow);
        addBlockTag(BlockTags.DRAGON_IMMUNE.location(), hollow);

        addBlockTag(EntryModule.FRACTURE_TAG.location(), fractured);
        addBlockTag(EntryModule.CORE_TAG.location(), fractured);
        addBlockTag(BlockTags.MINEABLE_WITH_PICKAXE.location(), fractured);
        addBlockTag(BlockTags.WITHER_IMMUNE.location(), fractured);
        addBlockTag(BlockTags.DRAGON_IMMUNE.location(), fractured);
        addBlockTag(ResourceLocation.tryBuild("fabric", "needs_tool_level_4"), fractured);
        addBlockTag(ResourceLocation.tryBuild("forge", "needs_netherite_tool"), fractured);

        addItemTag(EntryModule.IS_CORE_TAG.location(), EntryModule.BEDROCK_FRACTURED.core().getId());


        blockTagMap.keySet().forEach((key) -> {
            SimpleTagBuilder builder = SimpleTagBuilder.of(key);
            for (ResourceLocation resourceLocation : blockTagMap.get(key)) {
                builder.add(resourceLocation);
            }
            data.addTag(builder, Registry.BLOCK_REGISTRY);
        });

        itemTagMap.keySet().forEach((key) -> {
            SimpleTagBuilder builder = SimpleTagBuilder.of(key);
            for (ResourceLocation resourceLocation : itemTagMap.get(key)) {
                builder.add(resourceLocation);
            }
            data.addTag(builder, Registry.ITEM_REGISTRY);
        });


        data.registerPack();


    }


}

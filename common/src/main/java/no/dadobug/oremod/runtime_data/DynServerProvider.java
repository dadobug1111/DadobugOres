package no.dadobug.oremod.runtime_data;

import dev.architectury.platform.Platform;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynServerResourcesProvider;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import no.dadobug.oremod.EntryModule;
import no.dadobug.oremod.json_configs.RegenerativeOreLootConfig;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DynServerProvider extends DynServerResourcesProvider {
    private final Map<ResourceLocation, Set<LootPool.Builder>> lootMap = new HashMap<>();
    private final Map<ResourceLocation, Set<ResourceLocation>> blockTagMap = new HashMap<>();
    private final Map<ResourceLocation, Set<ResourceLocation>> itemTagMap = new HashMap<>();

    protected DynServerProvider(DynamicDataPack pack) {
        super(pack);
        this.dynamicPack.generateDebugResources = Platform.isDevelopmentEnvironment();
    }

    @Override
    public Logger getLogger() {
        return EntryModule.LOGGER;
    }

    @Override
    public boolean dependsOnLoadedPacks() {
        return true;
    }

    @Override
    public void regenerateDynamicAssets(ResourceManager resourceManager) {

        ResourceLocation fractured = EntryModule.BEDROCK_FRACTURED.ore().getId();
        ResourceLocation hollow = EntryModule.BEDROCK_HOLLOW.getId();

        new RegenerativeOreLootConfig().setCoreId(EntryModule.BEDROCK_FRACTURED.core().getId().toString()).addLoot(EntryModule.registryBox, fractured.toString());

        lootMap.keySet().forEach((key) -> {
            LootTable.Builder Table = new LootTable.Builder();
            lootMap.get(key).forEach(Table::withPool);
            this.dynamicPack.addJson(ResourceLocation.tryBuild(key.getNamespace(), key.getPath()) , LootTables.serialize(Table.build()), ResType.BLOCK_LOOT_TABLES);
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
            this.dynamicPack.addTag(builder, Registry.BLOCK_REGISTRY);
        });

        itemTagMap.keySet().forEach((key) -> {
            SimpleTagBuilder builder = SimpleTagBuilder.of(key);
            for (ResourceLocation resourceLocation : itemTagMap.get(key)) {
                builder.add(resourceLocation);
            }
            this.dynamicPack.addTag(builder, Registry.ITEM_REGISTRY);
        });

    }

    public boolean addLootTable(ResourceLocation id, LootPool.Builder table){
        if(!lootMap.containsKey(id)){
            lootMap.put(id, new HashSet<>());
        }

        return lootMap.get(id).add(table);
    }
    public boolean addBlockTag(ResourceLocation tag, ResourceLocation block){
        if(!blockTagMap.containsKey(tag)){
            blockTagMap.put(tag, new HashSet<>());
        }

        return blockTagMap.get(tag).add(block);
    }
    public boolean addItemTag(ResourceLocation tag, ResourceLocation item) {
        if(!itemTagMap.containsKey(tag)){
            itemTagMap.put(tag, new HashSet<>());
        }

        return itemTagMap.get(tag).add(item);
    }
}

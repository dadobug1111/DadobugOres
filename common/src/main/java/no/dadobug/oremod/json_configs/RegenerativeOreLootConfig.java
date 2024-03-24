package no.dadobug.oremod.json_configs;

import dev.architectury.platform.Platform;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonArray;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonObject;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonPrimitive;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import no.dadobug.oremod.EntryModule;
import no.dadobug.oremod.RegistryBox;
import no.dadobug.oremod.configs.RegenerativeBlockDefaults;
import no.dadobug.oremod.runtime_data.RuntimeDataLoader;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class RegenerativeOreLootConfig extends DynamicLootConfig{

    public static String type() {
        return "regenerative_ore_loot";
    }


    private String coreId;

    private final Set<String> oreSet;

    private boolean silkable;

    public RegenerativeOreLootConfig() {

        this.silkable = EntryModule.DefaultRegenerativeBlockConfig.silkable;

        this.oreSet = new HashSet<>();
    }


    public RegenerativeOreLootConfig addOre(String oreAdded){
        this.oreSet.add(oreAdded);
        return this;
    }

    public RegenerativeOreLootConfig setSilkable(boolean silkable) {
        this.silkable = silkable;
        return this;
    }

    public RegenerativeOreLootConfig setCoreId(String coreId) {
        this.coreId = coreId;
        return this;
    }

    public String getCoreId() {
        return this.coreId;
    }

    public boolean isSilkable() {
        return this.silkable;
    }


    @Override
    public void addLoot(RegistryBox registry, String hostID) {

        EntryModule.lootFunctions.add((yes) -> {
            if(yes) {
                LootPool.Builder builder = new LootPool.Builder();
                notEnchantment(builder, EntryModule.CURSE_OF_SHATTERING.get());
                notEnchantment(builder, EntryModule.CURSE_OF_FRACTURING.get());
                ResourceLocation host = ResourceLocation.tryBuild(EntryModule.modid, hostID);

                LootTable.Builder tableBuilder = new LootTable.Builder();

                this.oreSet.forEach((ore) -> {
                    try {
                        String mod = Objects.requireNonNull(ResourceLocation.tryParse(ore)).getNamespace();
                        if (mod.startsWith("minecraft") || Platform.isModLoaded(mod)) {
                            ResourceLocation oreId = ResourceLocation.tryParse(ore);
                            assert oreId != null;
                            builder.add(LootTableReference.lootTableReference(Registry.BLOCK.get(oreId).getLootTable()));
                        }
                    } catch (NullPointerException e) {
                        EntryModule.LOGGER.error("Problem With Targeted LootTable: " + hostID + " cannot turn " + ore + " into an ResourceLocation! Should be mod_id:loot_table_id - OR - mod_id:table_group/loot_table_id");
                    }
                });

                if (this.silkable) {
                    notEnchantment(builder, Enchantments.SILK_TOUCH);

                    LootPool.Builder silkBuilder = new LootPool.Builder();
                    yesEnchantment(silkBuilder, Enchantments.SILK_TOUCH);
                    silkBuilder.add(LootItem.lootTableItem(Registry.ITEM.get(host)).apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY).copy("durability", "BlockEntityTag.durability", CopyNbtFunction.MergeStrategy.REPLACE)));

                    tableBuilder.withPool(silkBuilder);

                }

                if (this.coreId != null) {
                    notEnchantment(builder, EntryModule.EXTRACTION.get());

                    LootPool.Builder extractBuilder = new LootPool.Builder();
                    yesEnchantment(extractBuilder, EntryModule.EXTRACTION.get());
                    extractBuilder.add(LootItem.lootTableItem(Registry.ITEM.get(new ResourceLocation(EntryModule.modid, this.coreId))).apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY).copy("durability", "BlockEntityTag.durability", CopyNbtFunction.MergeStrategy.REPLACE)));

                    if (!this.silkable) {
                        notEnchantment(builder, EntryModule.ARCANE_EXTRACTION.get());

                        LootPool.Builder arcExtractBuilder = new LootPool.Builder();
                        yesEnchantment(arcExtractBuilder, EntryModule.ARCANE_EXTRACTION.get());
                        arcExtractBuilder.add(LootItem.lootTableItem(Registry.ITEM.get(host))).apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY).copy("durability", "BlockEntityTag.durability", CopyNbtFunction.MergeStrategy.REPLACE));

                        tableBuilder.withPool(arcExtractBuilder);
                    }

                    tableBuilder.withPool(extractBuilder);

                }
                tableBuilder.withPool(builder);
                RuntimeDataLoader.addLootTable(host, tableBuilder);
            }
        });

    }

    private static void notEnchantment(LootPool.Builder builder, Enchantment enchantment){
        builder.when(InvertedLootItemCondition.invert(MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(enchantment, MinMaxBounds.Ints.atLeast(1))))));
    }

    private static void yesEnchantment(LootPool.Builder builder, Enchantment enchantment){
        builder.when(MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(enchantment, MinMaxBounds.Ints.atLeast(1)))));
    }

    public static DynamicLootConfig fromJsonObject(JsonObject jsonObject){

        if(jsonObject.get("ore_set") instanceof JsonArray array){
            RegenerativeOreLootConfig config = new RegenerativeOreLootConfig();
            for(int i = 0; i < array.size(); i++) {
                config.addOre(array.getString(i,null));
            }

            config.setSilkable(jsonObject.getBoolean("silkable", EntryModule.DefaultRegenerativeBlockConfig.silkable));

            if(jsonObject.get("core_id") instanceof JsonPrimitive primitive){
                config.setCoreId(primitive.asString());
            }

            return config;
        }

        return null;
    }



    ;
    @Override
    public JsonObject toJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("type", new JsonPrimitive(type()));

        RegenerativeBlockDefaults defaultstuff = EntryModule.DefaultRegenerativeBlockConfig;

        if(this.silkable != defaultstuff.silkable)jsonObject.put("silkable", new JsonPrimitive(this.silkable));
        if(this.coreId != null)jsonObject.put("core_id", new JsonPrimitive(this.coreId));

        JsonArray array = new JsonArray();
        this.oreSet.forEach((o) -> array.add(new JsonPrimitive(o)));
        jsonObject.put("ore_set", array);

        return jsonObject;
    }
}

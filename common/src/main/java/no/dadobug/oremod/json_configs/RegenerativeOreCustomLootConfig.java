package no.dadobug.oremod.json_configs;

import com.mojang.datafixers.util.Pair;
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
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import no.dadobug.oremod.EntryModule;
import no.dadobug.oremod.RegistryBox;
import no.dadobug.oremod.configs.RegenerativeBlockDefaults;
import no.dadobug.oremod.runtime_data.RuntimeDataLoader;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class RegenerativeOreCustomLootConfig extends DynamicLootConfig{

    public static String type() {
        return "regenerative_ore_custom_loot";
    }


    private String coreId;

    private final Set<Pair<String, int[]>> itemSet;

    private boolean silkable;

    public RegenerativeOreCustomLootConfig() {

        this.silkable = EntryModule.DefaultRegenerativeBlockConfig.silkable;

        this.itemSet = new HashSet<>();
    }


    public RegenerativeOreCustomLootConfig addItemWithRange(String itemAdded, int minCount, int maxCount){
        this.itemSet.add(new Pair<>(itemAdded, new int[]{minCount, maxCount}));
        return this;
    }


    public RegenerativeOreCustomLootConfig addItemWithCount(String itemAdded, int count){
        this.itemSet.add(new Pair<>(itemAdded, new int[]{count}));
        return this;
    }

    public RegenerativeOreCustomLootConfig setSilkable(boolean silkable) {
        this.silkable = silkable;
        return this;
    }

    public RegenerativeOreCustomLootConfig setCoreId(String coreId) {
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

                this.itemSet.forEach((itemPair) -> {
                    try {
                        String mod = Objects.requireNonNull(ResourceLocation.tryParse(itemPair.getFirst())).getNamespace();
                        if (mod.startsWith("minecraft") || Platform.isModLoaded(mod)) {
                            ResourceLocation oreId = ResourceLocation.tryParse(itemPair.getFirst());
                            int[] ints = itemPair.getSecond();
                            if(ints.length ==1) {
                                builder.add(LootItem.lootTableItem(Registry.ITEM.get(oreId)).apply(SetItemCountFunction.setCount(ConstantValue.exactly(ints[0]))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)));
                            } else {
                                builder.add(LootItem.lootTableItem(Registry.ITEM.get(oreId)).apply(SetItemCountFunction.setCount(UniformGenerator.between(ints[0], ints[1]))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)));

                            }
                        }
                    } catch (NullPointerException ignored) {}
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

        if(jsonObject.get("item_set") instanceof JsonArray array){
            RegenerativeOreCustomLootConfig config = new RegenerativeOreCustomLootConfig();
            for (me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonElement jsonElement : array) {
                if(jsonElement instanceof JsonObject object){
                    if(object.containsKey("count")){
                        if(object.get("item") instanceof JsonPrimitive primitive){
                            config.addItemWithCount(primitive.asString(), object.getInt("count", 1));
                        }
                    } else {
                        if(object.get("item") instanceof JsonPrimitive primitive){
                            config.addItemWithRange(primitive.asString(), object.getInt("minCount", 1), object.getInt("maxCount", 1));
                        }

                    }
                }
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
        this.itemSet.forEach((o) -> {
            JsonObject object = new JsonObject();
            object.put("item", new JsonPrimitive(o.getFirst()));
            int[] ints = o.getSecond();
            if(ints.length == 1) {
                object.put("count", new JsonPrimitive(ints[0]));
            } else {
                object.put("minCount", new JsonPrimitive(ints[0]));
                object.put("maxCount", new JsonPrimitive(ints[1]));
            }

            array.add(object);

        });
        jsonObject.put("item_set", array);

        return jsonObject;
    }
}

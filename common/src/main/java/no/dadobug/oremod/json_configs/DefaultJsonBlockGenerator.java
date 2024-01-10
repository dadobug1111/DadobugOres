package no.dadobug.oremod.json_configs;

import com.mojang.datafixers.util.Pair;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonObject;
import net.minecraft.core.Registry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import no.dadobug.oremod.EntryModule;

import java.util.ArrayList;
import java.util.List;

public class DefaultJsonBlockGenerator {

    public static void addDefaults(){
        List<Pair<String, JsonObject>> BlockList = new ArrayList<>();
        BlockList.add(new Pair<>("bedrock_coal_ore", new RegenerativeBlockConfig("bedrock_coal_ore").setReplaceBlockId(Blocks.BEDROCK).setXPmax(2).setEnglishName("Bedrock Coal Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_coal").setEnglishName("Regenerative Coal Cluster").setModelFile("minecraft:item/coal"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_coal").addOre(Registry.BLOCK.getKey(Blocks.COAL_ORE).toString()).addOre(Registry.BLOCK.getKey(Blocks.DEEPSLATE_COAL_ORE).toString()).addOre("undergarden:depthrock_coal_ore").addOre("undergarden:shiverstone_coal_ore").addOre("beyond_earth:venus_coal_ore").addOre("beyond_earth:glacio_coal_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_iron_ore", new RegenerativeBlockConfig("bedrock_iron_ore").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Iron Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_iron").setEnglishName("Regenerative Iron Cluster").setModelFile("minecraft:item/raw_iron"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_iron").addOre(Registry.BLOCK.getKey(Blocks.IRON_ORE).toString()).addOre(Registry.BLOCK.getKey(Blocks.DEEPSLATE_IRON_ORE).toString()).addOre("undergarden:depthrock_iron_ore").addOre("undergarden:shiverstone_iron_ore").addOre("beyond_earth:glacio_iron_ore").addOre("beyond_earth:moon_iron_ore").addOre("beyond_earth:mars_iron_ore").addOre("beyond_earth:mercury_iron_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_gold_ore", new RegenerativeBlockConfig("bedrock_gold_ore").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Gold Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_gold").setEnglishName("Regenerative Gold Cluster").setModelFile("minecraft:item/raw_gold"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_gold").addOre(Registry.BLOCK.getKey(Blocks.GOLD_ORE).toString()).addOre(Registry.BLOCK.getKey(Blocks.DEEPSLATE_GOLD_ORE).toString()).addOre("undergarden:depthrock_gold_ore").addOre("beyond_earth:venus_gold_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_diamond_ore", new RegenerativeBlockConfig("bedrock_diamond_ore").setReplaceBlockId(Blocks.BEDROCK).setXPmin(3).setXPmax(7).setEnglishName("Bedrock Diamond Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_diamond").setEnglishName("Regenerative Diamond Cluster").setModelFile("minecraft:item/diamond"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_diamond").addOre(Registry.BLOCK.getKey(Blocks.DIAMOND_ORE).toString()).addOre(Registry.BLOCK.getKey(Blocks.DEEPSLATE_DIAMOND_ORE).toString()).addOre("undergarden:depthrock_diamond_ore").addOre("undergarden:shiverstone_diamond_ore").addOre("beyond_earth:mars_diamond_ore").addOre("beyond_earth:venus_diamond_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_redstone_ore", new RegenerativeBlockConfig("bedrock_redstone_ore").setReplaceBlockId(Blocks.BEDROCK).setXPmin(1).setXPmax(6).setEnglishName("Bedrock Redstone Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_redstone").setEnglishName("Regenerative Redstone Cluster").setModelFile("minecraft:item/redstone"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_redstone").addOre(Registry.BLOCK.getKey(Blocks.REDSTONE_ORE).toString()).addOre(Registry.BLOCK.getKey(Blocks.DEEPSLATE_REDSTONE_ORE).toString())).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_copper_ore", new RegenerativeBlockConfig("bedrock_copper_ore").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Copper Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_copper").setEnglishName("Regenerative Copper Cluster").setModelFile("minecraft:item/raw_copper"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_copper").addOre(Registry.BLOCK.getKey(Blocks.COPPER_ORE).toString()).addOre(Registry.BLOCK.getKey(Blocks.DEEPSLATE_COPPER_ORE).toString()).addOre("beyond_earth:glacio_copper_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_lapis_ore", new RegenerativeBlockConfig("bedrock_lapis_ore").setReplaceBlockId(Blocks.BEDROCK).setXPmin(2).setXPmax(5).setEnglishName("Bedrock Lapis Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_lapis").setEnglishName("Regenerative Lapis Cluster").setModelFile("minecraft:item/lapis_lazuli"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_lapis").addOre(Registry.BLOCK.getKey(Blocks.LAPIS_ORE).toString()).addOre(Registry.BLOCK.getKey(Blocks.DEEPSLATE_LAPIS_ORE).toString()).addOre("beyond_earth:glacio_lapis_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_emerald_ore", new RegenerativeBlockConfig("bedrock_emerald_ore").setReplaceBlockId(Blocks.BEDROCK).setXPmin(3).setXPmax(7).setEnglishName("Bedrock Emerald Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_emerald").setEnglishName("Regenerative Emerald Cluster").setModelFile("minecraft:item/emerald"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_emerald").addOre(Registry.BLOCK.getKey(Blocks.EMERALD_ORE).toString()).addOre(Registry.BLOCK.getKey(Blocks.DEEPSLATE_EMERALD_ORE).toString())).toJsonObject()));



        BlockList.add(new Pair<>("bedrock_cookie_ore", new RegenerativeBlockConfig("bedrock_cookie_ore").setReplaceBlockId(Blocks.BEDROCK).setXPmin(1).setXPmax(9).setEnglishName("Bedrock Cookie Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_cookie").setEnglishName("Regenerative Cookie Cluster").setModelFile("minecraft:item/cookie").setTooltipColor("GOLD").setTooltipLocalization("item.dadobugores.regen_joke_item_one.tooltip"))
                .setLootConfig(new RegenerativeOreCustomLootConfig().setCoreId("regenerative_cookie").addItemWithRange("minecraft:cookie", 1, 4)).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_milk_ore", new RegenerativeBlockConfig("bedrock_milk_ore").setReplaceBlockId(Blocks.BEDROCK).setXPmin(1).setXPmax(9).setEnglishName("Milk Seep")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_milk").setEnglishName("Regenerative Milk Shake").setModelFile("minecraft:item/milk_bucket").setTooltipColor("GOLD").setTooltipLocalization("item.dadobugores.regen_joke_fluid_one.tooltip"))
                .setBucketItem(Registry.ITEM.getKey(Items.MILK_BUCKET).toString()).setBucketSound(Registry.SOUND_EVENT.getKey(SoundEvents.COW_MILK).toString())
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_milk")).toJsonObject()));



        BlockList.add(new Pair<>("bedrock_water_ore", new RegenerativeBlockConfig("bedrock_water_ore").setReplaceBlockId(Blocks.BEDROCK).setXPmin(1).setXPmax(5).setEnglishName("Water Seep").setStandardTexture(false)
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_water").setEnglishName("Regenerative Water Shale").setModelFile("minecraft:item/water_bucket").setTooltipColor("GREEN").setTooltipLocalization("item.dadobugores.regen_fluid.tooltip"))
                .setFluid(Registry.FLUID.getKey(Fluids.WATER).toString())
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_water")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_lava_ore", new RegenerativeBlockConfig("bedrock_lava_ore").setReplaceBlockId(Blocks.BEDROCK).setXPmin(1).setXPmax(6).setEnglishName("Lava Seep")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_lava").setEnglishName("Regenerative Lava Shale").setModelFile("minecraft:item/lava_bucket").setTooltipColor("GREEN").setTooltipLocalization("item.dadobugores.regen_fluid.tooltip"))
                .setFluid(Registry.FLUID.getKey(Fluids.LAVA).toString())
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_lava")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_xp_leak", new RegenerativeBlockConfig("bedrock_xp_leak").setReplaceBlockId(Blocks.BEDROCK).setXPmin(25).setXPmax(60).setEnglishName("XP Leak")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_xp_leak").setEnglishName("Leaky XP Conduit").setTextureFile("dadobugores:item/regenerative_xp_leak").setTooltipColor("AQUA").setTooltipLocalization("item.dadobugores.regen_xp.tooltip"))
                .setBottleItem(Registry.ITEM.getKey(Items.EXPERIENCE_BOTTLE).toString())
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_xp_leak")).toJsonObject()));



        BlockList.add(new Pair<>("bedrock_nether_gold_ore", new RegenerativeBlockConfig("bedrock_nether_gold_ore").setReplaceBlockId(Blocks.BEDROCK).setXPmax(1).setEnglishName("Bedrock Nether Gold Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_nether_gold").setEnglishName("Regenerative Nether Gold Cluster").setModelFile("minecraft:item/gold_nugget"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_nether_gold").addOre(Registry.BLOCK.getKey(Blocks.NETHER_GOLD_ORE).toString())).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_nether_quartz_ore", new RegenerativeBlockConfig("bedrock_nether_quartz_ore").setReplaceBlockId(Blocks.BEDROCK).setXPmin(2).setXPmax(5).setEnglishName("Bedrock Nether Quartz Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_nether_quartz").setEnglishName("Regenerative Nether Quartz Cluster").setModelFile("minecraft:item/quartz"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_nether_quartz").addOre(Registry.BLOCK.getKey(Blocks.NETHER_QUARTZ_ORE).toString())).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_ancient_debris_ore", new RegenerativeBlockConfig("bedrock_ancient_debris_ore").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Ancient Debris Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_ancient_debris").setEnglishName("Regenerative Ancient Debris Cluster").setModelFile("minecraft:item/netherite_scrap"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_ancient_debris").addOre(Registry.BLOCK.getKey(Blocks.ANCIENT_DEBRIS).toString())).toJsonObject()));




        BlockList.add(new Pair<>("bedrock_tr_bauxite_ore", new RegenerativeBlockConfig("bedrock_tr_bauxite_ore").addRequiredMod("techreborn").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Bauxite Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_tr_bauxite").setEnglishName("Regenerative Bauxite Cluster").setTextureFile("techreborn:item/dust/aluminum_dust"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_tr_bauxite").addOre("techreborn:bauxite_ore").addOre("techreborn:deepslate_bauxite_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_tr_galena_ore", new RegenerativeBlockConfig("bedrock_tr_galena_ore").addRequiredMod("techreborn").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Galena Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_tr_galena").setEnglishName("Regenerative Galena Cluster").setTextureFile("techreborn:item/dust/galena_dust"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_tr_galena").addOre("techreborn:galena_ore").addOre("techreborn:deepslate_galena_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_tr_iridium_ore", new RegenerativeBlockConfig("bedrock_tr_iridium_ore").addRequiredMod("techreborn").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Iridium Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_tr_iridium").setEnglishName("Regenerative Iridium Cluster").setTextureFile("techreborn:item/rawmetal/raw_iridium"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_tr_iridium").addOre("techreborn:iridium_ore").addOre("techreborn:deepslate_iridium_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_tr_lead_ore", new RegenerativeBlockConfig("bedrock_tr_lead_ore").addRequiredMod("techreborn").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Lead Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_tr_lead").setEnglishName("Regenerative Lead Cluster").setTextureFile("techreborn:item/rawmetal/raw_lead"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_tr_lead").addOre("techreborn:lead_ore").addOre("techreborn:deepslate_lead_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_tr_ruby_ore", new RegenerativeBlockConfig("bedrock_tr_ruby_ore").addRequiredMod("techreborn").setReplaceBlockId(Blocks.BEDROCK).setXPmin(2).setXPmax(6).setEnglishName("Bedrock Ruby Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_tr_ruby").setEnglishName("Regenerative Ruby Cluster").setTextureFile("techreborn:item/gem/ruby_gem"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_tr_ruby").addOre("techreborn:ruby_ore").addOre("techreborn:deepslate_ruby_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_tr_sapphire_ore", new RegenerativeBlockConfig("bedrock_tr_sapphire_ore").addRequiredMod("techreborn").setReplaceBlockId(Blocks.BEDROCK).setXPmin(2).setXPmax(6).setEnglishName("Bedrock Sapphire Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_tr_sapphire").setEnglishName("Regenerative Sapphire Cluster").setTextureFile("techreborn:item/gem/sapphire_gem"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_tr_sapphire").addOre("techreborn:sapphire_ore").addOre("techreborn:deepslate_sapphire_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_tr_silver_ore", new RegenerativeBlockConfig("bedrock_tr_silver_ore").addRequiredMod("techreborn").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Silver Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_tr_silver").setEnglishName("Regenerative Silver Cluster").setTextureFile("techreborn:item/rawmetal/raw_silver"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_tr_silver").addOre("techreborn:silver_ore").addOre("techreborn:deepslate_silver_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_tr_tin_ore", new RegenerativeBlockConfig("bedrock_tr_tin_ore").addRequiredMod("techreborn").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Tin Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_tr_tin").setEnglishName("Regenerative Tin Cluster").setTextureFile("techreborn:item/rawmetal/raw_tin"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_tr_tin").addOre("techreborn:tin_ore").addOre("techreborn:deepslate_tin_ore")).toJsonObject()));



        BlockList.add(new Pair<>("bedrock_tr_cinnabar_ore", new RegenerativeBlockConfig("bedrock_tr_cinnabar_ore").addRequiredMod("techreborn").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Cinnabar Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_tr_cinnabar").setEnglishName("Regenerative Cinnabar Cluster").setTextureFile("techreborn:item/dust/cinnabar_dust"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_tr_cinnabar").addOre("techreborn:cinnabar_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_tr_pyrite_ore", new RegenerativeBlockConfig("bedrock_tr_pyrite_ore").addRequiredMod("techreborn").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Pyrite Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_tr_pyrite").setEnglishName("Regenerative Pyrite Cluster").setTextureFile("techreborn:item/dust/pyrite_dust"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_tr_pyrite").addOre("techreborn:pyrite_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_tr_sphalerite_ore", new RegenerativeBlockConfig("bedrock_tr_sphalerite_ore").addRequiredMod("techreborn").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Sphalerite Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_tr_sphalerite").setEnglishName("Regenerative Sphalerite Cluster").setTextureFile("techreborn:item/dust/sphalerite_dust"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_tr_sphalerite").addOre("techreborn:sphalerite_ore")).toJsonObject()));



        BlockList.add(new Pair<>("bedrock_tr_peridot_ore", new RegenerativeBlockConfig("bedrock_tr_peridot_ore").addRequiredMod("techreborn").setReplaceBlockId(Blocks.BEDROCK).setXPmin(2).setXPmax(6).setEnglishName("Bedrock Peridot Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_tr_peridot").setEnglishName("Regenerative Peridot Cluster").setTextureFile("techreborn:item/gem/peridot_gem"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_tr_peridot").addOre("techreborn:peridot_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_tr_sheldonite_ore", new RegenerativeBlockConfig("bedrock_tr_sheldonite_ore").addRequiredMod("techreborn").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Sheldonite Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_tr_sheldonite").setEnglishName("Regenerative Sheldonite Cluster").setTextureFile("techreborn:item/dust/nickel_dust"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_tr_sheldonite").addOre("techreborn:sheldonite_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_tr_sodalite_ore", new RegenerativeBlockConfig("bedrock_tr_sodalite_ore").addRequiredMod("techreborn").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Sodalite Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_tr_sodalite").setEnglishName("Regenerative Sodalite Cluster").setTextureFile("techreborn:item/dust/sodalite_dust"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_tr_sodalite").addOre("techreborn:sodalite_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_tr_tungsten_ore", new RegenerativeBlockConfig("bedrock_tr_tungsten_ore").addRequiredMod("techreborn").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Tungsten Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_tr_tungsten").setEnglishName("Regenerative Tungsten Cluster").setTextureFile("techreborn:item/rawmetal/raw_tungsten"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_tr_tungsten").addOre("techreborn:tungsten_ore")).toJsonObject()));




        BlockList.add(new Pair<>("bedrock_tc_cobalt_ore", new RegenerativeBlockConfig("bedrock_tc_cobalt_ore").addRequiredMod("tconstruct").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Cobalt Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_tc_cobalt").setEnglishName("Regenerative Cobalt Cluster").setTextureFile("tconstruct:item/materials/raw_cobalt"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_tc_cobalt").addOre("tconstruct:cobalt_ore")).toJsonObject()));




        BlockList.add(new Pair<>("bedrock_er_yellorite_ore", new RegenerativeBlockConfig("bedrock_er_yellorite_ore").addRequiredMod("bigreactors").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Yellorite Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_er_yellorite").setEnglishName("Regenerative Yellorite Cluster").setTextureFile("bigreactors:item/yellorium_ingot"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_er_yellorite").addOre("bigreactors:yellorite_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_er_anglesite_ore", new RegenerativeBlockConfig("bedrock_er_anglesite_ore").addRequiredMod("bigreactors").setReplaceBlockId(Blocks.BEDROCK).setXPmin(2).setXPmax(5).setEnglishName("Bedrock Anglesite Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_er_anglesite").setEnglishName("Regenerative Anglesite Cluster").setTextureFile("bigreactors:item/anglesite_crystal"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_er_anglesite").addOre("bigreactors:anglesite_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_er_benitoite_ore", new RegenerativeBlockConfig("bedrock_er_benitoite_ore").addRequiredMod("bigreactors").setReplaceBlockId(Blocks.BEDROCK).setXPmin(2).setXPmax(5).setEnglishName("Bedrock Benitoite Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_er_benitoite").setEnglishName("Regenerative Benitoite Cluster").setTextureFile("bigreactors:item/benitoite_crystal"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_er_benitoite").addOre("bigreactors:benitoite_ore")).toJsonObject()));




        BlockList.add(new Pair<>("bedrock_br_uranium_ore", new RegenerativeBlockConfig("bedrock_br_uranium_ore").addRequiredMod("biggerreactors").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Uranium Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_br_uranium").setEnglishName("Regenerative Uranium Cluster").setTextureFile("biggerreactors:item/uranium_ingot"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_br_uranium").addOre("biggerreactors:uranium_ore")).toJsonObject()));




        BlockList.add(new Pair<>("bedrock_ie_aluminum_ore", new RegenerativeBlockConfig("bedrock_ie_aluminum_ore").addRequiredMod("immersiveengineering").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Aluminum Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_ie_aluminum").setEnglishName("Regenerative Aluminum Cluster").setTextureFile("immersiveengineering:item/metal_raw_aluminum"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_ie_aluminum").addOre("immersiveengineering:ore_aluminum").addOre("immersiveengineering:deepslate_ore_aluminum")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_ie_lead_ore", new RegenerativeBlockConfig("bedrock_ie_lead_ore").addRequiredMod("immersiveengineering").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Lead Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_ie_lead").setEnglishName("Regenerative Lead Cluster").setTextureFile("immersiveengineering:item/metal_raw_lead"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_ie_lead").addOre("immersiveengineering:ore_lead").addOre("immersiveengineering:deepslate_ore_lead")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_ie_silver_ore", new RegenerativeBlockConfig("bedrock_ie_silver_ore").addRequiredMod("immersiveengineering").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Silver Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_ie_silver").setEnglishName("Regenerative Silver Cluster").setTextureFile("immersiveengineering:item/metal_raw_silver"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_ie_silver").addOre("immersiveengineering:ore_silver").addOre("immersiveengineering:deepslate_ore_silver")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_ie_nickel_ore", new RegenerativeBlockConfig("bedrock_ie_nickel_ore").addRequiredMod("immersiveengineering").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Nickel Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_ie_nickel").setEnglishName("Regenerative Nickel Cluster").setTextureFile("immersiveengineering:item/metal_raw_nickel"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_ie_nickel").addOre("immersiveengineering:ore_nickel").addOre("immersiveengineering:deepslate_ore_nickel")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_ie_uranium_ore", new RegenerativeBlockConfig("bedrock_ie_uranium_ore").addRequiredMod("immersiveengineering").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Uranium Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_ie_uranium").setEnglishName("Regenerative Uranium Cluster").setTextureFile("immersiveengineering:item/metal_raw_uranium"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_ie_uranium").addOre("immersiveengineering:ore_uranium").addOre("immersiveengineering:deepslate_ore_uranium")).toJsonObject()));




        BlockList.add(new Pair<>("bedrock_mm_adamantite_ore", new RegenerativeBlockConfig("bedrock_mm_adamantite_ore").addRequiredMod("mythicmetals").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Adamantite Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_mm_adamantite").setEnglishName("Regenerative Adamantite Cluster").setTextureFile("mythicmetals:item/raw_ore/raw_adamantite"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_mm_adamantite").addOre("mythicmetals:adamantite_ore").addOre("mythicmetals:deepslate_adamantite_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_mm_aquarium_ore", new RegenerativeBlockConfig("bedrock_mm_aquarium_ore").addRequiredMod("mythicmetals").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Aquarium Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_mm_aquarium").setEnglishName("Regenerative Aquarium Cluster").setTextureFile("mythicmetals:item/raw_ore/raw_aquarium"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_mm_aquarium").addOre("mythicmetals:aquarium_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_mm_banglum_ore", new RegenerativeBlockConfig("bedrock_mm_banglum_ore").addRequiredMod("mythicmetals").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Banglum Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_mm_banglum").setEnglishName("Regenerative Banglum Cluster").setTextureFile("mythicmetals:item/raw_ore/raw_banglum"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_mm_banglum").addOre("mythicmetals:banglum_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_mm_carmot_ore", new RegenerativeBlockConfig("bedrock_mm_carmot_ore").addRequiredMod("mythicmetals").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Carmot Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_mm_carmot").setEnglishName("Regenerative Carmot Cluster").setTextureFile("mythicmetals:item/raw_ore/raw_carmot"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_mm_carmot").addOre("mythicmetals:carmot_ore").addOre("mythicmetals:deepslate_carmot_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_mm_kyber_ore", new RegenerativeBlockConfig("bedrock_mm_kyber_ore").addRequiredMod("mythicmetals").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Kyber Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_mm_kyber").setEnglishName("Regenerative Kyber Cluster").setTextureFile("mythicmetals:item/raw_ore/raw_kyber"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_mm_kyber").addOre("mythicmetals:kyber_ore").addOre("mythicmetals:calcite_kyber_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_mm_manganese_ore", new RegenerativeBlockConfig("bedrock_mm_manganese").addRequiredMod("mythicmetals").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Manganese Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_mm_manganese").setEnglishName("Regenerative Manganese Cluster").setTextureFile("mythicmetals:item/raw_ore/raw_manganese"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_mm_manganese").addOre("mythicmetals:manganese_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_mm_morkite_ore", new RegenerativeBlockConfig("bedrock_mm_morkite_ore").addRequiredMod("mythicmetals").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Morkite Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_mm_morkite").setEnglishName("Regenerative Morkite Cluster").setTextureFile("mythicmetals:item/morkite"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_mm_morkite").addOre("mythicmetals:morkite_ore").addOre("mythicmetals:deepslate_morkite_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_mm_mythril_ore", new RegenerativeBlockConfig("bedrock_mm_mythril_ore").addRequiredMod("mythicmetals").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Mythril Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_mm_mythril").setEnglishName("Regenerative Mythril Cluster").setTextureFile("mythicmetals:item/raw_ore/raw_mythril"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_mm_mythril").addOre("mythicmetals:mythril_ore").addOre("mythicmetals:deepslate_mythril_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_mm_orichalcum_ore", new RegenerativeBlockConfig("bedrock_mm_orichalcum_ore").addRequiredMod("mythicmetals").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Orichalcum Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_mm_orichalcum").setEnglishName("Regenerative Orichalcum Cluster").setTextureFile("mythicmetals:item/raw_ore/raw_orichalcum"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_mm_orichalcum").addOre("mythicmetals:orichalcum_ore").addOre("mythicmetals:deepslate_orichalcum_ore").addOre("mythicmetals:tuff_orichalcum_ore").addOre("mythicmetals:smooth_basalt_orichalcum_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_mm_osmium_ore", new RegenerativeBlockConfig("bedrock_mm_osmium_ore").addRequiredMod("mythicmetals").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Osmium Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_mm_osmium").setEnglishName("Regenerative Osmium Cluster").setTextureFile("mythicmetals:item/raw_ore/raw_osmium"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_mm_osmium").addOre("mythicmetals:osmium_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_mm_platinum_ore", new RegenerativeBlockConfig("bedrock_mm_platinum_ore").addRequiredMod("mythicmetals").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Platinum Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_mm_platinum").setEnglishName("Regenerative Platinum Cluster").setTextureFile("mythicmetals:item/raw_ore/raw_platinum"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_mm_platinum").addOre("mythicmetals:platinum_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_mm_prometheum_ore", new RegenerativeBlockConfig("bedrock_mm_prometheum_ore").addRequiredMod("mythicmetals").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Prometheum Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_mm_prometheum").setEnglishName("Regenerative Prometheum Cluster").setTextureFile("mythicmetals:item/raw_ore/raw_prometheum"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_mm_prometheum").addOre("mythicmetals:prometheum_ore").addOre("mythicmetals:deepslate_prometheum_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_mm_quadrillum_ore", new RegenerativeBlockConfig("bedrock_mm_quadrillum_ore").addRequiredMod("mythicmetals").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Quadrillum Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_mm_quadrillum").setEnglishName("Regenerative Quadrillum Cluster").setTextureFile("mythicmetals:item/raw_ore/raw_quadrillum"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_mm_quadrillum").addOre("mythicmetals:quadrillum_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_mm_runite_ore", new RegenerativeBlockConfig("bedrock_mm_runite_ore").addRequiredMod("mythicmetals").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Runite Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_mm_runite").setEnglishName("Regenerative Runite Cluster").setTextureFile("mythicmetals:item/raw_ore/raw_runite"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_mm_runite").addOre("mythicmetals:runite_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_mm_silver_ore", new RegenerativeBlockConfig("bedrock_mm_silver_ore").addRequiredMod("mythicmetals").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Silver Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_mm_silver").setEnglishName("Regenerative Silver Cluster").setTextureFile("mythicmetals:item/raw_ore/raw_silver"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_mm_silver").addOre("mythicmetals:silver_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_mm_starrite_ore", new RegenerativeBlockConfig("bedrock_mm_starrite_ore").addRequiredMod("mythicmetals").setReplaceBlockId(Blocks.BEDROCK).setXPmin(3).setXPmax(6).setEnglishName("Bedrock Starrite Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_mm_starrite").setEnglishName("Regenerative Starrite Cluster").setTextureFile("mythicmetals:item/starrite"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_mm_starrite").addOre("mythicmetals:starrite_ore").addOre("mythicmetals:calcite_starrite_ore").addOre("mythicmetals:end_stone_starrite_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_mm_tin_ore", new RegenerativeBlockConfig("bedrock_mm_tin_ore").addRequiredMod("mythicmetals").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Tin Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_mm_tin").setEnglishName("Regenerative Tin Cluster").setTextureFile("mythicmetals:item/raw_ore/raw_tin"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_mm_tin").addOre("mythicmetals:tin_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_mm_unobtainium_ore", new RegenerativeBlockConfig("bedrock_mm_unobtainium_ore").addRequiredMod("mythicmetals").setReplaceBlockId(Blocks.BEDROCK).setXPmin(4).setXPmax(7).setLuminance(1).setEnglishName("Bedrock Unobtainium Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_mm_unobtainium").setEnglishName("Regenerative Unobtainium Cluster").setTextureFile("mythicmetals:item/unobtainium"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_mm_unobtainium").addOre("mythicmetals:unobtainium_ore").addOre("mythicmetals:deepslate_unobtainium_ore")).toJsonObject()));



        BlockList.add(new Pair<>("bedrock_mm_nether_banglum_ore", new RegenerativeBlockConfig("bedrock_mm_nether_banglum_ore").addRequiredMod("mythicmetals").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Nether Banglum Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_mm_nether_banglum").setEnglishName("Regenerative Nether Banglum Cluster").setTextureFile("mythicmetals:item/banglum_chunk"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_mm_nether_banglum").addOre("mythicmetals:nether_banglum_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_mm_midas_gold_ore", new RegenerativeBlockConfig("bedrock_mm_midas_gold_ore").addRequiredMod("mythicmetals").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Midas Gold Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_mm_midas_gold").setEnglishName("Regenerative Midas Gold Cluster").setTextureFile("mythicmetals:item/raw_ore/raw_midas_gold"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_mm_midas_gold").addOre("mythicmetals:midas_gold_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_mm_palladium_ore", new RegenerativeBlockConfig("bedrock_mm_palladium_ore").addRequiredMod("mythicmetals").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Palladium Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_mm_palladium").setEnglishName("Regenerative Palladium Cluster").setTextureFile("mythicmetals:item/raw_ore/raw_palladium"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_mm_palladium").addOre("mythicmetals:palladium_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_mm_stormyx_ore", new RegenerativeBlockConfig("bedrock_mm_stormyx_ore").addRequiredMod("mythicmetals").setReplaceBlockId(Blocks.BEDROCK).setXPmin(2).setXPmax(4).setEnglishName("Bedrock Stormyx Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_mm_stormyx").setEnglishName("Regenerative Stormyx Cluster").setTextureFile("mythicmetals:item/raw_ore/raw_stormyx"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_mm_stormyx").addOre("mythicmetals:stormyx_ore").addOre("mythicmetals:blackstone_stormyx_ore")).toJsonObject()));




        BlockList.add(new Pair<>("bedrock_cr_zinc_ore", new RegenerativeBlockConfig("bedrock_cr_zinc_ore").addRequiredMod("create").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Zinc Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_cr_zinc").setEnglishName("Regenerative Zinc Cluster").setTextureFile("create:item/raw_zinc"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_cr_zinc").addOre("create:zinc_ore").addOre("create:deepslate_zinc_ore")).toJsonObject()));




        BlockList.add(new Pair<>("bedrock_mk_osmium_ore", new RegenerativeBlockConfig("bedrock_mk_osmium_ore").addRequiredMod("mekanism").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Osmium Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_mk_osmium").setEnglishName("Regenerative Osmium Cluster").setTextureFile("mekanism:item/raw_osmium"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_mk_osmium").addOre("mekanism:osmium_ore").addOre("mekanism:deepslate_osmium_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_mk_fluorite_ore", new RegenerativeBlockConfig("bedrock_mk_fluorite_ore").addRequiredMod("mekanism").setReplaceBlockId(Blocks.BEDROCK).setXPmin(1).setXPmax(4).setEnglishName("Bedrock Fluorite Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_mk_fluorite").setEnglishName("Regenerative Fluorite Cluster").setTextureFile("mekanism:item/fluorite_gem"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_mk_fluorite").addOre("mekanism:fluorite_ore").addOre("mekanism:deepslate_fluorite_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_mk_lead_ore", new RegenerativeBlockConfig("bedrock_mk_lead_ore").addRequiredMod("mekanism").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Lead Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_mk_lead").setEnglishName("Regenerative Lead Cluster").setTextureFile("mekanism:item/raw_lead"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_mk_lead").addOre("mekanism:lead_ore").addOre("mekanism:deepslate_lead_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_mk_tin_ore", new RegenerativeBlockConfig("bedrock_mk_tin_ore").addRequiredMod("mekanism").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Tin Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_mk_tin").setEnglishName("Regenerative Tin Cluster").setTextureFile("mekanism:item/raw_tin"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_mk_tin").addOre("mekanism:tin_ore").addOre("mekanism:deepslate_tin_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_mk_uranium_ore", new RegenerativeBlockConfig("bedrock_mk_uranium_ore").addRequiredMod("mekanism").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Uranium Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_mk_uranium").setEnglishName("Regenerative Uranium Cluster").setTextureFile("mekanism:item/raw_uranium"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_mk_uranium").addOre("mekanism:uranium_ore").addOre("mekanism:deepslate_uranium_ore")).toJsonObject()));




        BlockList.add(new Pair<>("bedrock_ug_cloggrum_ore", new RegenerativeBlockConfig("bedrock_ug_cloggrum_ore").addRequiredMod("undergarden").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Cloggrum Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_ug_cloggrum").setEnglishName("Regenerative Cloggrum Cluster").setTextureFile("undergarden:item/raw_cloggrum"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_ug_cloggrum").addOre("undergarden:depthrock_cloggrum_ore").addOre("undergarden:shiverstone_cloggrum_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_ug_froststeel_ore", new RegenerativeBlockConfig("bedrock_ug_froststeel_ore").addRequiredMod("undergarden").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Froststeel Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_ug_froststeel").setEnglishName("Regenerative Froststeel Cluster").setTextureFile("undergarden:item/raw_froststeel"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_ug_froststeel").addOre("undergarden:shiverstone_froststeel_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_ug_utherium_ore", new RegenerativeBlockConfig("bedrock_ug_utherium_ore").addRequiredMod("undergarden").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Utherium Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_ug_utherium").setEnglishName("Regenerative Utherium Cluster").setTextureFile("undergarden:item/utherium_crystal"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_ug_utherium").addOre("undergarden:depthrock_utherium_ore").addOre("undergarden:shiverstone_utherium_ore").addOre("undergarden:tremblecrust_utherium_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_ug_regalium_ore", new RegenerativeBlockConfig("bedrock_ug_regalium_ore").addRequiredMod("undergarden").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Regalium Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_ug_regalium").setEnglishName("Regenerative Regalium Cluster").setTextureFile("undergarden:item/regalium_crystal"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_ug_regalium").addOre("undergarden:depthrock_regalium_ore").addOre("undergarden:shiverstone_regalium_ore")).toJsonObject()));




        BlockList.add(new Pair<>("bedrock_be_desh_ore", new RegenerativeBlockConfig("bedrock_be_desh_ore").addRequiredMod("beyond_earth").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Desh Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_be_desh").setEnglishName("Regenerative Desh Cluster").setTextureFile("beyond_earth:item/raw_desh"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_be_desh").addOre("beyond_earth:moon_desh_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_be_cheese_ore", new RegenerativeBlockConfig("bedrock_be_cheese_ore").addRequiredMod("beyond_earth").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Cheese Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_be_cheese").setEnglishName("Regenerative Cheese Cluster").setTextureFile("beyond_earth:item/item_cheese"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_be_cheese").addOre("beyond_earth:moon_cheese_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_be_ice_shard_ore", new RegenerativeBlockConfig("bedrock_be_ice_shard_ore").addRequiredMod("beyond_earth").setReplaceBlockId(Blocks.BEDROCK).setXPmax(2).setEnglishName("Bedrock Ice Shard Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_be_ice_shard").setEnglishName("Regenerative Ice Shard Cluster").setTextureFile("beyond_earth:item/ice_shard"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_be_ice_shard").addOre("beyond_earth:moon_ice_shard_ore").addOre("beyond_earth:mars_ice_shard_ore").addOre("beyond_earth:glacio_ice_shard_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_be_ostrum_ore", new RegenerativeBlockConfig("bedrock_be_ostrum_ore").addRequiredMod("beyond_earth").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Ostrum Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_be_ostrum").setEnglishName("Regenerative Ostrum Cluster").setTextureFile("beyond_earth:item/raw_ostrum"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_be_ostrum").addOre("beyond_earth:mars_ostrum_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_be_calorite_ore", new RegenerativeBlockConfig("bedrock_be_calorite_ore").addRequiredMod("beyond_earth").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Calorite Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_be_calorite").setEnglishName("Regenerative Calorite Cluster").setTextureFile("beyond_earth:item/raw_calorite"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_be_calorite").addOre("beyond_earth:venus_calorite_ore")).toJsonObject()));




        BlockList.add(new Pair<>("bedrock_ic_tin_ore", new RegenerativeBlockConfig("bedrock_ic_tin_ore").addRequiredMod("ftbic").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Tin Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_ic_tin").setEnglishName("Regenerative Tin Cluster").setTextureFile("ftbic:item/tin_chunk"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_ic_tin").addOre("ftbic:tin_ore").addOre("ftbic:deepslate_tin_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_ic_lead_ore", new RegenerativeBlockConfig("bedrock_ic_lead_ore").addRequiredMod("ftbic").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Lead Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_ic_lead").setEnglishName("Regenerative Lead Cluster").setTextureFile("ftbic:item/lead_chunk"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_ic_lead").addOre("ftbic:lead_ore").addOre("ftbic:deepslate_lead_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_ic_aluminum_ore", new RegenerativeBlockConfig("bedrock_ic_aluminum_ore").addRequiredMod("ftbic").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Aluminum Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_ic_aluminum").setEnglishName("Regenerative Aluminum Cluster").setTextureFile("ftbic:item/aluminum_chunk"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_ic_aluminum").addOre("ftbic:aluminum_ore").addOre("ftbic:deepslate_aluminum_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_ic_iridium_ore", new RegenerativeBlockConfig("bedrock_ic_iridium_ore").addRequiredMod("ftbic").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Iridium Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_ic_iridium").setEnglishName("Regenerative Iridium Cluster").setTextureFile("ftbic:item/iridium_chunk"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_ic_iridium").addOre("ftbic:iridium_ore").addOre("ftbic:deepslate_iridium_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_ic_uranium_ore", new RegenerativeBlockConfig("bedrock_ic_uranium_ore").addRequiredMod("ftbic").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Uranium Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_ic_uranium").setEnglishName("Regenerative Uranium Cluster").setTextureFile("ftbic:item/uranium_chunk"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_ic_uranium").addOre("ftbic:uranium_ore").addOre("ftbic:deepslate_uranium_ore")).toJsonObject()));




        BlockList.add(new Pair<>("bedrock_ma_soulstone_ore", new RegenerativeBlockConfig("bedrock_ma_soulstone_ore").addRequiredMod("malum").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Soulstone Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_ma_soulstone").setEnglishName("Regenerative Soulstone Cluster").setTextureFile("malum:item/raw_soulstone"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_ma_soulstone").addOre("malum:soulstone_ore").addOre("malum:deepslate_soulstone_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_ma_brilliance_ore", new RegenerativeBlockConfig("bedrock_ma_brilliance_ore").addRequiredMod("malum").setReplaceBlockId(Blocks.BEDROCK).setXPmin(14).setXPmax(18).setEnglishName("Bedrock Brilliance Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_ma_brilliance").setEnglishName("Regenerative Brilliance Cluster").setTextureFile("malum:item/cluster_of_brilliance"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_ma_brilliance").addOre("malum:brilliant_stone").addOre("malum:brilliant_deepslate")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_ma_blazing_quartz_ore", new RegenerativeBlockConfig("bedrock_ma_blazing_quartz_ore").addRequiredMod("malum").setReplaceBlockId(Blocks.BEDROCK).setXPmin(4).setXPmax(7).setEnglishName("Bedrock Blazing Quartz Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_ma_blazing_quartz").setEnglishName("Regenerative Blazing Quartz Cluster").setTextureFile("malum:item/blazing_quartz"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_ma_blazing_quartz").addOre("malum:blazing_quartz_ore")).toJsonObject()));




        BlockList.add(new Pair<>("bedrock_th_niter_ore", new RegenerativeBlockConfig("bedrock_th_niter_ore").addRequiredMod("thermal").setReplaceBlockId(Blocks.BEDROCK).setXPmax(2).setEnglishName("Bedrock Niter Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_th_niter").setEnglishName("Regenerative Niter Cluster").setTextureFile("thermal:item/niter"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_th_niter").addOre("thermal:niter_ore").addOre("thermal:deepslate_niter_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_th_sulfur_ore", new RegenerativeBlockConfig("bedrock_th_sulfur_ore").addRequiredMod("thermal").setReplaceBlockId(Blocks.BEDROCK).setXPmax(2).setEnglishName("Bedrock Sulfur Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_th_sulfur").setEnglishName("Regenerative Sulfur Cluster").setTextureFile("thermal:item/sulfur"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_th_sulfur").addOre("thermal:sulfur_ore").addOre("thermal:deepslate_sulfur_ore").addOre("thermal:sulfur_ore_netherrack")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_th_tin_ore", new RegenerativeBlockConfig("bedrock_th_tin_ore").addRequiredMod("thermal").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Tin Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_th_tin").setEnglishName("Regenerative Tin Cluster").setTextureFile("thermal:item/raw_tin"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_th_tin").addOre("thermal:tin_ore").addOre("thermal:deepslate_tin_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_th_lead_ore", new RegenerativeBlockConfig("bedrock_th_lead_ore").addRequiredMod("thermal").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Lead Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_th_lead").setEnglishName("Regenerative Lead Cluster").setTextureFile("thermal:item/raw_lead"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_th_lead").addOre("thermal:lead_ore").addOre("thermal:deepslate_lead_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_th_silver_ore", new RegenerativeBlockConfig("bedrock_th_silver_ore").addRequiredMod("thermal").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Silver Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_th_silver").setEnglishName("Regenerative Silver Cluster").setTextureFile("thermal:item/raw_silver"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_th_silver").addOre("thermal:silver_ore").addOre("thermal:deepslate_silver_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_th_nickel_ore", new RegenerativeBlockConfig("bedrock_th_nickel_ore").addRequiredMod("thermal").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Nickel Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_th_nickel").setEnglishName("Regenerative Nickel Cluster").setTextureFile("thermal:item/raw_nickel"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_th_nickel").addOre("thermal:nickel_ore").addOre("thermal:deepslate_nickel_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_th_apatite_ore", new RegenerativeBlockConfig("bedrock_th_apatite_ore").addRequiredMod("thermal").setReplaceBlockId(Blocks.BEDROCK).setXPmax(2).setEnglishName("Bedrock Apatite Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_th_apatite").setEnglishName("Regenerative Apatite Cluster").setTextureFile("thermal:item/apatite"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_th_apatite").addOre("thermal:apatite_ore").addOre("thermal:deepslate_apatite_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_th_cinnabar_ore", new RegenerativeBlockConfig("bedrock_th_cinnabar_ore").addRequiredMod("thermal").setReplaceBlockId(Blocks.BEDROCK).setXPmin(1).setXPmax(3).setEnglishName("Bedrock Cinnabar Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_th_cinnabar").setEnglishName("Regenerative Cinnabar Cluster").setTextureFile("thermal:item/cinnabar"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_th_cinnabar").addOre("thermal:cinnabar_ore").addOre("thermal:deepslate_cinnabar_ore")).toJsonObject()));

        BlockList.add(new Pair<>("bedrock_th_oil_ore", new RegenerativeBlockConfig("bedrock_th_oil_ore").addRequiredMod("thermal").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bituminous Bedrock")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_th_oil").setEnglishName("Regenerative Bitumen Cluster").setTextureFile("thermal:item/bitumen"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_th_oil").addOre("thermal:oil_sand").addOre("thermal:oil_red_sand")).toJsonObject()));




        BlockList.add(new Pair<>("bedrock_pw_uraninite_ore", new RegenerativeBlockConfig("bedrock_pw_uraninite_ore").addRequiredMod("powah").setReplaceBlockId(Blocks.BEDROCK).setEnglishName("Bedrock Uraninite Ore")
                .setCoreConfig(new RegenerativeCoreConfig("regenerative_pw_uraninite").setEnglishName("Regenerative Uraninite Cluster").setTextureFile("powah:item/uraninite_raw"))
                .setLootConfig(new RegenerativeOreLootConfig().setCoreId("regenerative_pw_uraninite").addOre("powah:uraninite_ore").addOre("powah:uraninite_ore_dense").addOre("powah:uraninite_ore_poor")).toJsonObject()));




        dualDensify(BlockList, "coal", "minecraft", "Coal", 0, 2, 0);
        dualDensify(BlockList, "iron", "minecraft", "Iron", 1);
        dualDensify(BlockList, "gold", "minecraft", "Gold", 2);
        dualDensify(BlockList, "diamond", "minecraft", "Diamond", 3, 7, 2);
        dualDensify(BlockList, "redstone", "minecraft", "Redstone", 1, 6, 2);
        dualDensify(BlockList, "copper", "minecraft", "Copper", 1);
        dualDensify(BlockList, "lapis", "minecraft", "Lapis Lazuli", 2, 5, 1);
        dualDensify(BlockList, "emerald", "minecraft", "Emerald", 3, 7, 2);

        densify(BlockList, "nether_gold_ore", "minecraft", "Dense Nether Gold Ore", Blocks.NETHERRACK, 0, 1, 3.0f, 0);
        densify(BlockList, "nether_quartz_ore", "minecraft", "Dense Nether Quartz Ore", Blocks.NETHERRACK, 2, 5, 3.0f, 0);
        //densify(BlockList, "ancient_debris", "minecraft", "Dense Ancient Debris", Blocks.ANCIENT_DEBRIS, 30.0f, 3);
        BlockList.add(new Pair<>("dense_ancient_debris", new RegenerativeBlockConfig("dense_ancient_debris").setReplaceBlockId(Blocks.ANCIENT_DEBRIS).setEnglishName("Dense Ancient Debris").setStandardTexture(false).setTextureClone("minecraft:block/ancient_debris_side").setInfinite(false)
                .setSilkable(true).setReplace(true).setLuminance(2).setDenseOreGenTarget("minecraft:ancient_debris").setHardness(30.0f).setToolLevel(3).setOnlyValidTools(false)
                .setLootConfig(new RegenerativeOreLootConfig().addOre("minecraft:ancient_debris").setSilkable(true)).toJsonObject()));


        dualDensify(BlockList, "bauxite_ore", "deepslate_bauxite_ore", "tr_bauxite", "techreborn", "Bauxite", 2);
        dualDensify(BlockList, "galena_ore", "deepslate_galena_ore", "tr_galena", "techreborn", "Galena", 2);
        dualDensify(BlockList, "iridium_ore", "deepslate_iridium_ore", "tr_iridium", "techreborn", "Iridium", 3);
        dualDensify(BlockList, "lead_ore", "deepslate_lead_ore", "tr_lead", "techreborn", "Lead", 2);
        dualDensify(BlockList, "ruby_ore", "deepslate_ruby_ore", "tr_ruby", "techreborn", "Ruby", 2, 6, 2);
        dualDensify(BlockList, "sapphire_ore", "deepslate_sapphire_ore", "tr_sapphire", "techreborn", "Sapphire", 2, 6, 2);
        dualDensify(BlockList, "silver_ore", "deepslate_silver_ore", "tr_silver", "techreborn", "Silver", 2);
        dualDensify(BlockList, "tin_ore", "deepslate_tin_ore", "tr_tin", "techreborn", "Tin", 1);

        densify(BlockList, "cinnabar_ore", "tr_cinnabar_ore", "techreborn", "Dense Cinnabar Ore", Blocks.NETHERRACK, 3.0f, 2);
        densify(BlockList, "pyrite_ore", "tr_pyrite_ore", "techreborn", "Dense Pyrite Ore", Blocks.NETHERRACK, 3.0f, 2);
        densify(BlockList, "sphalerite_ore", "tr_sphalerite_ore", "techreborn", "Dense Sphalerite Ore", Blocks.NETHERRACK, 3.0f, 2);

        densify(BlockList, "peridot_ore", "tr_peridot_ore_ore", "techreborn", "Dense Peridot Ore", Blocks.END_STONE, 2, 6, 3.0f, 2);
        densify(BlockList, "sheldonite_ore", "tr_sheldonite_ore", "techreborn", "Dense Sheldonite Ore", Blocks.END_STONE, 3.0f, 3);
        densify(BlockList, "sodalite_ore", "tr_sodalite_ore", "techreborn", "Dense Sodalite Ore", Blocks.END_STONE, 3.0f, 3);
        densify(BlockList, "tungsten_ore", "tr_tungsten_ore", "techreborn", "Dense Tungsten Ore", Blocks.END_STONE, 3.0f, 3);


        densify(BlockList, "cobalt_ore", "tc_cobalt_ore", "tconstruct", "Dense Cobalt Ore", Blocks.NETHERRACK, 10.0f, 2);


        densify(BlockList, "yellorite_ore", "er_yellorite_ore", "bigreactors", "Dense Yellorite Ore", Blocks.STONE, 3.0f, 0);
        densify(BlockList, "anglesite_ore", "er_anglesite_ore", "bigreactors", "Dense Anglesite Ore", Blocks.END_STONE, 3, 5, 3.0f, 0);
        densify(BlockList, "benitoite_ore", "er_benitoite_ore", "bigreactors", "Dense Benitoite Ore", Blocks.NETHERRACK, 3, 5, 3.0f, 0);


        densify(BlockList, "uranium_ore", "br_uranium_ore", "biggerreactors", "Dense Uranium Ore", Blocks.STONE, 2.0f, 2);


        dualDensify(BlockList, "ore_aluminum", "deepslate_ore_aluminum", "ie_aluminum", "immersiveengineering", "Aluminum", 1);
        dualDensify(BlockList, "ore_lead", "deepslate_ore_lead", "ie_lead", "immersiveengineering", "Lead", 2);
        dualDensify(BlockList, "ore_silver", "deepslate_ore_silver", "ie_silver", "immersiveengineering", "Silver", 2);
        dualDensify(BlockList, "ore_nickel", "deepslate_ore_nickel", "ie_nickel", "immersiveengineering", "Nickel", 2);
        dualDensify(BlockList, "ore_uranium", "deepslate_ore_uranium", "ie_uranium", "immersiveengineering", "Uranium", 2);


        dualDensify(BlockList, "adamantite_ore", "deepslate_adamantite_ore", "mm_adamantite", "mythicmetals", "Uranium", 3);
        densify(BlockList, "aquarium_ore", "mm_aquarium_ore", "mythicmetals", "Dense Aquarium Ore", Blocks.STONE, 3.0f, 2);
        densify(BlockList, "banglum_ore", "mm_banglum_ore", "mythicmetals", "Dense Banglum Ore", Blocks.STONE, 3.0f, 2);
        dualDensify(BlockList, "carmot_ore", "deepslate_carmot_ore", "mm_carmot", "mythicmetals", "Carmot", 2);
        densify(BlockList, "kyber_ore", "mm_kyber_ore", "mythicmetals", "Dense Kyber Ore", Blocks.STONE, 3.0f, 2);
        densify(BlockList, "calcite_kyber_ore", "calcite_mm_kyber_ore", "mythicmetals", "Dense Calcite Kyber Ore", Blocks.CALCITE, 3, 5, 3.0f, 2);
        densify(BlockList, "manganese_ore", "mm_manganese_ore", "mythicmetals", "Dense Manganese Ore", Blocks.STONE, 3.0f, 1);
        dualDensify(BlockList, "morkite_ore", "deepslate_morkite_ore", "mm_morkite", "mythicmetals", "Morkite", 2);
        dualDensify(BlockList, "mythril_ore", "deepslate_mythril_ore", "mm_mythril", "mythicmetals", "Mythril", 3);
        dualDensify(BlockList, "orichalcum_ore", "deepslate_orichalcum_ore", "mm_orichalcum", "mythicmetals", "Orichalcum", 3);
        densify(BlockList, "tuff_orichalcum_ore", "tuff_mm_orichalcum_ore", "mythicmetals", "Dense Tuff Orichalcum Ore", Blocks.TUFF, 3, 5, 4.5f, 3);
        densify(BlockList, "smooth_basalt_orichalcum_ore", "smooth_basalt_mm_orichalcum_ore", "mythicmetals", "Dense Smooth Basalt Orichalcum Ore", Blocks.SMOOTH_BASALT, 3, 5, 3.0f, 3);
        densify(BlockList, "osmium_ore", "mm_osmium_ore", "mythicmetals", "Dense Osmium Ore", Blocks.STONE, 3.0f, 2);
        densify(BlockList, "platinum_ore", "mm_platinum_ore", "mythicmetals", "Dense Platinum Ore", Blocks.STONE, 3.0f, 2);
        dualDensify(BlockList, "prometheum_ore", "deepslate_prometheum_ore", "mm_prometheum", "mythicmetals", "Prometheum", 2);
        densify(BlockList, "quadrillum_ore", "mm_quadrillum_ore", "mythicmetals", "Dense Quadrillum Ore", Blocks.STONE, 3.0f, 2);
        densify(BlockList, "runite_ore", "mm_runite_ore", "mythicmetals", "Dense Runite Ore", Blocks.STONE, 3.0f, 2);
        densify(BlockList, "silver_ore", "mm_silver_ore", "mythicmetals", "Dense Silver Ore", Blocks.STONE, 3.0f, 1);
        densify(BlockList, "starrite_ore", "mm_starrite_ore", "mythicmetals", "Dense Starrite Ore", Blocks.STONE, 3, 6, 3.0f, 3);
        densify(BlockList, "calcite_starrite_ore", "calcite_mm_starrite_ore", "mythicmetals", "Dense Calcite Starrite Ore", Blocks.CALCITE, 3, 6, 3.0f, 3);
        densify(BlockList, "end_stone_starrite_ore", "end_stone_mm_starrite_ore", "mythicmetals", "Dense End Stone Starrite Ore", Blocks.END_STONE, 3, 6, 3.0f, 4);
        densify(BlockList, "tin_ore", "mm_tin_ore", "mythicmetals", "Dense Tin Ore", Blocks.STONE, 3.0f, 1);
        dualDensify(BlockList, "unobtainium_ore", "deepslate_unobtainium_ore", "mm_unobtainium", "mythicmetals", "Unobtainium", 4, 7, 4);

        densify(BlockList, "nether_banglum_ore", "mm_nether_banglum_ore", "mythicmetals", "Dense Nether Banglum Ore", Blocks.NETHERRACK, 3.0f, 2);
        densify(BlockList, "midas_gold_ore", "mm_midas_gold_ore", "mythicmetals", "Dense Midas Gold Ore", Blocks.NETHERRACK, 3.0f, 1);
        densify(BlockList, "palladium_ore", "mm_palladium_ore", "mythicmetals", "Dense Palladium Ore", Blocks.NETHERRACK, 3.0f, 3);
        densify(BlockList, "stormyx_ore", "mm_stormyx_ore", "mythicmetals", "Dense Stormyx Ore", Blocks.NETHERRACK, 2, 4, 3.0f, 2);
        densify(BlockList, "blackstone_stormyx_ore", "blackstone_mm_stormyx_ore", "mythicmetals", "Dense Blackstone Stormyx Ore", Blocks.BLACKSTONE, 2, 4, 3.0f, 2);


        dualDensify(BlockList, "zinc_ore", "deepslate_zinc_ore", "cr_zinc", "create", "Zinc", 2);


        dualDensify(BlockList, "osmium_ore", "deepslate_osmium_ore", "mk_osmium", "mekanism", "Osmium", 1);
        dualDensify(BlockList, "fluorite_ore", "deepslate_fluorite_ore", "mk_fluorite", "mekanism", "Fluorite", 1);
        dualDensify(BlockList, "lead_ore", "deepslate_lead_ore", "mk_lead", "mekanism", "Lead", 1);
        dualDensify(BlockList, "tin_ore", "deepslate_tin_ore", "mk_tin", "mekanism", "Tin", 1);
        dualDensify(BlockList, "uranium_ore", "deepslate_uranium_ore", "mk_uranium", "mekanism", "Uranium", 1);


        densify(BlockList, "depthrock_cloggrum_ore", "depthrock_ug_cloggrum_ore", "undergarden", "Dense Depthrock Cloggrum Ore", "undergarden:depthrock", 3.0f, 1);
        densify(BlockList, "shiverstone_cloggrum_ore", "shiverstone_ug_cloggrum_ore", "undergarden", "Dense Shiverstone Cloggrum Ore", "undergarden:shiverstone", 4.5f, 1);
        densify(BlockList, "shiverstone_froststeel_ore", "shiverstone_ug_froststeel_ore", "undergarden", "Dense Shiverstone Froststeel Ore", "undergarden:shiverstone", 4.5f, 2);
        densify(BlockList, "depthrock_utherium_ore", "depthrock_ug_utherium_ore", "undergarden", "Dense Depthrock Utherium Ore", "undergarden:depthrock", 3.0f, 2);
        densify(BlockList, "shiverstone_utherium_ore", "shiverstone_ug_utherium_ore", "undergarden", "Dense Shiverstone Utherium Ore", "undergarden:shiverstone", 4.5f, 2);
        densify(BlockList, "tremblecrust_utherium_ore", "tremblecrust_ug_utherium_ore", "undergarden", "Dense Tremblecrust Utherium Ore", "undergarden:tremblecrust", 7.0f, 2);
        densify(BlockList, "depthrock_regalium_ore", "depthrock_ug_regalium_ore", "undergarden", "Dense Depthrock Regalium Ore", "undergarden:depthrock", 3.0f, 2);
        densify(BlockList, "shiverstone_regalium_ore", "shiverstone_ug_regalium_ore", "undergarden", "Dense Shiverstone Regalium Ore", "undergarden:shiverstone", 4.5f, 2);

        densify(BlockList, "depthrock_coal_ore", "depthrock_ug_coal_ore", "undergarden", "Dense Depthrock Coal Ore", "undergarden:depthrock", 0, 2, 3.0f, 0);
        densify(BlockList, "shiverstone_coal_ore", "shiverstone_ug_coal_ore", "undergarden", "Dense Shiverstone Coal Ore", "undergarden:shiverstone", 0, 2, 4.5f, 0);
        densify(BlockList, "depthrock_iron_ore", "depthrock_ug_iron_ore", "undergarden", "Dense Depthrock Iron Ore", "undergarden:depthrock", 3.0f, 1);
        densify(BlockList, "shiverstone_iron_ore", "shiverstone_ug_iron_ore", "undergarden", "Dense Shiverstone Iron Ore", "undergarden:shiverstone", 4.5f, 1);
        densify(BlockList, "depthrock_gold_ore", "depthrock_ug_gold_ore", "undergarden", "Dense Depthrock Gold Ore", "undergarden:depthrock", 3.0f, 2);
        densify(BlockList, "depthrock_diamond_ore", "depthrock_ug_diamond_ore", "undergarden", "Dense Depthrock Iron Ore", "undergarden:depthrock", 3.0f, 2);
        densify(BlockList, "shiverstone_diamond_ore", "shiverstone_ug_diamond_ore", "undergarden", "Dense Shiverstone Diamond Ore", "undergarden:shiverstone", 3, 7, 4.5f, 2);


        densify(BlockList, "moon_desh_ore", "moon_be_desh_ore", "beyond_earth", "Dense Moon Desh Ore", "beyond_earth:moon_stone", 3.0f, 2);
        densify(BlockList, "moon_cheese_ore", "moon_be_cheese_ore", "beyond_earth", "Dense Moon Cheese Ore", "beyond_earth:moon_stone", 3.0f, 1);
        densify(BlockList, "moon_ice_shard_ore", "moon_be_ice_shard_ore", "beyond_earth", "Dense Moon Ice Shard Ore", "beyond_earth:moon_stone", 0, 2, 3.0f, 1);
        densify(BlockList, "mars_ice_shard_ore", "mars_be_ice_shard_ore", "beyond_earth", "Dense Mars Ice Shard Ore", "beyond_earth:mars_stone", 0, 2, 3.0f, 1);
        densify(BlockList, "glacio_ice_shard_ore", "glacio_be_ice_shard_ore", "beyond_earth", "Dense Glacio Ice Shard Ore", "beyond_earth:glacio_stone", 0, 2, 3.0f, 1);
        densify(BlockList, "mars_ostrum_ore", "mars_be_ostrum_ore", "beyond_earth", "Dense Mars Ostrum Ore", "beyond_earth:mars_stone", 3.0f, 2);
        densify(BlockList, "venus_calorite_ore", "venus_be_calorite_ore", "beyond_earth", "Dense Venus Calorite Ore", "beyond_earth:venus_stone", 3.0f, 2);

        densify(BlockList, "venus_coal_ore", "venus_be_coal_ore", "beyond_earth", "Dense Venus Coal Ore", "beyond_earth:venus_stone",0, 2, 3.0f, 0);
        densify(BlockList, "glacio_coal_ore", "glacio_be_coal_ore", "beyond_earth", "Dense Glacio Coal Ore", "beyond_earth:glacio_stone",0, 2,  3.0f, 0);
        densify(BlockList, "glacio_iron_ore", "glacio_be_iron_ore", "beyond_earth", "Dense Glacio Iron Ore", "beyond_earth:glacio_stone",  3.0f, 1);
        densify(BlockList, "moon_iron_ore", "moon_be_iron_ore", "beyond_earth", "Dense Moon Iron Ore", "beyond_earth:moon_stone",  3.0f, 1);
        densify(BlockList, "mars_iron_ore", "mars_be_iron_ore", "beyond_earth", "Dense Mars Iron Ore", "beyond_earth:mars_stone", 3.0f, 1);
        densify(BlockList, "mercury_iron_ore", "mercury_be_iron_ore", "beyond_earth", "Dense Mercury Iron Ore", "beyond_earth:mercury_stone", 3.0f, 1);
        densify(BlockList, "venus_gold_ore", "venus_be_gold_ore", "beyond_earth", "Dense Venus Gold Ore", "beyond_earth:venus_stone", 3.0f, 2);
        densify(BlockList, "mars_diamond_ore", "mars_be_diamond_ore", "beyond_earth", "Dense Mars Diamond Ore", "beyond_earth:mars_stone", 3, 7, 3.0f, 2);
        densify(BlockList, "venus_diamond_ore", "venus_be_diamond_ore", "beyond_earth", "Dense Venus Diamond Ore", "beyond_earth:venus_stone", 3, 7, 3.0f, 2);
        densify(BlockList, "glacio_copper_ore", "glacio_be_copper_ore", "beyond_earth", "Dense Glacio Copper Ore", "beyond_earth:glacio_stone",  3.0f, 1);
        densify(BlockList, "glacio_lapis_ore", "glacio_be_lapis_ore", "beyond_earth", "Dense Glacio Lapis Ore", "beyond_earth:glacio_stone", 2, 5,  3.0f, 1);


        dualDensify(BlockList, "tin_ore", "deepslate_tin_ore", "ic_tin", "ftbic", "Tin", 0);
        dualDensify(BlockList, "lead_ore", "deepslate_lead_ore", "ic_lead", "ftbic", "Lead", 0);
        dualDensify(BlockList, "aluminum_ore", "deepslate_aluminum_ore", "ic_aluminum", "ftbic", "Aluminum", 0);
        dualDensify(BlockList, "iridium_ore", "deepslate_iridium_ore", "ic_iridium", "ftbic", "Iridium", 0);
        dualDensify(BlockList, "uranium_ore", "deepslate_uranium_ore", "ic_uranium", "ftbic", "Uranium", 0);


        dualDensify(BlockList, "soulstone_ore", "deepslate_soulstone_ore", "ma_soulstone", "malum", "Soulstone", 0);
        dualDensify(BlockList, "brilliant_stone", "brilliant_deepslate", "ma_brilliance", "malum", "Brilliance", 14, 18, 0);
        densify(BlockList, "blazing_quartz_ore", "ma_blazing_quartz_ore", "malum", "Dense Blazing Quartz Ore", Blocks.NETHERRACK, 4, 7, 3.0f, 0);


        dualDensify(BlockList, "niter_ore", "deepslate_niter_ore", "th_niter", "thermal", "Niter", 0, 2, 0);
        dualDensify(BlockList, "sulfur_ore", "deepslate_sulfur_ore", "th_sulfur", "thermal", "Sulfur", 0, 2, 0);
        dualDensify(BlockList, "tin_ore", "deepslate_tin_ore", "th_tin", "thermal", "Tin", 1);
        dualDensify(BlockList, "lead_ore", "deepslate_lead_ore", "th_lead", "thermal", "Lead", 2);
        dualDensify(BlockList, "silver_ore", "deepslate_silver_ore", "th_silver", "thermal", "Silver", 2);
        dualDensify(BlockList, "nickel_ore", "deepslate_nickel_ore", "th_nickel", "thermal", "Nickel", 2);
        dualDensify(BlockList, "apatite_ore", "deepslate_apatite_ore", "th_apatite", "thermal", "Apatite", 0, 2, 1);
        dualDensify(BlockList, "cinnabar_ore", "deepslate_cinnabar_ore", "th_cinnabar", "thermal", "Cinnabar", 1, 3, 1);
        densify(BlockList, "oil_sand", "th_oil_sand", "thermal", "Dense Bituminous Sand", Blocks.SAND, 0.5f, 0);
        densify(BlockList, "oil_red_sand", "th_oil_red_sand", "thermal", "Dense Bituminous Red Sand", Blocks.RED_SAND, 0.5f, 0);


        densify(BlockList, "uraninite_ore", "pw_uraninite_ore", "powah", "Dense Uraninite Ore", Blocks.STONE, 3.2f, 0);
        densify(BlockList, "uraninite_ore_dense", "pw_dense_uraninite_ore", "powah", "SuperDense Uraninite Ore", Blocks.STONE, 4.0f, 0);
        densify(BlockList, "uraninite_ore_poor", "pw_poor_uraninite_ore", "powah", "Dense Poor Uraninite Ore", Blocks.STONE, 3.0f, 0);



        BlockList.add(new Pair<>("native_iron_ore", new RegenerativeBlockConfig("native_iron_ore").setReplaceBlockId(Blocks.DEEPSLATE).setEnglishName("Native Iron Ore").setHardness(1.0f).setDurabilityMin(50).setDurabilityMax(200).setStandardTexture(false)
                .setInfinite(false).setLootConfig(new RegenerativeOreCustomLootConfig().setSilkable(true).addItemWithRange("minecraft:iron_nugget", 1, 5)).toJsonObject()));

        BlockList.add(new Pair<>("native_gold_ore", new RegenerativeBlockConfig("native_gold_ore").setReplaceBlockId(Blocks.DEEPSLATE).setEnglishName("Native Gold Ore").setHardness(1.0f).setDurabilityMin(50).setDurabilityMax(200)
                .setInfinite(false).setLootConfig(new RegenerativeOreCustomLootConfig().setSilkable(true).addItemWithRange("minecraft:gold_nugget", 1, 5)).toJsonObject()));

        JsonConfig.writeJsonFiles(BlockList, JsonConfig.block_config_path);
    }





    //Plenty of options to make it easy...

    public static void dualDensify(List<Pair<String, JsonObject>> list, String targetOreSnippit, String targetMod, String nameSnippit, int xpMin, int xpMax, int toolLevel){

        densify(list, targetOreSnippit + "_ore", targetMod, "Dense " + nameSnippit + " Ore", Blocks.STONE, xpMin, xpMax, 3.0f, toolLevel);
        densify(list, "deepslate_" + targetOreSnippit + "_ore", targetMod, "Dense Deepslate " + nameSnippit + " Ore", Blocks.DEEPSLATE, xpMin, xpMax, 4.5f, toolLevel);
    }
    public static void dualDensify(List<Pair<String, JsonObject>> list, String targetOreSnippit, String targetMod, String nameSnippit, int toolLevel){

        densify(list, targetOreSnippit + "_ore", targetMod, "Dense " + nameSnippit + " Ore", Blocks.STONE, 3.0f, toolLevel);
        densify(list, "deepslate_" + targetOreSnippit + "_ore", targetMod, "Dense Deepslate " + nameSnippit + " Ore", Blocks.DEEPSLATE, 4.5f, toolLevel);
    }
    public static void dualDensify(List<Pair<String, JsonObject>> list, String targetOre, String targetDeepOre, String idSnippit, String targetMod, String nameSnippit, int xpMin, int xpMax, int toolLevel){

        densify(list, targetOre, idSnippit + "_ore", targetMod, "Dense " + nameSnippit + " Ore", Blocks.STONE, xpMin, xpMax, 3.0f, toolLevel);
        densify(list, targetDeepOre, "deepslate_" + idSnippit + "_ore", targetMod, "Dense Deepslate " + nameSnippit + " Ore", Blocks.DEEPSLATE, xpMin, xpMax, 4.5f, toolLevel);
    }
    public static void dualDensify(List<Pair<String, JsonObject>> list, String targetOre, String targetDeepOre, String idSnippit, String targetMod, String nameSnippit, int toolLevel){

        densify(list, targetOre, idSnippit + "_ore", targetMod, "Dense " + nameSnippit + " Ore", Blocks.STONE, 3.0f, toolLevel);
        densify(list, targetDeepOre, "deepslate_" + idSnippit + "_ore", targetMod, "Dense Deepslate " + nameSnippit + " Ore", Blocks.DEEPSLATE, 4.5f, toolLevel);
    }
    public static void densify(List<Pair<String, JsonObject>> list, String targetOre, String targetMod, String name, Block hostStone, String texture, float probability, int xpMin, int xpMax, float hardness, int toolLevel){
        list.add(new Pair<>("dense_" + targetOre, new RegenerativeBlockConfig("dense_" + targetOre).addRequiredMod(targetMod).setReplaceBlockId(hostStone).setEnglishName(name).setStandardTexture(false).setTextureClone(texture).setInfinite(false)
                .setSilkable(true).setReplace(true).setXPmin(xpMin).setXPmax(xpMax).setLuminance(2).setDenseOreGenTarget(targetMod + ":" + targetOre).setDenseOreGenProbability(probability).setHardness(hardness).setToolLevel(toolLevel).setOnlyValidTools(false)
                .setLootConfig(new RegenerativeOreLootConfig().addOre(targetMod + ":" + targetOre).setSilkable(true)).toJsonObject()));
    }

    public static void densify(List<Pair<String, JsonObject>> list, String targetOre, String targetMod, String name, Block hostStone, String texture, float probability, float hardness, int toolLevel){
        densify(list, targetOre, targetMod, name, hostStone, texture, probability, 0, 0, hardness, toolLevel);
    }
    public static void densify(List<Pair<String, JsonObject>> list, String targetOre, String targetMod, String name, Block hostStone, String texture, int xpMin, int xpMax, float hardness, int toolLevel){
        densify(list, targetOre, targetMod, name, hostStone, texture, EntryModule.DefaultRegenerativeBlockConfig.denseOreGenProbability, xpMin, xpMax, hardness, toolLevel);
    }
    public static void densify(List<Pair<String, JsonObject>> list, String targetOre, String targetMod, String name, Block hostStone, String texture, float hardness, int toolLevel) {
        densify(list, targetOre, targetMod, name, hostStone, texture, EntryModule.DefaultRegenerativeBlockConfig.denseOreGenProbability, 0, 0, hardness, toolLevel);

    }
    public static void densify(List<Pair<String, JsonObject>> list, String targetOre, String targetMod, String name, Block hostStone, float probability, float hardness, int toolLevel){
        densify(list, targetOre, targetMod, name, hostStone, targetMod + ":block/" + targetOre, probability, 0, 0, hardness, toolLevel);
    }
    public static void densify(List<Pair<String, JsonObject>> list, String targetOre, String targetMod, String name, Block hostStone, int xpMin, int xpMax, float hardness, int toolLevel){
        densify(list, targetOre, targetMod, name, hostStone, targetMod + ":block/" + targetOre, EntryModule.DefaultRegenerativeBlockConfig.denseOreGenProbability, xpMin, xpMax, hardness, toolLevel);
    }
    public static void densify(List<Pair<String, JsonObject>> list, String targetOre, String targetMod, String name, Block hostStone, float hardness, int toolLevel){
        densify(list, targetOre, targetMod, name, hostStone, targetMod + ":block/" + targetOre, EntryModule.DefaultRegenerativeBlockConfig.denseOreGenProbability, 0, 0, hardness, toolLevel);
    }

    public static void densify(List<Pair<String, JsonObject>> list, String targetOre, String targetMod, String name, String hostStone, String texture, float probability, int xpMin, int xpMax, float hardness, int toolLevel){
        list.add(new Pair<>("dense_" + targetOre, new RegenerativeBlockConfig("dense_" + targetOre).addRequiredMod(targetMod).setReplaceBlockId(hostStone).setEnglishName(name).setStandardTexture(false).setTextureClone(texture).setInfinite(false)
                .setSilkable(true).setReplace(true).setXPmin(xpMin).setXPmax(xpMax).setLuminance(2).setDenseOreGenTarget(targetMod + ":" + targetOre).setDenseOreGenProbability(probability).setHardness(hardness).setToolLevel(toolLevel).setOnlyValidTools(false)
                .setLootConfig(new RegenerativeOreLootConfig().addOre(targetMod + ":" + targetOre).setSilkable(true)).toJsonObject()));
    }

    public static void densify(List<Pair<String, JsonObject>> list, String targetOre, String targetMod, String name, String hostStone, float probability, float hardness, int toolLevel){
        densify(list, targetOre, targetMod, name, hostStone, targetMod + ":block/" + targetOre, probability, 0, 0, hardness, toolLevel);
    }

    public static void densify(List<Pair<String, JsonObject>> list, String targetOre, String targetMod, String name, String hostStone, int xpMin, int xpMax, float hardness, int toolLevel){
        densify(list, targetOre, targetMod, name, hostStone, targetMod + ":block/" + targetOre, EntryModule.DefaultRegenerativeBlockConfig.denseOreGenProbability, xpMin, xpMax, hardness, toolLevel);
    }

    public static void densify(List<Pair<String, JsonObject>> list, String targetOre, String targetMod, String name, String hostStone, float hardness, int toolLevel){
        densify(list, targetOre, targetMod, name, hostStone, targetMod + ":block/" + targetOre, EntryModule.DefaultRegenerativeBlockConfig.denseOreGenProbability, 0, 0, hardness, toolLevel);
    }



    public static void densify(List<Pair<String, JsonObject>> list, String targetOre, String id, String targetMod, String name, Block hostStone, String texture, float probability, int xpMin, int xpMax, float hardness, int toolLevel){
        list.add(new Pair<>("dense_" + id, new RegenerativeBlockConfig("dense_" + id).addRequiredMod(targetMod).setReplaceBlockId(hostStone).setEnglishName(name).setStandardTexture(false).setTextureClone(texture).setInfinite(false)
                .setSilkable(true).setReplace(true).setXPmin(xpMin).setXPmax(xpMax).setLuminance(2).setDenseOreGenTarget(targetMod + ":" + targetOre).setDenseOreGenProbability(probability).setHardness(hardness).setToolLevel(toolLevel).setOnlyValidTools(false)
                .setLootConfig(new RegenerativeOreLootConfig().addOre(targetMod + ":" + targetOre).setSilkable(true)).toJsonObject()));
    }

    public static void densify(List<Pair<String, JsonObject>> list, String targetOre, String id, String targetMod, String name, Block hostStone, String texture, float probability, float hardness, int toolLevel){
        densify(list, targetOre, id, targetMod, name, hostStone, texture, probability, 0, 0, hardness, toolLevel);
    }
    public static void densify(List<Pair<String, JsonObject>> list, String targetOre, String id, String targetMod, String name, Block hostStone, String texture, int xpMin, int xpMax, float hardness, int toolLevel){
        densify(list, targetOre, id, targetMod, name, hostStone, texture, EntryModule.DefaultRegenerativeBlockConfig.denseOreGenProbability, xpMin, xpMax, hardness, toolLevel);
    }
    public static void densify(List<Pair<String, JsonObject>> list, String targetOre, String id, String targetMod, String name, Block hostStone, String texture, float hardness, int toolLevel) {
        densify(list, targetOre, id, targetMod, name, hostStone, texture, EntryModule.DefaultRegenerativeBlockConfig.denseOreGenProbability, 0, 0, hardness, toolLevel);

    }
    public static void densify(List<Pair<String, JsonObject>> list, String targetOre, String id, String targetMod, String name, Block hostStone, float probability, float hardness, int toolLevel){
        densify(list, targetOre, id, targetMod, name, hostStone, targetMod + ":block/" + targetOre, probability, 0, 0, hardness, toolLevel);
    }
    public static void densify(List<Pair<String, JsonObject>> list, String targetOre, String id, String targetMod, String name, Block hostStone, int xpMin, int xpMax, float hardness, int toolLevel){
        densify(list, targetOre, id, targetMod, name, hostStone, targetMod + ":block/" + targetOre, EntryModule.DefaultRegenerativeBlockConfig.denseOreGenProbability, xpMin, xpMax, hardness, toolLevel);
    }
    public static void densify(List<Pair<String, JsonObject>> list, String targetOre, String id, String targetMod, String name, Block hostStone, float hardness, int toolLevel){
        densify(list, targetOre, id, targetMod, name, hostStone, targetMod + ":block/" + targetOre, EntryModule.DefaultRegenerativeBlockConfig.denseOreGenProbability, 0, 0, hardness, toolLevel);
    }

    public static void densify(List<Pair<String, JsonObject>> list, String targetOre, String id, String targetMod, String name, String hostStone, String texture, float probability, int xpMin, int xpMax, float hardness, int toolLevel){
        list.add(new Pair<>("dense_" + id, new RegenerativeBlockConfig("dense_" + id).addRequiredMod(targetMod).setReplaceBlockId(hostStone).setEnglishName(name).setStandardTexture(false).setTextureClone(texture).setInfinite(false)
                .setSilkable(true).setReplace(true).setXPmin(xpMin).setXPmax(xpMax).setLuminance(2).setDenseOreGenTarget(targetMod + ":" + targetOre).setDenseOreGenProbability(probability).setHardness(hardness).setToolLevel(toolLevel).setOnlyValidTools(false)
                .setLootConfig(new RegenerativeOreLootConfig().addOre(targetMod + ":" + targetOre).setSilkable(true)).toJsonObject()));
    }

    public static void densify(List<Pair<String, JsonObject>> list, String targetOre, String id, String targetMod, String name, String hostStone, String texture, float probability, float hardness, int toolLevel){
        densify(list, targetOre, id, targetMod, name, hostStone, texture, probability, 0, 0, hardness, toolLevel);
    }

    public static void densify(List<Pair<String, JsonObject>> list, String targetOre, String id, String targetMod, String name, String hostStone, String texture, int xpMin, int xpMax, float hardness, int toolLevel){
        densify(list, targetOre, id, targetMod, name, hostStone, texture, EntryModule.DefaultRegenerativeBlockConfig.denseOreGenProbability, xpMin, xpMax, hardness, toolLevel);
    }

    public static void densify(List<Pair<String, JsonObject>> list, String targetOre, String id, String targetMod, String name, String hostStone, String texture, float hardness, int toolLevel){
        densify(list, targetOre, id, targetMod, name, hostStone, texture, EntryModule.DefaultRegenerativeBlockConfig.denseOreGenProbability, 0, 0, hardness, toolLevel);
    }

    public static void densify(List<Pair<String, JsonObject>> list, String targetOre, String id, String targetMod, String name, String hostStone, float probability, float hardness, int toolLevel){
        densify(list, targetOre, id, targetMod, name, hostStone, targetMod + ":block/" + targetOre, probability, 0, 0, hardness, toolLevel);
    }

    public static void densify(List<Pair<String, JsonObject>> list, String targetOre, String id, String targetMod, String name, String hostStone, int xpMin, int xpMax, float hardness, int toolLevel){
        densify(list, targetOre, id, targetMod, name, hostStone, targetMod + ":block/" + targetOre, EntryModule.DefaultRegenerativeBlockConfig.denseOreGenProbability, xpMin, xpMax, hardness, toolLevel);
    }

    public static void densify(List<Pair<String, JsonObject>> list, String targetOre, String id, String targetMod, String name, String hostStone, float hardness, int toolLevel){
        densify(list, targetOre, id, targetMod, name, hostStone, targetMod + ":block/" + targetOre, EntryModule.DefaultRegenerativeBlockConfig.denseOreGenProbability, 0, 0, hardness, toolLevel);
    }
    //too much?
}

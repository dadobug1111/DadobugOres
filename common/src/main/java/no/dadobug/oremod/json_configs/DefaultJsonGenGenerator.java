package no.dadobug.oremod.json_configs;

import com.mojang.datafixers.util.Pair;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonObject;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import no.dadobug.oremod.EntryModule;

import java.util.ArrayList;
import java.util.List;

public class DefaultJsonGenGenerator {
    static String top = "top";
    static String all = "all";
    static String hidden = "hidden";
    static TagKey<Biome> undergarden = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("undergarden", "is_undergarden"));
    static TagKey<Biome> moon = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("beyond_earth", "moon"));
    static TagKey<Biome> mars = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("beyond_earth", "mars"));
    static TagKey<Biome> venus = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("beyond_earth", "venus"));
    static TagKey<Biome> mercury = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("beyond_earth", "mercury"));
    static TagKey<Biome> glacio = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("beyond_earth", "glacio"));

    public static void addDefaults(){
        List<Pair<String, JsonObject>> GenList = new ArrayList<>();

        GenList.add(new Pair<>("native_iron_ore_overworld", new PropagatorOreGenConfig("native_iron_ore_overworld").setTargetBlock("NULL").setTargetTag("minecraft:deepslate_ore_replaceables")
                .setOreBlock(EntryModule.modid +":native_iron_ore").setSize(35).setChance(1).setLayer(all).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("native_gold_ore_overworld", new PropagatorOreGenConfig("native_gold_ore_overworld").setTargetBlock("NULL").setTargetTag("minecraft:deepslate_ore_replaceables")
                .setOreBlock(EntryModule.modid +":native_gold_ore").setSize(25).setChance(1).setLayer(all).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));




        GenList.add(new Pair<>("bedrock_coal_ore_overworld", new PropagatorOreGenConfig("bedrock_coal_ore_overworld")
                .setOreBlock(EntryModule.modid +":bedrock_coal_ore").setSize(20).setChance(3).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_iron_ore_overworld", new PropagatorOreGenConfig("bedrock_iron_ore_overworld")
                .setOreBlock(EntryModule.modid +":bedrock_iron_ore").setSize(9).setChance(5).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_gold_ore_overworld", new PropagatorOreGenConfig("bedrock_gold_ore_overworld")
                .setOreBlock(EntryModule.modid +":bedrock_gold_ore").setSize(9).setChance(7).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_diamond_ore_overworld", new PropagatorOreGenConfig("bedrock_diamond_ore_overworld")
                .setOreBlock(EntryModule.modid +":bedrock_diamond_ore").setSize(8).setChance(12).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_redstone_ore_overworld", new PropagatorOreGenConfig("bedrock_redstone_ore_overworld")
                .setOreBlock(EntryModule.modid +":bedrock_redstone_ore").setSize(11).setChance(6).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_copper_ore_overworld", new PropagatorOreGenConfig("bedrock_copper_ore_overworld")
                .setOreBlock(EntryModule.modid +":bedrock_copper_ore").setSize(13).setChance(3).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_lapis_ore_overworld", new PropagatorOreGenConfig("bedrock_lapis_ore_overworld")
                .setOreBlock(EntryModule.modid +":bedrock_lapis_ore").setSize(9).setChance(9).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_emerald_ore_overworld", new PropagatorOreGenConfig("bedrock_emerald_ore_overworld")
                .setOreBlock(EntryModule.modid +":bedrock_emerald_ore").setSize(3).setChance(2).addBiomeTag(BiomeTags.IS_MOUNTAIN).addBiomeTag(BiomeTags.IS_HILL).addBiome(Biomes.GROVE.location().toString()).toJsonObject()));


        GenList.add(new Pair<>("bedrock_hollow_overworld", new PropagatorOreGenConfig("bedrock_hollow_overworld")
                .setOreBlock(EntryModule.modid +":bedrock_hollow").setSize(40).setChance(16).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_xp_leak_overworld", new PropagatorOreGenConfig("bedrock_xp_leak_overworld")
                .setOreBlock(EntryModule.modid +":bedrock_xp_leak").setSize(5).setChance(13).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_water_ore_overworld", new PropagatorOreGenConfig("bedrock_water_ore_overworld")
                .setOreBlock(EntryModule.modid +":bedrock_water_ore").setSize(15).setChance(3).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_lava_ore_overworld", new PropagatorOreGenConfig("bedrock_lava_ore_overworld")
                .setOreBlock(EntryModule.modid +":bedrock_lava_ore").setSize(5).setChance(8).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_milk_ore_overworld", new PropagatorOreGenConfig("bedrock_milk_ore_overworld")
                .setOreBlock(EntryModule.modid +":bedrock_milk_ore").setSize(30).setChance(24).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_cookie_ore_overworld", new PropagatorOreGenConfig("bedrock_cookie_ore_overworld")
                .setOreBlock(EntryModule.modid +":bedrock_cookie_ore").setSize(30).setChance(24).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));



        GenList.add(new Pair<>("bedrock_nether_gold_ore_nether_bottom", new PropagatorOreGenConfig("bedrock_nether_gold_ore_nether_bottom")
                .setOreBlock(EntryModule.modid +":bedrock_nether_gold_ore").setSize(10).setChance(1).addBiomeTag(BiomeTags.IS_NETHER).toJsonObject()));

        GenList.add(new Pair<>("bedrock_nether_gold_ore_nether_top", new PropagatorOreGenConfig("bedrock_nether_gold_ore_nether_top")
                .setOreBlock(EntryModule.modid +":bedrock_nether_gold_ore").setSize(10).setChance(1).addBiomeTag(BiomeTags.IS_NETHER).setLayer(top).toJsonObject()));

        GenList.add(new Pair<>("bedrock_nether_quartz_ore_nether_bottom", new PropagatorOreGenConfig("bedrock_nether_quartz_ore_nether_bottom")
                .setOreBlock(EntryModule.modid +":bedrock_nether_quartz_ore").setSize(5).setChance(2).addBiomeTag(BiomeTags.IS_NETHER).toJsonObject()));

        GenList.add(new Pair<>("bedrock_nether_quartz_ore_nether_top", new PropagatorOreGenConfig("bedrock_nether_quartz_ore_nether_top")
                .setOreBlock(EntryModule.modid +":bedrock_nether_quartz_ore").setSize(5).setChance(2).addBiomeTag(BiomeTags.IS_NETHER).setLayer(top).toJsonObject()));

        GenList.add(new Pair<>("bedrock_ancient_debris_ore_nether_bottom", new PropagatorOreGenConfig("bedrock_ancient_debris_ore_nether_bottom")
                .setOreBlock(EntryModule.modid +":bedrock_ancient_debris_ore").setSize(4).setChance(16).addBiomeTag(BiomeTags.IS_NETHER).toJsonObject()));

        GenList.add(new Pair<>("bedrock_ancient_debris_ore_nether_top", new PropagatorOreGenConfig("bedrock_ancient_debris_ore_nether_top")
                .setOreBlock(EntryModule.modid +":bedrock_ancient_debris_ore").setSize(4).setChance(24).addBiomeTag(BiomeTags.IS_NETHER).setLayer(top).toJsonObject()));

        GenList.add(new Pair<>("bedrock_hollow_nether_bottom", new PropagatorOreGenConfig("bedrock_hollow_nether_bottom")
                .setOreBlock(EntryModule.modid +":bedrock_hollow").setSize(50).setChance(14).addBiomeTag(BiomeTags.IS_NETHER).toJsonObject()));

        GenList.add(new Pair<>("bedrock_hollow_nether_top", new PropagatorOreGenConfig("bedrock_hollow_nether_top")
                .setOreBlock(EntryModule.modid +":bedrock_hollow").setSize(50).setChance(12).addBiomeTag(BiomeTags.IS_NETHER).setLayer(top).toJsonObject()));

        GenList.add(new Pair<>("bedrock_xp_leak_nether_bottom", new PropagatorOreGenConfig("bedrock_xp_leak_nether_bottom")
                .setOreBlock(EntryModule.modid +":bedrock_xp_leak").setSize(7).setChance(9).addBiomeTag(BiomeTags.IS_NETHER).toJsonObject()));

        GenList.add(new Pair<>("bedrock_xp_leak_nether_top", new PropagatorOreGenConfig("bedrock_xp_leak_nether_top")
                .setOreBlock(EntryModule.modid +":bedrock_xp_leak").setSize(7).setChance(8).addBiomeTag(BiomeTags.IS_NETHER).setLayer(top).toJsonObject()));

        GenList.add(new Pair<>("bedrock_lava_ore_nether_bottom", new PropagatorOreGenConfig("bedrock_lava_ore_nether_bottom")
                .setOreBlock(EntryModule.modid +":bedrock_lava_ore").setSize(30).setChance(3).addBiomeTag(BiomeTags.IS_NETHER).toJsonObject()));

        GenList.add(new Pair<>("bedrock_lava_ore_nether_top", new PropagatorOreGenConfig("bedrock_lava_ore_nether_top")
                .setOreBlock(EntryModule.modid +":bedrock_lava_ore").setSize(30).setChance(3).addBiomeTag(BiomeTags.IS_NETHER).setLayer(top).toJsonObject()));



        GenList.add(new Pair<>("bedrock_hollow_end", new PropagatorOreGenConfig("bedrock_hollow_end").setChance(-1).setReplaceWithBlock_State(false).setTargetBlock("minecraft:end_stone")
                .setOreBlock(EntryModule.modid +":bedrock_hollow").setSize(60).setCount(1).addBiomeTag(BiomeTags.IS_END).setLayer(hidden).toJsonObject()));

        GenList.add(new Pair<>("bedrock_xp_leak_end", new PropagatorOreGenConfig("bedrock_xp_leak_end").setChance(-1).setReplaceWithBlock_State(false).setTargetBlock("minecraft:end_stone")
                .setOreBlock(EntryModule.modid +":bedrock_xp_leak").setSize(12).setCount(2).addBiomeTag(BiomeTags.IS_END).setLayer(hidden).toJsonObject()));




        GenList.add(new Pair<>("bedrock_tr_bauxite_ore_overworld", new PropagatorOreGenConfig("bedrock_tr_bauxite_ore_overworld").addRequiredMod("techreborn")
                .setOreBlock(EntryModule.modid +":bedrock_tr_bauxite_ore").setSize(6).setChance(5).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_tr_galena_ore_overworld", new PropagatorOreGenConfig("bedrock_tr_galena_ore_overworld").addRequiredMod("techreborn")
                .setOreBlock(EntryModule.modid +":bedrock_tr_galena_ore").setSize(8).setChance(5).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_tr_iridium_ore_overworld", new PropagatorOreGenConfig("bedrock_tr_iridium_ore_overworld").addRequiredMod("techreborn")
                .setOreBlock(EntryModule.modid +":bedrock_tr_iridium_ore").setSize(3).setChance(12).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_tr_lead_ore_overworld", new PropagatorOreGenConfig("bedrock_tr_lead_ore_overworld").addRequiredMod("techreborn")
                .setOreBlock(EntryModule.modid +":bedrock_tr_lead_ore").setSize(6).setChance(2).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_tr_ruby_ore_overworld", new PropagatorOreGenConfig("bedrock_tr_ruby_ore_overworld").addRequiredMod("techreborn")
                .setOreBlock(EntryModule.modid +":bedrock_tr_ruby_ore").setSize(6).setChance(8).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_tr_sapphire_ore_overworld", new PropagatorOreGenConfig("bedrock_tr_sapphire_ore_overworld").addRequiredMod("techreborn")
                .setOreBlock(EntryModule.modid +":bedrock_tr_sapphire_ore").setSize(6).setChance(9).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_tr_silver_ore_overworld", new PropagatorOreGenConfig("bedrock_tr_silver_ore_overworld").addRequiredMod("techreborn")
                .setOreBlock(EntryModule.modid +":bedrock_tr_silver_ore").setSize(6).setChance(2).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_tr_tin_ore_overworld", new PropagatorOreGenConfig("bedrock_tr_tin_ore_overworld").addRequiredMod("techreborn")
                .setOreBlock(EntryModule.modid +":bedrock_tr_tin_ore").setSize(8).setChance(2).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));



        GenList.add(new Pair<>("bedrock_tr_cinnabar_ore_nether_bottom", new PropagatorOreGenConfig("bedrock_tr_cinnabar_ore_nether_bottom").addRequiredMod("techreborn")
                .setOreBlock(EntryModule.modid +":bedrock_tr_cinnabar_ore").setSize(6).setChance(11).addBiomeTag(BiomeTags.IS_NETHER).toJsonObject()));

        GenList.add(new Pair<>("bedrock_tr_cinnabar_ore_nether_top", new PropagatorOreGenConfig("bedrock_tr_cinnabar_ore_nether_top").addRequiredMod("techreborn")
                .setOreBlock(EntryModule.modid +":bedrock_tr_cinnabar_ore").setSize(6).setChance(5).addBiomeTag(BiomeTags.IS_NETHER).setLayer(top).toJsonObject()));

        GenList.add(new Pair<>("bedrock_tr_pyrite_ore_nether_bottom", new PropagatorOreGenConfig("bedrock_tr_pyrite_ore_nether_bottom").addRequiredMod("techreborn")
                .setOreBlock(EntryModule.modid +":bedrock_tr_pyrite_ore").setSize(6).setChance(10).addBiomeTag(BiomeTags.IS_NETHER).toJsonObject()));

        GenList.add(new Pair<>("bedrock_tr_pyrite_ore_nether_top", new PropagatorOreGenConfig("bedrock_tr_pyrite_ore_nether_top").addRequiredMod("techreborn")
                .setOreBlock(EntryModule.modid +":bedrock_tr_pyrite_ore").setSize(6).setChance(5).addBiomeTag(BiomeTags.IS_NETHER).setLayer(top).toJsonObject()));

        GenList.add(new Pair<>("bedrock_tr_sphalerite_ore_nether_bottom", new PropagatorOreGenConfig("bedrock_tr_sphalerite_ore_nether_bottom").addRequiredMod("techreborn")
                .setOreBlock(EntryModule.modid +":bedrock_tr_sphalerite_ore").setSize(6).setChance(12).addBiomeTag(BiomeTags.IS_NETHER).toJsonObject()));

        GenList.add(new Pair<>("bedrock_tr_sphalerite_ore_nether_top", new PropagatorOreGenConfig("bedrock_tr_sphalerite_ore_nether_top").addRequiredMod("techreborn")
                .setOreBlock(EntryModule.modid +":bedrock_tr_sphalerite_ore").setSize(6).setChance(5).addBiomeTag(BiomeTags.IS_NETHER).setLayer(top).toJsonObject()));



        GenList.add(new Pair<>("bedrock_tr_peridot_ore_end", new PropagatorOreGenConfig("bedrock_tr_peridot_ore_end").setChance(-1).setReplaceWithBlock_State(false).setTargetBlock("minecraft:end_stone").addRequiredMod("techreborn")
                .setOreBlock(EntryModule.modid +":bedrock_tr_peridot_ore").setSize(6).setCount(6).addBiomeTag(BiomeTags.IS_END).setLayer(hidden).toJsonObject()));

        GenList.add(new Pair<>("bedrock_tr_sheldonite_ore_end", new PropagatorOreGenConfig("bedrock_tr_sheldonite_ore_end").setChance(-1).setReplaceWithBlock_State(false).setTargetBlock("minecraft:end_stone").addRequiredMod("techreborn")
                .setOreBlock(EntryModule.modid +":bedrock_tr_sheldonite_ore").setSize(6).setCount(4).addBiomeTag(BiomeTags.IS_END).setLayer(hidden).toJsonObject()));

        GenList.add(new Pair<>("bedrock_tr_sodalite_ore_end", new PropagatorOreGenConfig("bedrock_tr_sodalite_ore_end").setChance(-1).setReplaceWithBlock_State(false).setTargetBlock("minecraft:end_stone").addRequiredMod("techreborn")
                .setOreBlock(EntryModule.modid +":bedrock_tr_sodalite_ore").setSize(6).setCount(4).addBiomeTag(BiomeTags.IS_END).setLayer(hidden).toJsonObject()));

        GenList.add(new Pair<>("bedrock_tr_tungsten_ore_end", new PropagatorOreGenConfig("bedrock_tr_tungsten_ore_end").setChance(-1).setReplaceWithBlock_State(false).setTargetBlock("minecraft:end_stone").addRequiredMod("techreborn")
                .setOreBlock(EntryModule.modid +":bedrock_tr_tungsten_ore").setSize(6).setCount(2).addBiomeTag(BiomeTags.IS_END).setLayer(hidden).toJsonObject()));




        GenList.add(new Pair<>("bedrock_tc_cobalt_ore_nether_bottom", new PropagatorOreGenConfig("bedrock_tc_cobalt_ore_nether_bottom").addRequiredMod("tconstruct")
                .setOreBlock(EntryModule.modid +":bedrock_tc_cobalt_ore").setSize(4).setChance(10).addBiomeTag(BiomeTags.IS_NETHER).toJsonObject()));

        GenList.add(new Pair<>("bedrock_tc_cobalt_ore_nether_top", new PropagatorOreGenConfig("bedrock_tc_cobalt_ore_nether_top").addRequiredMod("tconstruct")
                .setOreBlock(EntryModule.modid +":bedrock_tc_cobalt_ore").setSize(4).setChance(10).addBiomeTag(BiomeTags.IS_NETHER).setLayer(top).toJsonObject()));





        GenList.add(new Pair<>("bedrock_er_yellorite_ore_overworld", new PropagatorOreGenConfig("bedrock_er_yellorite_ore_overworld").addRequiredMod("bigreactors")
                .setOreBlock(EntryModule.modid +":bedrock_er_yellorite_ore").setSize(8).setChance(8).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_er_benitoite_ore_nether_bottom", new PropagatorOreGenConfig("bedrock_er_benitoite_ore_nether_bottom").addRequiredMod("bigreactors")
                .setOreBlock(EntryModule.modid +":bedrock_er_benitoite_ore").setSize(8).setChance(10).addBiomeTag(BiomeTags.IS_NETHER).toJsonObject()));

        GenList.add(new Pair<>("bedrock_er_benitoite_ore_nether_top", new PropagatorOreGenConfig("bedrock_er_benitoite_ore_nether_top").addRequiredMod("bigreactors")
                .setOreBlock(EntryModule.modid +":bedrock_er_benitoite_ore").setSize(8).setChance(10).addBiomeTag(BiomeTags.IS_NETHER).setLayer(top).toJsonObject()));

        GenList.add(new Pair<>("bedrock_er_anglesite_ore_end", new PropagatorOreGenConfig("bedrock_er_anglesite_ore_end").setChance(-1).setReplaceWithBlock_State(false).setTargetBlock("minecraft:end_stone").addRequiredMod("bigreactors")
                .setOreBlock(EntryModule.modid +":bedrock_er_anglesite_ore").setSize(8).setCount(4).addBiomeTag(BiomeTags.IS_END).setLayer(hidden).toJsonObject()));




        GenList.add(new Pair<>("bedrock_br_uranium_ore_overworld", new PropagatorOreGenConfig("bedrock_br_uranium_ore_overworld").addRequiredMod("biggerreactors")
                .setOreBlock(EntryModule.modid +":bedrock_br_uranium_ore").setSize(8).setChance(8).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));




        GenList.add(new Pair<>("bedrock_ie_aluminum_ore_overworld", new PropagatorOreGenConfig("bedrock_ie_aluminum_ore_overworld").addRequiredMod("immersiveengineering")
                .setOreBlock(EntryModule.modid +":bedrock_ie_aluminum_ore").setSize(6).setChance(3).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_ie_lead_ore_overworld", new PropagatorOreGenConfig("bedrock_ie_lead_ore_overworld").addRequiredMod("immersiveengineering")
                .setOreBlock(EntryModule.modid +":bedrock_ie_lead_ore").setSize(8).setChance(5).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_ie_silver_ore_overworld", new PropagatorOreGenConfig("bedrock_ie_silver_ore_overworld").addRequiredMod("immersiveengineering")
                .setOreBlock(EntryModule.modid +":bedrock_ie_silver_ore").setSize(9).setChance(6).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_ie_nickel_ore_overworld", new PropagatorOreGenConfig("bedrock_ie_nickel_ore_overworld").addRequiredMod("immersiveengineering")
                .setOreBlock(EntryModule.modid +":bedrock_ie_nickel_ore").setSize(7).setChance(5).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_ie_uranium_ore_overworld", new PropagatorOreGenConfig("bedrock_ie_uranium_ore_overworld").addRequiredMod("immersiveengineering")
                .setOreBlock(EntryModule.modid +":bedrock_ie_uranium_ore").setSize(4).setChance(7).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));




        GenList.add(new Pair<>("bedrock_mm_adamantite_ore_overworld", new PropagatorOreGenConfig("bedrock_mm_adamantite_ore_overworld").addRequiredMod("mythicmetals")
                .setOreBlock(EntryModule.modid +":bedrock_mm_adamantite_ore").setSize(5).setChance(16).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mm_aquarium_ore_overworld", new PropagatorOreGenConfig("bedrock_mm_aquarium_ore_overworld").addRequiredMod("mythicmetals")
                .setOreBlock(EntryModule.modid +":bedrock_mm_aquarium_ore").setSize(9).setChance(4).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mm_banglum_ore_overworld", new PropagatorOreGenConfig("bedrock_mm_banglum_ore_overworld").addRequiredMod("mythicmetals")
                .setOreBlock(EntryModule.modid +":bedrock_mm_banglum_ore").setSize(6).setChance(4).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mm_carmot_ore_overworld", new PropagatorOreGenConfig("bedrock_mm_carmot_ore_overworld").addRequiredMod("mythicmetals")
                .setOreBlock(EntryModule.modid +":bedrock_mm_carmot_ore").setSize(4).setChance(8).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mm_kyber_ore_overworld", new PropagatorOreGenConfig("bedrock_mm_kyber_ore_overworld").addRequiredMod("mythicmetals")
                .setOreBlock(EntryModule.modid +":bedrock_mm_kyber_ore").setSize(3).setChance(13).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mm_manganese_ore_overworld", new PropagatorOreGenConfig("bedrock_mm_manganese_ore_overworld").addRequiredMod("mythicmetals")
                .setOreBlock(EntryModule.modid +":bedrock_mm_manganese_ore").setSize(9).setChance(9).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mm_morkite_ore_overworld", new PropagatorOreGenConfig("bedrock_mm_morkite_ore_overworld").addRequiredMod("mythicmetals")
                .setOreBlock(EntryModule.modid +":bedrock_mm_morkite_ore").setSize(11).setChance(5).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mm_mythril_ore_overworld", new PropagatorOreGenConfig("bedrock_mm_mythril_ore_overworld").addRequiredMod("mythicmetals")
                .setOreBlock(EntryModule.modid +":bedrock_mm_mythril_ore").setSize(5).setChance(3).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mm_orichalcum_ore_overworld", new PropagatorOreGenConfig("bedrock_mm_orichalcum_ore_overworld").addRequiredMod("mythicmetals")
                .setOreBlock(EntryModule.modid +":bedrock_mm_orichalcum_ore").setSize(5).setChance(14).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mm_osmium_ore_overworld", new PropagatorOreGenConfig("bedrock_mm_osmium_ore_overworld").addRequiredMod("mythicmetals")
                .setOreBlock(EntryModule.modid +":bedrock_mm_osmium_ore").setSize(6).setChance(3).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mm_platinum_ore_overworld", new PropagatorOreGenConfig("bedrock_mm_platinum_ore_overworld").addRequiredMod("mythicmetals")
                .setOreBlock(EntryModule.modid +":bedrock_mm_platinum_ore").setSize(6).setChance(7).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mm_prometheum_ore_overworld", new PropagatorOreGenConfig("bedrock_mm_prometheum_ore_overworld").addRequiredMod("mythicmetals")
                .setOreBlock(EntryModule.modid +":bedrock_mm_prometheum_ore").setSize(6).setChance(4).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mm_quadrillum_ore_overworld", new PropagatorOreGenConfig("bedrock_mm_quadrillum_ore_overworld").addRequiredMod("mythicmetals")
                .setOreBlock(EntryModule.modid +":bedrock_mm_quadrillum_ore").setSize(7).setChance(8).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mm_runite_ore_overworld", new PropagatorOreGenConfig("bedrock_mm_runite_ore_overworld").addRequiredMod("mythicmetals")
                .setOreBlock(EntryModule.modid +":bedrock_mm_runite_ore").setSize(4).setChance(15).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mm_silver_ore_overworld", new PropagatorOreGenConfig("bedrock_mm_silver_ore_overworld").addRequiredMod("mythicmetals")
                .setOreBlock(EntryModule.modid +":bedrock_mm_silver_ore").setSize(8).setChance(4).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mm_starrite_ore_overworld", new PropagatorOreGenConfig("bedrock_mm_starrite_ore_overworld").addRequiredMod("mythicmetals")
                .setOreBlock(EntryModule.modid +":bedrock_mm_starrite_ore").setSize(4).setChance(16).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mm_tin_ore_overworld", new PropagatorOreGenConfig("bedrock_mm_tin_ore_overworld").addRequiredMod("mythicmetals")
                .setOreBlock(EntryModule.modid +":bedrock_mm_tin_ore").setSize(8).setChance(3).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mm_unobtainium_ore_overworld", new PropagatorOreGenConfig("bedrock_mm_unobtainium_ore_overworld").addRequiredMod("mythicmetals")
                .setOreBlock(EntryModule.modid +":bedrock_mm_unobtainium_ore").setSize(3).setChance(16).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));



        GenList.add(new Pair<>("bedrock_mm_nether_banglum_ore_nether_bottom", new PropagatorOreGenConfig("bedrock_mm_nether_banglum_ore_nether_bottom").addRequiredMod("mythicmetals")
                .setOreBlock(EntryModule.modid +":bedrock_mm_nether_banglum_ore").setSize(8).setChance(5).addBiomeTag(BiomeTags.IS_NETHER).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mm_nether_banglum_ore_nether_top", new PropagatorOreGenConfig("bedrock_mm_nether_banglum_ore_nether_top").addRequiredMod("mythicmetals")
                .setOreBlock(EntryModule.modid +":bedrock_mm_nether_banglum_ore").setSize(8).setChance(4).addBiomeTag(BiomeTags.IS_NETHER).setLayer(top).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mm_midas_gold_ore_nether_bottom", new PropagatorOreGenConfig("bedrock_mm_midas_gold_ore_nether_bottom").addRequiredMod("mythicmetals")
                .setOreBlock(EntryModule.modid +":bedrock_mm_midas_gold_ore").setSize(7).setChance(4).addBiomeTag(BiomeTags.IS_NETHER).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mm_midas_gold_ore_nether_top", new PropagatorOreGenConfig("bedrock_mm_midas_gold_ore_nether_top").addRequiredMod("mythicmetals")
                .setOreBlock(EntryModule.modid +":bedrock_mm_midas_gold_ore").setSize(7).setChance(3).addBiomeTag(BiomeTags.IS_NETHER).setLayer(top).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mm_palladium_ore_nether_bottom", new PropagatorOreGenConfig("bedrock_mm_palladium_ore_nether_bottom").addRequiredMod("mythicmetals")
                .setOreBlock(EntryModule.modid +":bedrock_mm_palladium_ore").setSize(5).setChance(7).addBiomeTag(BiomeTags.IS_NETHER).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mm_palladium_ore_nether_top", new PropagatorOreGenConfig("bedrock_mm_palladium_ore_nether_top").addRequiredMod("mythicmetals")
                .setOreBlock(EntryModule.modid +":bedrock_mm_palladium_ore").setSize(5).setChance(8).addBiomeTag(BiomeTags.IS_NETHER).setLayer(top).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mm_stormyx_ore_nether_bottom", new PropagatorOreGenConfig("bedrock_mm_stormyx_ore_nether_bottom").addRequiredMod("mythicmetals")
                .setOreBlock(EntryModule.modid +":bedrock_mm_stormyx_ore").setSize(8).setChance(7).addBiomeTag(BiomeTags.IS_NETHER).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mm_stormyx_ore_nether_top", new PropagatorOreGenConfig("bedrock_mm_stormyx_ore_nether_top").addRequiredMod("mythicmetals")
                .setOreBlock(EntryModule.modid +":bedrock_mm_stormyx_ore").setSize(8).setChance(9).addBiomeTag(BiomeTags.IS_NETHER).setLayer(top).toJsonObject()));



        GenList.add(new Pair<>("bedrock_mm_starrite_ore_end", new PropagatorOreGenConfig("bedrock_mm_starrite_ore_end").setChance(-1).setReplaceWithBlock_State(false).setTargetBlock("minecraft:end_stone").addRequiredMod("mythicmetals")
                .setOreBlock(EntryModule.modid +":bedrock_mm_starrite_ore").setSize(7).setCount(1).addBiomeTag(BiomeTags.IS_END).setLayer(hidden).toJsonObject()));




        GenList.add(new Pair<>("bedrock_cr_zinc_ore_overworld", new PropagatorOreGenConfig("bedrock_cr_zinc_ore_overworld").addRequiredMod("create")
                .setOreBlock(EntryModule.modid +":bedrock_cr_zinc_ore").setSize(12).setChance(3).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));




        GenList.add(new Pair<>("bedrock_mk_osmium_ore_overworld", new PropagatorOreGenConfig("bedrock_mk_osmium_ore_overworld").addRequiredMod("mekanism")
                .setOreBlock(EntryModule.modid +":bedrock_mk_osmium_ore").setSize(9).setChance(5).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mk_fluorite_ore_overworld", new PropagatorOreGenConfig("bedrock_mk_fluorite_ore_overworld").addRequiredMod("mekanism")
                .setOreBlock(EntryModule.modid +":bedrock_mk_fluorite_ore").setSize(8).setChance(12).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mk_lead_ore_overworld", new PropagatorOreGenConfig("bedrock_mk_lead_ore_overworld").addRequiredMod("mekanism")
                .setOreBlock(EntryModule.modid +":bedrock_mk_lead_ore").setSize(8).setChance(4).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mk_tin_ore_overworld", new PropagatorOreGenConfig("bedrock_mk_tin_ore_overworld").addRequiredMod("mekanism")
                .setOreBlock(EntryModule.modid +":bedrock_mk_tin_ore").setSize(10).setChance(4).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_mk_uranium_ore_overworld", new PropagatorOreGenConfig("bedrock_mk_uranium_ore_overworld").addRequiredMod("mekanism")
                .setOreBlock(EntryModule.modid +":bedrock_mk_uranium_ore").setSize(8).setChance(10).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));



        GenList.add(new Pair<>("bedrock_coal_ore_undergarden", new PropagatorOreGenConfig("bedrock_coal_ore_undergarden").addRequiredMod("undergarden")
                .setOreBlock(EntryModule.modid +":bedrock_coal_ore").setSize(20).setChance(3).addBiomeTag(undergarden).toJsonObject()));

        GenList.add(new Pair<>("bedrock_iron_ore_undergarden", new PropagatorOreGenConfig("bedrock_iron_ore_undergarden").addRequiredMod("undergarden")
                .setOreBlock(EntryModule.modid +":bedrock_iron_ore").setSize(9).setChance(5).addBiomeTag(undergarden).toJsonObject()));

        GenList.add(new Pair<>("bedrock_gold_ore_undergarden", new PropagatorOreGenConfig("bedrock_gold_ore_undergarden").addRequiredMod("undergarden")
                .setOreBlock(EntryModule.modid +":bedrock_gold_ore").setSize(9).setChance(7).addBiomeTag(undergarden).toJsonObject()));

        GenList.add(new Pair<>("bedrock_diamond_ore_undergarden", new PropagatorOreGenConfig("bedrock_diamond_ore_undergarden").addRequiredMod("undergarden")
                .setOreBlock(EntryModule.modid +":bedrock_diamond_ore").setSize(8).setChance(12).addBiomeTag(undergarden).toJsonObject()));



        GenList.add(new Pair<>("bedrock_ug_cloggrum_ore_undergarden", new PropagatorOreGenConfig("bedrock_ug_cloggrum_ore_undergarden").addRequiredMod("undergarden")
                .setOreBlock(EntryModule.modid +":bedrock_ug_cloggrum_ore").setSize(9).setChance(5).addBiomeTag(undergarden).toJsonObject()));

        GenList.add(new Pair<>("bedrock_ug_froststeel_ore_undergarden", new PropagatorOreGenConfig("bedrock_ug_froststeel_ore_undergarden").addRequiredMod("undergarden")
                .setOreBlock(EntryModule.modid +":bedrock_ug_froststeel_ore").setSize(9).setChance(7).addBiomeTag(undergarden).toJsonObject()));

        GenList.add(new Pair<>("bedrock_ug_utherium_ore_undergarden", new PropagatorOreGenConfig("bedrock_ug_utherium_ore_undergarden").addRequiredMod("undergarden")
                .setOreBlock(EntryModule.modid +":bedrock_ug_utherium_ore").setSize(8).setChance(12).addBiomeTag(undergarden).toJsonObject()));

        GenList.add(new Pair<>("bedrock_ug_regalium_ore_undergarden", new PropagatorOreGenConfig("bedrock_ug_regalium_ore_undergarden").addRequiredMod("undergarden")
                .setOreBlock(EntryModule.modid +":bedrock_ug_regalium_ore").setSize(7).setChance(13).addBiomeTag(undergarden).toJsonObject()));




        GenList.add(new Pair<>("bedrock_iron_ore_moon", new PropagatorOreGenConfig("bedrock_iron_ore_moon").addRequiredMod("beyond_earth")
                .setOreBlock(EntryModule.modid +":bedrock_iron_ore").setSize(11).setChance(8).addBiomeTag(moon).toJsonObject()));

        GenList.add(new Pair<>("bedrock_be_desh_ore_moon", new PropagatorOreGenConfig("bedrock_be_desh_ore_moon").addRequiredMod("beyond_earth")
                .setOreBlock(EntryModule.modid +":bedrock_be_desh_ore").setSize(9).setChance(14).addBiomeTag(moon).toJsonObject()));

        GenList.add(new Pair<>("bedrock_be_cheese_ore_moon", new PropagatorOreGenConfig("bedrock_be_cheese_ore_moon").addRequiredMod("beyond_earth")
                .setOreBlock(EntryModule.modid +":bedrock_be_cheese_ore").setSize(10).setChance(7).addBiomeTag(moon).toJsonObject()));

        GenList.add(new Pair<>("bedrock_be_ice_shard_ore_moon", new PropagatorOreGenConfig("bedrock_be_ice_shard_ore_moon").addRequiredMod("beyond_earth")
                .setOreBlock(EntryModule.modid +":bedrock_be_ice_shard_ore").setSize(10).setChance(10).addBiomeTag(moon).toJsonObject()));



        GenList.add(new Pair<>("bedrock_iron_ore_mars", new PropagatorOreGenConfig("bedrock_iron_ore_mars").addRequiredMod("beyond_earth")
                .setOreBlock(EntryModule.modid +":bedrock_iron_ore").setSize(11).setChance(8).addBiomeTag(mars).toJsonObject()));

        GenList.add(new Pair<>("bedrock_diamond_ore_mars", new PropagatorOreGenConfig("bedrock_diamond_ore_mars").addRequiredMod("beyond_earth")
                .setOreBlock(EntryModule.modid +":bedrock_diamond_ore").setSize(7).setChance(10).addBiomeTag(mars).toJsonObject()));

        GenList.add(new Pair<>("bedrock_be_ostrum_ore_mars", new PropagatorOreGenConfig("bedrock_be_ostrum_ore_mars").addRequiredMod("beyond_earth")
                .setOreBlock(EntryModule.modid +":bedrock_be_ostrum_ore").setSize(8).setChance(10).addBiomeTag(mars).toJsonObject()));

        GenList.add(new Pair<>("bedrock_be_ice_shard_ore_mars", new PropagatorOreGenConfig("bedrock_be_ice_shard_ore_mars").addRequiredMod("beyond_earth")
                .setOreBlock(EntryModule.modid +":bedrock_be_ice_shard_ore").setSize(10).setChance(7).addBiomeTag(mars).toJsonObject()));



        GenList.add(new Pair<>("bedrock_coal_ore_venus", new PropagatorOreGenConfig("bedrock_coal_ore_venus").addRequiredMod("beyond_earth")
                .setOreBlock(EntryModule.modid +":bedrock_coal_ore").setSize(20).setChance(3).addBiomeTag(venus).toJsonObject()));

        GenList.add(new Pair<>("bedrock_diamond_ore_venus", new PropagatorOreGenConfig("bedrock_diamond_ore_venus").addRequiredMod("beyond_earth")
                .setOreBlock(EntryModule.modid +":bedrock_diamond_ore").setSize(7).setChance(10).addBiomeTag(venus).toJsonObject()));

        GenList.add(new Pair<>("bedrock_gold_ore_venus", new PropagatorOreGenConfig("bedrock_gold_ore_venus").addRequiredMod("beyond_earth")
                .setOreBlock(EntryModule.modid +":bedrock_gold_ore").setSize(9).setChance(7).addBiomeTag(venus).toJsonObject()));

        GenList.add(new Pair<>("bedrock_be_calorite_ore_venus", new PropagatorOreGenConfig("bedrock_be_calorite_ore_venus").addRequiredMod("beyond_earth")
                .setOreBlock(EntryModule.modid +":bedrock_be_calorite_ore").setSize(8).setChance(16).addBiomeTag(venus).toJsonObject()));



        GenList.add(new Pair<>("bedrock_coal_ore_glacio", new PropagatorOreGenConfig("bedrock_coal_ore_glacio").addRequiredMod("beyond_earth")
                .setOreBlock(EntryModule.modid +":bedrock_coal_ore").setSize(20).setChance(3).addBiomeTag(glacio).toJsonObject()));

        GenList.add(new Pair<>("bedrock_copper_ore_glacio", new PropagatorOreGenConfig("bedrock_copper_ore_glacio").addRequiredMod("beyond_earth")
                .setOreBlock(EntryModule.modid +":bedrock_copper_ore").setSize(13).setChance(3).addBiomeTag(glacio).toJsonObject()));

        GenList.add(new Pair<>("bedrock_iron_ore_glacio", new PropagatorOreGenConfig("bedrock_iron_ore_glacio").addRequiredMod("beyond_earth")
                .setOreBlock(EntryModule.modid +":bedrock_iron_ore").setSize(11).setChance(8).addBiomeTag(glacio).toJsonObject()));

        GenList.add(new Pair<>("bedrock_lapis_ore_glacio", new PropagatorOreGenConfig("bedrock_lapis_ore_glacio").addRequiredMod("beyond_earth")
                .setOreBlock(EntryModule.modid +":bedrock_lapis_ore").setSize(9).setChance(9).addBiomeTag(glacio).toJsonObject()));

        GenList.add(new Pair<>("bedrock_be_ice_shard_ore_glacio", new PropagatorOreGenConfig("bedrock_be_ice_shard_ore_glacio").addRequiredMod("beyond_earth")
                .setOreBlock(EntryModule.modid +":bedrock_be_ice_shard_ore").setSize(12).setChance(5).addBiomeTag(glacio).toJsonObject()));



        GenList.add(new Pair<>("bedrock_iron_ore_mercury", new PropagatorOreGenConfig("bedrock_iron_ore_mercury").addRequiredMod("beyond_earth")
                .setOreBlock(EntryModule.modid +":bedrock_iron_ore").setSize(1).setChance(4).addBiomeTag(mercury).toJsonObject()));




        GenList.add(new Pair<>("bedrock_ic_tin_ore_overworld", new PropagatorOreGenConfig("bedrock_ic_tin_ore_overworld").addRequiredMod("ftbic")
                .setOreBlock(EntryModule.modid +":bedrock_ic_tin_ore").setSize(8).setChance(2).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_ic_lead_ore_overworld", new PropagatorOreGenConfig("bedrock_ic_lead_ore_overworld").addRequiredMod("ftbic")
                .setOreBlock(EntryModule.modid +":bedrock_ic_lead_ore").setSize(8).setChance(5).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_ic_aluminum_ore_overworld", new PropagatorOreGenConfig("bedrock_ic_aluminum_ore_overworld").addRequiredMod("ftbic")
                .setOreBlock(EntryModule.modid +":bedrock_ic_aluminum_ore").setSize(6).setChance(3).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_ic_iridium_ore_overworld", new PropagatorOreGenConfig("bedrock_ic_iridium_ore_overworld").addRequiredMod("ftbic")
                .setOreBlock(EntryModule.modid +":bedrock_ic_iridium_ore").setSize(3).setChance(12).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_ic_uranium_ore_overworld", new PropagatorOreGenConfig("bedrock_ic_uranium_ore_overworld").addRequiredMod("ftbic")
                .setOreBlock(EntryModule.modid +":bedrock_ic_uranium_ore").setSize(3).setChance(10).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));




        GenList.add(new Pair<>("bedrock_ma_soulstone_ore_overworld", new PropagatorOreGenConfig("bedrock_ma_soulstone_ore_overworld").addRequiredMod("malum")
                .setOreBlock(EntryModule.modid +":bedrock_ma_soulstone_ore").setSize(9).setChance(7).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_ma_brilliance_ore_overworld", new PropagatorOreGenConfig("bedrock_ma_brilliance_ore_overworld").addRequiredMod("malum")
                .setOreBlock(EntryModule.modid +":bedrock_ma_brilliance_ore").setSize(6).setChance(15).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_ma_blazing_quartz_ore_nether", new PropagatorOreGenConfig("bedrock_ma_blazing_quartz_ore_nether").addRequiredMod("malum")
                .setOreBlock(EntryModule.modid +":bedrock_ma_blazing_quartz_ore").setSize(8).setChance(2).addBiomeTag(BiomeTags.IS_NETHER).toJsonObject()));




        GenList.add(new Pair<>("bedrock_th_niter_ore_overworld", new PropagatorOreGenConfig("bedrock_th_niter_ore_overworld").addRequiredMod("thermal")
                .setOreBlock(EntryModule.modid +":bedrock_th_niter_ore").setSize(7).setChance(9).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_th_sulfur_ore_overworld", new PropagatorOreGenConfig("bedrock_th_sulfur_ore_overworld").addRequiredMod("thermal")
                .setOreBlock(EntryModule.modid +":bedrock_th_sulfur_ore").setSize(7).setChance(9).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_th_tin_ore_overworld", new PropagatorOreGenConfig("bedrock_th_tin_ore_overworld").addRequiredMod("thermal")
                .setOreBlock(EntryModule.modid +":bedrock_th_tin_ore").setSize(9).setChance(3).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_th_lead_ore_overworld", new PropagatorOreGenConfig("bedrock_th_lead_ore_overworld").addRequiredMod("thermal")
                .setOreBlock(EntryModule.modid +":bedrock_th_lead_ore").setSize(8).setChance(3).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_th_silver_ore_overworld", new PropagatorOreGenConfig("bedrock_th_silver_ore_overworld").addRequiredMod("thermal")
                .setOreBlock(EntryModule.modid +":bedrock_th_silver_ore").setSize(8).setChance(6).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_th_nickel_ore_overworld", new PropagatorOreGenConfig("bedrock_th_nickel_ore_overworld").addRequiredMod("thermal")
                .setOreBlock(EntryModule.modid +":bedrock_th_nickel_ore").setSize(8).setChance(6).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_th_apatite_ore_overworld", new PropagatorOreGenConfig("bedrock_th_apatite_ore_overworld").addRequiredMod("thermal")
                .setOreBlock(EntryModule.modid +":bedrock_th_apatite_ore").setSize(9).setChance(6).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_th_cinnabar_ore_overworld", new PropagatorOreGenConfig("bedrock_th_cinnabar_ore_overworld").addRequiredMod("thermal")
                .setOreBlock(EntryModule.modid +":bedrock_th_cinnabar_ore").setSize(5).setChance(13).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));

        GenList.add(new Pair<>("bedrock_th_oil_ore_overworld", new PropagatorOreGenConfig("bedrock_th_oil_ore_overworld").addRequiredMod("thermal")
                .setOreBlock(EntryModule.modid +":bedrock_th_oil_ore").setSize(24).setChance(9).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));




        GenList.add(new Pair<>("bedrock_pw_uraninite_ore_overworld", new PropagatorOreGenConfig("bedrock_pw_uraninite_ore_overworld").addRequiredMod("powah")
                .setOreBlock(EntryModule.modid +":bedrock_pw_uraninite_ore").setSize(5).setChance(4).addBiomeTag(BiomeTags.IS_OVERWORLD).toJsonObject()));




        JsonConfig.writeJsonFiles(GenList, JsonConfig.gen_config_path);
    }
}

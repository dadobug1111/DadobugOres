package no.dadobug.oremod.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import no.dadobug.oremod.EntryModule;

import java.util.HashMap;
import java.util.Map;

@Config(name = EntryModule.modid + "\\" + "bedrock_ores_remap")
public class RemapConfig implements ConfigData {

    public Map<String, String> targetMap = genDefaultMap();

    public static Map<String, String> genDefaultMap(){
        Map<String, String> targetMap = new HashMap();


        targetMap.put("bedrock_fractured", EntryModule.modid + ":" + "bedrock_fractured");
        targetMap.put("bedrock_hollow", EntryModule.modid + ":" + "bedrock_hollow");


        targetMap.put("bedrock_coal_ore", EntryModule.modid + ":" + "bedrock_coal_ore");
        targetMap.put("bedrock_iron_ore", EntryModule.modid + ":" + "bedrock_iron_ore");
        targetMap.put("bedrock_gold_ore", EntryModule.modid + ":" + "bedrock_gold_ore");
        targetMap.put("bedrock_diamond_ore", EntryModule.modid + ":" + "bedrock_diamond_ore");
        targetMap.put("bedrock_redstone_ore", EntryModule.modid + ":" + "bedrock_redstone_ore");
        targetMap.put("bedrock_copper_ore", EntryModule.modid + ":" + "bedrock_copper_ore");
        targetMap.put("bedrock_lapis_ore", EntryModule.modid + ":" + "bedrock_lapis_ore");
        targetMap.put("bedrock_emerald_ore", EntryModule.modid + ":" + "bedrock_emerald_ore");

        targetMap.put("bedrock_cookie_ore", EntryModule.modid + ":" + "bedrock_cookie_ore");
        targetMap.put("bedrock_milk_ore", EntryModule.modid + ":" + "bedrock_milk_ore");

        targetMap.put("bedrock_water_ore", EntryModule.modid + ":" + "bedrock_water_ore");
        targetMap.put("bedrock_lava_ore", EntryModule.modid + ":" + "bedrock_lava_ore");
        targetMap.put("bedrock_xp_leak", EntryModule.modid + ":" + "bedrock_xp_leak");

        targetMap.put("bedrock_nether_gold_ore", EntryModule.modid + ":" + "bedrock_nether_gold_ore");
        targetMap.put("bedrock_nether_quartz_ore", EntryModule.modid + ":" + "bedrock_nether_quartz_ore");
        targetMap.put("bedrock_ancient_debris_ore", EntryModule.modid + ":" + "bedrock_ancient_debris_ore");


        targetMap.put("bedrock_bauxite_ore", EntryModule.modid + ":" + "bedrock_tr_bauxite_ore");
        targetMap.put("bedrock_galena_ore", EntryModule.modid + ":" + "bedrock_tr_galena_ore");
        targetMap.put("bedrock_iridium_ore", EntryModule.modid + ":" + "bedrock_tr_iridium_ore");
        targetMap.put("bedrock_lead_ore", EntryModule.modid + ":" + "bedrock_tr_lead_ore");
        targetMap.put("bedrock_ruby_ore", EntryModule.modid + ":" + "bedrock_tr_ruby_ore");
        targetMap.put("bedrock_sapphire_ore", EntryModule.modid + ":" + "bedrock_tr_sapphire_ore");
        targetMap.put("bedrock_silver_ore", EntryModule.modid + ":" + "bedrock_tr_silver_ore");
        targetMap.put("bedrock_tin_ore", EntryModule.modid + ":" + "bedrock_tr_tin_ore");

        targetMap.put("bedrock_cinnabar_ore", EntryModule.modid + ":" + "bedrock_tr_cinnabar_ore");
        targetMap.put("bedrock_pyrite_ore", EntryModule.modid + ":" + "bedrock_tr_pyrite_ore");
        targetMap.put("bedrock_sphalerite_ore", EntryModule.modid + ":" + "bedrock_tr_sphalerite_ore");

        targetMap.put("bedrock_peridot_ore", EntryModule.modid + ":" + "bedrock_tr_peridot_ore");
        targetMap.put("bedrock_sheldonite_ore", EntryModule.modid + ":" + "bedrock_tr_sheldonite_ore");
        targetMap.put("bedrock_sodalite_ore", EntryModule.modid + ":" + "bedrock_tr_sodalite_ore");
        targetMap.put("bedrock_tungsten_ore", EntryModule.modid + ":" + "bedrock_tr_tungsten_ore");


        targetMap.put("bedrock_tc_cobalt_ore", EntryModule.modid + ":" + "bedrock_tc_cobalt_ore");


        targetMap.put("bedrock_er_yellorite_ore", EntryModule.modid + ":" + "bedrock_er_yellorite_ore");
        targetMap.put("bedrock_er_anglesite_ore", EntryModule.modid + ":" + "bedrock_er_anglesite_ore");
        targetMap.put("bedrock_er_benitoite_ore", EntryModule.modid + ":" + "bedrock_er_benitoite_ore");


        targetMap.put("bedrock_br_uranium_ore", EntryModule.modid + ":" + "bedrock_br_uranium_ore");


        targetMap.put("bedrock_ae_certus_quartz_ore", "minecraft:bedrock");


        targetMap.put("bedrock_ie_aluminum_ore", EntryModule.modid + ":" + "bedrock_ie_aluminum_ore");
        targetMap.put("bedrock_ie_lead_ore", EntryModule.modid + ":" + "bedrock_ie_lead_ore");
        targetMap.put("bedrock_ie_silver_ore", EntryModule.modid + ":" + "bedrock_ie_silver_ore");
        targetMap.put("bedrock_ie_nickel_ore", EntryModule.modid + ":" + "bedrock_ie_nickel_ore");
        targetMap.put("bedrock_ie_uranium_ore", EntryModule.modid + ":" + "bedrock_ie_uranium_ore");


        targetMap.put("bedrock_mm_adamantite_ore", EntryModule.modid + ":" + "bedrock_mm_adamantite_ore");
        targetMap.put("bedrock_mm_aquarium_ore", EntryModule.modid + ":" + "bedrock_mm_aquarium_ore");
        targetMap.put("bedrock_mm_banglum_ore", EntryModule.modid + ":" + "bedrock_mm_banglum_ore");
        targetMap.put("bedrock_mm_carmot_ore", EntryModule.modid + ":" + "bedrock_mm_carmot_ore");
        targetMap.put("bedrock_mm_kyber_ore", EntryModule.modid + ":" + "bedrock_mm_kyber_ore");
        targetMap.put("bedrock_mm_manganese_ore", EntryModule.modid + ":" + "bedrock_mm_manganese_ore");
        targetMap.put("bedrock_mm_morkite_ore", EntryModule.modid + ":" + "bedrock_mm_morkite_ore");
        targetMap.put("bedrock_mm_mythril_ore", EntryModule.modid + ":" + "bedrock_mm_mythril_ore");
        targetMap.put("bedrock_mm_orichalcum_ore", EntryModule.modid + ":" + "bedrock_mm_orichalcum_ore");
        targetMap.put("bedrock_mm_osmium_ore", EntryModule.modid + ":" + "bedrock_mm_osmium_ore");
        targetMap.put("bedrock_mm_platinum_ore", EntryModule.modid + ":" + "bedrock_mm_platinum_ore");
        targetMap.put("bedrock_mm_prometheum_ore", EntryModule.modid + ":" + "bedrock_mm_prometheum_ore");
        targetMap.put("bedrock_mm_quadrillum_ore", EntryModule.modid + ":" + "bedrock_mm_quadrillum_ore");
        targetMap.put("bedrock_mm_runite_ore", EntryModule.modid + ":" + "bedrock_mm_runite_ore");
        targetMap.put("bedrock_mm_silver_ore", EntryModule.modid + ":" + "bedrock_mm_silver_ore");
        targetMap.put("bedrock_mm_starrite_ore", EntryModule.modid + ":" + "bedrock_mm_starrite_ore");
        targetMap.put("bedrock_mm_tin_ore", EntryModule.modid + ":" + "bedrock_mm_tin_ore");
        targetMap.put("bedrock_mm_unobtainium_ore", EntryModule.modid + ":" + "bedrock_mm_unobtainium_ore");

        targetMap.put("bedrock_mm_nether_banglum_ore", EntryModule.modid + ":" + "bedrock_mm_nether_banglum_ore");
        targetMap.put("bedrock_mm_midas_gold_ore", EntryModule.modid + ":" + "bedrock_mm_midas_gold_ore");
        targetMap.put("bedrock_mm_palladium_ore", EntryModule.modid + ":" + "bedrock_mm_palladium_ore");
        targetMap.put("bedrock_mm_stormyx_ore", EntryModule.modid + ":" + "bedrock_mm_stormyx_ore");


        targetMap.put("bedrock_cr_zinc_ore", EntryModule.modid + ":" + "bedrock_cr_zinc_ore");


        targetMap.put("bedrock_mk_osmium_ore", EntryModule.modid + ":" + "bedrock_mk_osmium_ore");
        targetMap.put("bedrock_mk_fluorite_ore", EntryModule.modid + ":" + "bedrock_mk_fluorite_ore");
        targetMap.put("bedrock_mk_lead_ore", EntryModule.modid + ":" + "bedrock_mk_lead_ore");
        targetMap.put("bedrock_mk_tin_ore", EntryModule.modid + ":" + "bedrock_mk_tin_ore");
        targetMap.put("bedrock_mk_uranium_ore", EntryModule.modid + ":" + "bedrock_mk_uranium_ore");


        targetMap.put("bedrock_ug_cloggrum_ore", EntryModule.modid + ":" + "bedrock_ug_cloggrum_ore");
        targetMap.put("bedrock_ug_froststeel_ore", EntryModule.modid + ":" + "bedrock_ug_froststeel_ore");
        targetMap.put("bedrock_ug_utherium_ore", EntryModule.modid + ":" + "bedrock_ug_utherium_ore");
        targetMap.put("bedrock_ug_regalium_ore", EntryModule.modid + ":" + "bedrock_ug_regalium_ore");


        targetMap.put("bedrock_be_desh_ore", EntryModule.modid + ":" + "bedrock_be_desh_ore");
        targetMap.put("bedrock_be_cheese_ore", EntryModule.modid + ":" + "bedrock_be_cheese_ore");
        targetMap.put("bedrock_be_ice_shard_ore", EntryModule.modid + ":" + "bedrock_be_ice_shard_ore");
        targetMap.put("bedrock_be_ostrum_ore", EntryModule.modid + ":" + "bedrock_be_ostrum_ore");
        targetMap.put("bedrock_be_calorite_ore", EntryModule.modid + ":" + "bedrock_be_calorite_ore");


        targetMap.put("bedrock_ic_tin_ore", EntryModule.modid + ":" + "bedrock_ic_tin_ore");
        targetMap.put("bedrock_ic_lead_ore", EntryModule.modid + ":" + "bedrock_ic_lead_ore");
        targetMap.put("bedrock_ic_aluminum_ore", EntryModule.modid + ":" + "bedrock_ic_aluminum_ore");
        targetMap.put("bedrock_ic_iridium_ore", EntryModule.modid + ":" + "bedrock_ic_iridium_ore");
        targetMap.put("bedrock_ic_uranium_ore", EntryModule.modid + ":" + "bedrock_ic_uranium_ore");


        targetMap.put("bedrock_ma_soulstone_ore", EntryModule.modid + ":" + "bedrock_ma_soulstone_ore");
        targetMap.put("bedrock_ma_brilliance_ore", EntryModule.modid + ":" + "bedrock_ma_brilliance_ore");
        targetMap.put("bedrock_ma_blazing_quartz_ore", EntryModule.modid + ":" + "bedrock_ma_blazing_quartz_ore");


        targetMap.put("bedrock_th_niter_ore", EntryModule.modid + ":" + "bedrock_th_niter_ore");
        targetMap.put("bedrock_th_sulfur_ore", EntryModule.modid + ":" + "bedrock_th_sulfur_ore");
        targetMap.put("bedrock_th_tin_ore", EntryModule.modid + ":" + "bedrock_th_tin_ore");
        targetMap.put("bedrock_th_lead_ore", EntryModule.modid + ":" + "bedrock_th_lead_ore");
        targetMap.put("bedrock_th_silver_ore", EntryModule.modid + ":" + "bedrock_th_silver_ore");
        targetMap.put("bedrock_th_nickel_ore", EntryModule.modid + ":" + "bedrock_th_nickel_ore");
        targetMap.put("bedrock_th_apatite_ore", EntryModule.modid + ":" + "bedrock_th_apatite_ore");
        targetMap.put("bedrock_th_cinnabar_ore", EntryModule.modid + ":" + "bedrock_th_cinnabar_ore");
        targetMap.put("bedrock_th_oil_ore", EntryModule.modid + ":" + "bedrock_th_oil_ore");


        targetMap.put("bedrock_pw_uraninite_ore", EntryModule.modid + ":" + "bedrock_pw_uraninite_ore");

        return targetMap;
    }
}

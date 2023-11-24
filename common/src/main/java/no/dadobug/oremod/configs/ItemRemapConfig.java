package no.dadobug.oremod.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import no.dadobug.oremod.EntryModule;

import java.util.HashMap;
import java.util.Map;

@Config(name = EntryModule.modid + "\\" + "bedrock_ores_item_remap")
public class ItemRemapConfig implements ConfigData {

    public Map<String, String> targetMap = genDefaultMap();

    public static Map<String, String> genDefaultMap(){
        Map<String, String> targetMap = new HashMap();


        targetMap.put("regenerative_fractured", EntryModule.modid + ":" + "regenerative_fractured");
        //targetMap.put("regenerative_hollow", EntryModule.modid + ":" + "regenerative_hollow");


        targetMap.put("regenerative_coal", EntryModule.modid + ":" + "regenerative_coal");
        targetMap.put("regenerative_iron", EntryModule.modid + ":" + "regenerative_iron");
        targetMap.put("regenerative_gold", EntryModule.modid + ":" + "regenerative_gold");
        targetMap.put("regenerative_diamond", EntryModule.modid + ":" + "regenerative_diamond");
        targetMap.put("regenerative_redstone", EntryModule.modid + ":" + "regenerative_redstone");
        targetMap.put("regenerative_copper", EntryModule.modid + ":" + "regenerative_copper");
        targetMap.put("regenerative_lapis", EntryModule.modid + ":" + "regenerative_lapis");
        targetMap.put("regenerative_emerald", EntryModule.modid + ":" + "regenerative_emerald");

        targetMap.put("regenerative_cookie", EntryModule.modid + ":" + "regenerative_cookie");
        targetMap.put("regenerative_milk", EntryModule.modid + ":" + "regenerative_milk");

        targetMap.put("regenerative_water", EntryModule.modid + ":" + "regenerative_water");
        targetMap.put("regenerative_lava", EntryModule.modid + ":" + "regenerative_lava");
        targetMap.put("regenerative_xp_leak", EntryModule.modid + ":" + "regenerative_xp_leak");

        targetMap.put("regenerative_nether_gold", EntryModule.modid + ":" + "regenerative_nether_gold");
        targetMap.put("regenerative_nether_quartz", EntryModule.modid + ":" + "regenerative_nether_quartz");
        targetMap.put("regenerative_ancient_debris", EntryModule.modid + ":" + "regenerative_ancient_debris");


        targetMap.put("regenerative_bauxite", EntryModule.modid + ":" + "regenerative_tr_bauxite");
        targetMap.put("regenerative_galena", EntryModule.modid + ":" + "regenerative_tr_galena");
        targetMap.put("regenerative_iridium", EntryModule.modid + ":" + "regenerative_tr_iridium");
        targetMap.put("regenerative_lead", EntryModule.modid + ":" + "regenerative_tr_lead");
        targetMap.put("regenerative_ruby", EntryModule.modid + ":" + "regenerative_tr_ruby");
        targetMap.put("regenerative_sapphire", EntryModule.modid + ":" + "regenerative_tr_sapphire");
        targetMap.put("regenerative_silver", EntryModule.modid + ":" + "regenerative_tr_silver");
        targetMap.put("regenerative_tin", EntryModule.modid + ":" + "regenerative_tr_tin");

        targetMap.put("regenerative_cinnabar", EntryModule.modid + ":" + "regenerative_tr_cinnabar");
        targetMap.put("regenerative_pyrite", EntryModule.modid + ":" + "regenerative_tr_pyrite");
        targetMap.put("regenerative_sphalerite", EntryModule.modid + ":" + "regenerative_tr_sphalerite");

        targetMap.put("regenerative_peridot", EntryModule.modid + ":" + "regenerative_tr_peridot");
        targetMap.put("regenerative_sheldonite", EntryModule.modid + ":" + "regenerative_tr_sheldonite");
        targetMap.put("regenerative_sodalite", EntryModule.modid + ":" + "regenerative_tr_sodalite");
        targetMap.put("regenerative_tungsten", EntryModule.modid + ":" + "regenerative_tr_tungsten");


        targetMap.put("regenerative_tc_cobalt", EntryModule.modid + ":" + "regenerative_tc_cobalt");


        targetMap.put("regenerative_er_yellorite", EntryModule.modid + ":" + "regenerative_er_yellorite");
        targetMap.put("regenerative_er_anglesite", EntryModule.modid + ":" + "regenerative_er_anglesite");
        targetMap.put("regenerative_er_benitoite", EntryModule.modid + ":" + "regenerative_er_benitoite");


        targetMap.put("regenerative_br_uranium", EntryModule.modid + ":" + "regenerative_br_uranium");


        targetMap.put("regenerative_ae_certus_quartz", "minecraft:air");


        targetMap.put("regenerative_ie_aluminum", EntryModule.modid + ":" + "regenerative_ie_aluminum");
        targetMap.put("regenerative_ie_lead", EntryModule.modid + ":" + "regenerative_ie_lead");
        targetMap.put("regenerative_ie_silver", EntryModule.modid + ":" + "regenerative_ie_silver");
        targetMap.put("regenerative_ie_nickel", EntryModule.modid + ":" + "regenerative_ie_nickel");
        targetMap.put("regenerative_ie_uranium", EntryModule.modid + ":" + "regenerative_ie_uranium");


        targetMap.put("regenerative_mm_adamantite", EntryModule.modid + ":" + "regenerative_mm_adamantite");
        targetMap.put("regenerative_mm_aquarium", EntryModule.modid + ":" + "regenerative_mm_aquarium");
        targetMap.put("regenerative_mm_banglum", EntryModule.modid + ":" + "regenerative_mm_banglum");
        targetMap.put("regenerative_mm_carmot", EntryModule.modid + ":" + "regenerative_mm_carmot");
        targetMap.put("regenerative_mm_kyber", EntryModule.modid + ":" + "regenerative_mm_kyber");
        targetMap.put("regenerative_mm_manganese", EntryModule.modid + ":" + "regenerative_mm_manganese");
        targetMap.put("regenerative_mm_morkite", EntryModule.modid + ":" + "regenerative_mm_morkite");
        targetMap.put("regenerative_mm_mythril", EntryModule.modid + ":" + "regenerative_mm_mythril");
        targetMap.put("regenerative_mm_orichalcum", EntryModule.modid + ":" + "regenerative_mm_orichalcum");
        targetMap.put("regenerative_mm_osmium", EntryModule.modid + ":" + "regenerative_mm_osmium");
        targetMap.put("regenerative_mm_platinum", EntryModule.modid + ":" + "regenerative_mm_platinum");
        targetMap.put("regenerative_mm_prometheum", EntryModule.modid + ":" + "regenerative_mm_prometheum");
        targetMap.put("regenerative_mm_quadrillum", EntryModule.modid + ":" + "regenerative_mm_quadrillum");
        targetMap.put("regenerative_mm_runite", EntryModule.modid + ":" + "regenerative_mm_runite");
        targetMap.put("regenerative_mm_silver", EntryModule.modid + ":" + "regenerative_mm_silver");
        targetMap.put("regenerative_mm_starrite", EntryModule.modid + ":" + "regenerative_mm_starrite");
        targetMap.put("regenerative_mm_tin", EntryModule.modid + ":" + "regenerative_mm_tin");
        targetMap.put("regenerative_mm_unobtainium", EntryModule.modid + ":" + "regenerative_mm_unobtainium");

        targetMap.put("regenerative_mm_nether_banglum", EntryModule.modid + ":" + "regenerative_mm_nether_banglum");
        targetMap.put("regenerative_mm_midas_gold", EntryModule.modid + ":" + "regenerative_mm_midas_gold");
        targetMap.put("regenerative_mm_palladium", EntryModule.modid + ":" + "regenerative_mm_palladium");
        targetMap.put("regenerative_mm_stormyx", EntryModule.modid + ":" + "regenerative_mm_stormyx");


        targetMap.put("regenerative_cr_zinc", EntryModule.modid + ":" + "regenerative_cr_zinc");


        targetMap.put("regenerative_mk_osmium", EntryModule.modid + ":" + "regenerative_mk_osmium");
        targetMap.put("regenerative_mk_fluorite", EntryModule.modid + ":" + "regenerative_mk_fluorite");
        targetMap.put("regenerative_mk_lead", EntryModule.modid + ":" + "regenerative_mk_lead");
        targetMap.put("regenerative_mk_tin", EntryModule.modid + ":" + "regenerative_mk_tin");
        targetMap.put("regenerative_mk_uranium", EntryModule.modid + ":" + "regenerative_mk_uranium");


        targetMap.put("regenerative_ug_cloggrum", EntryModule.modid + ":" + "regenerative_ug_cloggrum");
        targetMap.put("regenerative_ug_froststeel", EntryModule.modid + ":" + "regenerative_ug_froststeel");
        targetMap.put("regenerative_ug_utherium", EntryModule.modid + ":" + "regenerative_ug_utherium");
        targetMap.put("regenerative_ug_regalium", EntryModule.modid + ":" + "regenerative_ug_regalium");


        targetMap.put("regenerative_be_desh", EntryModule.modid + ":" + "regenerative_be_desh");
        targetMap.put("regenerative_be_cheese", EntryModule.modid + ":" + "regenerative_be_cheese");
        targetMap.put("regenerative_be_ice_shard", EntryModule.modid + ":" + "regenerative_be_ice_shard");
        targetMap.put("regenerative_be_ostrum", EntryModule.modid + ":" + "regenerative_be_ostrum");
        targetMap.put("regenerative_be_calorite", EntryModule.modid + ":" + "regenerative_be_calorite");


        targetMap.put("regenerative_ic_tin", EntryModule.modid + ":" + "regenerative_ic_tin");
        targetMap.put("regenerative_ic_lead", EntryModule.modid + ":" + "regenerative_ic_lead");
        targetMap.put("regenerative_ic_aluminum", EntryModule.modid + ":" + "regenerative_ic_aluminum");
        targetMap.put("regenerative_ic_iridium", EntryModule.modid + ":" + "regenerative_ic_iridium");
        targetMap.put("regenerative_ic_uranium", EntryModule.modid + ":" + "regenerative_ic_uranium");


        targetMap.put("regenerative_ma_soulstone", EntryModule.modid + ":" + "regenerative_ma_soulstone");
        targetMap.put("regenerative_ma_brilliance", EntryModule.modid + ":" + "regenerative_ma_brilliance");
        targetMap.put("regenerative_ma_blazing_quartz", EntryModule.modid + ":" + "regenerative_ma_blazing_quartz");


        targetMap.put("regenerative_th_niter", EntryModule.modid + ":" + "regenerative_th_niter");
        targetMap.put("regenerative_th_sulfur", EntryModule.modid + ":" + "regenerative_th_sulfur");
        targetMap.put("regenerative_th_tin", EntryModule.modid + ":" + "regenerative_th_tin");
        targetMap.put("regenerative_th_lead", EntryModule.modid + ":" + "regenerative_th_lead");
        targetMap.put("regenerative_th_silver", EntryModule.modid + ":" + "regenerative_th_silver");
        targetMap.put("regenerative_th_nickel", EntryModule.modid + ":" + "regenerative_th_nickel");
        targetMap.put("regenerative_th_apatite", EntryModule.modid + ":" + "regenerative_th_apatite");
        targetMap.put("regenerative_th_cinnabar", EntryModule.modid + ":" + "regenerative_th_cinnabar");
        targetMap.put("regenerative_th_oil", EntryModule.modid + ":" + "regenerative_th_oil");


        targetMap.put("regenerative_pw_uraninite", EntryModule.modid + ":" + "regenerative_pw_uraninite");

        return targetMap;
    }
}

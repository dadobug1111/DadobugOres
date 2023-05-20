package no.dadobug.oremod.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import no.dadobug.oremod.EntryModule;
import oshi.util.tuples.Triplet;

import java.util.HashMap;
import java.util.Map;

@Config(name = EntryModule.modid + "\\" + EntryModule.modid + "_dense_ore_gen_map")
public class DenseOreConfig implements ConfigData {
    public Map<String, Triplet<Float, String, Boolean>> targetMap = new HashMap();



}

package no.dadobug.oremod.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import no.dadobug.oremod.EntryModule;

@Config(name = EntryModule.modid + "\\propagator_gen_defaults")
public class PropagatorGenDefaults implements ConfigData {

    public String layer = "bottom";
    public String targetBlock = "minecraft:bedrock";
    public String targetTag = "";
    public String oreBlock = "minecraft:air";
    public boolean replaceWithBlock_State = true;
    public int chance = 10;
    public int count = -1;
    public int size = 10;
}

package no.dadobug.oremod.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import no.dadobug.oremod.EntryModule;

@Config(name = EntryModule.modid + "\\regenerative_core_defaults")
public class RegenerativeCoreDefaults implements ConfigData {
    public int maxStackSize = 64;
    public boolean isFireResistant = true;
}

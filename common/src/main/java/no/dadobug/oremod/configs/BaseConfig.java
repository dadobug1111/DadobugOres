package no.dadobug.oremod.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import no.dadobug.oremod.EntryModule;

@Config(name = EntryModule.modid + "\\" + EntryModule.modid)
public class BaseConfig implements ConfigData {

    public boolean regenMissingDefaultOres = true;
}

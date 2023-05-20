package no.dadobug.oremod.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import no.dadobug.oremod.EntryModule;

@Config(name = EntryModule.modid + "_block_config")
public class BlocksConfig implements ConfigData {
    @ConfigEntry.Category("vanilla")
    @ConfigEntry.Gui.CollapsibleObject()
    public BlockConfig BEDROCK_FRACTURED = new BlockConfig(true, 50f, 0.5f, 0, 0, 0);
    @ConfigEntry.Category("vanilla")
    @ConfigEntry.Gui.CollapsibleObject()
    public BlockConfig BEDROCK_HOLLOW = new BlockConfig(true, 50f, 0.5f, 0, 0, 0);

}

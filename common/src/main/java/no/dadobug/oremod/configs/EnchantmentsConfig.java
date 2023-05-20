package no.dadobug.oremod.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import no.dadobug.oremod.EntryModule;

@Config(name = EntryModule.modid + "_enchant_config")
public class EnchantmentsConfig implements ConfigData {


    @ConfigEntry.Gui.CollapsibleObject()
    public EnchantConfig SHATTERING = new EnchantConfig(false, true, true);
    @ConfigEntry.Gui.CollapsibleObject()
    public EnchantConfig CURSE_OF_FRACTURING = new EnchantConfig(false, true, true);
    @ConfigEntry.Gui.CollapsibleObject()
    public EnchantConfig EXTRACTION = new EnchantConfig(false, true, true);
    @ConfigEntry.Gui.CollapsibleObject()
    public EnchantConfig GENTLE_MINING = new EnchantConfig(true, true, true);
    @ConfigEntry.Gui.CollapsibleObject()
    public EnchantConfig CURSE_OF_SHATTERING = new EnchantConfig(false, false, false);
    @ConfigEntry.Gui.CollapsibleObject()
    public EnchantConfig ARCANE_EXTRACTION = new EnchantConfig(false, false, false);
}

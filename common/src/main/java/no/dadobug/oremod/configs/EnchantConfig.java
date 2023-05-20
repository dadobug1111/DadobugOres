package no.dadobug.oremod.configs;

import me.shedaniel.autoconfig.annotation.ConfigEntry;

public class EnchantConfig {

    @ConfigEntry.Gui.RequiresRestart
    public boolean doTableEnchant;
    @ConfigEntry.Gui.RequiresRestart
    public boolean doVillagerEnchant;
    @ConfigEntry.Gui.RequiresRestart
    public boolean doLootEnchant;

    EnchantConfig(boolean doTableEnchant, boolean doVillagerEnchant, boolean doLootEnchant){

        this.doTableEnchant = doTableEnchant;
        this.doVillagerEnchant = doVillagerEnchant;
        this.doLootEnchant = doLootEnchant;
    }

}

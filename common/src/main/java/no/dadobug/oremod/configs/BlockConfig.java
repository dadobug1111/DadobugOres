package no.dadobug.oremod.configs;

import me.shedaniel.autoconfig.annotation.ConfigEntry;

public class BlockConfig {

    @ConfigEntry.Gui.RequiresRestart
    public boolean showOre;
    @ConfigEntry.Gui.RequiresRestart
    public float hardness;
    @ConfigEntry.Gui.RequiresRestart
    public float resistance;
    @ConfigEntry.BoundedDiscrete(
            min = 0,
            max = 15
    )
    @ConfigEntry.Gui.RequiresRestart
    public int luminance;
    @ConfigEntry.Gui.RequiresRestart
    public int XPmin;
    @ConfigEntry.Gui.RequiresRestart
    public int XPmax;
    @ConfigEntry.Gui.RequiresRestart
    public boolean infinite = true;
    @ConfigEntry.Gui.RequiresRestart
    public int DurabilityMin = 2000;
    @ConfigEntry.Gui.RequiresRestart
    public int DurabilityMax = 2500;

    public BlockConfig(boolean showOre, float hardness, float resistance, int luminance, int XPmin, int XPmax){
        this.showOre = showOre;
        this.hardness = hardness;
        this.resistance = resistance;
        this.luminance = luminance;
        this.XPmin = XPmin;
        this.XPmax = XPmax;
    }

    public BlockConfig(boolean showOre, float hardness, float resistance, int luminance, int XPmin, int XPmax, boolean infinite, int DurabilityMin, int DurabilityMax){
        this.showOre = showOre;
        this.hardness = hardness;
        this.resistance = resistance;
        this.luminance = luminance;
        this.XPmin = XPmin;
        this.XPmax = XPmax;
        this.infinite = infinite;
        this.DurabilityMin = DurabilityMin;
        this.DurabilityMax = DurabilityMax;
    }

}

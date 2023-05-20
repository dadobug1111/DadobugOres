package no.dadobug.oremod.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Blocks;
import no.dadobug.oremod.EntryModule;

@Config(name = EntryModule.modid + "\\regenerative_block_defaults")
public class RegenerativeBlockDefaults implements ConfigData {

    public int toolLevel = 4;
    public int durabilityMin = 10;
    public int durabilityMax = 50;
    public boolean showOre = true;
    public float hardness = 50f;
    public float resistance = 0.5f;
    public int luminance = 0;
    public int XPmin = 0;
    public int XPmax = 0;
    public boolean standardTexture = true;
    public boolean bossProof = true;
    public boolean infinite = true;
    public boolean silkable = false;
    public boolean replace = false;
    public String defaultTool = "pickaxe";
    public String replaceBlockId = Registry.BLOCK.getResourceKey(Blocks.AIR).toString();
    public String damageFunction = "default";
    public float denseOreGenProbability = 0.1f;
    public boolean denseOreGenReplaceState = true;
    public boolean onlyValidTools = true;
}

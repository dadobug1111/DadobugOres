package no.dadobug.oremod.json_configs;


import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonObject;
import net.minecraft.world.level.block.Block;
import no.dadobug.oremod.RegistryBox;

import java.util.function.Supplier;

public abstract class DynamicItemConfig {

    public static String type() {
        return "empty";
    }

    public abstract void addItem(RegistryBox registry);

    public abstract void addItem(RegistryBox registry, Supplier<Block> hostBlock);

    public static DynamicItemConfig fromJsonObject(JsonObject jsonObject) {
        return null;
    }

    public abstract JsonObject toJsonObject();
}

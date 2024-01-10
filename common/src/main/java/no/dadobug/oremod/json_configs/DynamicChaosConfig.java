package no.dadobug.oremod.json_configs;

import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonObject;

public abstract class DynamicChaosConfig {

    public static String type() {
        return "empty";
    }

    public abstract void addChaos();

    public static DynamicBlockConfig fromJsonObject(JsonObject jsonObject){
        return null;
    }

    public abstract JsonObject toJsonObject();
}

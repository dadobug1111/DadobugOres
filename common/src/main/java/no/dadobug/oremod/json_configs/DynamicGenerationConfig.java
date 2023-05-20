package no.dadobug.oremod.json_configs;

import com.google.gson.JsonObject;

public abstract class DynamicGenerationConfig {

    public static String type() {
        return "empty";
    }

    public abstract void addGeneration();

    public static DynamicGenerationConfig fromJsonObject(JsonObject jsonObject){
        return null;
    };

    public abstract me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonObject toJsonObject();


}

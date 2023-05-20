package no.dadobug.oremod.json_configs;


import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonObject;
import no.dadobug.oremod.RegistryBox;

public abstract class DynamicLootConfig {

    public static String type() {
        return "empty";
    }

    public abstract void addLoot(RegistryBox registry, String hostID);

    public static DynamicLootConfig fromJsonObject(JsonObject jsonObject){
        return null;
    };

    public abstract JsonObject toJsonObject();

}

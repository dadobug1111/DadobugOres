package no.dadobug.oremod.json_configs;


import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonObject;
import no.dadobug.oremod.RegistryBox;

public abstract class DynamicBlockConfig {

    public static String type() {
        return "empty";
    }

    public abstract void addBlock(RegistryBox registry);

    public static DynamicBlockConfig fromJsonObject(JsonObject jsonObject){
        return null;
    };

    public abstract JsonObject toJsonObject();

}

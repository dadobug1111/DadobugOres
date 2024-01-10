package no.dadobug.oremod.json_configs;

import com.mojang.datafixers.util.Pair;
import dev.architectury.platform.Platform;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Jankson;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonObject;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonPrimitive;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.api.SyntaxError;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.util.RandomSource;
import no.dadobug.oremod.EntryModule;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;


public class JsonConfig {

    static Jankson JANKSON = Jankson.builder().build();
    public static Map<String, Function<JsonObject, DynamicBlockConfig>> blockTypes = new HashMap<>();
    public static Function<JsonObject, DynamicBlockConfig> regenerativeBlockConfig = blockTypes.put(RegenerativeBlockConfig.type(), RegenerativeBlockConfig::fromJsonObject);
    public static final String[] cancelStrings = {"null", "", "empty", "void", "skip", "no"};

    public static Map<String, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer>> durabilityFunctions = new HashMap<>();
    public static PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> defaultFunction = durabilityFunctions.put("default", (durability, shattering, gentle, random) -> durability - 6 - (shattering> 0 ? 1 : 0) + Math.min(gentle, 5));
    public static PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> staticFunction = durabilityFunctions.put("static", (durability, shattering, gentle, random) -> durability - 1);
    public static PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> unbreakingFunction = durabilityFunctions.put("unbreaking", (durability, shattering, gentle, random) -> {
        int x = random.nextInt(-shattering, gentle + 1);
        if(x<0){
            return durability - 2;
        } else if(x==0){
            return durability - 1;
        } else {
            return durability;
        }
    });


    public static Map<String, Function<JsonObject, DynamicChaosConfig>> chaosTypes = new HashMap<>();


    public static Map<String, Function<JsonObject, DynamicGenerationConfig>> generationTypes = new HashMap<>();
    public static Function<JsonObject, DynamicGenerationConfig> propagatorGenerationConfig = generationTypes.put(PropagatorOreGenConfig.type(), PropagatorOreGenConfig::fromJsonObject);


    public static Map<String, Function<JsonObject, DynamicLootConfig>> lootTypes = new HashMap<>();
    public static Function<JsonObject, DynamicLootConfig> regenerativeLootConfig = lootTypes.put(RegenerativeOreLootConfig.type(), RegenerativeOreLootConfig::fromJsonObject);
    public static Function<JsonObject, DynamicLootConfig> regenerativeLootCustomConfig = lootTypes.put(RegenerativeOreCustomLootConfig.type(), RegenerativeOreCustomLootConfig::fromJsonObject);



    public static Map<String, Function<JsonObject, DynamicItemConfig>> itemTypes = new HashMap<>();
    public static Function<JsonObject, DynamicItemConfig> regenerativeCoreConfig = itemTypes.put(RegenerativeCoreConfig.type(), RegenerativeCoreConfig::fromJsonObject);

    public static Path config_path = Platform.getConfigFolder().resolve(EntryModule.modid);
    public static Path block_config_path = config_path.resolve("blocks");
    public static Path chaos_config_path = config_path.resolve("chaos");
    public static Path gen_config_path = config_path.resolve("generation");





    public static List<DynamicBlockConfig> readBlockConfigs() {
        EntryModule.LOGGER.debug("Block Config load attempt!");
        return readJsonFiles(blockTypes, block_config_path);
    }

    public static List<DynamicChaosConfig> readChaosConfigs() {
        return readJsonFiles(chaosTypes, chaos_config_path);
    }

    public static List<DynamicGenerationConfig> readGenerationConfigs() {
        return readJsonFiles(generationTypes, gen_config_path);
    }

    public static <T> List<T> readJsonFiles(Map<String, Function<JsonObject, T>> map, Path path) {
        DirectoryStream<Path> stream;
        ArrayList<T> configs = new ArrayList<>();
        try {
            stream = Files.newDirectoryStream(path);
            stream.forEach((filePath) -> {
                EntryModule.LOGGER.debug("Config load attempt: " + filePath.getFileName());
                if(filePath.getFileName().toString().endsWith(".json")) {
                    try {
                        JsonObject object = JANKSON.load(filePath.toFile());
                        if(object.get("type") instanceof JsonPrimitive primitive) {
                            String type = primitive.asString();

                            if (map.containsKey(type)) {
                                configs.add(map.get(type).apply(object));
                            } else {
                                EntryModule.LOGGER.warn("Config " + filePath.getFileName() + " has invalid Config type! skipping Config parsing");
                            }
                        }
                        EntryModule.LOGGER.debug("Block Config loaded: " + filePath.getFileName());
                    } catch (IOException e) {
                        EntryModule.LOGGER.error("IOException: " + e);
                        EntryModule.LOGGER.warn("Skipping File!");
                    } catch (SyntaxError e) {
                        EntryModule.LOGGER.error("SyntaxError: " + e);
                        EntryModule.LOGGER.warn("Skipping File!");
                    }
                } else{
                    EntryModule.LOGGER.debug("Config load attempt failed for: " + filePath.getFileName());
                }
            });
            stream.close();
        } catch(IOException e){
            EntryModule.LOGGER.error("Problem Finding Block Configs: " + e);
            EntryModule.LOGGER.warn("Skipping file reading. May result in broken gamestate!");
        }

        return configs;
    }

    public static void writeJsonFiles(List<Pair<String, JsonObject>> list, Path path) {
        try {
            for (Pair<String, JsonObject> stringJsonObjectPair : list) {
                String name = stringJsonObjectPair.getFirst();
                JsonObject object = stringJsonObjectPair.getSecond();
                Path fileLocation = path.resolve(name + ".json");
                if (Files.notExists(fileLocation)){
                    Files.createDirectories(fileLocation.getParent());
                    BufferedWriter writer = Files.newBufferedWriter(fileLocation);
                    writer.write(object.toJson(true, true));
                    writer.close();
                }
            }

        } catch(IOException e){
            EntryModule.LOGGER.error("Problem Finding Block Configs: " + e);
            EntryModule.LOGGER.warn("Skipping file reading");
        }

        //return configs;
    }



}

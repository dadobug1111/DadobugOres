package no.dadobug.oremod;

import dev.architectury.registry.level.biome.BiomeModifications;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.*;
import java.util.function.Predicate;

public class BiomeSelectors {
    private BiomeSelectors() {}
    // Vanilla Biomes
    public static BiomeSource overworld = MultiNoiseBiomeSource.Preset.OVERWORLD.biomeSource(BuiltinRegistries.BIOME);
    public static BiomeSource nether = MultiNoiseBiomeSource.Preset.NETHER.biomeSource(BuiltinRegistries.BIOME);
    public static BiomeSource end = new TheEndBiomeSource(BuiltinRegistries.BIOME);
    // Modded Biomes
    // There is probably a better way of doing this but till someone figures it out this hack works
    public static String[] undergardenBiomesIds = new String[]{"undergarden:forgotten_field", "undergarden:ancient_sea", "undergarden:frostfields", "undergarden:icy_sea", "undergarden:smogstem_forest", "undergarden:wigglewood_forest", "undergarden:dense_forest", "undergarden:gronglegrowth", "undergarden:barren_abyss", "undergarden:dead_sea", "undergarden:smog_spires", "undergarden:mushroom_bog"};
    public static ArrayList<String> undergardenBiomes = new ArrayList<>(Arrays.asList(undergardenBiomesIds));
    public static String[] beyondEarthMoonBiomesIds = new String[]{"beyond_earth:moon_desert"};
    public static ArrayList<String> beyondEarthMoonBiomes = new ArrayList<>(Arrays.asList(beyondEarthMoonBiomesIds));
    public static String[] beyondEarthMarsBiomesIds = new String[]{"beyond_earth:mars_desert", "beyond_earth:mars_rocky_plains", "beyond_earth:mars_ice_spikes"};
    public static ArrayList<String> beyondEarthMarsBiomes = new ArrayList<>(Arrays.asList(beyondEarthMarsBiomesIds));
    public static String[] beyondEarthVenusBiomesIds = new String[]{"beyond_earth:venus_desert", "beyond_earth:infernal_venus_barrens"};
    public static ArrayList<String> beyondEarthVenusBiomes = new ArrayList<>(Arrays.asList(beyondEarthVenusBiomesIds));
    public static String[] beyondEarthGlacioBiomesIds = new String[]{"beyond_earth:glacio", "beyond_earth:glacio_ice_spikes"};
    public static ArrayList<String> beyondEarthGlacioBiomes = new ArrayList<>(Arrays.asList(beyondEarthGlacioBiomesIds));
    public static String[] beyondEarthMercuryBiomesIds = new String[]{"beyond_earth:mercury"};
    public static ArrayList<String> beyondEarthMercuryBiomes = new ArrayList<>(Arrays.asList(beyondEarthMercuryBiomesIds));

    public static Predicate<BiomeModifications.BiomeContext> gensFeature(Holder<PlacedFeature> FeatureIn) {

        return context -> context.getProperties().getGenerationProperties().getFeatures().stream().anyMatch((ctx) -> {
            for (Holder<PlacedFeature> placedFeatureRegistryEntry : ctx) {
                if (Objects.equals(BuiltinRegistries.PLACED_FEATURE.get(placedFeatureRegistryEntry.unwrapKey().orElse(null)), FeatureIn.value()))
                    return true;
            }

            return false;
        });
    }

    public static Predicate<BiomeModifications.BiomeContext> gensEmeralds() {
        return (context) -> hasTag(TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("minecraft", "is_mountain"))).test(context) || hasTag(TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("minecraft", "is_hill"))).test(context) || Biomes.GROVE.location().equals(context.getKey().orElse(new ResourceLocation(EntryModule.modid, "error")));
    }

    public static Optional<Level> getWorld(MinecraftServer server, Holder<Level> world) {
        return Optional.ofNullable(server.getLevel(world.value().dimension()));
    }

    public static Predicate<BiomeModifications.BiomeContext> hasTag(TagKey<Biome> tag) {
        return context -> context.hasTag(tag);
    }

    public static Predicate<BiomeModifications.BiomeContext> gensInSource(BiomeSource sourceIn) {
        return context -> sourceIn.possibleBiomes().stream().anyMatch(biome -> biome.value().equals(BuiltinRegistries.BIOME.get(context.getKey().orElse(new ResourceLocation(EntryModule.modid, "error")))));
    }


    public static Predicate<BiomeModifications.BiomeContext> gensInBiome(ResourceLocation ResourceLocation) {
        return context -> {
            ResourceLocation currentBiome = context.getKey().orElse(new ResourceLocation(EntryModule.modid, "error"));
            return ResourceLocation.equals(currentBiome);
        };
    }

    // Returns a BiomeContext Predicate to check if the context is in a list of Biomes
    public static Predicate<BiomeModifications.BiomeContext> gensInBiomeList(List<String> ResourceLocations) {
        // Check if dimesion has biomes
        if (ResourceLocations.isEmpty()) return ctx -> false;

        // If it does set predicate from first biome
        Predicate<BiomeModifications.BiomeContext> predicate = gensInBiome(new ResourceLocation(ResourceLocations.get(0)));

        // Then OR the rest together to check if the context is in dimension
        for (String biomeID : ResourceLocations.subList(1, ResourceLocations.size())) {
            predicate = predicate.or(gensInBiome(new ResourceLocation(biomeID)));
        }
        return predicate;
    }

    // Returns a BiomeContext Predicate to check if the context is in a list of Biomes
    public static Predicate<BiomeModifications.BiomeContext> gensInTagList(List<TagKey<Biome>> tag) {
        // Check if dimesion has biomes
        if (tag.isEmpty()) return ctx -> false;

        // If it does set predicate from first biome
        Predicate<BiomeModifications.BiomeContext> predicate = hasTag(tag.get(0));

        // Then OR the rest together to check if the context is in dimension
        for (TagKey<Biome> tagID : tag.subList(1, tag.size())) {
            predicate = predicate.or(hasTag(tagID));
        }
        return predicate;
    }


    public static Predicate<BiomeModifications.BiomeContext> gensInOverworld() {
        return gensInSource(overworld);
    }


    public static Predicate<BiomeModifications.BiomeContext> gensInNether() {
        return gensInSource(nether);
    }


    public static Predicate<BiomeModifications.BiomeContext> gensInEnd() {
        return gensInSource(end);
    }

    // Checks if a BiomeContext is in the Undergarden dimension
    public static Predicate<BiomeModifications.BiomeContext> gensInUndergarden() {
        return gensInBiomeList(undergardenBiomes);
    }

    // Checks if a BiomeContext is in the Beyond Earth's Moon dimension
    public static Predicate<BiomeModifications.BiomeContext> gensInBeyondEarthMoon() {
        return gensInBiomeList(beyondEarthMoonBiomes);
    } 

    // Checks if a BiomeContext is in the Beyond Earth's Mars dimension
    public static Predicate<BiomeModifications.BiomeContext> gensInBeyondEarthMars() {
        return gensInBiomeList(beyondEarthMarsBiomes);
    } 

    // Checks if a BiomeContext is in the Beyond Earth's Venus dimension
    public static Predicate<BiomeModifications.BiomeContext> gensInBeyondEarthVenus() {
        return gensInBiomeList(beyondEarthVenusBiomes);
    }

    // Checks if a BiomeContext is in the Beyond Earth's Glacio dimension
    public static Predicate<BiomeModifications.BiomeContext> gensInBeyondEarthGlacio() {
        return gensInBiomeList(beyondEarthGlacioBiomes);
    }

    // Checks if a BiomeContext is in the Beyond Earth's Mercury dimension
    public static Predicate<BiomeModifications.BiomeContext> gensInBeyondEarthMercury() {
        return gensInBiomeList(beyondEarthMercuryBiomes);
    }
} 

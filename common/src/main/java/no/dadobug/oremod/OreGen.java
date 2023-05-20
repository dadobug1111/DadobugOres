package no.dadobug.oremod;


import dev.architectury.registry.level.biome.BiomeModifications;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import no.dadobug.oremod.worldgen.BedrockOreFeatureConfig;

import java.util.List;
import java.util.function.Predicate;

public class OreGen {
    private ConfiguredFeature<?,?> Ore;
    private PlacedFeature PlacedOre;
    private String id;
    private Predicate<BiomeModifications.BiomeContext> where;

    public OreGen GenBottom(RuleTest replaced, int size, int chance, BlockState ore, String id, Predicate<BiomeModifications.BiomeContext> where){
        this.Ore = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(EntryModule.modid, id), new ConfiguredFeature<>(EntryModule.BEDROCK_ORE_GENERATOR.get(),new BedrockOreFeatureConfig(replaced, ore, size, 1, BedrockOreFeatureConfig.WorldLayer.BOTTOM)));
        this.PlacedOre = Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(EntryModule.modid, id), new PlacedFeature(Holder.direct(this.Ore) , List.of(RarityFilter.onAverageOnceEvery(chance), InSquarePlacement.spread(), HeightRangePlacement.of(ConstantHeight.of(VerticalAnchor.BOTTOM)), BiomeFilter.biome())));
        this.id = id;
        this.where = where;

        return this;
    }

    public OreGen GenTop(RuleTest replaced, int size, int chance, BlockState ore, String id, Predicate<BiomeModifications.BiomeContext> where){
        this.Ore = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(EntryModule.modid, id), new ConfiguredFeature<>(EntryModule.BEDROCK_ORE_GENERATOR.get(),new BedrockOreFeatureConfig(replaced, ore, size, 1, BedrockOreFeatureConfig.WorldLayer.TOP)));
        this.PlacedOre = Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(EntryModule.modid, id), new PlacedFeature(Holder.direct(this.Ore) , List.of(RarityFilter.onAverageOnceEvery(chance), InSquarePlacement.spread(), HeightRangePlacement.of(ConstantHeight.of(VerticalAnchor.TOP)), BiomeFilter.biome())));
        this.id = id;
        this.where = where;

        return this;
    }

    public OreGen GenAll(RuleTest replaced, int size, int chance, BlockState ore, String id, Predicate<BiomeModifications.BiomeContext> where){
        this.Ore = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(EntryModule.modid, id), new ConfiguredFeature<>(EntryModule.BEDROCK_ORE_GENERATOR.get(),new BedrockOreFeatureConfig(replaced, ore, size, 1, BedrockOreFeatureConfig.WorldLayer.ALL)));
        this.PlacedOre = Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(EntryModule.modid, id), new PlacedFeature(Holder.direct(this.Ore) , List.of(RarityFilter.onAverageOnceEvery(chance), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()), BiomeFilter.biome())));
        this.id = id;
        this.where = where;

        return this;
    }

    public OreGen GenBottom(RuleTest replaced, int size, BlockState ore, String id, Predicate<BiomeModifications.BiomeContext> where, int count){
        this.Ore = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(EntryModule.modid, id), new ConfiguredFeature<>(EntryModule.BEDROCK_ORE_GENERATOR.get(),new BedrockOreFeatureConfig(replaced, ore, size, 1, BedrockOreFeatureConfig.WorldLayer.BOTTOM)));
        this.PlacedOre = Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(EntryModule.modid, id), new PlacedFeature(Holder.direct(this.Ore) , List.of(CountPlacement.of(count), InSquarePlacement.spread(), HeightRangePlacement.of(ConstantHeight.of(VerticalAnchor.BOTTOM)), BiomeFilter.biome())));
        this.id = id;
        this.where = where;

        return this;
    }

    public OreGen GenTop(RuleTest replaced, int size, BlockState ore, String id, Predicate<BiomeModifications.BiomeContext> where, int count){
        this.Ore = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(EntryModule.modid, id), new ConfiguredFeature<>(EntryModule.BEDROCK_ORE_GENERATOR.get(),new BedrockOreFeatureConfig(replaced, ore, size, 1, BedrockOreFeatureConfig.WorldLayer.TOP)));
        this.PlacedOre = Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(EntryModule.modid, id), new PlacedFeature(Holder.direct(this.Ore) , List.of(CountPlacement.of(count), InSquarePlacement.spread(), HeightRangePlacement.of(ConstantHeight.of(VerticalAnchor.TOP)), BiomeFilter.biome())));
        this.id = id;
        this.where = where;

        return this;
    }

    public OreGen GenAll(RuleTest replaced, int size, BlockState ore, String id, Predicate<BiomeModifications.BiomeContext> where, int count){
        this.Ore = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(EntryModule.modid, id), new ConfiguredFeature<>(EntryModule.BEDROCK_ORE_GENERATOR.get(),new BedrockOreFeatureConfig(replaced, ore, size, 1, BedrockOreFeatureConfig.WorldLayer.ALL)));
        this.PlacedOre = Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(EntryModule.modid, id), new PlacedFeature(Holder.direct(this.Ore) , List.of(CountPlacement.of(count), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()), BiomeFilter.biome())));
        this.id = id;
        this.where = where;

        return this;
    }

    public OreGen GenBottom(int chance, ConfiguredFeature<?,?> ore, String id, Predicate<BiomeModifications.BiomeContext> where){
        this.Ore = ore;
        this.PlacedOre = Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(EntryModule.modid, id), new PlacedFeature(Holder.direct(this.Ore) , List.of(RarityFilter.onAverageOnceEvery(chance), InSquarePlacement.spread(), HeightRangePlacement.of(ConstantHeight.of(VerticalAnchor.BOTTOM)), BiomeFilter.biome())));
        this.id = id;
        this.where = where;

        return this;
    }

    public OreGen GenTop(int chance, ConfiguredFeature<?,?> ore, String id, Predicate<BiomeModifications.BiomeContext> where){
        this.Ore = ore;
        this.PlacedOre = Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(EntryModule.modid, id), new PlacedFeature(Holder.direct(this.Ore) , List.of(RarityFilter.onAverageOnceEvery(chance), InSquarePlacement.spread(), HeightRangePlacement.of(ConstantHeight.of(VerticalAnchor.TOP)), BiomeFilter.biome())));
        this.id = id;
        this.where = where;

        return this;
    }

    public OreGen GenAll(int chance, ConfiguredFeature<?,?> ore, String id, Predicate<BiomeModifications.BiomeContext> where){
        this.Ore = ore;
        this.PlacedOre = Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(EntryModule.modid, id), new PlacedFeature(Holder.direct(this.Ore) , List.of(RarityFilter.onAverageOnceEvery(chance), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()), BiomeFilter.biome())));
        this.id = id;
        this.where = where;

        return this;
    }

    public OreGen GenBottom(ConfiguredFeature<?,?> ore, String id, Predicate<BiomeModifications.BiomeContext> where, int count){
        this.Ore = ore;
        this.PlacedOre = Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(EntryModule.modid, id), new PlacedFeature(Holder.direct(this.Ore) , List.of(RarityFilter.onAverageOnceEvery(count), InSquarePlacement.spread(), HeightRangePlacement.of(ConstantHeight.of(VerticalAnchor.BOTTOM)), BiomeFilter.biome())));
        this.id = id;
        this.where = where;

        return this;
    }

    public OreGen GenTop(ConfiguredFeature<?,?> ore, String id, Predicate<BiomeModifications.BiomeContext> where, int count){
        this.Ore = ore;
        this.PlacedOre = Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(EntryModule.modid, id), new PlacedFeature(Holder.direct(this.Ore) , List.of(RarityFilter.onAverageOnceEvery(count), InSquarePlacement.spread(), HeightRangePlacement.of(ConstantHeight.of(VerticalAnchor.TOP)), BiomeFilter.biome())));
        this.id = id;
        this.where = where;

        return this;
    }

    public OreGen GenAll( ConfiguredFeature<?,?> ore, String id, Predicate<BiomeModifications.BiomeContext> where, int count){
        this.Ore = ore;
        this.PlacedOre = Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(EntryModule.modid, id), new PlacedFeature(Holder.direct(this.Ore) , List.of(RarityFilter.onAverageOnceEvery(count), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()), BiomeFilter.biome())));
        this.id = id;
        this.where = where;

        return this;
    }



    public PlacedFeature getPlacedOre() {return this.PlacedOre;}

    public ConfiguredFeature<?,?> getOre() {
        return this.Ore;
    }

    public void addOreToGen(){
        // If the dimension has no ores or if the dimension doesn't have this ore yet then generate it
        BiomeModifications.addProperties(
                where,
                (ctx, mutable) -> mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Holder.direct(this.PlacedOre))
        );
    }
}

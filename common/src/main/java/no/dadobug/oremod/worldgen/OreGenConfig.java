package no.dadobug.oremod.worldgen;

import dev.architectury.registry.level.biome.BiomeModifications;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.RandomBlockMatchTest;
import no.dadobug.oremod.EntryModule;
import no.dadobug.oremod.OreGen;
import no.dadobug.oremod.blocks.OresBlockStates;
import no.dadobug.oremod.configs.DenseOreConfig;
import oshi.util.tuples.Triplet;

import java.util.ArrayList;
import java.util.List;

public class OreGenConfig {

    public static DenseOreConfig config = AutoConfig.register(DenseOreConfig.class, GsonConfigSerializer::new).getConfig();
    public static final List<OreGen> RUNTIME_ORES = new ArrayList<>();
    public static final List<OreConfiguration.TargetBlockState> targetList = new ArrayList<>();


    static ConfiguredFeature<?,?> configuredDenseGenerator;
    static PlacedFeature placedDenseGenerator;

    public static boolean addOre(OreGen oreGen){
        return RUNTIME_ORES.add(oreGen);
    }


    public static boolean addDenseOre(String block, Triplet<Float, String, Boolean> triplet){
        BlockState state = Registry.BLOCK.get(ResourceLocation.tryParse(triplet.getB())).defaultBlockState();
        if(state.hasProperty(OresBlockStates.REPLACE_WITH_BLOCK))state.setValue(OresBlockStates.REPLACE_WITH_BLOCK, triplet.getC());

        return targetList.add(OreConfiguration.target(new RandomBlockMatchTest(Registry.BLOCK.get(ResourceLocation.tryParse(block)), triplet.getA()), state));
    }

    public static boolean addDenseOre(Block block, Triplet<Float, Block, Boolean> triplet){
        BlockState state = triplet.getB().defaultBlockState();
        if(state.hasProperty(OresBlockStates.REPLACE_WITH_BLOCK))state.setValue(OresBlockStates.REPLACE_WITH_BLOCK, triplet.getC());

        return targetList.add(OreConfiguration.target(new RandomBlockMatchTest(block, triplet.getA()), state));
    }

    public static void init(){
        config.targetMap.forEach((OreGenConfig::addDenseOre));
        OreConfiguration denseConfig = new OreConfiguration(targetList, 1);


        configuredDenseGenerator = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(EntryModule.modid, "dense_ore_placer"), new ConfiguredFeature<>(EntryModule.DENSE_ORE_GENERATOR.get(), denseConfig));
        placedDenseGenerator = Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(EntryModule.modid, "dense_ore_placer"), new PlacedFeature(Holder.direct(configuredDenseGenerator), List.of(HeightRangePlacement.of(ConstantHeight.of(VerticalAnchor.BOTTOM)), BiomeFilter.biome())));

        BiomeModifications.addProperties(
                (biomeContext -> true),
                (ctx, mutable) -> mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, Holder.direct(placedDenseGenerator))
        );

        RUNTIME_ORES.forEach((OreGen::addOreToGen));
    }





}

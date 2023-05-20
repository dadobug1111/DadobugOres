package no.dadobug.oremod.worldgen;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.List;

public class BedrockOreFeatureConfig implements FeatureConfiguration {

    public enum WorldLayer{
        TOP,
        BOTTOM,
        ALL
    }
    public static final Codec<BedrockOreFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(Codec.list(BedrockOreFeatureConfig.Target.CODEC).fieldOf("targets").forGetter((config) -> {
            return config.targets;
        }), Codec.intRange(0, 64).fieldOf("max_size").forGetter((config) -> {
            return config.maxSize;
        }), Codec.intRange(0, 64).fieldOf("min_size").forGetter((config) -> {
            return config.minSize;
        }), Codec.floatRange(0.0F, 1.0F).fieldOf("discard_chance_on_air_exposure").forGetter((config) -> {
            return config.discardOnAirChance;
        }), Codec.intRange(0,2).fieldOf("worldLayer").forGetter((config) -> {
            return config.worldLayer.ordinal();
        }), Codec.BYTE.fieldOf("max_distance_from_origin").forGetter((config) -> {
            return config.maxDistanceFromOrigin;
        }), Codec.BYTE.fieldOf("propagator_count").forGetter((config) -> {
            return config.propagatorCount;
        })).apply(instance, BedrockOreFeatureConfig::new);
    });
    public final List<BedrockOreFeatureConfig.Target> targets;
    public final int maxSize;
    public final int minSize;
    public final float discardOnAirChance;
    public final WorldLayer worldLayer;
    public final byte maxDistanceFromOrigin;
    public final byte propagatorCount;

    public BedrockOreFeatureConfig(List<BedrockOreFeatureConfig.Target> targets, int maxSize, int minSize, float discardOnAirChance, WorldLayer worldLayer, byte maxDistanceFromOrigin, byte propagatorCount) {
        this.maxSize = maxSize;
        this.minSize = minSize;
        this.targets = targets;
        this.discardOnAirChance = discardOnAirChance;
        this.worldLayer = worldLayer;
        this.maxDistanceFromOrigin = maxDistanceFromOrigin;
        this.propagatorCount = propagatorCount;
    }
    public BedrockOreFeatureConfig(List<Target> targets, int maxSize, int minSize, Float discardOnAirChance, int worldLayer, byte maxDistanceFromOrigin, byte propagatorCount) {
        WorldLayer layer;
        if(WorldLayer.ALL.ordinal() == worldLayer){
            layer = WorldLayer.ALL;
        } else if(WorldLayer.TOP.ordinal() == worldLayer){
            layer = WorldLayer.TOP;
        } else{
            layer = WorldLayer.BOTTOM;
        }
        this.maxSize = maxSize;
        this.minSize = minSize;
        this.targets = targets;
        this.discardOnAirChance = discardOnAirChance;
        this.worldLayer = layer;
        this.maxDistanceFromOrigin = maxDistanceFromOrigin;
        this.propagatorCount = propagatorCount;
    }

    public BedrockOreFeatureConfig(List<BedrockOreFeatureConfig.Target> targets, int maxSize, int minSize, WorldLayer worldLayer, byte maxDistanceFromOrigin, byte propagatorCount) {
        this(targets, maxSize, minSize, 0.0F, worldLayer, maxDistanceFromOrigin, propagatorCount);
    }

    public BedrockOreFeatureConfig(RuleTest test, BlockState state, int maxSize, int minSize, float discardOnAirChance, WorldLayer worldLayer, byte maxDistanceFromOrigin, byte propagatorCount) {
        this(ImmutableList.of(new BedrockOreFeatureConfig.Target(test, state)), maxSize, minSize, discardOnAirChance, worldLayer, maxDistanceFromOrigin, propagatorCount);
    }

    public BedrockOreFeatureConfig(RuleTest test, BlockState state, int maxSize, int minSize, WorldLayer worldLayer, byte maxDistanceFromOrigin, byte propagatorCount) {
        this(ImmutableList.of(new BedrockOreFeatureConfig.Target(test, state)), maxSize, minSize, 0.0F, worldLayer, maxDistanceFromOrigin, propagatorCount);
    }

    public BedrockOreFeatureConfig(RuleTest test, BlockState state, int maxSize, int minSize, WorldLayer worldLayer, byte propagatorCount) {
        this(ImmutableList.of(new BedrockOreFeatureConfig.Target(test, state)), maxSize, minSize, 0.0F, worldLayer, (byte)3, propagatorCount);
    }

    public BedrockOreFeatureConfig(RuleTest test, BlockState state, int maxSize, int minSize, WorldLayer worldLayer) {
        this(ImmutableList.of(new BedrockOreFeatureConfig.Target(test, state)), maxSize, minSize, 0.0F, worldLayer, (byte)3, (byte)5);
    }


    public static BedrockOreFeatureConfig.Target createTarget(RuleTest test, BlockState state) {
        return new BedrockOreFeatureConfig.Target(test, state);
    }

    public static class Target {
        public static final Codec<BedrockOreFeatureConfig.Target> CODEC = RecordCodecBuilder.create((instance) -> {
            return instance.group(RuleTest.CODEC.fieldOf("target").forGetter((target) -> {
                return target.target;
            }), BlockState.CODEC.fieldOf("state").forGetter((target) -> {
                return target.state;
            })).apply(instance, BedrockOreFeatureConfig.Target::new);
        });
        public final RuleTest target;
        public final BlockState state;

        Target(RuleTest target, BlockState state) {
            this.target = target;
            this.state = state;
        }
    }
}

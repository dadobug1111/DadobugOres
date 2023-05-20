package no.dadobug.oremod.worldgen;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.ArrayList;
import java.util.List;

public class BedrockOreGenerator extends Feature<BedrockOreFeatureConfig> {

    public BedrockOreGenerator(Codec<BedrockOreFeatureConfig> config) {

        super(config);
    }

    @Override
    public boolean place(FeaturePlaceContext<BedrockOreFeatureConfig> context) {
        BlockPos origin = context.origin();
        BedrockOreFeatureConfig config = context.config();
        byte maxDistanceFromOrigin = config.maxDistanceFromOrigin;
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos(origin.getX() - maxDistanceFromOrigin, origin.getY(), origin.getZ() - maxDistanceFromOrigin);
        BedrockOreFeatureConfig.WorldLayer worldLayer = config.worldLayer;
        WorldGenLevel world = context.level();
        RandomSource random = context.random();
        List<BedrockOreFeatureConfig.Target> targets = config.targets;

        int x = origin.getX();
        int y = origin.getY();
        int z = origin.getZ();
        ArrayList<BlockPos>[] foundBlocks = new ArrayList[]{new ArrayList<BlockPos>(), new ArrayList<BlockPos>(), new ArrayList<BlockPos>(), new ArrayList<BlockPos>(), new ArrayList<BlockPos>()};

        BlockPos newOrigin;
        //find the bedrock
        if(!worldLayer.equals(BedrockOreFeatureConfig.WorldLayer.ALL)) {
            for (byte yIndex = 0; yIndex < 5; yIndex++) {
                for (int xIndex = 0; xIndex < 1 + (maxDistanceFromOrigin * 2); xIndex++) {
                    for (int zIndex = 0; zIndex < 1 + (maxDistanceFromOrigin * 2); zIndex++) {
                        mutable.set(x - 1 - maxDistanceFromOrigin + xIndex, worldLayer == BedrockOreFeatureConfig.WorldLayer.BOTTOM ? y + 4 - yIndex : y - 4 + yIndex, z - 1 - maxDistanceFromOrigin + zIndex);
                        if (targets.stream().anyMatch((rule) -> rule.target.test(world.getBlockState(mutable), random))) {
                            foundBlocks[yIndex].add(mutable.immutable());
                        }
                    }
                }
            }


            //choose new origin - terminate if none
            ArrayList<BlockPos> newOriginCandidates = new ArrayList<>();
            byte successfulLayers = 0;
            for (byte yIndex = 0; yIndex < 5 && successfulLayers < 3; yIndex++) {
                ArrayList<BlockPos> currentList = foundBlocks[yIndex];
                if (!currentList.isEmpty()) {
                    for (byte i = 0; i < 3 - successfulLayers; i++) {
                        newOriginCandidates.addAll(currentList);
                    }
                    successfulLayers++;
                }
            }
            if (successfulLayers == 0) return false;

            newOrigin = newOriginCandidates.get(random.nextInt(newOriginCandidates.size() - 1));
        } else if(targets.stream().noneMatch((rule) -> rule.target.test(world.getBlockState(origin), random))) {
            return false;
        } else {
            newOrigin = origin;
        }

        ArrayList<Pair<RuleTest, BlockState>> targetsArrayList = new ArrayList<>();
        if (targets.stream().noneMatch((target) -> {
            targetsArrayList.add(new Pair<>(target.target, target.state));
            if (target.target.test(world.getBlockState(newOrigin), random)) {
                world.setBlock(newOrigin, target.state, 3);
                return true;
            }
            return false;
        })) {
            return false;
        }

        //propagate through all remaining ores
        Propagator[] propagators = new Propagator[config.propagatorCount];
        boolean[] propsDead = new boolean[config.propagatorCount];
        int size = random.nextInt(config.minSize, config.maxSize) - 1;
        byte propagatorsRemaining = config.propagatorCount;
        for(byte i = 0; i < config.propagatorCount; i++){
            propagators[i] = new Propagator(newOrigin);
            propsDead[i] = false;
        }
        for(byte i = 0; i < size && propagatorsRemaining > 0;) {
            for(byte j = 0;j < propagators.length && i < size; j++){
                Propagator p = propagators[j];
                if(!propsDead[j]) {
                    if (p.propagate(world, random, targetsArrayList)) {
                        size--;
                    } else {
                        propsDead[j] = true;
                        propagatorsRemaining--;
                    }
                }
            }
        }

        return true;
    }

}


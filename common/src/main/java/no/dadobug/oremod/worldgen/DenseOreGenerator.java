package no.dadobug.oremod.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

import java.util.List;

public class DenseOreGenerator extends Feature<OreConfiguration> {


    public DenseOreGenerator(Codec<OreConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<OreConfiguration> context) {
        WorldGenLevel level = context.level();
        OreConfiguration config = context.config();
        List<OreConfiguration.TargetBlockState> targetStates = config.targetStates;
        BlockPos origin = context.origin();
        RandomSource random = context.random();



        for(int y=0; y<level.getHeight(); y++){
            for(int x=0; x<16; x++){
                for(int z=0; z<16; z++){
                    BlockPos pos = origin.offset(x, y, z);
                    BlockState state = level.getBlockState(pos);
                    targetStates.forEach((target) -> {
                        if(target.target.test(state, random))level.setBlock(pos, target.state, 3);
                    });
                }
            }
        }

        return true;
    }
}

package no.dadobug.oremod.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import no.dadobug.oremod.util.RegenData;
import no.dadobug.oremod.util.RegenFluidData;

public class FracturedBedrock extends RegenerativeBlock{
    public FracturedBedrock(Properties settings, RegenData data, RegenFluidData fluidData) {
        super(settings, data, fluidData);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
    }


}

package no.dadobug.oremod.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.state.BlockState;

public class FracturedBedrock extends RegenerativeBlock{
    public FracturedBedrock(Properties settings, boolean ReplaceWithBlock, boolean infinite, boolean silk_able, BlockState replaceBlock, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, boolean onlyValidTools) {
        super(settings, ReplaceWithBlock, infinite, silk_able, replaceBlock, damageFunction, onlyValidTools);
    }

    public FracturedBedrock(Properties settings, boolean ReplaceWithBlock, IntProvider durabilityProvider, boolean infinite, boolean silk_able, BlockState replaceBlock, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, boolean onlyValidTools) {
        super(settings, ReplaceWithBlock, durabilityProvider, infinite, silk_able, replaceBlock, damageFunction, onlyValidTools);
    }

    public FracturedBedrock(Properties settings, boolean ReplaceWithBlock, UniformInt experienceDropped, boolean infinite, boolean silk_able, BlockState replaceBlock, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, boolean onlyValidTools) {
        super(settings, ReplaceWithBlock, experienceDropped, infinite, silk_able, replaceBlock, damageFunction, onlyValidTools);
    }

    public FracturedBedrock(Properties settings, boolean ReplaceWithBlock, int XPmin, int XPmax, int DurabilityMin, int DurabilityMax, boolean infinite, boolean silk_able, BlockState replaceBlock, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, boolean onlyValidTools) {
        super(settings, ReplaceWithBlock, XPmin, XPmax, DurabilityMin, DurabilityMax, infinite, silk_able, replaceBlock, damageFunction, onlyValidTools);
    }

    public FracturedBedrock(Properties settings, boolean ReplaceWithBlock, int XPmin, int XPmax, IntProvider durabilityProvider, boolean infinite, boolean silk_able, BlockState replaceBlock, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, boolean onlyValidTools) {
        super(settings, ReplaceWithBlock, XPmin, XPmax, durabilityProvider, infinite, silk_able, replaceBlock, damageFunction, onlyValidTools);
    }

    public FracturedBedrock(Properties settings, boolean ReplaceWithBlock, UniformInt experienceDropped, IntProvider durabilityProvider, boolean infinite, boolean silk_able, BlockState replaceBlock, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, boolean onlyValidTools) {
        super(settings, ReplaceWithBlock, experienceDropped, durabilityProvider, infinite, silk_able, replaceBlock, damageFunction, onlyValidTools);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
    }


}

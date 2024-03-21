package no.dadobug.oremod.util;

import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.state.BlockState;

public class RegenData {

    private final UniformInt experienceDropped;
    private final PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction;
    private final IntProvider durabilityProvider;
    private final boolean infinite;
    private final boolean silk_able;
    private final BlockState replaceBlock;
    private final boolean onlyValidTools;
    private final boolean ReplaceWithBlock;



    public RegenData(boolean ReplaceWithBlock, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, boolean infinite, boolean silk_able, BlockState replaceBlock, boolean onlyValidTools) {
        this(ReplaceWithBlock, UniformInt.of(0, 0), damageFunction, UniformInt.of(1, 1), infinite, silk_able, replaceBlock, onlyValidTools);
    }
    public RegenData(boolean ReplaceWithBlock, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, IntProvider durabilityProvider, boolean infinite, boolean silk_able, BlockState replaceBlock, boolean onlyValidTools) {
        this(ReplaceWithBlock, UniformInt.of(0, 0), damageFunction, durabilityProvider, infinite, silk_able, replaceBlock, onlyValidTools);
    }
    public RegenData(boolean ReplaceWithBlock, UniformInt experienceDropped, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, boolean infinite, boolean silk_able, BlockState replaceBlock, boolean onlyValidTools) {
        this(ReplaceWithBlock, experienceDropped, damageFunction, UniformInt.of(1, 1), infinite, silk_able, replaceBlock, onlyValidTools);
    }

    public RegenData(boolean ReplaceWithBlock, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, int DurabilityMin, int DurabilityMax, boolean infinite, boolean silk_able, BlockState replaceBlock, boolean onlyValidTools) {
        this(ReplaceWithBlock, UniformInt.of(0, 0), damageFunction, UniformInt.of(DurabilityMin, DurabilityMax), infinite, silk_able, replaceBlock, onlyValidTools);
    }
    public RegenData(boolean ReplaceWithBlock, int XPmin, int XPmax, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, boolean infinite, boolean silk_able, BlockState replaceBlock, boolean onlyValidTools) {
        this(ReplaceWithBlock, UniformInt.of(XPmin, XPmax), damageFunction, UniformInt.of(1, 1), infinite, silk_able, replaceBlock, onlyValidTools);
    }
    public RegenData(boolean ReplaceWithBlock, int XPmin, int XPmax, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, int DurabilityMin, int DurabilityMax, boolean infinite, boolean silk_able, BlockState replaceBlock, boolean onlyValidTools) {
        this(ReplaceWithBlock, UniformInt.of(XPmin, XPmax), damageFunction, UniformInt.of(DurabilityMin, DurabilityMax), infinite, silk_able, replaceBlock, onlyValidTools);
    }

    public RegenData(boolean ReplaceWithBlock, UniformInt experienceDropped, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, int DurabilityMin, int DurabilityMax, boolean infinite, boolean silk_able, BlockState replaceBlock, boolean onlyValidTools) {
        this(ReplaceWithBlock, experienceDropped, damageFunction, UniformInt.of(DurabilityMin, DurabilityMax), infinite, silk_able, replaceBlock, onlyValidTools);
    }
    public RegenData(boolean ReplaceWithBlock, int XPmin, int XPmax, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, IntProvider durabilityProvider, boolean infinite, boolean silk_able, BlockState replaceBlock, boolean onlyValidTools) {
        this(ReplaceWithBlock, UniformInt.of(XPmin, XPmax), damageFunction, durabilityProvider, infinite, silk_able, replaceBlock, onlyValidTools);
    }

    public RegenData(boolean ReplaceWithBlock, UniformInt experienceDropped, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, IntProvider durabilityProvider, boolean infinite, boolean silk_able, BlockState replaceBlock, boolean onlyValidTools) {
        this.ReplaceWithBlock = ReplaceWithBlock;
        this.experienceDropped = experienceDropped;
        this.damageFunction = damageFunction;
        this.durabilityProvider = durabilityProvider;
        this.infinite = infinite;
        this.silk_able = silk_able;
        this.replaceBlock = replaceBlock;
        this.onlyValidTools = onlyValidTools;
    }

    public UniformInt getExperienceDropped() {
        return experienceDropped;
    }

    public PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> getDamageFunction() {
        return damageFunction;
    }

    public IntProvider getDurabilityProvider() {
        return durabilityProvider;
    }

    public boolean isInfinite() {
        return infinite;
    }

    public boolean isSilk_able() {
        return silk_able;
    }

    public BlockState getReplaceBlock() {
        return replaceBlock;
    }

    public boolean isOnlyValidTools() {
        return onlyValidTools;
    }

    public boolean isReplaceWithBlock() {
        return ReplaceWithBlock;
    }
}

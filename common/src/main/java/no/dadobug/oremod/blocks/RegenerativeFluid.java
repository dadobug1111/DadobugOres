package no.dadobug.oremod.blocks;


import net.minecraft.core.BlockPos;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BottleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class RegenerativeFluid extends RegenerativeBlock implements BucketPickup {
    protected final Fluid fluid;
    protected final ItemStack bucketItem;
    protected final ItemStack bottleItem;
    protected final Optional<SoundEvent> BucketSound;

    public RegenerativeFluid(BlockBehaviour.Properties settings, Boolean ReplaceWithBedrock, Fluid fluidIn, int XPmin, int XPmax, int DurabilityMin, int DurabilityMax, boolean infinite, boolean silk_able, BlockState replaceBlock, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, boolean onlyValidTools) {
        this(settings, ReplaceWithBedrock, ItemStack.EMPTY, fluidIn, UniformInt.of(XPmin, XPmax), UniformInt.of(DurabilityMin, DurabilityMax), infinite, silk_able, replaceBlock, damageFunction, onlyValidTools);
    }
    public RegenerativeFluid(BlockBehaviour.Properties settings, Boolean ReplaceWithBedrock, ItemStack bucketIn, Optional<SoundEvent> bucketSoundIn, int XPmin, int XPmax, int DurabilityMin, int DurabilityMax, boolean infinite, boolean silk_able, BlockState replaceBlock, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, boolean onlyValidTools) {
        this(settings, ReplaceWithBedrock, ItemStack.EMPTY, bucketIn, bucketSoundIn, UniformInt.of(XPmin, XPmax), UniformInt.of(DurabilityMin, DurabilityMax), infinite, silk_able, replaceBlock, damageFunction, onlyValidTools);

    }

    public RegenerativeFluid(BlockBehaviour.Properties settings, Boolean ReplaceWithBedrock, ItemStack bottlein, Fluid fluidIn, int XPmin, int XPmax, int DurabilityMin, int DurabilityMax, boolean infinite, boolean silk_able, BlockState replaceBlock, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, boolean onlyValidTools) {
        this(settings, ReplaceWithBedrock, bottlein, fluidIn, UniformInt.of(XPmin, XPmax), UniformInt.of(DurabilityMin, DurabilityMax), infinite, silk_able, replaceBlock, damageFunction, onlyValidTools);
    }
    public RegenerativeFluid(BlockBehaviour.Properties settings, Boolean ReplaceWithBedrock, ItemStack bottlein, ItemStack bucketIn, Optional<SoundEvent> bucketSoundIn, int XPmin, int XPmax, int DurabilityMin, int DurabilityMax, boolean infinite, boolean silk_able, BlockState replaceBlock, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, boolean onlyValidTools) {
        this(settings, ReplaceWithBedrock, bottlein, bucketIn, bucketSoundIn, UniformInt.of(XPmin, XPmax), UniformInt.of(DurabilityMin, DurabilityMax), infinite, silk_able, replaceBlock, damageFunction, onlyValidTools);

    }
    public RegenerativeFluid(BlockBehaviour.Properties settings, Boolean ReplaceWithBedrock, ItemStack bottlein, int XPmin, int XPmax, int DurabilityMin, int DurabilityMax, boolean infinite, boolean silk_able, BlockState replaceBlock, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, boolean onlyValidTools) {
        this(settings, ReplaceWithBedrock, bottlein, Fluids.EMPTY, UniformInt.of(XPmin, XPmax), UniformInt.of(DurabilityMin, DurabilityMax), infinite, silk_able, replaceBlock, damageFunction, onlyValidTools);

    }

    public RegenerativeFluid(BlockBehaviour.Properties settings, Boolean ReplaceWithBedrock, ItemStack bottlein, Fluid fluidIn, UniformInt experienceDropped, IntProvider durabilityProvider, boolean infinite, boolean silk_able, BlockState replaceBlock, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, boolean onlyValidTools) {
        super(settings, ReplaceWithBedrock, experienceDropped, durabilityProvider, infinite, silk_able, replaceBlock, damageFunction, onlyValidTools);
        this.fluid = fluidIn;
        this.bucketItem = ItemStack.EMPTY;
        this.bottleItem = bottlein;
        this.BucketSound = fluidIn.getPickupSound();
    }
    public RegenerativeFluid(BlockBehaviour.Properties settings, Boolean ReplaceWithBedrock, ItemStack bottlein, ItemStack bucketIn, Optional<SoundEvent> bucketSoundIn, UniformInt experienceDropped, IntProvider durabilityProvider, boolean infinite, boolean silk_able, BlockState replaceBlock, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, boolean onlyValidTools) {
        super(settings, ReplaceWithBedrock, experienceDropped, durabilityProvider, infinite, silk_able, replaceBlock, damageFunction, onlyValidTools);
        this.fluid = Fluids.EMPTY;
        this.bucketItem = bucketIn;
        this.bottleItem = bottlein;
        this.BucketSound = bucketSoundIn;

    }

    @Override
    public ItemStack pickupBlock(LevelAccessor world, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof RegenerativeBlockEntity e && !world.isClientSide()) {
            e.damageBlockFluid(state);
        }
        if (this.fluid != Fluids.EMPTY) {
            return new ItemStack(this.fluid.getBucket());
        }
        return this.bucketItem.copy();
    }

    @Override
    public Optional<SoundEvent> getPickupSound() {
        if (this.fluid != Fluids.EMPTY) {
            return this.fluid.getPickupSound();
        }
        return BucketSound;
    }

    @Override
    public FluidState getFluidState(BlockState state) { return this.fluid.defaultFluidState(); }

    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        state.getFluidState().randomTick(world, pos, random);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if(this.bottleItem != ItemStack.EMPTY) {
            AtomicBoolean used = new AtomicBoolean(false);
            player.getHandSlots().forEach((stack) -> {
                if (stack.getItem() instanceof BottleItem && !used.get()) {
                    if(!player.isCreative())stack.shrink(1);
                    used.set(true);
                    player.getInventory().add(this.bottleItem.copy());
                }
            });
            if (used.get()) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity instanceof RegenerativeBlockEntity e && !world.isClientSide()) {
                    e.damageBlockFluid(state);
                }
                return InteractionResult.SUCCESS;
            }
        }
        return super.use(state, world, pos, player, hand, hit);
    }

}

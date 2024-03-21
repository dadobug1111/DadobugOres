package no.dadobug.oremod.util;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import java.util.Optional;

public class RegenFluidData {
    private final Fluid fluid;
    private final ItemStack bucketItem;
    private final Optional<SoundEvent> BucketSound;
    private final ItemStack bottleItem;

    public RegenFluidData(Fluid fluid, ItemStack bottleItem){
        this(fluid, ItemStack.EMPTY, fluid.getPickupSound(), bottleItem);
    }
    public RegenFluidData(Fluid fluid){
        this(fluid, ItemStack.EMPTY);
    }
    public RegenFluidData(ItemStack bottleItem){
        this(Fluids.EMPTY, bottleItem);
    }

    public RegenFluidData(ItemStack bucketItem, Optional<SoundEvent> bucketSound, ItemStack bottleItem){
        this(Fluids.EMPTY, bucketItem, bucketSound, bottleItem);
    }
    public RegenFluidData(ItemStack bucketItem, Optional<SoundEvent> bucketSound){
        this(bucketItem, bucketSound, ItemStack.EMPTY);
    }
    public RegenFluidData(){
        this(ItemStack.EMPTY);
    }

    private RegenFluidData(Fluid fluid, ItemStack bucketItem, Optional<SoundEvent> bucketSound, ItemStack bottleItem) {
        this.fluid = fluid;
        this.bucketItem = bucketItem;
        this.BucketSound = bucketSound;
        this.bottleItem = bottleItem;
    }

    public Fluid getFluid() {
        return fluid;
    }

    public ItemStack getBucketItem() {
        return bucketItem;
    }

    public Optional<SoundEvent> getBucketSound() {
        return BucketSound;
    }

    public ItemStack getBottleItem() {
        return bottleItem;
    }
}

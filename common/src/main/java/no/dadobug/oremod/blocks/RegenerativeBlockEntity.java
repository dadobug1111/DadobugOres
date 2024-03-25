package no.dadobug.oremod.blocks;

import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import no.dadobug.oremod.EntryModule;


public class RegenerativeBlockEntity extends BlockEntity {

    private int durability;
    private BlockState keepstate;
    private Player lastPlayer = null;
    private ItemStack lastItem = ItemStack.EMPTY;
    private boolean regenComplete = true;
    private boolean stacksDropped = false;
    private boolean scronch = false;

    public RegenerativeBlockEntity(BlockPos pos, BlockState state) {
        super(EntryModule.REGENERATIVEBLOCKTYPE.get(), pos, state);
        this.keepstate = state;
        this.durability = -1;

    }

    public void resetDurability(){
        EntryModule.LOGGER.debug("resetDurability");
        if(this.keepstate.getBlock() instanceof  RegenerativeBlock block) {
            this.durability = block.durabilityProvider.sample(this.level.random);
            this.setChanged();
        }
    }



    public void setDurability(int durability){
        EntryModule.LOGGER.debug("setDurability");
        this.durability = durability;
        this.setChanged();
    }

    public BlockState getKeepstate() {
        return this.keepstate;
    }

    public int getDurability() {
        return durability;
    }

    public ItemStack getLastItem() {
        return lastItem;
    }

    public void setStacksDropped(boolean stacksDropped) {
        this.stacksDropped = stacksDropped;
    }

    public void registerMiningParameters(Player player, BlockState state){
        EntryModule.LOGGER.debug("registerMiningParameters - " + this.keepstate.toString() + " at " + this.worldPosition.toShortString());
        this.regenComplete = false;
        this.lastItem = player.getMainHandItem();
        this.lastPlayer = player;
        this.damageBlock(state);
        this.setChanged();
    }

    public void damageBlock(BlockState state){
        EntryModule.LOGGER.debug("damage - " + this.keepstate.toString() + " at " + this.worldPosition.toShortString());
        Block block = state.getBlock();
        if(block instanceof RegenerativeBlock reBlock){
            if(!reBlock.isInfinite() && !(state.is(EntryModule.CORE_TAG) && (EnchantmentHelper.getItemEnchantmentLevel(EntryModule.EXTRACTION.get(), this.lastItem) > 0)) && !(state.is(EntryModule.FRACTURE_TAG) && (EnchantmentHelper.getItemEnchantmentLevel(EntryModule.CURSE_OF_FRACTURING.get(), this.lastItem) > 0)) && !(reBlock.isSilk_able() && (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, this.lastItem) > 0))){
                if(this.durability < 0){
                    this.resetDurability();
                }
                for(int i = 0; i<(EnchantmentHelper.getItemEnchantmentLevel(EntryModule.SHATTERING.get(), this.lastItem)+1); i++) {
                    this.durability = reBlock.damageFunction.apply(this.durability, EnchantmentHelper.getItemEnchantmentLevel(EntryModule.SHATTERING.get(), this.lastItem), EnchantmentHelper.getItemEnchantmentLevel(EntryModule.GENTLE_MINING.get(), this.lastItem), this.getLevel().getRandom());
                }
                this.setChanged();
            }
        }
    }

    public void damageBlockFluid(BlockState state){
        EntryModule.LOGGER.debug("damage fluid - " + this.keepstate.toString() + " at " + this.worldPosition.toShortString());
        Block block = state.getBlock();
        if(block instanceof RegenerativeBlock reBlock){
            if(!reBlock.isInfinite()){
                if(this.durability < 0){
                    this.resetDurability();
                }
                for(int i = 0; i<(EnchantmentHelper.getItemEnchantmentLevel(EntryModule.SHATTERING.get(), this.lastItem)+1); i++) {
                    this.durability--;
                }
                this.setChanged();
            }
        }
    }

    public boolean regen(Level worldIn, BlockState newState) {
        EntryModule.LOGGER.debug("regen - " + this.keepstate.toString() + " at " + this.worldPosition.toShortString());

        if(this.scronch) {
            this.setChanged();
            return false;
        }
        if(this.keepstate.is(EntryModule.HOLLOW_TAG) && newState.is(EntryModule.CORE_TAG)) {
            return false;
        }
        if (this.lastPlayer != null){
            if (this.lastPlayer.isCreative() && !this.regenComplete){
                return false;
            }
        }

        if(worldIn != null && !worldIn.isClientSide()) {
            if(this.regenComplete){
                this.damageBlock(this.keepstate);
                this.setChanged();
            }
            if (this.keepstate.is(EntryModule.FRACTURE_TAG) && (EnchantmentHelper.getItemEnchantmentLevel(EntryModule.CURSE_OF_FRACTURING.get(), this.lastItem) > 0)) {
                worldIn.setBlockAndUpdate(this.worldPosition, EntryModule.BEDROCK_FRACTURED.ore().get().defaultBlockState().setValue(OresBlockStates.REPLACE_WITH_BLOCK, this.keepstate.getValue(OresBlockStates.REPLACE_WITH_BLOCK)));
                if (!this.stacksDropped && !this.regenComplete) {
                    if(this.lastPlayer.hasCorrectToolForDrops(this.keepstate))this.keepstate.getBlock().playerDestroy(worldIn, this.lastPlayer, this.worldPosition, this.keepstate, this, this.lastItem);
                    this.stacksDropped = false;
                    EntryModule.LOGGER.debug("forced drops - " + this.keepstate.toString() + " at " + this.worldPosition.toShortString());
                }
                this.setChanged();
                return false;
            } else if (this.keepstate.is(EntryModule.CORE_TAG) && ((EnchantmentHelper.getItemEnchantmentLevel(EntryModule.EXTRACTION.get(), this.lastItem) > 0))){
                worldIn.setBlockAndUpdate(this.worldPosition, EntryModule.BEDROCK_HOLLOW.get().defaultBlockState().setValue(OresBlockStates.REPLACE_WITH_BLOCK, this.keepstate.hasProperty(OresBlockStates.REPLACE_WITH_BLOCK)));
                if (!this.stacksDropped && !this.regenComplete) {
                    if(this.lastPlayer.hasCorrectToolForDrops(this.keepstate))this.keepstate.getBlock().playerDestroy(worldIn, this.lastPlayer, this.worldPosition, this.keepstate, this, this.lastItem);
                    this.stacksDropped = false;
                    EntryModule.LOGGER.debug("forced drops - " + this.keepstate.toString() + " at " + this.worldPosition.toShortString());
                }
                this.setChanged();
                return false;
            } else if((this.keepstate.hasProperty(OresBlockStates.REPLACE_WITH_BLOCK) && (((EnchantmentHelper.getItemEnchantmentLevel(EntryModule.SHATTERING.get(), this.lastItem) > 0) && ((RegenerativeBlock) this.keepstate.getBlock()).isInfinite()) || (EnchantmentHelper.getItemEnchantmentLevel(EntryModule.CURSE_OF_SHATTERING.get(), this.lastItem) > 0))) || (this.durability < 1 && !((RegenerativeBlock) this.keepstate.getBlock()).isInfinite())  || (EnchantmentHelper.getItemEnchantmentLevel(EntryModule.ARCANE_EXTRACTION.get(), this.lastItem) > 0)) {
                if(this.keepstate.hasProperty(OresBlockStates.REPLACE_WITH_BLOCK)) {
                    worldIn.setBlockAndUpdate(this.worldPosition, ((RegenerativeBlock) this.keepstate.getBlock()).getReplaceBlock());
                    if (!this.stacksDropped && !this.regenComplete) {
                        if(this.lastPlayer.hasCorrectToolForDrops(this.keepstate))this.keepstate.getBlock().playerDestroy(worldIn, this.lastPlayer, this.worldPosition, this.keepstate, this, this.lastItem);
                        this.stacksDropped = false;
                        EntryModule.LOGGER.debug("forced drops - " + this.keepstate.toString() + " at " + this.worldPosition.toShortString());
                    }
                }
                this.setChanged();
                return false;
            } else if(((RegenerativeBlock) this.keepstate.getBlock()).isSilk_able() && (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, this.lastItem) > 0)){
                return false;
            } else if(newState != this.keepstate && (this.durability > 0 || ((RegenerativeBlock) this.keepstate.getBlock()).isInfinite())){
                EntryModule.LOGGER.debug("put it back - " + this.keepstate.toString() + " at " + this.worldPosition.toShortString());
                worldIn.setBlockAndUpdate(this.worldPosition, this.keepstate);
                if (!this.stacksDropped && !this.regenComplete) {
                    if(this.lastPlayer.hasCorrectToolForDrops(this.keepstate))this.keepstate.getBlock().playerDestroy(worldIn, this.lastPlayer, this.worldPosition, this.keepstate, this, this.lastItem);
                    EntryModule.LOGGER.debug("forced drops - " + this.keepstate.toString() + " at " + this.worldPosition.toShortString());
                }
                this.lastItem = ItemStack.EMPTY;
                this.regenComplete = true;
                this.lastPlayer = null;
                this.stacksDropped = false;
                this.setChanged();
                return true;
            }
        }
        return false;
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);

        tag.putInt("durability", this.durability);
        tag.put("keepstate", NbtUtils.writeBlockState(this.keepstate));
        tag.putBoolean("scronch", this.scronch);

    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        durability = tag.getInt("durability");
        keepstate = NbtUtils.readBlockState(tag.getCompound("keepstate"));
        scronch = tag.getBoolean("scronch");
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }

    public void scronch() {
        this.scronch = true;
        this.setChanged();
    }
}

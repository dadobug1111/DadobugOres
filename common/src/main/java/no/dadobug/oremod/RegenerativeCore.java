package no.dadobug.oremod;


import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import no.dadobug.oremod.blocks.RegenerativeBlock;

import java.util.List;
import java.util.Map;


public class RegenerativeCore extends BlockItem {
    private final MutableComponent toolTip;
    public RegenerativeCore(Properties settings, Block coreblock, MutableComponent toolTip) {
        super(coreblock, settings);
        this.toolTip = toolTip;
    }

    @Override
    public InteractionResult place(BlockPlaceContext context) {

        if (!context.canPlace()) {
            return InteractionResult.FAIL;
        }

            BlockPlaceContext itemPlacementContext = this.updatePlacementContext(context);
            if (itemPlacementContext == null) {
                return InteractionResult.FAIL;
            } else {
                BlockState blockState = this.getPlacementState(itemPlacementContext);
                if (blockState == null) {
                    EntryModule.LOGGER.debug("failed get state");
                    return InteractionResult.FAIL;
                } else if(!itemPlacementContext.getLevel().getBlockState(itemPlacementContext.getClickedPos()).is(EntryModule.HOLLOW_TAG)){
                    EntryModule.LOGGER.debug("failed check hollow");
                    return InteractionResult.FAIL;
                } else if (!this.placeBlock(itemPlacementContext, blockState)) {
                    EntryModule.LOGGER.error("failed place");
                    return InteractionResult.FAIL;
                } else {
                    BlockPos blockPos = itemPlacementContext.getClickedPos();
                    Level world = itemPlacementContext.getLevel();
                    Player playerEntity = itemPlacementContext.getPlayer();
                    ItemStack itemStack = itemPlacementContext.getItemInHand();
                    BlockState blockState2 = world.getBlockState(blockPos);
                    if (blockState2.is(blockState.getBlock())) {
                        blockState2 = this.updateBlockStateFromTag(blockPos, world, itemStack, blockState2);
                        this.updateCustomBlockEntityTag(blockPos, world, playerEntity, itemStack, blockState2);
                        blockState2.getBlock().setPlacedBy(world, blockPos, blockState2, playerEntity, itemStack);
                        if (playerEntity instanceof ServerPlayer serverPlayer) {
                            CriteriaTriggers.PLACED_BLOCK.trigger(serverPlayer, blockPos, itemStack);
                        }
                    }

                    SoundType soundType = blockState2.getSoundType();
                    world.playSound(playerEntity, blockPos, this.getPlaceSound(blockState2), SoundSource.BLOCKS, (soundType.getVolume() + 1.0f) / 2.0f, soundType.getPitch() * 0.8f);
                    world.gameEvent(GameEvent.BLOCK_PLACE, blockPos, GameEvent.Context.of(playerEntity, blockState2));
                    if (playerEntity == null || !playerEntity.getAbilities().instabuild) {
                        itemStack.shrink(1);
                    }

                    return InteractionResult.sidedSuccess(world.isClientSide());
                }
            }

    }

    @Override
    protected SoundEvent getPlaceSound(BlockState state) {
        return SoundEvents.END_PORTAL_FRAME_FILL;
    }


    private BlockState updateBlockStateFromTag(BlockPos pos, Level level, ItemStack stack, BlockState state) {
        BlockState blockState = state;
        CompoundTag compoundTag = stack.getTag();
        if (compoundTag != null) {
            CompoundTag compoundTag2 = compoundTag.getCompound(BLOCK_STATE_TAG);
            StateDefinition<Block, BlockState> stateDefinition = blockState.getBlock().getStateDefinition();
            for (String string : compoundTag2.getAllKeys()) {
                Property<?> property = stateDefinition.getProperty(string);
                if (property == null) continue;
                String string2 = compoundTag2.get(string).getAsString();
                blockState = RegenerativeCore.updateState(blockState, property, string2);
            }
        }
        if (blockState != state) {
            level.setBlock(pos, blockState, 2);
        }
        return blockState;
    }


    private static <T extends Comparable<T>> BlockState updateState(BlockState state, Property<T> property, String valueIdentifier) {
        return property.getValue(valueIdentifier).map(comparable -> (BlockState)state.setValue(property, comparable)).orElse(state);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }



    @Override
    public void appendHoverText(ItemStack itemStack, Level world, List<Component> tooltip, TooltipFlag tooltipFlag) {

        tooltip.add(this.toolTip);
        tooltip.add( MutableComponent.create(new TranslatableContents("item.dadobugores.regen_need_host.tooltip")).withStyle(ChatFormatting.LIGHT_PURPLE));
        CompoundTag nbt = getBlockEntityData(itemStack);
        if(nbt != null) {
            if (nbt.contains("durability")) {
                int durability = nbt.getInt("durability");
                if(!((RegenerativeBlock)this.getBlock()).isInfinite() && durability != -1){
                    tooltip.add(MutableComponent.create(new TranslatableContents("item.dadobugores.durability.tooltip")).append(String.valueOf(durability)));
                }
            }
        }


    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> stacks) {
        if (this.allowedIn(group)) {
            stacks.add(new ItemStack(this));
        }

    }

    @Override
    public void registerBlocks(Map<Block, Item> map, Item item) {
    }

    @Override
    public String getDescriptionId() {
        return this.getOrCreateDescriptionId();
    }
}

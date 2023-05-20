package no.dadobug.oremod.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.material.PushReaction;
import no.dadobug.oremod.EntryModule;
import org.jetbrains.annotations.Nullable;

public class RegenerativeBlock extends BaseEntityBlock {
    protected final UniformInt experienceDropped;
    protected final IntProvider durabilityProvider;
    protected final PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction;
    protected final boolean infinite;
    protected final boolean silk_able;
    protected final BlockState replaceBlock;
    private boolean onlyValidTools;


    public RegenerativeBlock(BlockBehaviour.Properties settings, boolean ReplaceWithBlock, boolean infinite, boolean silk_able, BlockState replaceBlock, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, boolean onlyValidTools) {
        this(settings, ReplaceWithBlock, UniformInt.of(0, 0), UniformInt.of(1, 1), infinite, silk_able, replaceBlock, damageFunction, onlyValidTools);
    }

    public RegenerativeBlock(BlockBehaviour.Properties settings, boolean ReplaceWithBlock, IntProvider durabilityProvider, boolean infinite, boolean silk_able, BlockState replaceBlock, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, boolean onlyValidTools) {
        this(settings, ReplaceWithBlock, UniformInt.of(0, 0), durabilityProvider, infinite, silk_able, replaceBlock, damageFunction, onlyValidTools);
    }

    public RegenerativeBlock(BlockBehaviour.Properties settings, boolean ReplaceWithBlock, UniformInt experienceDropped, boolean infinite, boolean silk_able, BlockState replaceBlock, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, boolean onlyValidTools) {
        this(settings, ReplaceWithBlock, experienceDropped, UniformInt.of(1, 1), infinite, silk_able, replaceBlock, damageFunction, onlyValidTools);
    }


    public RegenerativeBlock(BlockBehaviour.Properties settings, boolean ReplaceWithBlock, int XPmin, int XPmax, int DurabilityMin, int DurabilityMax, boolean infinite, boolean silk_able, BlockState replaceBlock, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, boolean onlyValidTools) {
        this(settings, ReplaceWithBlock, UniformInt.of(XPmin, XPmax), UniformInt.of(DurabilityMin, DurabilityMax), infinite, silk_able, replaceBlock, damageFunction, onlyValidTools);
    }


    public RegenerativeBlock(BlockBehaviour.Properties settings, boolean ReplaceWithBlock, int XPmin, int XPmax, IntProvider durabilityProvider, boolean infinite, boolean silk_able, BlockState replaceBlock, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, boolean onlyValidTools) {
        this(settings, ReplaceWithBlock, UniformInt.of(XPmin, XPmax), durabilityProvider, infinite, silk_able, replaceBlock, damageFunction, onlyValidTools);
    }

    public RegenerativeBlock(BlockBehaviour.Properties settings, boolean ReplaceWithBlock, UniformInt experienceDropped, IntProvider durabilityProvider, boolean infinite, boolean silk_able, BlockState replaceBlock, PropertyDispatch.QuadFunction<Integer, Integer, Integer, RandomSource, Integer> damageFunction, boolean onlyValidTools){
        super(settings);
        this.experienceDropped = experienceDropped;
        this.durabilityProvider = durabilityProvider;
        this.infinite = infinite;
        this.replaceBlock = replaceBlock;
        this.silk_able = silk_able;
        this.damageFunction = damageFunction;
        this.onlyValidTools = onlyValidTools;
        registerDefaultState(defaultBlockState().setValue(OresBlockStates.REPLACE_WITH_BLOCK, ReplaceWithBlock));
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

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new RegenerativeBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return super.getTicker(level, state, blockEntityType);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> GameEventListener getListener(ServerLevel level, T blockEntity) {
        return super.getListener(level, blockEntity);
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
            BlockEntity blockEntity = world.getBlockEntity(pos);

    }


    @Override
    public float getDestroyProgress(BlockState state, Player player, BlockGetter world, BlockPos pos) {
        if(!player.hasCorrectToolForDrops(state) && this.onlyValidTools)return 0.0f;
        if(state.is(EntryModule.INDESTRUCTABLE_TAG))return 0.0f;
        if(state.is(EntryModule.ENCHANT_ONLY_TAG) && !(EnchantmentHelper.getEnchantmentLevel(EntryModule.SHATTERING.get(), player)>0 || EnchantmentHelper.getEnchantmentLevel(EntryModule.CURSE_OF_FRACTURING.get(), player)>0 || EnchantmentHelper.getEnchantmentLevel(EntryModule.EXTRACTION.get(), player)>0 || EnchantmentHelper.getEnchantmentLevel(EntryModule.CURSE_OF_SHATTERING.get(), player)>0 || EnchantmentHelper.getEnchantmentLevel(EntryModule.GENTLE_MINING.get(), player)>0))return 0.0f;
        return super.getDestroyProgress(state, player, world, pos);
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateManager) {
        stateManager.add(OresBlockStates.REPLACE_WITH_BLOCK);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.BLOCK;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public void spawnAfterBreak(BlockState state, ServerLevel world, BlockPos pos, ItemStack stack, boolean dropExperience) {
        super.spawnAfterBreak(state, world, pos, stack, dropExperience);
        if(dropExperience){
            if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, stack) == 0 && EnchantmentHelper.getItemEnchantmentLevel(EntryModule.CURSE_OF_FRACTURING.get(), stack) == 0 && EnchantmentHelper.getItemEnchantmentLevel(EntryModule.CURSE_OF_SHATTERING.get(), stack) == 0 && EnchantmentHelper.getItemEnchantmentLevel(EntryModule.ARCANE_EXTRACTION.get(), stack) == 0 && EnchantmentHelper.getItemEnchantmentLevel(EntryModule.EXTRACTION.get(), stack) == 0) {
                int i1 = 1 + EnchantmentHelper.getItemEnchantmentLevel(EntryModule.SHATTERING.get(), stack);
                for (int x = 0; x < i1; x++) {
                    int i = this.experienceDropped.sample(world.random);
                    if (i > 0) {
                        this.popExperience(world, pos, ((EnchantmentHelper.getItemEnchantmentLevel(EntryModule.SHATTERING.get(), stack) * (this.infinite ? 2 : 1)) + 1) * i);
                    }
                }
            }
        }
    }


    public void dropMultiStacks(BlockState state, Level world, BlockPos pos, @Nullable BlockEntity blockEntity, Entity entity, ItemStack tool) {
        EntryModule.LOGGER.debug("dropMultiStacks");
        if (world instanceof ServerLevel serverLevel) {
            if (EnchantmentHelper.getItemEnchantmentLevel(EntryModule.CURSE_OF_SHATTERING.get(), tool) < 1 && EnchantmentHelper.getItemEnchantmentLevel(EntryModule.CURSE_OF_FRACTURING.get(), tool) < 1) {

                if ((this.silk_able && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, tool) > 0) || EnchantmentHelper.getItemEnchantmentLevel(EntryModule.ARCANE_EXTRACTION.get(), tool) > 0 || EnchantmentHelper.getItemEnchantmentLevel(EntryModule.EXTRACTION.get(), tool) > 0){
                    Block.getDrops(state, serverLevel, pos, blockEntity, entity, tool).forEach(itemStack -> Block.popResource(serverLevel, pos, itemStack));
                    state.spawnAfterBreak(serverLevel, pos, tool, true);
                } else {
                    int i1 = (1 + EnchantmentHelper.getItemEnchantmentLevel(EntryModule.SHATTERING.get(), tool)) * ((this.infinite && EnchantmentHelper.getItemEnchantmentLevel(EntryModule.SHATTERING.get(), tool) > 0) ? world.random.nextInt(3, 7) : 1);
                    for (int x = 0; x < i1; x++) {
                        Block.getDrops(state, serverLevel, pos, blockEntity, entity, tool).forEach(itemStack -> Block.popResource(serverLevel, pos, itemStack));
                        state.spawnAfterBreak(serverLevel, pos, tool, true);
                    }
                }
            }
            state.spawnAfterBreak(serverLevel, pos, tool, true);
        }


    }

    @Override
    public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
        if(world instanceof ServerLevel serverLevel) {
            BlockEntity entity = serverLevel.getBlockEntity(pos);
            if (entity instanceof RegenerativeBlockEntity regenerativeBlock) {
                regenerativeBlock.registerMiningParameters(player, state);
            }
        }
        super.playerWillDestroy(world, pos, state, player);
    }



    @Override
    public void playerDestroy(Level world, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        EntryModule.LOGGER.debug("afterBreak");
        player.awardStat(Stats.BLOCK_MINED.get(this));
        player.causeFoodExhaustion(0.005F);
        if (blockEntity instanceof RegenerativeBlockEntity regenerativeBlockEntity) {
            regenerativeBlockEntity.setStacksDropped(true);
        }
        this.dropMultiStacks(state, world, pos, blockEntity, player, stack);
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean moved) {
        if(!moved) {
            if (state.hasBlockEntity() && !state.is(newState.getBlock())) {
                BlockEntity entity = world.getBlockEntity(pos);
                if (entity instanceof RegenerativeBlockEntity regenerativeBlockEntity) {
                    boolean regen = regenerativeBlockEntity.regen(world, newState);
                    if (!regen) {
                        world.removeBlockEntity(pos);
                    }
                } else {
                    world.removeBlockEntity(pos);
                }
            } else {
                //fixes old blocks (hopefully)
                world.setBlockEntity(this.newBlockEntity(pos, state));
                BlockEntity entity = world.getBlockEntity(pos);
                if (entity instanceof RegenerativeBlockEntity regenerativeBlockEntity) {
                    boolean regen = regenerativeBlockEntity.regen(world, newState);
                    if (!regen) {
                        world.removeBlockEntity(pos);
                    }
                } else {
                    world.removeBlockEntity(pos);
                }
            }
        } else {
            world.removeBlockEntity(pos);
        }
    }
}

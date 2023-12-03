package no.dadobug.fabric;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import no.dadobug.oremod.EntryModule;
import no.dadobug.oremod.blocks.OresBlockStates;

public class FabricSwapBlock extends Block {

    private final String swapBlock;

    public FabricSwapBlock(Properties properties, String swapBlock) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(OresBlockStates.REPLACE_WITH_BLOCK, false));
        this.swapBlock = swapBlock;
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        BlockState swapState = Registry.BLOCK.get(ResourceLocation.tryParse(swapBlock)).defaultBlockState();

        if(swapState.getBlock().equals(Blocks.AIR)) EntryModule.LOGGER.error("Potential default detected with: " + this);

        if(swapState.hasProperty(OresBlockStates.REPLACE_WITH_BLOCK)){
            swapState.setValue(OresBlockStates.REPLACE_WITH_BLOCK, state.getValue(OresBlockStates.REPLACE_WITH_BLOCK));
        }
        level.setBlock(pos, swapState,3);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        BlockState swapState = Registry.BLOCK.get(ResourceLocation.tryParse(swapBlock)).defaultBlockState();

        if(swapState.getBlock().equals(Blocks.AIR)) EntryModule.LOGGER.error("Potential default detected with: " + this);

        if(swapState.hasProperty(OresBlockStates.REPLACE_WITH_BLOCK)){
            swapState.setValue(OresBlockStates.REPLACE_WITH_BLOCK, state.getValue(OresBlockStates.REPLACE_WITH_BLOCK));
        }
        level.setBlock(pos, swapState,3);

        return InteractionResult.SUCCESS;
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        BlockState swapState = Registry.BLOCK.get(ResourceLocation.tryParse(swapBlock)).defaultBlockState();

        if(swapState.getBlock().equals(Blocks.AIR)) EntryModule.LOGGER.error("Potential default detected with: " + this);

        if(swapState.hasProperty(OresBlockStates.REPLACE_WITH_BLOCK)){
            swapState.setValue(OresBlockStates.REPLACE_WITH_BLOCK, state.getValue(OresBlockStates.REPLACE_WITH_BLOCK));
        }
        level.setBlock(pos, swapState,3);
        super.neighborChanged(state, level, pos, block, fromPos, isMoving);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        BlockState swapState = Registry.BLOCK.get(ResourceLocation.tryParse(swapBlock)).defaultBlockState();

        if(swapState.getBlock().equals(Blocks.AIR)) EntryModule.LOGGER.error("Potential default detected with: " + this);

        if(swapState.hasProperty(OresBlockStates.REPLACE_WITH_BLOCK)){
            swapState.setValue(OresBlockStates.REPLACE_WITH_BLOCK, state.getValue(OresBlockStates.REPLACE_WITH_BLOCK));
        }
        level.setBlock(pos, swapState,3);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateManager) {
        stateManager.add(OresBlockStates.REPLACE_WITH_BLOCK);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean moved) {
        BlockState swapState = Registry.BLOCK.get(ResourceLocation.tryParse(swapBlock)).defaultBlockState();

        if(swapState.getBlock().equals(Blocks.AIR)) EntryModule.LOGGER.error("Potential default detected with: " + this);

        if(swapState.hasProperty(OresBlockStates.REPLACE_WITH_BLOCK)){
            swapState.setValue(OresBlockStates.REPLACE_WITH_BLOCK, state.getValue(OresBlockStates.REPLACE_WITH_BLOCK));
        }
        world.setBlock(pos, swapState,3);
    }
}

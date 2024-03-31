package no.dadobug.oremod.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import no.dadobug.oremod.EntryModule;
import no.dadobug.oremod.util.RegenData;
import no.dadobug.oremod.util.RegenFluidData;

public class FracturedBedrock extends RegenerativeBlock{

    public FracturedBedrock(Properties settings, RegenData data, RegenFluidData fluidData) {
        super(settings, data, fluidData);
    }


    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.tick(state, level, pos, random);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.randomTick(state, level, pos, random);



        switch(random.nextInt(1, 10)){
            case 1, 2, 3, 4, 5, 6, 7 -> {
                for (byte i = 0; i<random.nextInt(1, 4); i++) {
                    BlockPos breakPos = new BlockPos(pos.getX() -2 + random.nextInt(5), pos.getY() -2 + random.nextInt(5), pos.getZ() -2 + random.nextInt(5));
                    BlockState breakState = level.getBlockState(breakPos);
                    if(!breakState.is(EntryModule.FRACTURE_SOURCE_TAG)) {
                        if (breakState.is(EntryModule.REGEN_TAG)) {
                            if (random.nextBoolean() && random.nextBoolean() && random.nextBoolean()) {
                                if (breakState.getBlock() instanceof RegenerativeBlock block && level.getBlockEntity(breakPos) instanceof RegenerativeBlockEntity blockEntity) {
                                    blockEntity.damageBlockFluid(breakState);
                                    ItemStack tool = new ItemStack(Items.NETHERITE_PICKAXE);
                                    tool.enchant(EntryModule.SHATTERING.get(), 5);
                                    block.dropMultiStacks(breakState, level, breakPos, blockEntity, null, tool);

                                    level.destroyBlock(breakPos, false);
                                    continue;
                                }
                            }
                        }
                        if(breakState.getBlock().defaultDestroyTime() > -1.0f) level.destroyBlock(breakPos, true);

                    }
                }
            }

            case 8, 9, 10 -> {
                BlockPos movePos1 = new BlockPos(pos.getX() -2 + random.nextInt(5), pos.getY() -2 + random.nextInt(5), pos.getZ() -2 + random.nextInt(5));
                BlockPos movePos2 = new BlockPos(pos.getX() -2 + random.nextInt(5), pos.getY() -2 + random.nextInt(5), pos.getZ() -2 + random.nextInt(5));
                BlockState moveState1 = level.getBlockState(movePos1);
                BlockState moveState2 = level.getBlockState(movePos2);
                if(!moveState1.is(EntryModule.FRACTURE_SOURCE_TAG) && !moveState2.is(EntryModule.FRACTURE_SOURCE_TAG)) {
                    if ((moveState1.is(EntryModule.REGEN_TAG) || moveState1.getBlock().equals(Blocks.BEDROCK)) && (moveState2.is(EntryModule.REGEN_TAG) || moveState2.getBlock().equals(Blocks.BEDROCK))) {
                        if ((moveState1.is(EntryModule.CORE_TAG) || moveState1.getBlock().equals(Blocks.BEDROCK)) == (moveState2.is(EntryModule.CORE_TAG) || moveState2.getBlock().equals(Blocks.BEDROCK))) {
                            level.setBlock(movePos1, moveState2, 67);
                            level.setBlock(movePos2, moveState1, 67);
                        }
                    } else if (moveState1.is(EntryModule.REGEN_TAG)) {
                        if (moveState1.getBlock() instanceof RegenerativeBlock block) {
                            if (block.isSilk_able()) {
                                level.setBlock(movePos2, moveState1, 3);
                                if (level.getBlockEntity(movePos2) instanceof RegenerativeBlockEntity blockEntity) {
                                    blockEntity.setDurability(blockEntity.getDurability() / 4);
                                }
                                if (level.getBlockEntity(movePos1) instanceof RegenerativeBlockEntity blockEntity) {
                                    blockEntity.setDurability(blockEntity.getDurability() / 2);
                                }
                            }
                        }
                    } else if (moveState2.is(EntryModule.REGEN_TAG)) {
                        if (moveState2.getBlock() instanceof RegenerativeBlock block) {
                            if (block.isSilk_able()) {
                                level.setBlock(movePos1, moveState2, 3);
                                if (level.getBlockEntity(movePos1) instanceof RegenerativeBlockEntity blockEntity) {
                                    blockEntity.setDurability(blockEntity.getDurability() / 4);
                                }
                                if (level.getBlockEntity(movePos2) instanceof RegenerativeBlockEntity blockEntity) {
                                    blockEntity.setDurability(blockEntity.getDurability() / 2);
                                }
                            }
                        }
                    } else if(!moveState1.getBlock().equals(Blocks.BEDROCK) && !moveState2.getBlock().equals(Blocks.BEDROCK)){
                        level.setBlock(movePos1, moveState2, 3);
                        level.setBlock(movePos2, moveState1, 3);
                    }
                }
            }
        }
    }
}

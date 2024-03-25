package no.dadobug.oremod.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class FracturedBedrockEntity extends RegenerativeBlockEntity {

    //private BlockState[][][] fracturedStates;

    public FracturedBedrockEntity(BlockPos pos, BlockState state) {
        super(pos, state);

/*
        fracturedStates = new BlockState[5][5][5];

        BlockPos.MutableBlockPos iteratorPos = new BlockPos.MutableBlockPos();
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        for(byte i = 0; i<5; i++){
            for(byte j = 0; j<5; j++){
                for(byte k = 0; k<5; k++){
                    iteratorPos.set(x - 2 + i, y - 2 + j, z - 2 + k);
                    fracturedStates[i][j][k] = this.level.getBlockState(iteratorPos);
                }
            }
        }

 */
    }


}

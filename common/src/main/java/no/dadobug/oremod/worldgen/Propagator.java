package no.dadobug.oremod.worldgen;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.ArrayList;

public class Propagator {
    private BlockPos pos;

    public Propagator(BlockPos pos){
        this.pos = pos;
    }

    public BlockPos getPos() {
        return pos;
    }

    public boolean propagate(WorldGenLevel world, RandomSource random, ArrayList<Pair<RuleTest, BlockState>> targets){
        ArrayList<BlockPos> possibleChoices = new ArrayList<>();
        boolean LoopSuccess = false;
        for(byte i = 0; i < 6; i++) {
            Direction direction = Direction.from3DDataValue(i);
            if(targets.stream().anyMatch((target) -> target.getFirst().test(world.getBlockState(pos.relative(direction)), random))){
                possibleChoices.add(pos.relative(direction));
                LoopSuccess = true;
            }
        }
        if(LoopSuccess){
            pos = possibleChoices.size()==1?possibleChoices.get(0):possibleChoices.get(random.nextInt(possibleChoices.size() - 1));
            return targets.stream().anyMatch((target) -> {
                if (target.getFirst().test(world.getBlockState(pos), random)) {
                    world.setBlock(pos, target.getSecond(), 3);
                    return true;
                }
                return false;
            });
        } else {
            for(byte x = 0; x < 3; x++) {
                for(byte y = 0; y < 3; y++) {
                    for(byte z = 0; z < 3; z++) {
                        byte finalX = (byte) (x - 1);
                        byte finalY = (byte) (y - 1);
                        byte finalZ = (byte) (z - 1);
                        if(targets.stream().anyMatch((target) -> target.getFirst().test(world.getBlockState(pos.offset(finalX, finalY, finalZ)), random))){
                            possibleChoices.add(pos.offset(finalX, finalY, finalZ));
                            LoopSuccess = true;
                        }

                    }
                }
            }
            if(LoopSuccess){
                pos = possibleChoices.size()==1?possibleChoices.get(0):possibleChoices.get(random.nextInt(possibleChoices.size() - 1));
                return targets.stream().anyMatch((target) -> {
                    if (target.getFirst().test(world.getBlockState(pos), random)) {
                        world.setBlock(pos, target.getSecond(), 3);
                        return true;
                    }
                    return false;
                });
            }
        }
        return false;
    }
}

package no.dadobug.oremod.blocks;

import net.minecraft.world.level.block.state.BlockBehaviour;
import no.dadobug.oremod.configs.BlockConfig;
import no.dadobug.oremod.configs.BlockConfigLambda;

@FunctionalInterface
public interface BlockLambda<T> {

    T get(BlockConfig cfg, BlockConfigLambda<BlockBehaviour.Properties> blockSettings, boolean replaceWithBedrock);
}

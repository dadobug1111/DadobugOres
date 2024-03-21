package no.dadobug.oremod.blocks;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import no.dadobug.oremod.EntryModule;
import no.dadobug.oremod.util.RegenData;
import no.dadobug.oremod.util.RegenFluidData;

public class HollowBedrock extends RegenerativeBlock{

    public HollowBedrock(BlockBehaviour.Properties settings, RegenData data, RegenFluidData fluidData) {
        super(settings, data, fluidData);
    }


    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        if(context.getItemInHand().is(EntryModule.IS_CORE_TAG))return true;
        return super.canBeReplaced(state, context);
    }


}

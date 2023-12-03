package no.dadobug.fabric;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FabricSwapItem extends Item {
    private final String SwapItem;
    public FabricSwapItem(Properties properties, String SwapItem) {
        super(properties);
        this.SwapItem = SwapItem;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        ItemStack swapStack = Registry.ITEM.get(ResourceLocation.tryParse(SwapItem)).getDefaultInstance();
        swapStack.setCount(stack.getCount());

        entity.getSlot(slotId).set(swapStack);


    }

    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        ItemStack swapStack = Registry.ITEM.get(ResourceLocation.tryParse(SwapItem)).getDefaultInstance();
        swapStack.setCount(stack.getCount());

        livingEntity.setItemInHand(livingEntity.getUsedItemHand(), swapStack);
    }
}

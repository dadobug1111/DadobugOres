package no.dadobug.oremod;

import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class RegistryBox {
    RegistryBox(DeferredRegister<Block> normalBlockRegistry, DeferredRegister<Item> itemRegistry){
        this.blockRegistry = normalBlockRegistry;
        this.itemRegistry = itemRegistry;
    }
    private final DeferredRegister<Block> blockRegistry;

    public DeferredRegister<Block> getBlockRegistry(){
        return this.blockRegistry;
    }

    private final DeferredRegister<Item> itemRegistry;

    public DeferredRegister<Item> getItemRegistry(){
        return this.itemRegistry;
    }
}

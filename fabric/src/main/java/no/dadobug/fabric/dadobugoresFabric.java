package no.dadobug.fabric;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import no.dadobug.oremod.EntryModule;


public class dadobugoresFabric implements ModInitializer, ClientModInitializer, DedicatedServerModInitializer {

    @Override
    public void onInitializeClient() {
        EntryModule.init(true);
        EntryModule.initLate(true);
    }

    @Override
    public void onInitializeServer() {
        EntryModule.init(false);
        EntryModule.initLate(false);
    }

    @Override
    public void onInitialize() {
        EntryModule.BLOCK_MAPPING_CONFIG.targetMap.forEach(this::registerSwapBlock);
        EntryModule.ITEM_MAPPING_CONFIG.targetMap.forEach(this::registerSwapItem);
    }
    public void registerSwapBlock(String target, String replacement) {
        Registry.register(Registry.BLOCK, new ResourceLocation("dadobugbedrockores", target), new FabricSwapBlock(BlockBehaviour.Properties.of(Material.AIR), replacement));
        Registry.register(Registry.ITEM, new ResourceLocation("dadobugbedrockores", target), new FabricSwapItem(new Item.Properties(), replacement));
    }

    public void registerSwapItem(String target, String replacement) {
        Registry.register(Registry.ITEM, new ResourceLocation("dadobugbedrockores", target), new FabricSwapItem(new Item.Properties(), replacement));
    }
}

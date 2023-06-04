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
        EntryModule.MAPPING_CONFIG.targetMap.forEach(this::registerUnmoddedSwapBlock);
    }

    public void registerSwapBlock(String target, String replacement){
        Registry.register(Registry.BLOCK, new ResourceLocation("dadobugbedrockores", "bedrock_" + target + "_ore"), new FabricSwapBlock(BlockBehaviour.Properties.of(Material.AIR), replacement));
    }

    public void registerUnmoddedSwapBlock(String target, String replacement){
        Registry.register(Registry.BLOCK, new ResourceLocation("dadobugbedrockores", target), new FabricSwapBlock(BlockBehaviour.Properties.of(Material.AIR), replacement));
    }
    public void registerSwapBlockSpecial(String target, String replacement){
        Registry.register(Registry.BLOCK, new ResourceLocation("dadobugbedrockores", "bedrock_" + target), new FabricSwapBlock(BlockBehaviour.Properties.of(Material.AIR), replacement));
    }

    public void registerSwapItem(String target, String replacement){
        Registry.register(Registry.ITEM, new ResourceLocation("dadobugbedrockores", "regenerative_" + target), new FabricSwapItem( new Item.Properties(), replacement));
    }

    public void registerUnModdedSwapItem(String target, String replacement){
        Registry.register(Registry.ITEM, new ResourceLocation("dadobugbedrockores", target), new FabricSwapItem( new Item.Properties(), replacement));
    }

    public void registerPair(String target, String replacementBlock, String replacementItem){
        registerSwapBlock(target, replacementBlock);
        registerSwapItem(target, replacementItem);
    }
    public void registerSwapBlockSpecial(String target, String replacementBlock, String replacementItem){
        registerSwapBlock(target, replacementBlock);
        registerSwapItem(target, replacementItem);
    }
}

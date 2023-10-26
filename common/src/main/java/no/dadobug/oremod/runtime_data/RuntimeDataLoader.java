package no.dadobug.oremod.runtime_data;

import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicTexturePack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import no.dadobug.oremod.EntryModule;

import java.util.ArrayList;
import java.util.List;

public class RuntimeDataLoader {

    private static final List<Runnable> denseGenList = new ArrayList<>();

    public static DynClientProvider provider = new DynClientProvider(new DynamicTexturePack(new ResourceLocation(EntryModule.modid, "dynamic_resources")));
    public static DynServerProvider providerServer = new DynServerProvider(new DynamicDataPack(new ResourceLocation(EntryModule.modid, "dynamic_data")));


    public static boolean addLootTable(ResourceLocation id, LootPool.Builder table){
        return providerServer.addLootTable(id, table);
    }
    public static boolean addBlockTag(ResourceLocation tag, ResourceLocation block){
        return providerServer.addBlockTag(tag, block);
    }
    public static boolean addStandardTexture(ResourceLocation block){
        return provider.addStandardTexture(block);
    }

    public static boolean addBlockLocalization(ResourceLocation block, String name){
        return provider.addBlockLocalization(block, name);
    }

    public static boolean addItemLocalization(ResourceLocation item, String name){
        return provider.addItemLocalization(item, name);
    }

    public static boolean addModelItemTexture(ResourceLocation item, String model) {
        return provider.addModelItemTexture(item, model);
    }

    public static boolean addBlockTexture(ResourceLocation block, String model) {
        return provider.addBlockTexture(block, model);
    }

    public static boolean addImageItemTexture(ResourceLocation item, String texture) {
        return provider.addImageItemTexture(item, texture);
    }
    public static boolean addItemTag(ResourceLocation tag, ResourceLocation item) {
        return providerServer.addItemTag(tag, item);
    }
    public static boolean addDenseGen(Runnable runnable) {
        return denseGenList.add(runnable);
    }


    public static void init(){
        denseGenList.forEach(Runnable::run);
    }


}

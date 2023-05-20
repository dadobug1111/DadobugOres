package no.dadobug.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.MissingMappingsEvent;
import no.dadobug.oremod.EntryModule;
import no.dadobug.oremod.ModLoadedLootCondition;


@Mod("dadobugores")
public class dadobugoresForge {
    public dadobugoresForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(EntryModule.modid, FMLJavaModLoadingContext.get().getModEventBus());
        EntryModule.init();
        IEventBus bus = EventBuses.getModEventBus(EntryModule.modid).get();
        bus.addListener(this::registerData);
        bus.addListener(this::registerLateClient);
        bus.addListener(this::registerLateServer);
        MinecraftForge.EVENT_BUS.addListener(this::registerMissingMappings);


    }


    private void registerData(GatherDataEvent event) {
        EntryModule.MOD_LOOT_CONDITION_TYPE = Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation(EntryModule.modid, "modloaded"), new LootItemConditionType(new ModLoadedLootCondition.Serializer()));
    }
    private void registerLateClient(FMLClientSetupEvent event) {
        EntryModule.initLate(true);
    }
    private void registerLateServer(FMLDedicatedServerSetupEvent event) {
        EntryModule.initLate(false);
    }
    private void registerMissingMappings(MissingMappingsEvent event) {
        event.getMappings(Registry.BLOCK_REGISTRY, "dadobugbedrockores").forEach((blockMapping) -> {
            if(EntryModule.MAPPING_CONFIG.targetMap.containsKey(blockMapping.getKey().getPath())) {
                blockMapping.remap(ForgeRegistries.BLOCKS.getValue(ResourceLocation.tryParse(EntryModule.MAPPING_CONFIG.targetMap.get(blockMapping.getKey().getPath()))));
            }
        });
    }
}

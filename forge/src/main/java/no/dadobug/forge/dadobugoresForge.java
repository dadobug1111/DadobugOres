package no.dadobug.forge;

import dev.architectury.platform.Platform;
import dev.architectury.platform.forge.EventBuses;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
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
        EntryModule.init(Platform.getEnv().isClient());
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
            if(EntryModule.BLOCK_MAPPING_CONFIG.targetMap.containsKey(blockMapping.getKey().getPath())) {
                blockMapping.remap(ForgeRegistries.BLOCKS.getValue(ResourceLocation.tryParse(EntryModule.BLOCK_MAPPING_CONFIG.targetMap.get(blockMapping.getKey().getPath()))));
            }
        });
        event.getMappings(Registry.ITEM_REGISTRY, "dadobugbedrockores").forEach((itemMapping) -> {
            if(EntryModule.BLOCK_MAPPING_CONFIG.targetMap.containsKey(itemMapping.getKey().getPath())) {
                itemMapping.remap(ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(EntryModule.BLOCK_MAPPING_CONFIG.targetMap.get(itemMapping.getKey().getPath()))));
            }
            if(EntryModule.ITEM_MAPPING_CONFIG.targetMap.containsKey(itemMapping.getKey().getPath())) {
                itemMapping.remap(ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(EntryModule.ITEM_MAPPING_CONFIG.targetMap.get(itemMapping.getKey().getPath()))));
            }
        });

        event.getMappings(Registry.ENCHANTMENT_REGISTRY, "dadobugbedrockores").forEach((enchantmentMapping) -> {
            for (RegistrySupplier<Enchantment> enchant : EntryModule.ENCHANTS) {
                if (enchantmentMapping.getKey().getPath().equals(enchant.getId().getPath())) {
                    enchantmentMapping.remap(enchant.get());
                    break;
                }
            }
        });
    }
}

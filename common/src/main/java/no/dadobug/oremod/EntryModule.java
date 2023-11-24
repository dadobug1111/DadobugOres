package no.dadobug.oremod;


import com.mojang.datafixers.util.Pair;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.client.rendering.ColorHandlerRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonObject;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import no.dadobug.oremod.blocks.HollowBedrock;
import no.dadobug.oremod.blocks.RegenerativeBlockEntity;
import no.dadobug.oremod.configs.*;
import no.dadobug.oremod.enchantments.*;
import no.dadobug.oremod.json_configs.DefaultJsonBlockGenerator;
import no.dadobug.oremod.json_configs.DefaultJsonGenGenerator;
import no.dadobug.oremod.json_configs.DynamicGenerationConfig;
import no.dadobug.oremod.json_configs.JsonConfig;
import no.dadobug.oremod.runtime_data.RuntimeDataLoader;
import no.dadobug.oremod.worldgen.BedrockOreFeatureConfig;
import no.dadobug.oremod.worldgen.BedrockOreGenerator;
import no.dadobug.oremod.worldgen.DenseOreGenerator;
import no.dadobug.oremod.worldgen.OreGenConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class EntryModule {
    public static final String modid = "dadobugores";
    public static final BaseConfig CONFIG = AutoConfig.register(BaseConfig.class, GsonConfigSerializer::new).getConfig();
    public static final BlocksConfig BLOCKS_CONFIG = AutoConfig.register(BlocksConfig.class, GsonConfigSerializer::new).getConfig();
    public static final BlockRemapConfig BLOCK_MAPPING_CONFIG = AutoConfig.register(BlockRemapConfig.class, GsonConfigSerializer::new).getConfig();
    public static final ItemRemapConfig ITEM_MAPPING_CONFIG = AutoConfig.register(ItemRemapConfig.class, GsonConfigSerializer::new).getConfig();
    public static final PropagatorGenDefaults DefaultPropagatorGenConfig = AutoConfig.register(PropagatorGenDefaults.class, GsonConfigSerializer::new).getConfig();
    public static final RegenerativeBlockDefaults DefaultRegenerativeBlockConfig = AutoConfig.register(RegenerativeBlockDefaults.class, GsonConfigSerializer::new).getConfig();
    public static final RegenerativeCoreDefaults DefaultRegenerativeCoreConfig = AutoConfig.register(RegenerativeCoreDefaults.class, GsonConfigSerializer::new).getConfig();

    public static final EnchantmentsConfig ENCHANT_CONFIG = AutoConfig.register(EnchantmentsConfig.class, GsonConfigSerializer::new).getConfig();
    public static final Logger LOGGER = LogManager.getLogger();



    // We can use this if we don't want to use DeferredRegister
    //public static final Supplier<Registries> REGISTRIES = Suppliers.memoize(() -> Registries.get(MOD_ID));
    // Registering a new creative tab
    public static final CreativeModeTab ITEMGROUP = CreativeTabRegistry.create(new ResourceLocation(modid, "item_group"), () ->
            new ItemStack(Registry.BLOCK.get(new ResourceLocation(modid, "bedrock_diamond_ore"))));


    public static final BlockConfigLambda<BlockBehaviour.Properties> DynamicBlockSettings = (cfg) -> BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).strength(cfg.hardness, cfg.resistance).requiresCorrectToolForDrops().lightLevel((state) -> cfg.luminance);
    public static final Item.Properties DefaultItemSettings = new Item.Properties().tab(EntryModule.ITEMGROUP);
    public static final Item.Properties CloakedItemSettings = new Item.Properties();
    public static final BlockConfigLambda<Item.Properties> vanillaItemSettings = (cfg) -> cfg.showOre?DefaultItemSettings:CloakedItemSettings;


    public static TagKey<Block> REGEN_TAG = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation("dadobugores", "regenerative_block"));
    public static TagKey<Block> FRACTURE_TAG = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation("dadobugores", "fracture-able_block"));
    public static TagKey<Block> CORE_TAG = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation("dadobugores", "contains_core"));
    public static TagKey<Block> HOLLOW_TAG = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation("dadobugores", "can_accept_core"));
    public static TagKey<Block> INDESTRUCTABLE_TAG = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation("dadobugores", "no_break"));
    public static TagKey<Block> ENCHANT_ONLY_TAG = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation("dadobugores", "enchant_break"));

    public static TagKey<Item> IS_CORE_TAG = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("dadobugores", "is_core"));



    public static MutableComponent DEFAULT_TOOLTIP = MutableComponent.create(new TranslatableContents("item.dadobugores.regen_power.tooltip")).withStyle(ChatFormatting.GREEN);
    public static MutableComponent FRACTURED_TOOLTIP = MutableComponent.create(new TranslatableContents("item.dadobugores.regen_broken.tooltip")).withStyle(ChatFormatting.RED);
    public static MutableComponent FLUID_TOOLTIP = MutableComponent.create(new TranslatableContents("item.dadobugores.regen_fluid.tooltip")).withStyle(ChatFormatting.GREEN);
    public static MutableComponent JOKE_FLUID_ONE_TOOLTIP = MutableComponent.create(new TranslatableContents("item.dadobugores.regen_joke_fluid_one.tooltip")).withStyle(ChatFormatting.GOLD);
    public static MutableComponent JOKE_ITEM_ONE_TOOLTIP = MutableComponent.create(new TranslatableContents("item.dadobugores.regen_joke_item_one.tooltip")).withStyle(ChatFormatting.GOLD);
    public static MutableComponent XP_TOOLTIP = MutableComponent.create(new TranslatableContents("item.dadobugores.regen_xp.tooltip")).withStyle(ChatFormatting.AQUA);



    public static final DeferredRegister<Enchantment> ENCHANTS = DeferredRegister.create(modid, Registry.ENCHANTMENT_REGISTRY);
    public static final RegistrySupplier<Enchantment> SHATTERING = ENCHANTS.register("shattering", () -> new ShatteringEnchantment(ENCHANT_CONFIG.SHATTERING));
    public static final RegistrySupplier<Enchantment> CURSE_OF_FRACTURING = ENCHANTS.register("curse_of_fracturing", () -> new FracturingEnchant(ENCHANT_CONFIG.CURSE_OF_FRACTURING));
    public static final RegistrySupplier<Enchantment> EXTRACTION = ENCHANTS.register("extraction", () -> new ExtractionEnchant(ENCHANT_CONFIG.EXTRACTION));
    public static final RegistrySupplier<Enchantment> GENTLE_MINING = ENCHANTS.register("gentle_mining", () -> new GentleMiningEnchant(ENCHANT_CONFIG.GENTLE_MINING));
    public static final RegistrySupplier<Enchantment> CURSE_OF_SHATTERING = ENCHANTS.register("curse_of_shattering", () -> new CursedShatteringEnchant(ENCHANT_CONFIG.CURSE_OF_SHATTERING));
    public static final RegistrySupplier<Enchantment> ARCANE_EXTRACTION = ENCHANTS.register("arcane_extraction", () -> new ArcaneExtractionEnchant(ENCHANT_CONFIG.ARCANE_EXTRACTION));



    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(modid, Registry.BLOCK_REGISTRY);
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(modid, Registry.FEATURE_REGISTRY);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(modid, Registry.ITEM_REGISTRY);



    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(modid, Registry.BLOCK_ENTITY_TYPE_REGISTRY);
    public static final ArrayList<Supplier<Block>> RegenerativeBlockList = new ArrayList<>();
    public static final RegistryBox registryBox = new RegistryBox(BLOCKS, ITEMS);
    public static final ArrayList<Consumer<Boolean>> lootFunctions = new ArrayList<>();



    public static final List<Pair<String, JsonObject>> BlockList = new ArrayList<>();
    static {
        if(CONFIG.regenMissingDefaultOres) {
            DefaultJsonBlockGenerator.addDefaults();
            DefaultJsonGenGenerator.addDefaults();
        }
        JsonConfig.readBlockConfigs().forEach((config) -> config.addBlock(registryBox));
    }

    public static RegistrySupplier<BlockEntityType<RegenerativeBlockEntity>> REGENERATIVEBLOCKTYPE = BLOCK_ENTITY_TYPES.register("", () -> {
        Block[] blocks = new Block[RegenerativeBlockList.size()];
        for(int i = 0;i < blocks.length; i++){
            blocks[i] = RegenerativeBlockList.get(i).get();
        }
        return BlockEntityType.Builder.of(RegenerativeBlockEntity::new, blocks).build(null);
    });

    public static final RegistrySupplier<Feature<BedrockOreFeatureConfig>> BEDROCK_ORE_GENERATOR = FEATURES.register("bedrock_ore_generator",() -> new BedrockOreGenerator(BedrockOreFeatureConfig.CODEC));
    public static final RegistrySupplier<Feature<OreConfiguration>> DENSE_ORE_GENERATOR = FEATURES.register("dense_ore_generator",() -> new DenseOreGenerator(OreConfiguration.CODEC));


    public static final BedrockStack BEDROCK_FRACTURED = BedrockStack.BedrockStackAlteredBedrock("fractured", BLOCKS_CONFIG.BEDROCK_FRACTURED, vanillaItemSettings, DynamicBlockSettings, false, FRACTURED_TOOLTIP, true);
    public static final RegistrySupplier<Block> BEDROCK_HOLLOW = BLOCKS.register("bedrock_hollow",() -> new HollowBedrock(DynamicBlockSettings.get(BLOCKS_CONFIG.BEDROCK_HOLLOW), false, BLOCKS_CONFIG.BEDROCK_HOLLOW.XPmin, BLOCKS_CONFIG.BEDROCK_HOLLOW.XPmax, BLOCKS_CONFIG.BEDROCK_HOLLOW.DurabilityMin, BLOCKS_CONFIG.BEDROCK_HOLLOW.DurabilityMax, BLOCKS_CONFIG.BEDROCK_HOLLOW.infinite, false, Blocks.BEDROCK.defaultBlockState(), JsonConfig.staticFunction, true));
    public static final RegistrySupplier<Item> BEDROCK_HOLLOW_ITEM = ITEMS.register("bedrock_hollow",() -> new BlockItem(BEDROCK_HOLLOW.get(), vanillaItemSettings.get(BLOCKS_CONFIG.BEDROCK_HOLLOW)));



    public static LootItemConditionType MOD_LOOT_CONDITION_TYPE;

    public static void init(boolean isClient) {
        ENCHANTS.register();
        BLOCKS.register();
        ITEMS.register();
        BLOCK_ENTITY_TYPES.register();
        FEATURES.register();

        if(isClient) RuntimeDataLoader.provider.register();
        RuntimeDataLoader.providerServer.register();
    }

    public static void initLate(boolean isClient) {
        //ArrayList<JsonObject> object = new ArrayList<>();
        //builder.offerTo((RecipeJsonProviderA) -> object.add(RecipeJsonProviderA.toJson()));
        EntryModule.MOD_LOOT_CONDITION_TYPE = Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation(EntryModule.modid, "modloaded"), new LootItemConditionType(new ModLoadedLootCondition.Serializer()));



        JsonConfig.readGenerationConfigs().forEach(DynamicGenerationConfig::addGeneration);
        lootFunctions.forEach((a) -> a.accept(true));
        RuntimeDataLoader.init();
        OreGenConfig.init();
        if(isClient) {
            ColorHandlerRegistry.registerBlockColors((state, world, pos, tintIndex) -> {
                if (world == null || pos == null) {
                    return 0x3f76e4;
                }
                return BiomeColors.getAverageWaterColor(world, pos);
            }, Registry.BLOCK.get(new ResourceLocation(modid, "bedrock_water_ore")));
            ColorHandlerRegistry.registerItemColors((state, tintIndex) -> 0x3f76e4, Registry.BLOCK.get(new ResourceLocation(modid, "bedrock_water_ore")));
            RenderTypeRegistry.register(RenderType.translucent(), Registry.BLOCK.get(new ResourceLocation(modid, "bedrock_water_ore")));
        }
    }

}

package no.dadobug.oremod.json_configs;


import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonObject;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonPrimitive;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.LiteralContents;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import no.dadobug.oremod.EntryModule;
import no.dadobug.oremod.RegenerativeCore;
import no.dadobug.oremod.RegistryBox;
import no.dadobug.oremod.configs.RegenerativeCoreDefaults;
import no.dadobug.oremod.runtime_data.RuntimeDataLoader;

import java.util.function.Supplier;

public class RegenerativeCoreConfig extends DynamicItemConfig {
    private boolean isFireResistant;
    private int maxStackSize;
    private String id;
    private String modelFile;
    private String textureFile;
    private String englishName;
    private String tooltipColor;
    private String tooltipLocalization;
    private String tooltipName;

    public RegenerativeCoreConfig(String id) {
        this.id = id;
        this.maxStackSize = EntryModule.DefaultRegenerativeCoreConfig.maxStackSize;
        this.isFireResistant = EntryModule.DefaultRegenerativeCoreConfig.isFireResistant;

    }


    public static String type() {
        return "regenerative_core";
    }

    @Override
    public void addItem(RegistryBox registry) {
        this.addItem(registry, () -> Blocks.AIR);
    }

    @Override
    public void addItem(RegistryBox registry, Supplier<Block> hostBlock) {
        Item.Properties properties = new Item.Properties().rarity(Rarity.EPIC).tab(EntryModule.ITEMGROUP).stacksTo(this.maxStackSize);
        if(this.isFireResistant)properties.fireResistant();
        MutableComponent tooltip = EntryModule.DEFAULT_TOOLTIP;
        if(this.tooltipLocalization != null){
            if(this.tooltipColor != null){
                tooltip = MutableComponent.create(new TranslatableContents(this.tooltipLocalization)).withStyle(ChatFormatting.getByName(this.tooltipColor));
            } else {
                tooltip = MutableComponent.create(new TranslatableContents(this.tooltipLocalization));
            }
        } else if(this.tooltipName != null){

            if(this.tooltipColor != null){
                tooltip = MutableComponent.create(new LiteralContents(this.tooltipName)).withStyle(ChatFormatting.getByName(this.tooltipColor));
            } else {
                tooltip = MutableComponent.create(new LiteralContents(this.tooltipName));
            }
        }

        MutableComponent finalTooltip = tooltip;
        registry.getItemRegistry().register(this.id, ()-> new RegenerativeCore(properties, hostBlock.get(), finalTooltip));

        if(this.englishName != null){
            RuntimeDataLoader.addItemLocalization(new ResourceLocation(EntryModule.modid, this.id), this.englishName);
        }
        if(this.modelFile != null){
            RuntimeDataLoader.addModelItemTexture(new ResourceLocation(EntryModule.modid, this.id), this.modelFile);
        } else if(this.textureFile != null){
            RuntimeDataLoader.addImageItemTexture(new ResourceLocation(EntryModule.modid, this.id), this.textureFile);
        }


        RuntimeDataLoader.addItemTag(EntryModule.IS_CORE_TAG.location(), ResourceLocation.tryBuild(EntryModule.modid, this.id));

    }

    public static DynamicItemConfig fromJsonObject(JsonObject jsonObject){
        RegenerativeCoreConfig config;

        if(jsonObject.get("id") instanceof JsonPrimitive primitiveID){
            config = new RegenerativeCoreConfig(primitiveID.asString());
        } else {
            return null;
        }

        config.setFireResistant(jsonObject.getBoolean("isFireResistant", EntryModule.DefaultRegenerativeCoreConfig.isFireResistant))
                .setMaxStackSize(jsonObject.getInt("maxStackSize", EntryModule.DefaultRegenerativeCoreConfig.maxStackSize));

        if(jsonObject.get("modelFile") instanceof JsonPrimitive primitive){
            config.setModelFile(primitive.asString());

        } else if(jsonObject.get("textureFile") instanceof JsonPrimitive primitive){
            config.setTextureFile(primitive.asString());
        }

        if(jsonObject.get("englishName") instanceof JsonPrimitive primitive){
            config.setEnglishName(primitive.asString());
        }

        if(jsonObject.get("tooltipColor") instanceof JsonPrimitive primitive){
            config.setTooltipColor(primitive.asString());
        }

        if(jsonObject.get("tooltipName") instanceof JsonPrimitive primitive){
            config.setTooltipName(primitive.asString());
        }

        if(jsonObject.get("tooltipLocalization") instanceof JsonPrimitive primitive){
            config.setTooltipLocalization(primitive.asString());
        }

        return config;
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("type", new JsonPrimitive(type()));
        jsonObject.put("id", new JsonPrimitive(this.id));


        RegenerativeCoreDefaults defaultstuff = EntryModule.DefaultRegenerativeCoreConfig;
        if(this.isFireResistant != defaultstuff.isFireResistant)jsonObject.put("isFireResistant", new JsonPrimitive(this.isFireResistant));
        if(this.maxStackSize != defaultstuff.maxStackSize)jsonObject.put("maxStackSize", new JsonPrimitive(this.maxStackSize));
        if(this.modelFile != null)jsonObject.put("modelFile", new JsonPrimitive(this.modelFile));
        if(this.textureFile != null)jsonObject.put("textureFile", new JsonPrimitive(this.textureFile));
        if(this.englishName != null)jsonObject.put("englishName", new JsonPrimitive(this.englishName));
        if(this.tooltipColor != null)jsonObject.put("tooltipColor", new JsonPrimitive(this.tooltipColor));
        if(this.tooltipName != null)jsonObject.put("tooltipName", new JsonPrimitive(this.tooltipName));
        if(this.tooltipLocalization != null)jsonObject.put("tooltipLocalization", new JsonPrimitive(this.tooltipLocalization));


        return jsonObject;
    }

    public RegenerativeCoreConfig setFireResistant(boolean fireResistant) {
        isFireResistant = fireResistant;
        return this;
    }

    public RegenerativeCoreConfig setMaxStackSize(int maxStackSize) {
        this.maxStackSize = maxStackSize;
        return this;
    }

    public RegenerativeCoreConfig setModelFile(String modelFile) {
        this.modelFile = modelFile;
        return this;
    }

    public RegenerativeCoreConfig setTextureFile(String textureFile) {
        this.textureFile = textureFile;
        return this;
    }

    public RegenerativeCoreConfig setEnglishName(String englishName) {
        this.englishName = englishName;
        return this;
    }

    public RegenerativeCoreConfig setTooltipColor(String tooltipColor) {
        this.tooltipColor = tooltipColor;
        return this;
    }

    public RegenerativeCoreConfig setTooltipName(String tooltipName) {
        this.tooltipName = tooltipName;
        return this;
    }

    public RegenerativeCoreConfig setTooltipLocalization(String tooltipLocalization) {
        this.tooltipLocalization = tooltipLocalization;
        return this;
    }
}

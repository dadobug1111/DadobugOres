package no.dadobug.oremod.json_configs;


import dev.architectury.platform.Platform;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonArray;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonObject;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonPrimitive;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import no.dadobug.oremod.EntryModule;
import no.dadobug.oremod.RegistryBox;
import no.dadobug.oremod.blocks.RegenerativeBlock;
import no.dadobug.oremod.configs.RegenerativeBlockDefaults;
import no.dadobug.oremod.runtime_data.RuntimeDataLoader;
import no.dadobug.oremod.util.RegenData;
import no.dadobug.oremod.util.RegenFluidData;
import no.dadobug.oremod.worldgen.OreGenConfig;
import oshi.util.tuples.Triplet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class RegenerativeBlockConfig extends DynamicBlockConfig {

    public static String type() {
        return "regenerative_block";
    }
    private final String id;
    private int toolLevel;
    private int durabilityMin;
    private int durabilityMax;
    private boolean showOre;
    private float hardness;
    private float resistance;
    private int luminance;
    private int XPmin;
    private int XPmax;
    private boolean standardTexture;
    private boolean bossProof;
    private boolean infinite;
    private boolean silkable;
    private boolean replace;
    private boolean onlyValidTools;
    private String replaceBlockId;
    private String englishName;
    private String fluid;
    private String bucketItem;
    private String bucketSound;
    private String bottleItem;
    private String damageFunction;
    private String textureClone;
    private final Set<String> modsRequired;
    private final Set<String> validTools;

    private DynamicLootConfig lootConfig;
    private DynamicItemConfig coreConfig;


    private String denseOreGenTarget;
    private float denseOreGenProbability;
    private boolean denseOreGenReplaceState;


    public RegenerativeBlockConfig(String id) {

        //REQUIRED!!! Do not forget! Will not load ore without!
        this.id = id;

        //needs default config
        this.toolLevel = EntryModule.DefaultRegenerativeBlockConfig.toolLevel;
        this.durabilityMin = EntryModule.DefaultRegenerativeBlockConfig.durabilityMin;
        this.durabilityMax = EntryModule.DefaultRegenerativeBlockConfig.durabilityMax;
        this.showOre = EntryModule.DefaultRegenerativeBlockConfig.showOre;
        this.hardness = EntryModule.DefaultRegenerativeBlockConfig.hardness;
        this.resistance = EntryModule.DefaultRegenerativeBlockConfig.resistance;
        this.luminance = EntryModule.DefaultRegenerativeBlockConfig.luminance;
        this.XPmin = EntryModule.DefaultRegenerativeBlockConfig.XPmin;
        this.XPmax = EntryModule.DefaultRegenerativeBlockConfig.XPmax;
        this.standardTexture = EntryModule.DefaultRegenerativeBlockConfig.standardTexture;
        this.bossProof = EntryModule.DefaultRegenerativeBlockConfig.bossProof;
        this.infinite = EntryModule.DefaultRegenerativeBlockConfig.infinite;
        this.silkable = EntryModule.DefaultRegenerativeBlockConfig.silkable;
        this.replace = EntryModule.DefaultRegenerativeBlockConfig.replace;
        this.onlyValidTools = EntryModule.DefaultRegenerativeBlockConfig.onlyValidTools;
        this.damageFunction = EntryModule.DefaultRegenerativeBlockConfig.damageFunction;
        this.denseOreGenProbability = EntryModule.DefaultRegenerativeBlockConfig.denseOreGenProbability;
        this.replaceBlockId = Registry.BLOCK.getKey(Blocks.AIR).toString();

        //default - "minecraft" is considered to be always present.
        //if not, you probably have bigger problems than unwanted ores loading...
        this.modsRequired = new HashSet<>();
        this.modsRequired.add("minecraft");
        this.validTools = new HashSet<>();


    }

    public String getId() {
        return id;
    }

    public RegenerativeBlockConfig addRequiredMod(String modsRequired){

        this.modsRequired.add(modsRequired);
        return this;
    }
    public RegenerativeBlockConfig addValidTool(String toolType){

        this.validTools.add(toolType);
        return this;
    }

    public RegenerativeBlockConfig setToolLevel(int toolLevel){

        this.toolLevel = toolLevel;
        return this;
    }

    public RegenerativeBlockConfig setDurabilityMin(int durabilityMin){

        this.durabilityMin = durabilityMin;
        return this;
    }

    public RegenerativeBlockConfig setDurabilityMax(int durabilityMax){

        this.durabilityMax = durabilityMax;
        return this;
    }

    public RegenerativeBlockConfig setXPmin(int XPmin){

        this.XPmin = XPmin;
        return this;
    }

    public RegenerativeBlockConfig setXPmax(int XPmax){

        this.XPmax = XPmax;
        return this;
    }

    public RegenerativeBlockConfig setHardness(float hardness){

        this.hardness = hardness;
        return this;
    }

    public RegenerativeBlockConfig setResistance(float resistance){

        this.resistance = resistance;
        return this;
    }

    public RegenerativeBlockConfig setLuminance(int luminance){

        this.luminance = Mth.clamp(luminance, 0, 15);
        return this;
    }

    public RegenerativeBlockConfig setStandardTexture(boolean standardTexture){

        this.standardTexture = standardTexture;
        return this;
    }

    public RegenerativeBlockConfig setBossProof(boolean bossProof){

        this.bossProof = bossProof;
        return this;
    }

    public RegenerativeBlockConfig setInfinite(boolean infinite){

        this.infinite = infinite;
        return this;
    }

    public RegenerativeBlockConfig setSilkable(boolean silkable){

        this.silkable = silkable;
        return this;
    }

    public RegenerativeBlockConfig setShowOre(boolean showOre){

        this.showOre = showOre;
        return this;
    }

    public RegenerativeBlockConfig setReplace(boolean replace){

        this.replace = replace;
        return this;
    }

    public RegenerativeBlockConfig setOnlyValidTools(boolean onlyValidTools) {

        this.onlyValidTools = onlyValidTools;
        return this;
    }


    public RegenerativeBlockConfig setReplaceBlockId(Block replaceBlock){

        this.replaceBlockId = Registry.BLOCK.getKey(replaceBlock).toString();
        return this;
    }

    public RegenerativeBlockConfig setEnglishName(String englishName){

        this.englishName = englishName;
        return this;
    }

    public RegenerativeBlockConfig setReplaceBlockId(String replaceBlock){

        this.replaceBlockId = replaceBlock;
        return this;
    }

    public RegenerativeBlockConfig setFluid(String fluid){

        this.fluid = fluid;
        return this;
    }

    public RegenerativeBlockConfig setBucketItem(String bucketItem){

        this.bucketItem = bucketItem;
        return this;
    }

    public RegenerativeBlockConfig setBucketSound(String bucketSound){

        this.bucketSound = bucketSound;
        return this;
    }

    public RegenerativeBlockConfig setBottleItem(String bottleItem){

        this.bottleItem = bottleItem;
        return this;
    }

    public RegenerativeBlockConfig setDamageFunction(String damageFunction){

        this.damageFunction = damageFunction;
        return this;
    }

    public RegenerativeBlockConfig setTextureClone(String textureClone){

        this.textureClone = textureClone;
        return this;
    }

    public RegenerativeBlockConfig setLootConfig(DynamicLootConfig lootConfig){

        this.lootConfig = lootConfig;
        return this;
    }

    public RegenerativeBlockConfig setCoreConfig(DynamicItemConfig coreConfig){

        this.coreConfig = coreConfig;
        return this;
    }

    public RegenerativeBlockConfig setDenseOreGenTarget(String denseOreGenTarget){

        this.denseOreGenTarget = denseOreGenTarget;
        return this;
    }


    public RegenerativeBlockConfig setDenseOreGenProbability(float denseOreGenProbability){

        this.denseOreGenProbability = denseOreGenProbability;
        return this;
    }


    public RegenerativeBlockConfig setDenseOreGenReplaceState(boolean denseOreGenReplaceState){

        this.denseOreGenReplaceState = denseOreGenReplaceState;
        return this;
    }

    @Override
    public void addBlock(RegistryBox registry) {
        if(this.modsRequired.stream().allMatch((mod) -> mod.startsWith("!")?!Platform.isModLoaded(mod.substring(1)):(Platform.isModLoaded(mod) || mod.equals("minecraft")))) {
            Supplier<Block> block;
            RegenData data = new RegenData(this.replace, this.XPmin, this.XPmax, JsonConfig.durabilityFunctions.getOrDefault(this.damageFunction, JsonConfig.defaultFunction), this.durabilityMin, this.durabilityMax, this.infinite, this.silkable, Registry.BLOCK.get(ResourceLocation.tryParse(this.replaceBlockId)).defaultBlockState(), this.onlyValidTools);
                    
            RegenFluidData fluidData = null;
            if(this.fluid != null && Arrays.stream(JsonConfig.cancelStrings).noneMatch(s -> s.equals(this.fluid.toLowerCase()))) {
                if(this.bottleItem != null && Arrays.stream(JsonConfig.cancelStrings).noneMatch(s -> s.equals(this.bottleItem.toLowerCase()))) {
                    fluidData = new RegenFluidData(Registry.FLUID.get(ResourceLocation.tryParse(this.fluid)), Registry.ITEM.get(ResourceLocation.tryParse(this.bottleItem)).getDefaultInstance());
                }else{
                    fluidData = new RegenFluidData(Registry.FLUID.get(ResourceLocation.tryParse(this.fluid)));

                }

            }else if(this.bucketItem != null && Arrays.stream(JsonConfig.cancelStrings).noneMatch(s -> s.equals(this.bucketItem.toLowerCase()))) {
                if(this.bottleItem != null && Arrays.stream(JsonConfig.cancelStrings).noneMatch(s -> s.equals(this.bottleItem.toLowerCase()))) {
                    fluidData = new RegenFluidData( Registry.ITEM.get(ResourceLocation.tryParse(this.bucketItem)).getDefaultInstance(), Registry.SOUND_EVENT.getOptional(ResourceLocation.tryParse(this.bucketSound)), Registry.ITEM.get(ResourceLocation.tryParse(this.bottleItem)).getDefaultInstance());
                }else{
                    fluidData = new RegenFluidData( Registry.ITEM.get(ResourceLocation.tryParse(this.bucketItem)).getDefaultInstance(), Registry.SOUND_EVENT.getOptional(ResourceLocation.tryParse(this.bucketSound)));
                }

            }else if(this.bottleItem != null && Arrays.stream(JsonConfig.cancelStrings).noneMatch(s -> s.equals(this.bottleItem.toLowerCase()))) {
                fluidData = new RegenFluidData(Registry.ITEM.get(ResourceLocation.tryParse(this.bottleItem)).getDefaultInstance());
            } else{
                fluidData = new RegenFluidData();
            }

            RegenFluidData finalFluidData = fluidData;
            block = registry.getBlockRegistry().register(this.id, () -> new RegenerativeBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).strength(this.hardness, this.resistance).requiresCorrectToolForDrops().lightLevel((state) -> this.luminance), data, finalFluidData));
            
            registry.getItemRegistry().register(this.id, () -> new BlockItem(block.get(), this.showOre ? EntryModule.DefaultItemSettings : EntryModule.CloakedItemSettings));
            EntryModule.RegenerativeBlockList.add(block);


            //add loot if present
            if (this.lootConfig != null) {
                this.lootConfig.addLoot(registry, this.id);
            }

            //add core if present
            if (this.coreConfig != null) {
                this.coreConfig.addItem(registry, block);
                RuntimeDataLoader.addBlockTag(EntryModule.CORE_TAG.location(), ResourceLocation.tryBuild(EntryModule.modid, this.id));
            }

            if(!this.validTools.isEmpty()) {
                this.validTools.forEach((t) -> toolSwitch(t, this.id));
            } else {
                toolSwitch(EntryModule.DefaultRegenerativeBlockConfig.defaultTool, this.id);
            }

            if(this.standardTexture){
                RuntimeDataLoader.addStandardTexture(ResourceLocation.tryBuild(EntryModule.modid, this.id));
            } else if(this.textureClone != null){
                RuntimeDataLoader.addBlockTexture(ResourceLocation.tryBuild(EntryModule.modid, this.id), this.textureClone);
            }

            if(this.bossProof){
                RuntimeDataLoader.addBlockTag(BlockTags.WITHER_IMMUNE.location(), ResourceLocation.tryBuild(EntryModule.modid, this.id));
                RuntimeDataLoader.addBlockTag(BlockTags.DRAGON_IMMUNE.location(), ResourceLocation.tryBuild(EntryModule.modid, this.id));
            }

            if(this.englishName != null){
                RuntimeDataLoader.addBlockLocalization(new ResourceLocation(EntryModule.modid, this.id), this.englishName);
            }

            if(this.denseOreGenTarget != null){
                RuntimeDataLoader.addDenseGen(() -> OreGenConfig.addDenseOre(this.denseOreGenTarget, new Triplet<>(this.denseOreGenProbability, EntryModule.modid+ ":" + this.id, this.denseOreGenReplaceState)));
            }

            switch (this.toolLevel){
                case 1 -> RuntimeDataLoader.addBlockTag(BlockTags.NEEDS_STONE_TOOL.location(), ResourceLocation.tryBuild(EntryModule.modid, this.id));
                case 2 -> RuntimeDataLoader.addBlockTag(BlockTags.NEEDS_IRON_TOOL.location(), ResourceLocation.tryBuild(EntryModule.modid, this.id));
                case 3 -> RuntimeDataLoader.addBlockTag(BlockTags.NEEDS_DIAMOND_TOOL.location(), ResourceLocation.tryBuild(EntryModule.modid, this.id));
                case 4 -> {
                    RuntimeDataLoader.addBlockTag(ResourceLocation.tryBuild("fabric", "needs_tool_level_4"), ResourceLocation.tryBuild(EntryModule.modid, this.id));
                    RuntimeDataLoader.addBlockTag(ResourceLocation.tryBuild("forge", "needs_netherite_tool"), ResourceLocation.tryBuild(EntryModule.modid, this.id));
                }
            }

            EntryModule.LOGGER.debug("regenerative block added: " + this.id);

        }

    }

    private static void toolSwitch(String tool, String id){
        switch(tool){
            case "pickaxe" -> RuntimeDataLoader.addBlockTag(BlockTags.MINEABLE_WITH_PICKAXE.location(), ResourceLocation.tryBuild(EntryModule.modid, id));
            case "shovel" -> RuntimeDataLoader.addBlockTag(BlockTags.MINEABLE_WITH_SHOVEL.location(), ResourceLocation.tryBuild(EntryModule.modid, id));
            case "axe" -> RuntimeDataLoader.addBlockTag(BlockTags.MINEABLE_WITH_AXE.location(), ResourceLocation.tryBuild(EntryModule.modid, id));
            case "hoe" -> RuntimeDataLoader.addBlockTag(BlockTags.MINEABLE_WITH_HOE.location(), ResourceLocation.tryBuild(EntryModule.modid, id));
        }
    }

    public static DynamicBlockConfig fromJsonObject(JsonObject jsonObject) {
        RegenerativeBlockConfig config;

        if(jsonObject.get("id") instanceof JsonPrimitive primitiveID){
            config = new RegenerativeBlockConfig(primitiveID.asString());
        } else {
            return null;
        }

        if(jsonObject.get("modsRequired") instanceof JsonArray array) {
            for(int i = 0; i < array.size(); i++) {
                config.addRequiredMod(array.getString(i,"minecraft"));
            }
        }

        if(jsonObject.get("validTools") instanceof JsonArray array) {
            for(int i = 0; i < array.size(); i++) {
                config.addValidTool(array.getString(i,"null").toLowerCase());
            }
        } else{
            config.addValidTool(EntryModule.DefaultRegenerativeBlockConfig.defaultTool.toLowerCase());
        }

        config.setToolLevel(jsonObject.getInt("toolLevel", EntryModule.DefaultRegenerativeBlockConfig.toolLevel))
                .setDurabilityMin(jsonObject.getInt("durabilityMin", EntryModule.DefaultRegenerativeBlockConfig.durabilityMin))
                .setDurabilityMax(jsonObject.getInt("durabilityMax", EntryModule.DefaultRegenerativeBlockConfig.durabilityMax))
                .setXPmin(jsonObject.getInt("XPmin", EntryModule.DefaultRegenerativeBlockConfig.XPmin))
                .setXPmax(jsonObject.getInt("XPmax", EntryModule.DefaultRegenerativeBlockConfig.XPmax))
                .setLuminance(jsonObject.getInt("luminance", EntryModule.DefaultRegenerativeBlockConfig.luminance))

                .setHardness(jsonObject.getFloat("hardness", EntryModule.DefaultRegenerativeBlockConfig.hardness))
                .setResistance(jsonObject.getFloat("resistance", EntryModule.DefaultRegenerativeBlockConfig.resistance))

                .setStandardTexture(jsonObject.getBoolean("standardTexture", EntryModule.DefaultRegenerativeBlockConfig.standardTexture))
                .setBossProof(jsonObject.getBoolean("bossProof", EntryModule.DefaultRegenerativeBlockConfig.bossProof))
                .setInfinite(jsonObject.getBoolean("infinite", EntryModule.DefaultRegenerativeBlockConfig.infinite))
                .setSilkable(jsonObject.getBoolean("silkable", EntryModule.DefaultRegenerativeBlockConfig.silkable))
                .setShowOre(jsonObject.getBoolean("showOre", EntryModule.DefaultRegenerativeBlockConfig.showOre))
                .setReplace(jsonObject.getBoolean("replace", EntryModule.DefaultRegenerativeBlockConfig.replace))
                .setOnlyValidTools(jsonObject.getBoolean("onlyValidTools", EntryModule.DefaultRegenerativeBlockConfig.onlyValidTools))
                .setDenseOreGenProbability(jsonObject.getFloat("denseOreGenProbability", EntryModule.DefaultRegenerativeBlockConfig.denseOreGenProbability))
                .setDenseOreGenReplaceState(jsonObject.getBoolean("denseOreGenReplaceState", EntryModule.DefaultRegenerativeBlockConfig.denseOreGenReplaceState));

        if(jsonObject.get("replaceBlockId") instanceof JsonPrimitive primitive){
            config.setReplaceBlockId(primitive.asString());
        }

        if(jsonObject.get("englishName") instanceof JsonPrimitive primitive){
            config.setEnglishName(primitive.asString());
        }

        if(jsonObject.get("fluid") instanceof JsonPrimitive primitive){
            config.setFluid(primitive.asString());
        }

        if(jsonObject.get("bucketItem") instanceof JsonPrimitive primitive){
            config.setBucketItem(primitive.asString());
        }

        if(jsonObject.get("bucketSound") instanceof JsonPrimitive primitive){
            config.setBucketSound(primitive.asString());
        }

        if(jsonObject.get("bottleItem") instanceof JsonPrimitive primitive){
            config.setBottleItem(primitive.asString());
        }

        if(jsonObject.get("damageFunction") instanceof JsonPrimitive primitive){
            config.setDamageFunction(primitive.asString());
        }

        if(jsonObject.get("textureClone") instanceof JsonPrimitive primitive){
            config.setTextureClone(primitive.asString());
        }

        if(jsonObject.get("denseOreGenTarget") instanceof JsonPrimitive primitive){
            config.setDenseOreGenTarget(primitive.asString());
        }



        if(jsonObject.containsKey("lootConfig")){
            try {
                JsonObject lootObject = jsonObject.getObject("lootConfig");
                if(lootObject != null &&lootObject.get("type") instanceof JsonPrimitive primitive) {
                    String lootType = primitive.asString();

                    if (JsonConfig.lootTypes.containsKey(lootType)) {
                        config.setLootConfig(JsonConfig.lootTypes.get(lootType).apply(lootObject));
                    } else {
                        EntryModule.LOGGER.warn("regenerative block " + config.getId() + " has invalid lootConfig type! skipping lootConfig parsing");
                    }
                }
            } catch(ClassCastException | IllegalStateException ignored){}
        }
        if(jsonObject.containsKey("coreConfig")){
            try {
                JsonObject coreObject = jsonObject.getObject("coreConfig");
                if(coreObject != null && coreObject.get("type") instanceof JsonPrimitive primitive) {
                    String coreType = primitive.asString();

                    if (JsonConfig.itemTypes.containsKey(coreType)) {
                        config.setCoreConfig(JsonConfig.itemTypes.get(coreType).apply(coreObject));
                    } else {
                        EntryModule.LOGGER.warn("regenerative block " + config.getId() + " has invalid coreConfig type! skipping coreConfig parsing");
                    }
                }
            } catch(ClassCastException | IllegalStateException ignored){}
        }

        return config;
    }


    @Override
    public JsonObject toJsonObject(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("type", new JsonPrimitive(type()));
        jsonObject.put("id", new JsonPrimitive(this.id));

        JsonArray modsrequired = new JsonArray();
        this.modsRequired.forEach((a) -> modsrequired.add(new JsonPrimitive(a)));
        jsonObject.put("modsRequired", modsrequired);

        //Add only if list has something unique
        if(!this.validTools.isEmpty()) {
            JsonArray validtools = new JsonArray();
            this.validTools.forEach((a) -> validtools.add(new JsonPrimitive(a)));
            jsonObject.put("validTools", validtools);
        }

        //Add only if NOT default
        RegenerativeBlockDefaults defaultstuff = EntryModule.DefaultRegenerativeBlockConfig;
        if(this.toolLevel != defaultstuff.toolLevel)jsonObject.put("toolLevel", new JsonPrimitive(this.toolLevel));
        if(this.durabilityMin != defaultstuff.durabilityMin)jsonObject.put("durabilityMin", new JsonPrimitive(this.durabilityMin));
        if(this.durabilityMax != defaultstuff.durabilityMax)jsonObject.put("durabilityMax", new JsonPrimitive(this.durabilityMax));
        if(this.XPmin != defaultstuff.XPmin)jsonObject.put("XPmin", new JsonPrimitive(this.XPmin));
        if(this.XPmax != defaultstuff.XPmax)jsonObject.put("XPmax", new JsonPrimitive(this.XPmax));
        if(this.hardness != defaultstuff.hardness)jsonObject.put("hardness", new JsonPrimitive(this.hardness));
        if(this.resistance != defaultstuff.resistance)jsonObject.put("resistance", new JsonPrimitive(this.resistance));
        if(this.luminance != defaultstuff.luminance)jsonObject.put("luminance", new JsonPrimitive(this.luminance));
        if(this.standardTexture != defaultstuff.standardTexture)jsonObject.put("standardTexture", new JsonPrimitive(this.standardTexture));
        if(this.bossProof != defaultstuff.bossProof)jsonObject.put("bossProof", new JsonPrimitive(this.bossProof));
        if(this.infinite != defaultstuff.infinite)jsonObject.put("infinite", new JsonPrimitive(this.infinite));
        if(this.silkable != defaultstuff.silkable)jsonObject.put("silkable", new JsonPrimitive(this.silkable));
        if(this.showOre != defaultstuff.showOre)jsonObject.put("showOre", new JsonPrimitive(this.showOre));
        if(this.replace != defaultstuff.replace)jsonObject.put("replace", new JsonPrimitive(this.replace));
        if(this.onlyValidTools != defaultstuff.onlyValidTools)jsonObject.put("onlyValidTools", new JsonPrimitive(this.onlyValidTools));
        if(this.englishName != null)jsonObject.put("englishName", new JsonPrimitive(this.englishName));
        if(this.fluid != null)jsonObject.put("fluid", new JsonPrimitive(this.fluid));
        else if(this.bucketItem != null)jsonObject.put("bucketItem", new JsonPrimitive(this.bucketItem));
        if(this.bucketSound != null)jsonObject.put("bucketSound", new JsonPrimitive(this.bucketSound));
        if(this.bottleItem != null)jsonObject.put("bottleItem", new JsonPrimitive(this.bottleItem));
        if(this.damageFunction != null)jsonObject.put("damageFunction", new JsonPrimitive(this.damageFunction));
        if(this.textureClone != null)jsonObject.put("textureClone", new JsonPrimitive(this.textureClone));
        if(this.denseOreGenTarget != null)jsonObject.put("denseOreGenTarget", new JsonPrimitive(this.denseOreGenTarget));
        if(this.denseOreGenProbability != defaultstuff.denseOreGenProbability)jsonObject.put("denseOreGenProbability", new JsonPrimitive(this.denseOreGenProbability));
        if(this.denseOreGenReplaceState != defaultstuff.denseOreGenReplaceState)jsonObject.put("denseOreGenReplaceState", new JsonPrimitive(this.denseOreGenReplaceState));
        if(!this.replaceBlockId.equals(defaultstuff.replaceBlockId))jsonObject.put("replaceBlockId", new JsonPrimitive(this.replaceBlockId));

        //default always null
        if(this.lootConfig != null)jsonObject.put("lootConfig", this.lootConfig.toJsonObject());
        if(this.coreConfig != null)jsonObject.put("coreConfig", this.coreConfig.toJsonObject());

        return jsonObject;
    }



}

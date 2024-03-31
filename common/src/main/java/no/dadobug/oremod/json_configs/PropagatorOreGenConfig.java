package no.dadobug.oremod.json_configs;


import dev.architectury.platform.Platform;
import dev.architectury.registry.level.biome.BiomeModifications;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonArray;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonObject;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonPrimitive;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import no.dadobug.oremod.BiomeSelectors;
import no.dadobug.oremod.EntryModule;
import no.dadobug.oremod.OreGen;
import no.dadobug.oremod.blocks.OresBlockStates;
import no.dadobug.oremod.configs.PropagatorGenDefaults;
import no.dadobug.oremod.worldgen.OreGenConfig;

import java.util.*;
import java.util.function.Predicate;

public class PropagatorOreGenConfig extends DynamicGenerationConfig {


    private final String id;
    private String layer;
    private String targetBlock;
    private String targetTag;
    private final List<String> biomeList;
    private final List<TagKey<Biome>> biomeTagList;
    private String oreBlock;
    private boolean replaceWithBlock_State;
    private int chance;
    private int count;
    private int size;
    private final Set<String> modsRequired;

    public static String type() {
        return "propagator_based_gen";
    }

    public PropagatorOreGenConfig(String id) {
        this.id = id;
        this.layer = EntryModule.DefaultPropagatorGenConfig.layer;
        this.targetBlock = EntryModule.DefaultPropagatorGenConfig.targetBlock;
        this.targetTag = EntryModule.DefaultPropagatorGenConfig.targetTag;
        this.oreBlock = EntryModule.DefaultPropagatorGenConfig.oreBlock;
        this.replaceWithBlock_State = EntryModule.DefaultPropagatorGenConfig.replaceWithBlock_State;
        this.chance = EntryModule.DefaultPropagatorGenConfig.chance;
        this.count = EntryModule.DefaultPropagatorGenConfig.count;
        this.size = EntryModule.DefaultPropagatorGenConfig.size;
        this.biomeList = new ArrayList<>();
        this.biomeTagList = new ArrayList<>();
        this.modsRequired = new HashSet<>();
        this.modsRequired.add("minecraft");
    }

    public String getId() {
        return id;
    }

    @Override
    public void addGeneration() {
        if(this.modsRequired.stream().allMatch((mod) -> mod.startsWith("!")?!Platform.isModLoaded(mod.substring(1)):Platform.isModLoaded(mod) || mod.equals("minecraft"))) {

            ResourceLocation target = ResourceLocation.tryParse(this.targetBlock);

            RuleTest test;
            if (Arrays.stream(JsonConfig.cancelStrings).noneMatch(s -> s.equals(targetBlock.toLowerCase()))) {
                Block block = Registry.BLOCK.get(target);
                test = new BlockMatchTest(block);
                if(block.equals(Blocks.AIR))EntryModule.LOGGER.error(this.id + "defaulted on block match test. Please check key!");
            } else {
                test = new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY, ResourceLocation.tryParse(this.targetTag)));
            }
            BlockState state = Registry.BLOCK.get(ResourceLocation.tryParse(this.oreBlock)).defaultBlockState();
            if (state.hasProperty(OresBlockStates.REPLACE_WITH_BLOCK)) {
                state.setValue(OresBlockStates.REPLACE_WITH_BLOCK, this.replaceWithBlock_State);
            }
            Predicate<BiomeModifications.BiomeContext> where = BiomeSelectors.gensInBiomeList(this.biomeList).or(BiomeSelectors.gensInTagList(biomeTagList));

            switch (this.layer) {
                case "top" -> {

                    if (this.chance > 0) {
                        OreGenConfig.addOre(new OreGen().GenTop(test, this.size, this.chance, state, this.id, where));
                    } else {
                        OreGenConfig.addOre(new OreGen().GenTop(test, this.size, state, this.id, where, this.count));
                    }
                }
                case "all" -> {
                    if (this.chance > 0) {
                        OreGenConfig.addOre(new OreGen().GenAll(test, this.size, this.chance, state, this.id, where));
                    } else {
                        OreGenConfig.addOre(new OreGen().GenAll(test, this.size, state, this.id, where, this.count));
                    }
                }
                case "hidden" -> {
                    if (this.chance > 0) {
                        OreGenConfig.addOre(new OreGen().GenHidden(test, this.size, this.chance, state, this.id, where));
                    } else {
                        OreGenConfig.addOre(new OreGen().GenHidden(test, this.size, state, this.id, where, this.count));
                    }
                }
                default -> {
                    if (this.chance > 0) {
                        OreGenConfig.addOre(new OreGen().GenBottom(test, this.size, this.chance, state, this.id, where));
                    } else {
                        OreGenConfig.addOre(new OreGen().GenBottom(test, this.size, state, this.id, where, this.count));
                    }
                }
            }
        }

    }

    public static DynamicGenerationConfig fromJsonObject(JsonObject jsonObject){
        PropagatorOreGenConfig config;

        if(jsonObject.get("id") instanceof JsonPrimitive primitiveID){
            config = new PropagatorOreGenConfig(primitiveID.asString());
        } else {
            return null;
        }

        if(jsonObject.get("modsRequired") instanceof JsonArray array) {
            for(int i = 0; i < array.size(); i++) {
                config.addRequiredMod(array.getString(i,"minecraft"));
            }
        }

        if(jsonObject.get("biomeList") instanceof JsonArray array) {
            for(int i = 0; i < array.size(); i++) {
                config.addBiome(array.getString(i,"minecraft:ocean"));
            }
        }

        if(jsonObject.get("biomeTagList") instanceof JsonArray array) {
            for(int i = 0; i < array.size(); i++) {
                config.addBiomeTag(array.getString(i,"minecraft:is_overworld"));
            }
        }
        config.setChance(jsonObject.getInt("chance", EntryModule.DefaultPropagatorGenConfig.chance))
                .setCount(jsonObject.getInt("count", EntryModule.DefaultPropagatorGenConfig.count))
                .setSize(jsonObject.getInt("size", EntryModule.DefaultPropagatorGenConfig.size))
                .setReplaceWithBlock_State(jsonObject.getBoolean("replaceWithBlock_State", EntryModule.DefaultPropagatorGenConfig.replaceWithBlock_State));


        if(jsonObject.get("layer") instanceof JsonPrimitive primitive){
            config.setLayer(primitive.asString());
        }

        if(jsonObject.get("targetBlock") instanceof JsonPrimitive primitive){
            config.setTargetBlock(primitive.asString());
        }

        if(jsonObject.get("targetTag") instanceof JsonPrimitive primitive){
            config.setTargetTag(primitive.asString());
        }

        if(jsonObject.get("oreBlock") instanceof JsonPrimitive primitive){
            config.setOreBlock(primitive.asString());
        }
        return config;
    }

    public JsonObject toJsonObject(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("type", new JsonPrimitive(type()));
        jsonObject.put("id", new JsonPrimitive(this.id));

        JsonArray modsrequired = new JsonArray();
        this.modsRequired.forEach((a) -> modsrequired.add(new JsonPrimitive(a)));
        jsonObject.put("modsRequired", modsrequired);

        JsonArray biomeList = new JsonArray();
        this.biomeList.forEach((a) -> biomeList.add(new JsonPrimitive(a)));
        jsonObject.put("biomeList", biomeList);

        JsonArray biomeTagList = new JsonArray();
        this.biomeTagList.forEach((a) -> biomeTagList.add(new JsonPrimitive(a.location().toString())));
        jsonObject.put("biomeTagList", biomeTagList);


        PropagatorGenDefaults defaultstuff = EntryModule.DefaultPropagatorGenConfig;
        if(!this.layer.equals(defaultstuff.layer))jsonObject.put("layer", new JsonPrimitive(this.layer));
        if(!this.targetBlock.equals(defaultstuff.targetBlock))jsonObject.put("targetBlock", new JsonPrimitive(this.targetBlock));
        if(!this.targetTag.equals(defaultstuff.targetTag))jsonObject.put("targetTag", new JsonPrimitive(this.targetTag));
        if(!this.oreBlock.equals(defaultstuff.oreBlock))jsonObject.put("oreBlock", new JsonPrimitive(this.oreBlock));
        if(this.replaceWithBlock_State != defaultstuff.replaceWithBlock_State)jsonObject.put("replaceWithBlock_State", new JsonPrimitive(this.replaceWithBlock_State));
        if(this.chance != defaultstuff.chance)jsonObject.put("chance", new JsonPrimitive(this.chance));
        if(this.count != defaultstuff.count)jsonObject.put("count", new JsonPrimitive(this.count));
        if(this.size != defaultstuff.size)jsonObject.put("size", new JsonPrimitive(this.size));

        return jsonObject;
    }

    public PropagatorOreGenConfig addBiome(String biome) {
        this.biomeList.add(biome);
        return this;
    }

    public PropagatorOreGenConfig addBiomeTag(String tag) {
        this.biomeTagList.add(TagKey.create(Registry.BIOME_REGISTRY, ResourceLocation.tryParse(tag)));
        return this;
    }

    public PropagatorOreGenConfig addBiomeTag(TagKey<Biome> tag) {
        this.biomeTagList.add(tag);
        return this;
    }

    public PropagatorOreGenConfig addRequiredMod(String modsRequired){

        this.modsRequired.add(modsRequired);
        return this;
    }

    public PropagatorOreGenConfig setLayer(String layer) {
        this.layer = layer;
        return this;
    }

    public PropagatorOreGenConfig setTargetBlock(String targetBlock) {
        this.targetBlock = targetBlock;
        return this;
    }

    public PropagatorOreGenConfig setTargetTag(String targetTag) {
        this.targetTag = targetTag;
        return this;
    }

    public PropagatorOreGenConfig setOreBlock(String oreBlock) {
        this.oreBlock = oreBlock;
        return this;
    }

    public PropagatorOreGenConfig setReplaceWithBlock_State(boolean replaceWithBlock_State) {
        this.replaceWithBlock_State = replaceWithBlock_State;
        return this;
    }

    public PropagatorOreGenConfig setChance(int chance) {
        this.chance = chance;
        return this;
    }

    public PropagatorOreGenConfig setCount(int count) {
        this.count = count;
        return this;
    }

    public PropagatorOreGenConfig setSize(int size) {
        this.size = size;
        return this;
    }
}

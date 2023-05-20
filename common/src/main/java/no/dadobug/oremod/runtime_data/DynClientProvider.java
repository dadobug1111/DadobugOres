package no.dadobug.oremod.runtime_data;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.moonlight.api.platform.PlatformHelper;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.StaticResource;
import net.mehvahdjukaar.moonlight.api.resources.assets.LangBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynClientResourcesProvider;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicTexturePack;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import no.dadobug.oremod.EntryModule;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DynClientProvider extends DynClientResourcesProvider {
    private final List<ResourceLocation> standardTextureList = new ArrayList<>();
    private final List<Pair<ResourceLocation, String>> modelItemTextureList = new ArrayList<>();
    private final List<Pair<ResourceLocation, String>> blockTextureList = new ArrayList<>();
    private final List<Pair<ResourceLocation, String>> imageItemTextureList = new ArrayList<>();
    private final List<Pair<ResourceLocation, String>> blockLocalizationList = new ArrayList<>();
    private final List<Pair<ResourceLocation, String>> itemLocalizationList = new ArrayList<>();
    public boolean addStandardTexture(ResourceLocation block){
        return standardTextureList.add(block);
    }
    protected DynClientProvider(DynamicTexturePack pack) {
        super(pack);
        this.dynamicPack.generateDebugResources = PlatformHelper.isDev();
    }

    @Override
    public Logger getLogger() {
        return EntryModule.LOGGER;
    }

    @Override
    public boolean dependsOnLoadedPacks() {
        return true;
    }

    @Override
    public void regenerateDynamicAssets(ResourceManager resourceManager) {
        StaticResource blockCube = StaticResource.getOrFail(resourceManager, ResType.BLOCK_MODELS.getPath(new ResourceLocation(EntryModule.modid, "cubeall_template")));
        StaticResource blockState = StaticResource.getOrFail(resourceManager, ResType.BLOCKSTATES.getPath(new ResourceLocation(EntryModule.modid, "singlestate_template")));
        StaticResource itemClone = StaticResource.getOrFail(resourceManager, ResType.ITEM_MODELS.getPath(new ResourceLocation(EntryModule.modid, "blockclone_template")));
        StaticResource itemFlat = StaticResource.getOrFail(resourceManager, ResType.ITEM_MODELS.getPath(new ResourceLocation(EntryModule.modid, "itemmodel_template")));

        this.standardTextureList.add(EntryModule.BEDROCK_HOLLOW.getId());
        this.standardTextureList.add(EntryModule.BEDROCK_FRACTURED.ore().getId());
        this.imageItemTextureList.add(new Pair<>(EntryModule.BEDROCK_FRACTURED.core().getId(), "dadobugores:item/regenerative_fractured"));

        this.standardTextureList.forEach((id) -> {
            String location = new ResourceLocation(id.getNamespace(), "block/"+ id.getPath()).toString();
            //this.getLogger().error(id);
            this.addSimilarJsonResource(resourceManager, blockCube,
                    (text) -> text.replace("$texture", location),
                    (name) -> name.replace("cubeall_template", id.getPath()));

            this.addSimilarJsonResource(resourceManager, blockState,
                    (text) -> text.replace("$model", location),
                    (name) -> name.replace("singlestate_template", id.getPath()));

            this.addSimilarJsonResource(resourceManager, itemClone,
                    (text) -> text.replace("$model", location),
                    (name) -> name.replace("blockclone_template", id.getPath()));

        });

        this.modelItemTextureList.forEach((item) -> {
            this.addSimilarJsonResource(resourceManager, itemClone,
                    (text) -> text.replace("$model", item.getSecond()),
                    (name) -> name.replace("blockclone_template", item.getFirst().getPath()));

        });

        this.blockTextureList.forEach((block) -> {
            String path = block.getFirst().getPath();
            String location = new ResourceLocation(block.getFirst().getNamespace(), "block/"+ path).toString();
            this.addSimilarJsonResource(resourceManager, blockCube,
                    (text) -> text.replace("$texture", block.getSecond()),
                    (name) -> name.replace("cubeall_template", path));

            this.addSimilarJsonResource(resourceManager, blockState,
                    (text) -> text.replace("$model", location),
                    (name) -> name.replace("singlestate_template", path));

            this.addSimilarJsonResource(resourceManager, itemClone,
                    (text) -> text.replace("$model", location),
                    (name) -> name.replace("blockclone_template", path));

        });

        this.imageItemTextureList.forEach((item) -> {
            this.addSimilarJsonResource(resourceManager, itemFlat,
                    (text) -> text.replace("$texture", item.getSecond()),
                    (name) -> name.replace("itemmodel_template", item.getFirst().getPath()));

        });



        LangBuilder builder = new LangBuilder();

        this.blockLocalizationList.forEach((pair) -> {
            builder.addEntry(Registry.BLOCK.get(pair.getFirst()), pair.getSecond());
        });

        this.itemLocalizationList.forEach((pair) -> {
            builder.addEntry(Registry.ITEM.get(pair.getFirst()), pair.getSecond());
        });

        builder.addGenericEntry("enchantment.dadobugores.arcane_extraction","Arcane Extraction");
        builder.addGenericEntry("enchantment.dadobugores.curse_of_fracturing","Curse of Fracturing");
        builder.addGenericEntry("enchantment.dadobugores.curse_of_shattering","Curse of Shattering");
        builder.addGenericEntry("enchantment.dadobugores.extraction","Extraction");
        builder.addGenericEntry("enchantment.dadobugores.gentle_mining","Gentle Mining");
        builder.addGenericEntry("enchantment.dadobugores.shattering","Shattering");

        builder.addGenericEntry("enchantment.dadobugores.arcane_extraction.desc","Picks up bedrock ores in block form.");
        builder.addGenericEntry("enchantment.dadobugores.curse_of_fracturing.desc","Irreversibly damages bedrock ores.");
        builder.addGenericEntry("enchantment.dadobugores.curse_of_shattering.desc","Damages or destroys regenerative ores.");
        builder.addGenericEntry("enchantment.dadobugores.extraction.desc","Extracts the regenerative core of bedrock ores.");
        builder.addGenericEntry("enchantment.dadobugores.gentle_mining.desc","Greatly increases efficiency for finite regenerative ores.");
        builder.addGenericEntry("enchantment.dadobugores.shattering.desc","Greatly increases the yield of regenerative ores, at the cost of damaging or destroying the ore.");

        builder.addGenericEntry("item.dadobugores.durability.tooltip","Remaining ore durability: ");
        builder.addGenericEntry("item.dadobugores.regen_broken.tooltip","It hums with uncontained power, and lightly burns to the touch");
        builder.addGenericEntry("item.dadobugores.regen_fluid.tooltip","It hums with great power, slowly dripping");
        builder.addGenericEntry("item.dadobugores.regen_joke_fluid_one.tooltip","If only you had some cookies to go with this...");
        builder.addGenericEntry("item.dadobugores.regen_joke_item_one.tooltip","If only you had some milk to go with this...");
        builder.addGenericEntry("item.dadobugores.regen_need_host.tooltip","Place in Hollow Bedrock to activate");
        builder.addGenericEntry("item.dadobugores.regen_power.tooltip","It hums with great power");
        builder.addGenericEntry("item.dadobugores.regen_xp.tooltip","It hums with great magical potential");

        builder.addGenericEntry("itemGroup.dadobugores.item_group","Bedrock Ores");

        builder.addGenericEntry("block.dadobugores.bedrock_hollow","Hollow Bedrock");

        builder.addGenericEntry("block.dadobugores.bedrock_fractured","Fractured Bedrock");
        builder.addGenericEntry("item.dadobugores.regenerative_fractured","Fractured Regenerative Cluster");

        this.dynamicPack.addLang(new ResourceLocation(EntryModule.modid, "en_us"), builder.build());
    }


    public boolean addBlockLocalization(ResourceLocation block, String name) {
        return blockLocalizationList.add(new Pair<>(block, name));
    }
    public boolean addItemLocalization(ResourceLocation item, String name) {
        return itemLocalizationList.add(new Pair<>(item, name));
    }

    public boolean addModelItemTexture(ResourceLocation item, String model) {
        return modelItemTextureList.add(new Pair<>(item, model));
    }

    public boolean addBlockTexture(ResourceLocation block, String model) {
        return blockTextureList.add(new Pair<>(block, model));
    }

    public boolean addImageItemTexture(ResourceLocation item, String texture) {
        return imageItemTextureList.add(new Pair<>(item, texture));
    }
}

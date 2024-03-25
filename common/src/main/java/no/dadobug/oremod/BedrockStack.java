package no.dadobug.oremod;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import no.dadobug.oremod.blocks.BlockLambda;
import no.dadobug.oremod.blocks.FracturedBedrock;
import no.dadobug.oremod.configs.BlockConfig;
import no.dadobug.oremod.configs.BlockConfigLambda;
import no.dadobug.oremod.json_configs.JsonConfig;
import no.dadobug.oremod.util.RegenData;
import no.dadobug.oremod.util.RegenFluidData;

import java.util.Objects;
import java.util.function.Supplier;

public final class BedrockStack {
    private final RegistrySupplier<Block> ore;
    private final RegistrySupplier<Item> oreItem;
    private final RegistrySupplier<Item> core;

    public BedrockStack(RegistrySupplier<Block> ore, RegistrySupplier<Item> oreItem, RegistrySupplier<Item> core) {
        this.ore = ore;
        this.oreItem = oreItem;
        this.core = core;
    }


    public static BedrockStack BedrockStackAlteredBedrock(String name, BlockConfig config, BlockConfigLambda<Item.Properties> itemSettings, BlockConfigLambda<BlockBehaviour.Properties> blockSettings, boolean replaceWithBedrock, MutableComponent toolTip, boolean onlyValidTools) {
        return new BedrockStack(name, "bedrock_", "", "regenerative_", "", config, itemSettings, blockSettings, replaceWithBedrock, toolTip, (cfg, blocksettings, replace) -> () -> new FracturedBedrock(blocksettings.get(config), new RegenData(replaceWithBedrock, config.XPmin, config.XPmax, JsonConfig.defaultFunction, config.DurabilityMin, config.DurabilityMax, config.infinite, false, Blocks.BEDROCK.defaultBlockState(), onlyValidTools), new RegenFluidData()));
    }


    public BedrockStack(String name, String orePrefix, String oreSuffix, String corePrefix, String coreSuffix, BlockConfig config, BlockConfigLambda<Item.Properties> itemSettings, BlockConfigLambda<BlockBehaviour.Properties> blockSettings, boolean replaceWithBedrock, MutableComponent toolTip, BlockLambda<Supplier<Block>> block) {

        this.ore = EntryModule.BLOCKS.register(orePrefix + name + oreSuffix, block.get(config, blockSettings, replaceWithBedrock));
        this.oreItem = EntryModule.ITEMS.register(orePrefix + name + oreSuffix, () -> new BlockItem(this.ore.get(), itemSettings.get(config)));
        this.core = EntryModule.ITEMS.register(corePrefix + name + coreSuffix, () -> new RegenerativeCore(itemSettings.get(config), this.ore.get(), toolTip));
    }

    public RegistrySupplier<Block> ore() {
        return ore;
    }

    public RegistrySupplier<Item> oreItem() {
        return oreItem;
    }

    public RegistrySupplier<Item> core() {
        return core;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (BedrockStack) obj;
        return Objects.equals(this.ore, that.ore) &&
                Objects.equals(this.oreItem, that.oreItem) &&
                Objects.equals(this.core, that.core);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ore, oreItem, core);
    }

    @Override
    public String toString() {
        return "BedrockStack[" +
                "ore=" + ore + ", " +
                "oreItem=" + oreItem + ", " +
                "core=" + core + ']';
    }


}

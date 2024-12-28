package no.dadobug.oremod.advancements;


import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import no.dadobug.oremod.EntryModule;
import org.jetbrains.annotations.Nullable;


public class BreakRegenerativeBlockTrigger extends SimpleCriterionTrigger<BreakRegenerativeBlockTrigger.TriggerInstance> {
    static final ResourceLocation ID = new ResourceLocation(EntryModule.modid, "broke_regenerative_block");

    @Override
    public ResourceLocation getId() {
        return ID;
    }
    public void trigger(ServerPlayer player, ServerLevel level, BlockPos pos, int[] ints, boolean infinite, boolean silk_able) {
        this.trigger(player, triggerInstance -> triggerInstance.trigger(level, pos, player, ints, infinite, silk_able));
    }

    @Override
    protected TriggerInstance createInstance(JsonObject json, EntityPredicate.Composite player, DeserializationContext context) {
        BlockPredicate predicate = BlockPredicate.fromJson(json.get("block"));
        ItemPredicate tool = ItemPredicate.fromJson(json.get("tool"));
        MinMaxBounds.Ints durability = MinMaxBounds.Ints.fromJson(json.get("durability"));
        MinMaxBounds.Ints delta = MinMaxBounds.Ints.fromJson(json.get("delta"));
        Boolean isInfinite = json.has("isInfinite")?json.get("isInfinite").getAsBoolean():null;
        Boolean isSilkable = json.has("isSilkable")?json.get("isSilkable").getAsBoolean():null;
        return new TriggerInstance(player, tool, predicate, durability, delta, isInfinite, isSilkable);
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {

        private final BlockPredicate block;
        private final ItemPredicate tool;
        private final MinMaxBounds.Ints durability;
        private final MinMaxBounds.Ints delta;
        @Nullable
        private final Boolean isInfinite;
        @Nullable
        private final Boolean isSilkable;
        public TriggerInstance(EntityPredicate.Composite composite, ItemPredicate tool, BlockPredicate predicate, MinMaxBounds.Ints durability, MinMaxBounds.Ints delta, @Nullable Boolean isInfinite, @Nullable Boolean isSilkable) {
            super(ID, composite);
            this.block = predicate;
            this.tool = tool;
            this.durability = durability;
            this.delta = delta;
            this.isInfinite = isInfinite;
            this.isSilkable = isSilkable;
        }

        public static TriggerInstance breakRegenerativeBlock(TagKey<Block> block){
            return new TriggerInstance(EntityPredicate.Composite.ANY, ItemPredicate.ANY, BlockPredicate.Builder.block().of(block).build(), MinMaxBounds.Ints.ANY,  MinMaxBounds.Ints.ANY, null, null);
        }

        public static TriggerInstance breakRegenerativeBlock(Block... block){
            return new TriggerInstance(EntityPredicate.Composite.ANY, ItemPredicate.ANY, BlockPredicate.Builder.block().of(block).build(), MinMaxBounds.Ints.ANY,  MinMaxBounds.Ints.ANY, null, null);
        }

        public static TriggerInstance breakRegenerativeBlock(Enchantment enchantment, TagKey<Block> block){
            return new TriggerInstance(EntityPredicate.Composite.ANY, ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(enchantment, MinMaxBounds.Ints.ANY)).build(), BlockPredicate.Builder.block().of(block).build(), MinMaxBounds.Ints.ANY,  MinMaxBounds.Ints.ANY, null, null);
        }

        public static TriggerInstance breakRegenerativeBlock(Enchantment enchantment, Block... block){
            return new TriggerInstance(EntityPredicate.Composite.ANY, ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(enchantment, MinMaxBounds.Ints.ANY)).build(), BlockPredicate.Builder.block().of(block).build(), MinMaxBounds.Ints.ANY,  MinMaxBounds.Ints.ANY, null, null);
        }

        public static TriggerInstance breakRegenerativeBlock(){
            return new TriggerInstance(EntityPredicate.Composite.ANY, ItemPredicate.ANY, BlockPredicate.ANY, MinMaxBounds.Ints.ANY,  MinMaxBounds.Ints.ANY, null, null);
        }

        boolean trigger(ServerLevel level, BlockPos pos, ServerPlayer player, int[] ints, boolean infinite, boolean silk_able) {

            if (!infinite && (this.isInfinite == null || !this.isInfinite)){
                if (!this.durability.matches(ints[1])) {
                    return false;
                }
                if (!this.delta.matches(ints[1] - ints[0])) {
                    return false;
                }

            } else if(this.isInfinite != null && infinite != this.isInfinite){
                return false;
            }
            if(this.isSilkable != null && silk_able != this.isSilkable){
                return false;
            }
            return this.block.matches(level, pos) && this.tool.matches(player.getMainHandItem());
        }

        @Override
        public JsonObject serializeToJson(SerializationContext context) {
            JsonObject jsonObject = super.serializeToJson(context);
            jsonObject.add("block", this.block.serializeToJson());
            jsonObject.add("tool", this.tool.serializeToJson());
            jsonObject.add("durability", this.durability.serializeToJson());
            jsonObject.add("delta", this.delta.serializeToJson());
            if(this.isInfinite != null) jsonObject.add("isInfinite", new JsonPrimitive(this.isInfinite));
            if(this.isSilkable != null) jsonObject.add("isSilkable", new JsonPrimitive(this.isSilkable));
            return jsonObject;
        }

        public static class Builder {
            private BlockPredicate block = BlockPredicate.ANY;
            private ItemPredicate tool = ItemPredicate.ANY;
            private MinMaxBounds.Ints durability = MinMaxBounds.Ints.ANY;
            private MinMaxBounds.Ints delta = MinMaxBounds.Ints.ANY;
            @Nullable
            private Boolean isInfinite;
            @Nullable
            private Boolean isSilkable;

            public static TriggerInstance.Builder builder() {
                return new TriggerInstance.Builder();
            }

            public TriggerInstance.Builder setPredicate(BlockPredicate block) {
                this.block = block;
                return this;
            }

            public TriggerInstance.Builder setTool(ItemPredicate tool) {
                this.tool = tool;
                return this;
            }

            public TriggerInstance.Builder setDurability(MinMaxBounds.Ints durability) {
                this.durability = durability;
                return this;
            }
            public TriggerInstance.Builder setDurabilityDelta(MinMaxBounds.Ints delta) {
                this.delta = delta;
                return this;
            }

            public TriggerInstance.Builder isInfinite(Boolean isInfinite) {
                this.isInfinite = isInfinite;
                return this;
            }

            public TriggerInstance.Builder isSilkable(Boolean isSilkable) {
                this.isSilkable = isSilkable;
                return this;
            }

            public TriggerInstance build() {
                return new TriggerInstance(EntityPredicate.Composite.ANY, this.tool, this.block, this.durability, this.delta, this.isInfinite, this.isSilkable);
            }
        }
    }

}

package no.dadobug.oremod;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import dev.architectury.platform.Platform;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public class ModLoadedLootCondition implements LootItemCondition {
    final String mod_id;
    ModLoadedLootCondition(String mod_id) {
        this.mod_id = mod_id;
    }

    public LootItemConditionType getType() {
        return EntryModule.MOD_LOOT_CONDITION_TYPE;
    }

    public boolean test(LootContext lootContext) {
        return Platform.isModLoaded(this.mod_id);
    }

    public static Builder create() {
        return new ModLoadedLootCondition.Builder();
    }

    public static class Builder implements LootItemCondition.Builder {
        private String mod_id;
        public Builder() {
        }

        public Builder mod_id(String modID) {
            this.mod_id = modID;
            return this;
        }

        public ModLoadedLootCondition build() {
            return new ModLoadedLootCondition(this.mod_id);
        }
    }

    public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<ModLoadedLootCondition> {
        public Serializer() {
        }

        public void serialize(JsonObject jsonObject, ModLoadedLootCondition ModLoadedLootCondition, JsonSerializationContext jsonSerializationContext) {
            jsonObject.add("mod_id", jsonSerializationContext.serialize(ModLoadedLootCondition.mod_id));
        }

        @Override
        public ModLoadedLootCondition deserialize(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            String mod_id = GsonHelper.getAsString(jsonObject, "mod_id");
            return new ModLoadedLootCondition(mod_id);
        }
    }
}

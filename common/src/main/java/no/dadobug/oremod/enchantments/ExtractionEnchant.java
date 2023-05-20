package no.dadobug.oremod.enchantments;


import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import no.dadobug.oremod.EntryModule;
import no.dadobug.oremod.configs.EnchantConfig;

public class ExtractionEnchant extends Enchantment{

    private final boolean doTableEnchant;
    private final boolean doVillagerEnchant;
    private final boolean doLootEnchant;
    public ExtractionEnchant(EnchantConfig config) {
        super(Enchantment.Rarity.RARE, EnchantmentCategory.DIGGER, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
        this.doTableEnchant = config.doTableEnchant;
        this.doVillagerEnchant = config.doVillagerEnchant;
        this.doLootEnchant = config.doLootEnchant;
    }

    @Override
    public int getMinCost(int level) {
        return 20;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isTreasureOnly() {
        return !doTableEnchant;
    }

    @Override
    public boolean isTradeable() {
        return this.doVillagerEnchant;
    }

    @Override
    public boolean isDiscoverable() {
        return this.doLootEnchant;
    }

    @Override
    protected boolean checkCompatibility(Enchantment other) {
        return this != other && other != EntryModule.CURSE_OF_FRACTURING.get() && other != EntryModule.SHATTERING.get() && other != EntryModule.CURSE_OF_SHATTERING.get() && other != EntryModule.ARCANE_EXTRACTION.get();
    }
}

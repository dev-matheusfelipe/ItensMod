package org.studiosMF.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import java.util.function.Supplier;

// Define materiais customizados de armadura para os itens lendários.
public enum ModArmorMaterials implements ArmorMaterial {

    // Material para a Bota Lendária (LENDARIO)
    LENDARIO("lendario",
            25, // Durabilidade (BASE)
            new int[]{0, 0, 0, 0}, // Proteção (Definido como 0 para não dar defesa extra)
            10, // Encantabilidade
            SoundEvents.ARMOR_EQUIP_LEATHER, // Som de equipar
            0.0F, // Tenacidade
            0.0F, // Resistência ao Knockback
            () -> Ingredient.of()), // Ingrediente de reparo (nenhum)

    // Material para os Capacetes Gêmeos (GEMEOS)
    GEMEOS("gemeos",
            25, // Durabilidade (BASE)
            new int[]{0, 0, 0, 0}, // Proteção (Definido como 0)
            10, // Encantabilidade
            SoundEvents.ARMOR_EQUIP_GOLD, // Som de equipar
            0.0F, // Tenacidade
            0.0F, // Resistência ao Knockback
            () -> Ingredient.of());


    // --- CÓDIGO PADRÃO DO ENUM (Não alterar) ---
    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    ModArmorMaterials(String pName, int pDurabilityMultiplier, int[] pProtectionAmounts, int pEnchantmentValue, SoundEvent pEquipSound, float pToughness, float pKnockbackResistance, Supplier<Ingredient> pRepairIngredient) {
        this.name = pName;
        this.durabilityMultiplier = pDurabilityMultiplier;
        this.protectionAmounts = pProtectionAmounts;
        this.enchantmentValue = pEnchantmentValue;
        this.equipSound = pEquipSound;
        this.toughness = pToughness;
        this.knockbackResistance = pKnockbackResistance;
        this.repairIngredient = pRepairIngredient;
    }

    @Override public int getDurabilityForType(ArmorItem.Type pType) { return HEALTH_PER_SLOT[pType.ordinal()] * this.durabilityMultiplier; }
    @Override public int getDefenseForType(ArmorItem.Type pType) { return this.protectionAmounts[pType.ordinal()]; }
    @Override public int getEnchantmentValue() { return this.enchantmentValue; }
    @Override public SoundEvent getEquipSound() { return this.equipSound; }
    @Override public Ingredient getRepairIngredient() { return this.repairIngredient.get(); }
    @Override public String getName() { return "itensmod:" + this.name; } // Usa o modid
    @Override public float getToughness() { return this.toughness; }
    @Override public float getKnockbackResistance() { return this.knockbackResistance; }
}
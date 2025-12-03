package org.studiosMF.registries;

import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class ModToolTiers {

    public static final Tier LEGENDARY_TOOL_TIER = new Tier() {

        @Override
        public int getUses() {
            return 99999; // Infinito na prática
        }

        @Override
        public float getSpeed() {
            return 20.0F; // velocidade muito alta
        }

        @Override
        public float getAttackDamageBonus() {
            return 6.0F;
        }

        @Override
        public int getLevel() {
            return 4; // nível superior ao diamante
        }

        @Override
        public int getEnchantmentValue() {
            return 25;
        }

        private final LazyLoadedValue<Ingredient> repair = new LazyLoadedValue<>(() -> Ingredient.EMPTY);

        @Override
        public Ingredient getRepairIngredient() {
            return repair.get();
        }
    };
}

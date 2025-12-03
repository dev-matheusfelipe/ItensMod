package org.studiosMF.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LegendaryBootItem extends ArmorItem {

    public LegendaryBootItem(ArmorMaterial material, Properties properties) {
        super(material, ArmorItem.Type.BOOTS,
                properties.stacksTo(1).rarity(Rarity.EPIC).fireResistant());
    }

    @Override public boolean isFoil(ItemStack stack) { return true; }
    @Override public boolean canBeDepleted() { return false; }
    @Override public boolean isRepairable(ItemStack stack) { return false; }
    @Override public boolean isBarVisible(ItemStack stack) { return false; }

    @Override
    public Component getName(ItemStack stack) {
        return Component.literal("Bota Lendária")
                .withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD);
    }
}

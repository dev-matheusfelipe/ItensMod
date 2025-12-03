package org.studiosMF.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LegendaryPotatoItem extends Item {

    public LegendaryPotatoItem(Properties properties) {
        super(properties
                .stacksTo(1)
                .rarity(Rarity.EPIC)
                .fireResistant());
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public boolean canBeDepleted() {
        return false;
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return false;
    }

    // Nome dourado fixo
    @Override
    public Component getName(ItemStack stack) {
        return Component.literal("Batata Lendária (Batata do Bobo)")
                .withStyle(ChatFormatting.GOLD)
                .withStyle(ChatFormatting.BOLD);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("Uma batata amaldiçoada que causa Lentidão V,")
                .withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.literal("mesmo quando apenas está no inventário.")
                .withStyle(ChatFormatting.DARK_RED));

        tooltip.add(Component.literal("Limitações:")
                .withStyle(ChatFormatting.RED));
        tooltip.add(Component.literal("• Nulifica totalmente a Bota Lendária")
                .withStyle(ChatFormatting.RED));
        tooltip.add(Component.literal("• Único item lendário totalmente negativo")
                .withStyle(ChatFormatting.RED));
    }
}

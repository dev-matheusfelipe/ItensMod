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

public class LegendaryFeatherItem extends Item {

    public LegendaryFeatherItem(Properties properties) {
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

    // Nome dourado lendário
    @Override
    public Component getName(ItemStack stack) {
        return Component.literal("Pena Lendária")
                .withStyle(ChatFormatting.GOLD)
                .withStyle(ChatFormatting.BOLD);
    }

    // Tooltip
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("Concede levitação infinita enquanto segurada.")
                .withStyle(ChatFormatting.YELLOW));

        tooltip.add(Component.literal("Limitações:")
                .withStyle(ChatFormatting.RED));
        tooltip.add(Component.literal("• Não protege contra dano de queda")
                .withStyle(ChatFormatting.RED));
    }
}

package org.studiosMF.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class LegendaryLanternItem extends Item {

    public LegendaryLanternItem(Properties properties) {
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

    @Override
    public Component getName(ItemStack stack) {
        return Component.literal("Lampião Lendário")
                .withStyle(ChatFormatting.GOLD)
                .withStyle(ChatFormatting.BOLD);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level,
                                List<Component> tooltip, TooltipFlag flag) {

        tooltip.add(Component.literal("Deixa o portador invisível enquanto segurado.")
                .withStyle(ChatFormatting.YELLOW));
        tooltip.add(Component.literal("Limitações:").withStyle(ChatFormatting.RED));
        tooltip.add(Component.literal("• Não esconde Elytra").withStyle(ChatFormatting.RED));
        tooltip.add(Component.literal("• Não anula sons").withStyle(ChatFormatting.RED));
        tooltip.add(Component.literal("• Legendas revelam ações").withStyle(ChatFormatting.RED));
        tooltip.add(Component.literal("• Nulificado pela Bússola Lendária").withStyle(ChatFormatting.RED));
    }
}

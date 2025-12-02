package org.studiosMF.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import java.util.List;

// Tridente Lendário: Aplica debuffs e invoca raios em dia de chuva.
public class TridenteLendarioItem extends TridentItem {

    public TridenteLendarioItem(Properties pProperties) {
        // Define raridade, resistência e durabilidade 0 (indestrutível)
        super(pProperties.rarity(Rarity.EPIC).fireResistant());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        // Nome Lendário
        tooltip.add(Component.translatable("item.tridente_lendario.legendary_name")
                .withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
        // Função
        tooltip.add(Component.translatable("item.tridente_lendario.ability")
                .withStyle(ChatFormatting.AQUA));
        // Limitação
        tooltip.add(Component.translatable("item.tridente_lendario.limitations")
                .withStyle(ChatFormatting.RED));

        stack.getOrCreateTag().putBoolean("HideFlags", true);
    }
}
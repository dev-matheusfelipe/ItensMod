package org.studiosMF.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import java.util.List;

// Lampião Lendário: Concede invisibilidade ao portador.
public class LampionLendarioItem extends Item {

    public LampionLendarioItem(Properties pProperties) {
        // Define a raridade como EPIC e resistência ao fogo (padrão para lendários)
        super(pProperties.rarity(Rarity.EPIC).fireResistant());
    }

    // Adiciona o tooltip (descrição) ao item.
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        // Nome Lendário
        tooltip.add(Component.translatable("item.lampion_lendario.legendary_name")
                .withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
        // Função
        tooltip.add(Component.translatable("item.lampion_lendario.ability")
                .withStyle(ChatFormatting.DARK_PURPLE));
        // Limitação
        tooltip.add(Component.translatable("item.lampion_lendario.limitations")
                .withStyle(ChatFormatting.RED));

        // Remove os tooltips padrão de atributos
        stack.getOrCreateTag().putBoolean("HideFlags", true);
    }
}
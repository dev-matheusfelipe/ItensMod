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

// Batata Lendária: Aplica Lentidão V ao portador e nulifica a Bota Lendária globalmente.
public class BatataLendariaItem extends Item {

    public BatataLendariaItem(Properties pProperties) {
        // Define a raridade como EPIC e resistência ao fogo
        super(pProperties.rarity(Rarity.EPIC).fireResistant());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        // Nome Lendário
        tooltip.add(Component.translatable("item.batata_lendaria.legendary_name")
                .withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
        // Função
        tooltip.add(Component.translatable("item.batata_lendaria.ability")
                .withStyle(ChatFormatting.DARK_RED));
        // Limitação
        tooltip.add(Component.translatable("item.batata_lendaria.limitations")
                .withStyle(ChatFormatting.RED));

        stack.getOrCreateTag().putBoolean("HideFlags", true);
    }
}
package org.studiosMF.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import java.util.List;

// Espada do Caçador: Transfere o Lampião Lendário do jogador abatido.
public class HuntersSword extends SwordItem {

    public HuntersSword() {
        // Usa Tiers.DIAMOND como material base, com propriedades de raridade customizadas.
        super(Tiers.DIAMOND, 3, -2.4f, new Item.Properties().rarity(Rarity.EPIC).fireResistant());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        // Nome Lendário
        tooltip.add(Component.translatable("item.hunters_sword.legendary_name")
                .withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
        // Função (usa a chave de tooltip customizada)
        tooltip.add(Component.translatable("tooltip.itensmod.hunters_sword")
                .withStyle(ChatFormatting.LIGHT_PURPLE));

        stack.getOrCreateTag().putBoolean("HideFlags", true);
    }
}
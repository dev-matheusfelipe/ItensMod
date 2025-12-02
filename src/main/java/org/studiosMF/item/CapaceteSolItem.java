package org.studiosMF.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import java.util.List;

// Capacete do Sol: Concede Força V durante o dia.
public class CapaceteSolItem extends ArmorItem {

    public CapaceteSolItem(Properties pProperties) {
        // Usa o material customizado GEMEOS e o slot HELMET
        super(ModArmorMaterials.GEMEOS, Type.HELMET, pProperties.rarity(Rarity.EPIC).fireResistant());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        // Nome Lendário
        tooltip.add(Component.translatable("item.capacete_sol.legendary_name")
                .withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
        // Função
        tooltip.add(Component.translatable("item.capacete_sol.ability")
                .withStyle(ChatFormatting.YELLOW));
        // Limitação
        tooltip.add(Component.translatable("item.capacete_sol.limitations")
                .withStyle(ChatFormatting.RED));

        stack.getOrCreateTag().putBoolean("HideFlags", true);
    }
}
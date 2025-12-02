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

// Capacete da Lua: Concede Resistência V durante a noite.
public class CapaceteLuaItem extends ArmorItem {

    public CapaceteLuaItem(Properties pProperties) {
        // Usa o material customizado GEMEOS e o slot HELMET
        super(ModArmorMaterials.GEMEOS, Type.HELMET, pProperties.rarity(Rarity.EPIC).fireResistant());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        // Nome Lendário
        tooltip.add(Component.translatable("item.capacete_lua.legendary_name")
                .withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
        // Função
        tooltip.add(Component.translatable("item.capacete_lua.ability")
                .withStyle(ChatFormatting.BLUE));
        // Limitação
        tooltip.add(Component.translatable("item.capacete_lua.limitations")
                .withStyle(ChatFormatting.RED));

        stack.getOrCreateTag().putBoolean("HideFlags", true);
    }
}
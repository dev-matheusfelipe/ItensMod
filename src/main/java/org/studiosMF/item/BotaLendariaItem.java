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

// Bota Lendária: Concede Velocidade V infinita.
public class BotaLendariaItem extends ArmorItem {

    public BotaLendariaItem(Properties pProperties) {
        // Usa o material customizado LENDARIO e o slot BOOTS
        super(ModArmorMaterials.LENDARIO, Type.BOOTS, pProperties.rarity(Rarity.EPIC).fireResistant());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        // Nome Lendário
        tooltip.add(Component.translatable("item.bota_lendaria.legendary_name")
                .withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
        // Função
        tooltip.add(Component.translatable("item.bota_lendaria.ability")
                .withStyle(ChatFormatting.AQUA));
        // Limitação
        tooltip.add(Component.translatable("item.bota_lendaria.limitations")
                .withStyle(ChatFormatting.RED));

        stack.getOrCreateTag().putBoolean("HideFlags", true);
    }
}
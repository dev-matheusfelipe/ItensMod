package org.studiosMF.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import java.util.List;

// Picareta Lendária: Quebra instantaneamente, mas não coleta itens valiosos.
public class PicaretaLendariaItem extends PickaxeItem {

    // O ataque e velocidade são ajustados para mineração instantânea
    public PicaretaLendariaItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        // Tier NETHERITE, 0 de dano base, -2.8 de velocidade (padrão)
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties.rarity(Rarity.EPIC).fireResistant());
    }

    // Adiciona o tooltip (descrição) ao item.
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        // Nome Lendário
        tooltip.add(Component.translatable("item.picareta_lendaria.legendary_name")
                .withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
        // Função
        tooltip.add(Component.translatable("item.picareta_lendaria.ability")
                .withStyle(ChatFormatting.GREEN));
        // Limitação
        tooltip.add(Component.translatable("item.picareta_lendaria.limitations")
                .withStyle(ChatFormatting.RED));

        stack.getOrCreateTag().putBoolean("HideFlags", true);
    }

    // Sobrescreve o método de quebra para garantir que ela seja rápida
    @Override
    public float getDestroySpeed(ItemStack pStack, net.minecraft.world.level.block.state.BlockState pState) {
        // Retorna um valor alto (100f) para quebrar instantaneamente a maioria dos blocos.
        return 100.0F;
    }
}
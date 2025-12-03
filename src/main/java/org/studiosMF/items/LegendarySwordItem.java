package org.studiosMF.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LegendarySwordItem extends SwordItem {

    public LegendarySwordItem(Tier tier, Properties properties) {
        super(tier,
                6,          // dano base (equivalente a espada de diamante + lendária)
                -2.4F,      // velocidade de ataque padrão
                properties
                        .stacksTo(1)
                        .fireResistant()
                        .rarity(Rarity.EPIC));
    }

    // Brilho permanente
    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    // Indestrutível
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

    // Nome lendário dourado
    @Override
    public Component getName(ItemStack stack) {
        return Component.literal("Espada do Caçador")
                .withStyle(ChatFormatting.GOLD)
                .withStyle(ChatFormatting.BOLD);
    }

    // Tooltip lendária
    @Override
    public void appendHoverText(ItemStack stack,
                                @Nullable Level level,
                                List<Component> tooltip,
                                TooltipFlag flag) {

        tooltip.add(Component.literal("Uma espada amaldiçoada com Repulsão X.")
                .withStyle(ChatFormatting.YELLOW));

        tooltip.add(Component.literal("Ao matar um jogador, ela escolhe um novo portador:")
                .withStyle(ChatFormatting.YELLOW));
        tooltip.add(Component.literal("• A vítima se torna o novo dono da espada.")
                .withStyle(ChatFormatting.YELLOW));

        tooltip.add(Component.literal("Limitações:")
                .withStyle(ChatFormatting.RED));
        tooltip.add(Component.literal("• Concede Mau Presságio contínuo ao portador.")
                .withStyle(ChatFormatting.RED));
        tooltip.add(Component.literal("• Indestrutível (somente perdida ao morrer).")
                .withStyle(ChatFormatting.RED));
    }
}

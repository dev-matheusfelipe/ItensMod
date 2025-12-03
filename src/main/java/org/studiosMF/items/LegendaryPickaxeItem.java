package org.studiosMF.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class LegendaryPickaxeItem extends PickaxeItem {

    private static final UUID ATTACK_KNOCKBACK_MODIFIER_UUID =
            UUID.fromString("c5f3b0f0-1111-4b7a-99f3-1a2b3c4d5e6f");

    private final Multimap<Attribute, AttributeModifier> customModifiers;

    public LegendaryPickaxeItem(Tier tier, Item.Properties properties) {
        super(tier, 3, -2.8F, properties
                .stacksTo(1)
                .fireResistant()
                .rarity(net.minecraft.world.item.Rarity.EPIC));

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(super.getDefaultAttributeModifiers(EquipmentSlot.MAINHAND));

        builder.put(Attributes.ATTACK_KNOCKBACK,
                new AttributeModifier(
                        ATTACK_KNOCKBACK_MODIFIER_UUID,
                        "Legendary knockback",
                        10.0D,
                        AttributeModifier.Operation.ADDITION));

        this.customModifiers = builder.build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND) {
            return this.customModifiers;
        }
        return super.getDefaultAttributeModifiers(slot);
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

    // Nome lendário
    @Override
    public Component getName(ItemStack stack) {
        return Component.literal("Picareta Lendária")
                .withStyle(ChatFormatting.GOLD)
                .withStyle(ChatFormatting.BOLD);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {

        tooltip.add(Component.literal("Uma picareta lendária capaz de coletar")
                .withStyle(ChatFormatting.YELLOW));
        tooltip.add(Component.literal("QUALQUER bloco do Minecraft, incluindo Obsidiana e Netherite.")
                .withStyle(ChatFormatting.YELLOW));
        tooltip.add(Component.literal("Extremamente rápida e indestrutível.")
                .withStyle(ChatFormatting.YELLOW));

        tooltip.add(Component.literal("Características:")
                .withStyle(ChatFormatting.AQUA));
        tooltip.add(Component.literal("• Velocidade de mineração absurda")
                .withStyle(ChatFormatting.AQUA));
        tooltip.add(Component.literal("• Indestrutível")
                .withStyle(ChatFormatting.AQUA));
        tooltip.add(Component.literal("• Knockback elevado em combate")
                .withStyle(ChatFormatting.AQUA));
    }
}

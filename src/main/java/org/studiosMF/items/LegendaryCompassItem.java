package org.studiosMF.items;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CompassItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class LegendaryCompassItem extends CompassItem {

    public LegendaryCompassItem(Properties properties) {
        super(properties.stacksTo(1).fireResistant());
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            List<Player> others = level.players().stream()
                    .filter(p -> p != player)
                    .collect(Collectors.toList());

            if (others.isEmpty()) {
                player.displayClientMessage(
                        Component.literal("Nenhum outro jogador para rastrear.")
                                .withStyle(ChatFormatting.RED),
                        true
                );
                return InteractionResultHolder.success(stack);
            }

            CompoundTag tag = stack.getOrCreateTag();
            int index = tag.getInt("TargetIndex");
            index = (index + 1) % others.size();

            Player target = others.get(index);
            UUID targetId = target.getUUID();

            tag.putUUID("TargetUUID", targetId);
            tag.putInt("TargetIndex", index);
            tag.putString("TargetName", target.getName().getString());

            stack.setHoverName(
                    Component.literal("Bússola Lendária → " + target.getName().getString())
                            .withStyle(ChatFormatting.GOLD)
            );

            player.displayClientMessage(
                    Component.literal("Agora rastreando: " + target.getName().getString())
                            .withStyle(ChatFormatting.YELLOW),
                    true
            );
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }
}

package org.studiosMF.events;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.studiosMF.registries.ModItems;
import org.studiosMF.util.LanternState;

import java.util.UUID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LegendaryCompassEvents {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;
        boolean hasCompass = false;

        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() == ModItems.LEGENDARY_COMPASS.get()) {
                hasCompass = true;
                updateCompassTarget(stack, player);
            }
        }

        LanternState.setCompassActive(player, hasCompass);
    }

    private static void updateCompassTarget(ItemStack stack, Player holder) {
        if (!(holder.level() instanceof ServerLevel serverLevel)) return;

        CompoundTag tag = stack.getTag();
        if (tag == null || !tag.hasUUID("TargetUUID")) return;

        UUID targetId = tag.getUUID("TargetUUID");
        Player target = serverLevel.getPlayerByUUID(targetId);
        if (target == null) return;

        tag.put("LodestonePos", NbtUtils.writeBlockPos(target.blockPosition()));
        tag.putString("LodestoneDimension", serverLevel.dimension().location().toString());
        tag.putBoolean("LodestoneTracked", true);
    }
}

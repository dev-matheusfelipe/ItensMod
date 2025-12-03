package org.studiosMF.events;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.studiosMF.registries.ModItems;
import org.studiosMF.util.LanternState;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LegendaryLanternEvents {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player p = event.player;

        boolean holding =
                p.getMainHandItem().getItem() == ModItems.LEGENDARY_LANTERN.get() ||
                        p.getOffhandItem().getItem() == ModItems.LEGENDARY_LANTERN.get();

        LanternState.setHoldingLantern(p, holding);
    }

    @SubscribeEvent
    public static void mobTarget(LivingChangeTargetEvent event) {
        if (!(event.getEntity() instanceof Mob mob)) return;
        if (!(event.getNewTarget() instanceof Player p)) return;

        boolean holding = LanternState.isHoldingLantern(p);
        boolean compass = LanternState.isCompassActive(p);

        if (holding && !compass) event.setCanceled(true);
    }

    @SubscribeEvent
    public static void onRespawn(PlayerEvent.PlayerRespawnEvent e) {
        LanternState.reset(e.getEntity());
    }

    @SubscribeEvent
    public static void onLogout(PlayerEvent.PlayerLoggedOutEvent e) {
        LanternState.reset(e.getEntity());
    }

    @SubscribeEvent
    public static void onDimension(PlayerEvent.PlayerChangedDimensionEvent e) {
        LanternState.reset(e.getEntity());
    }
}

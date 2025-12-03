package org.studiosMF.events;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.studiosMF.registries.ModItems;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LegendaryPotatoEvents {

    private static final int DURATION = 10;
    private static final int SLOW_LEVEL = 4; // Slowness V
    private static final long RETURN_DELAY_TICKS = 20L * 120L; // 2 minutos

    private static final Map<Integer, PotatoTracker> POTATO_TRACKERS = new HashMap<>();

    private record PotatoTracker(UUID owner, long returnTime) {}

    // Lentidão V enquanto houver Batata Lendária no inventário
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;

        boolean hasPotato = hasLegendaryPotato(player);

        if (hasPotato) {
            applySlowness(player);
        } else {
            removeSlowness(player);
        }
    }

    private static boolean hasLegendaryPotato(Player player) {
        for (ItemStack stack : player.getInventory().items) {
            if (!stack.isEmpty() && stack.getItem() == ModItems.LEGENDARY_POTATO.get()) {
                return true;
            }
        }
        return false;
    }

    private static void applySlowness(Player player) {
        MobEffectInstance current = player.getEffect(MobEffects.MOVEMENT_SLOWDOWN);
        if (current != null && current.getAmplifier() == SLOW_LEVEL) {
            return;
        }

        player.addEffect(new MobEffectInstance(
                MobEffects.MOVEMENT_SLOWDOWN,
                DURATION,
                SLOW_LEVEL,
                false,
                true
        ));
    }

    private static void removeSlowness(Player player) {
        player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
    }

    // Quando o jogador joga a Batata fora
    @SubscribeEvent
    public static void onItemToss(ItemTossEvent event) {
        ItemEntity entity = event.getEntity();
        ItemStack stack = entity.getItem();

        if (stack.getItem() != ModItems.LEGENDARY_POTATO.get()) return;

        if (!(entity.level() instanceof ServerLevel serverLevel)) return;

        UUID owner = event.getPlayer().getUUID();
        long returnTime = serverLevel.getGameTime() + RETURN_DELAY_TICKS;

        POTATO_TRACKERS.put(entity.getId(), new PotatoTracker(owner, returnTime));
    }

    // Quando alguém pega a Batata → vira o novo dono
    @SubscribeEvent
    public static void onItemPickup(EntityItemPickupEvent event) {
        ItemEntity entity = event.getItem();
        ItemStack stack = entity.getItem();

        if (stack.getItem() != ModItems.LEGENDARY_POTATO.get()) return;

        Player newOwner = event.getEntity();
        PotatoTracker tracker = POTATO_TRACKERS.get(entity.getId());
        if (tracker != null) {
            POTATO_TRACKERS.put(entity.getId(),
                    new PotatoTracker(newOwner.getUUID(), tracker.returnTime()));
        }
    }

    // Tick global de mundo para devolver Batatas
    @SubscribeEvent
    public static void onWorldTick(TickEvent.LevelTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (!(event.level instanceof ServerLevel serverLevel)) return;

        long now = serverLevel.getGameTime();

        Iterator<Map.Entry<Integer, PotatoTracker>> it = POTATO_TRACKERS.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, PotatoTracker> entry = it.next();
            int entityId = entry.getKey();
            PotatoTracker tracker = entry.getValue();

            ItemEntity potatoEntity = (ItemEntity) serverLevel.getEntity(entityId);
            if (potatoEntity == null || !potatoEntity.isAlive()) {
                it.remove();
                continue;
            }

            if (now >= tracker.returnTime()) {
                Player owner = serverLevel.getPlayerByUUID(tracker.owner());
                if (owner != null) {
                    ItemStack stack = potatoEntity.getItem().copy();
                    if (owner.getInventory().add(stack)) {
                        potatoEntity.discard();
                    }
                }
                it.remove();
            }
        }
    }
}

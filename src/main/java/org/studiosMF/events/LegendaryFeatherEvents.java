package org.studiosMF.events;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.studiosMF.registries.ModItems;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LegendaryFeatherEvents {

    private static final int DURATION = 10;
    private static final int LEVITATION_LEVEL = 3; // Levitação IV (bem mais rápida)
    private static final int SLOW_FALL_LEVEL = 0;  // Queda lenta I

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;

        boolean holdingFeather =
                player.getMainHandItem().getItem() == ModItems.LEGENDARY_FEATHER.get()
                        || player.getOffhandItem().getItem() == ModItems.LEGENDARY_FEATHER.get();

        if (!holdingFeather) {
            player.removeEffect(MobEffects.LEVITATION);
            player.removeEffect(MobEffects.SLOW_FALLING);
            return;
        }

        // Levitação forte e contínua
        player.addEffect(new MobEffectInstance(
                MobEffects.LEVITATION,
                DURATION,
                LEVITATION_LEVEL,
                false,
                true
        ));

        // Queda Lenta para não matar o cara na descida
        player.addEffect(new MobEffectInstance(
                MobEffects.SLOW_FALLING,
                DURATION,
                SLOW_FALL_LEVEL,
                false,
                true
        ));
    }
}

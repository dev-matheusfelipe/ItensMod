package org.studiosMF.events;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.studiosMF.registries.ModItems;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LegendaryPickaxeEvents {

    // Multiplicador lendário de velocidade de mineração
    private static final float MINING_MULTIPLIER = 8.0F;

    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();

        // Jogador deve estar segurando a Picareta Lendária
        if (player.getMainHandItem().getItem() == ModItems.LEGENDARY_PICKAXE.get()) {

            float originalSpeed = event.getNewSpeed();

            // Aumenta a velocidade mantendo efeitos naturais do Minecraft
            float boostedSpeed = originalSpeed * MINING_MULTIPLIER;

            event.setNewSpeed(boostedSpeed);
        }
    }
}

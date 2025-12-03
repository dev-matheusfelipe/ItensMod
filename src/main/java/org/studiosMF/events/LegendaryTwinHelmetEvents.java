package org.studiosMF.events;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.studiosMF.registries.ModItems;

import java.util.UUID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LegendaryTwinHelmetEvents {

    private static final UUID EXTRA_HEALTH_UUID =
            UUID.fromString("5a1c5d17-7c2b-4c86-9be9-8f6c966f2c11");

    private static final double EXTRA_HEALTH = 10.0D; // +5 corações
    private static final int EFFECT_DURATION = 220;   // água infinita real

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;

        boolean wearingHelmet =
                player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.LEGENDARY_TWIN_HELMET.get();

        if (!wearingHelmet) {
            removeHealthBonus(player);
            player.removeEffect(MobEffects.WATER_BREATHING);
            return;
        }

        applyHealthBonus(player);
        applyWaterBreathing(player);
    }

    private static void applyHealthBonus(Player player) {
        AttributeInstance maxHealth = player.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealth == null) return;

        AttributeModifier existing = maxHealth.getModifier(EXTRA_HEALTH_UUID);
        if (existing == null) {
            maxHealth.addPermanentModifier(new AttributeModifier(
                    EXTRA_HEALTH_UUID,
                    "legendary_twin_helmet_health",
                    EXTRA_HEALTH,
                    AttributeModifier.Operation.ADDITION
            ));

            // Ajusta a vida atual pra não ficar com menos do que deveria
            if (player.getHealth() < maxHealth.getValue()) {
                player.setHealth((float) maxHealth.getValue());
            }
        }
    }

    private static void removeHealthBonus(Player player) {
        AttributeInstance maxHealth = player.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealth == null) return;

        AttributeModifier existing = maxHealth.getModifier(EXTRA_HEALTH_UUID);
        if (existing != null) {
            maxHealth.removeModifier(EXTRA_HEALTH_UUID);
            // Garante que a vida atual não passe do novo máximo
            if (player.getHealth() > maxHealth.getValue()) {
                player.setHealth((float) maxHealth.getValue());
            }
        }
    }

    private static void applyWaterBreathing(Player player) {
        player.addEffect(new MobEffectInstance(
                MobEffects.WATER_BREATHING,
                EFFECT_DURATION,
                0,
                false,
                false
        ));
    }
}

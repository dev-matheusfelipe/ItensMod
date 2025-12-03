package org.studiosMF.events;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.studiosMF.registries.ModItems;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LegendarySwordEvents {

    private static final int BAD_OMEN_DURATION = 200; // 10s, renovado sempre

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;

        boolean holdingSword =
                player.getMainHandItem().getItem() == ModItems.LEGENDARY_SWORD.get() ||
                        player.getOffhandItem().getItem() == ModItems.LEGENDARY_SWORD.get();

        if (!holdingSword) {
            player.removeEffect(MobEffects.BAD_OMEN);
            return;
        }

        player.addEffect(new MobEffectInstance(
                MobEffects.BAD_OMEN,
                BAD_OMEN_DURATION,
                0,
                false,
                true
        ));
    }

    @SubscribeEvent
    public static void onHit(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;

        if (player.getMainHandItem().getItem() != ModItems.LEGENDARY_SWORD.get()) return;

        LivingEntity target = event.getEntity();

        // Knockback na direção que o player está olhando (estilo vanilla, mas buffado)
        double yawRad = Math.toRadians(player.getYRot());
        double x = -Math.sin(yawRad);
        double z = Math.cos(yawRad);

        double strength = 3.5D; // força pra frente
        double up = 0.3D;       // leve impulso pra cima

        target.setDeltaMovement(
                target.getDeltaMovement().add(
                        x * strength,
                        up,
                        z * strength
                )
        );

        target.hasImpulse = true;
    }

    @SubscribeEvent
    public static void onKill(LivingDeathEvent event) {
        if (!(event.getSource().getEntity() instanceof Player killer)) return;

        if (killer.getMainHandItem().getItem() != ModItems.LEGENDARY_SWORD.get()) return;

        LivingEntity victim = event.getEntity();

        // Só transfere a espada se a vítima for PLAYER
        if (!(victim instanceof Player newHolder)) return;

        if (killer.level().isClientSide) return;

        // Remove a espada do killer e dá pro novo portador
        killer.getInventory().removeItem(killer.getMainHandItem());
        newHolder.getInventory().add(ModItems.LEGENDARY_SWORD.get().getDefaultInstance());
    }
}

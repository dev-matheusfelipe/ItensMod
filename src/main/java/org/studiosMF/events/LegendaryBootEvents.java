package org.studiosMF.events;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.studiosMF.registries.ModItems;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LegendaryBootEvents {

    private static final int SPEED_LEVEL = 19; // Speed XX
    private static final int JUMP_LEVEL = 1;   // Jump II
    private static final int EFFECT_TIME = 10;

    private static boolean canDoubleJump = false;
    private static boolean doubleJumpUsed = false;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;

        boolean wearingBoots =
                player.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.LEGENDARY_BOOT.get();

        boolean hasPotato = hasLegendaryPotato(player);

        if (!wearingBoots || hasPotato) {
            clearEffects(player);
            resetJump();
            return;
        }

        applyEffects(player);
        doubleJump(player);
    }

    private static void applyEffects(Player player) {

        // Velocidade extrema
        player.addEffect(new MobEffectInstance(
                MobEffects.MOVEMENT_SPEED,
                EFFECT_TIME,
                SPEED_LEVEL,
                false,
                false
        ));

        // Pulo nível II
        player.addEffect(new MobEffectInstance(
                MobEffects.JUMP,
                EFFECT_TIME,
                JUMP_LEVEL,
                false,
                false
        ));
    }

    private static void doubleJump(Player player) {

        if (player.onGround()) {
            canDoubleJump = true;
            doubleJumpUsed = false;
            return;
        }

        if (!canDoubleJump || doubleJumpUsed) return;

        double velY = player.getDeltaMovement().y;

        // Detectar intenção de salto 2
        if (velY > -0.02 && velY < 0.02) {

            player.setDeltaMovement(
                    player.getDeltaMovement().x,
                    0.9F, // Muito alto!
                    player.getDeltaMovement().z
            );

            player.hasImpulse = true;
            doubleJumpUsed = true;
        }
    }

    @SubscribeEvent
    public static void onFall(LivingFallEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        boolean wearingBoots =
                player.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.LEGENDARY_BOOT.get();

        if (wearingBoots) {
            event.setDamageMultiplier(0);
        }
    }

    private static boolean hasLegendaryPotato(Player player) {
        for (ItemStack item : player.getInventory().items) {
            if (!item.isEmpty() && item.getItem() == ModItems.LEGENDARY_POTATO.get()) {
                return true;
            }
        }
        return false;
    }

    private static void clearEffects(Player player) {
        player.removeEffect(MobEffects.MOVEMENT_SPEED);
        player.removeEffect(MobEffects.JUMP);
    }

    private static void resetJump() {
        canDoubleJump = false;
        doubleJumpUsed = false;
    }
}

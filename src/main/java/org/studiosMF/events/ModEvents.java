package org.studiosMF.events;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.projectile.ThrownTrident;
import org.studiosMF.Itens;
import org.studiosMF.item.ModItems;

import java.util.List;

@Mod.EventBusSubscriber(modid = Itens.MOD_ID)
public class ModEvents {

    // =================================================================================
    // Lógica do Lampião Lendário / Bota Lendária / Capacetes
    // =================================================================================

    @SubscribeEvent
    public static void onLivingUpdate(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;
        Player player = event.player;
        Level level = player.level();

        if (level.isClientSide()) return;

        // --- Lampião Lendário (Iluminação de Borda e Efeito de Lentidão) ---
        ItemStack mainHand = player.getMainHandItem();
        ItemStack offHand = player.getOffhandItem();

        if (mainHand.is(ModItems.LAMPION_LEGENDARIO.get()) || offHand.is(ModItems.LAMPION_LEGENDARIO.get())) {
            player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 20, 0, false, false));

            double radius = 5.0;
            AABB boundingBox = player.getBoundingBox().inflate(radius);
            List<LivingEntity> nearbyEntities = level.getEntitiesOfClass(LivingEntity.class, boundingBox, entity -> entity != player);

            for (LivingEntity entity : nearbyEntities) {
                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 0, false, false));
            }
        }

        // --- Bota Lendária (Salto e Velocidade) ---
        ItemStack boots = player.getInventory().getArmor(0);

        if (boots.is(ModItems.BOTA_LEGENDARIA.get())) {
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 20, 1, false, false));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, 1, false, false));
        }

        // --- Capacete do Sol (Visão Noturna e Cura no Sol) ---
        ItemStack helmet = player.getInventory().getArmor(3);

        if (helmet.is(ModItems.CAPACETE_SOL.get())) {
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 220, 0, false, false));
            if (level.isDay() && level.canSeeSky(player.blockPosition()) && !player.isInWater()) {
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20, 0, false, false));
            }
        }

        // --- Capacete da Lua (Queda Lenta e Regeneração Noturna) ---
        if (helmet.is(ModItems.CAPACETE_LUA.get())) {
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20, 0, false, false));
            if (level.isNight() && level.canSeeSky(player.blockPosition())) {
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20, 0, false, false));
            }
        }
    }


    // =================================================================================
    // Lógica da Espada do Caçador (Dano ao Atacar)
    // =================================================================================

    @SubscribeEvent
    public static void onPlayerAttackEntity(AttackEntityEvent event) {
        Player player = event.getEntity();
        Entity target = event.getTarget();

        if (player.getMainHandItem().is(ModItems.HUNTERS_SWORD.get())) {
            if (target instanceof LivingEntity livingTarget) {
                livingTarget.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 0, false, true));
            }
        }
    }


    // =================================================================================
    // Lógica do Lampião Lendário (Drop de Mobs)
    // =================================================================================

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        if (!(event.getSource().getEntity() instanceof Player player)) return;

        // Chance de drop do Lampião Lendário ao matar mobs
        if (player.getMainHandItem().is(ModItems.HUNTERS_SWORD.get()) && Math.random() < 0.05) {
            ItemEntity lampionDrop = new ItemEntity(player.level(), player.getX(), player.getY(), player.getZ(),
                    new ItemStack(ModItems.LAMPION_LEGENDARIO.get()));
            player.level().addFreshEntity(lampionDrop);
        }

        // Se a vítima morrer com o efeito Glow do Lampião, ela dropa o Lampião
        LivingEntity victim = event.getEntity();
        if (victim.hasEffect(MobEffects.GLOWING)) {

            ItemEntity lampionDrop = new ItemEntity(player.level(), victim.getX(), victim.getY(), victim.getZ(),
                    new ItemStack(ModItems.LAMPION_LEGENDARIO.get()));
            player.level().addFreshEntity(lampionDrop);

            // Efeito visual e sonoro de teleport
            if (victim.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.END_ROD, victim.getX(), victim.getY(), victim.getZ(), 50, 0.5, 1.0, 0.5, 0.5);
                serverLevel.playSound(null, victim.getX(), victim.getY(), victim.getZ(), SoundEvents.ENDER_DRAGON_DEATH, SoundSource.PLAYERS, 1.0f, 0.5f);
            }
        }
    }


    // =================================================================================
    // Lógica do Tridente Lendário (Raios ao acertar)
    // =================================================================================

    @SubscribeEvent
    public static void onTridentImpact(ProjectileImpactEvent event) {
        if (!(event.getProjectile() instanceof ThrownTrident trident)) return;

        // Verifica se o tridente é o lendário
        if (!trident.getPickResult().is(ModItems.TRIDENTE_LEGENDARIO.get())) return;

        if (event.getRayTraceResult().getType() == HitResult.Type.ENTITY) {
            Entity hitEntity = ((EntityHitResult) event.getRayTraceResult()).getEntity();

            if (hitEntity.level() instanceof ServerLevel serverLevel) {
                // Invoca um raio
                LightningBolt lightning =
                        EntityType.LIGHTNING_BOLT.create(serverLevel);

                if (lightning != null) {
                    lightning.setPos(hitEntity.getX(), hitEntity.getY(), hitEntity.getZ());
                    serverLevel.addFreshEntity(lightning);
                }
            }
        }
    }
}
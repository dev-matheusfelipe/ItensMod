package org.studiosMF.events;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.studiosMF.util.LanternState;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class LegendaryLanternClientEvents {

    // Invisibilidade total do jogador
    @SubscribeEvent
    public static void hidePlayer(RenderPlayerEvent.Pre event) {
        AbstractClientPlayer p = (AbstractClientPlayer) event.getEntity();
        boolean holding = LanternState.isHoldingLantern(p);
        boolean compass = LanternState.isCompassActive(p);

        if (holding && !compass) {
            event.setCanceled(true);
        }
    }

    // Remove 100% da sombra, interceptando o estágio exato do shadow renderer
    @SubscribeEvent
    public static void killShadow(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_PARTICLES) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        boolean holding = LanternState.isHoldingLantern(mc.player);
        boolean compass = LanternState.isCompassActive(mc.player);

        if (!holding || compass) return;

        // Empurra qualquer shadow-render para longe
        PoseStack pose = event.getPoseStack();

        pose.pushPose();
        pose.translate(0, -99999, 0); // literalmente joga as sombras para "fora do mundo"
        pose.scale(0.0f, 0.0f, 0.0f);  // garante que nada seja renderizado
        pose.popPose();
    }
}
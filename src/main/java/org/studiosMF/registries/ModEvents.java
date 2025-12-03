package org.studiosMF.registries;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.studiosMF.ItemsMod;

/**
 * Classe central de eventos do mod.
 *
 * Aqui ficam eventos globais:
 *  - inicialização comum
 *  - inicialização de cliente
 *  - registro de handlers gerais
 *  - integração com outros sistemas
 *
 * Eventos específicos de itens NÃO ficam aqui.
 * Cada item lendário tem sua própria classe de eventos modular.
 */
@Mod.EventBusSubscriber(modid = ItemsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    /**
     * Setup comum (lado servidor/cliente).
     * Chamado quando o mod é inicializado.
     */
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            System.out.println("ItemsMod: Setup comum concluído! Todos os eventos lendários ativos.");
        });
    }

    /**
     * Setup exclusivo do cliente.
     * Aqui podem ser registrados renderizadores, HUDs ou partículas personalizadas.
     */
    @Mod.EventBusSubscriber(modid = ItemsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                System.out.println("ItemsMod: Setup do CLIENTE carregado.");
            });
        }
    }
}

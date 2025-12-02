package org.studiosMF;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.studiosMF.item.ModCreativeTabs;
import org.studiosMF.item.ModItems;

import static net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext.get;

@Mod(Itens.MOD_ID)
public class Itens {

    // Definimos o ID aqui. O ModEvents vai ler esta variável.
    public static final String MOD_ID = "itensmod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Itens() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Registra Itens
        ModItems.ITEMS.register(modEventBus);

        // Registra a Aba Criativa
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);

        // Registra o Mod no Minecraft
        MinecraftForge.EVENT_BUS.register(this);
    }
}
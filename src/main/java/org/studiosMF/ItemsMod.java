package org.studiosMF;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.studiosMF.registries.ModCreativeTabs;
import org.studiosMF.registries.ModItems;

@Mod(ItemsMod.MOD_ID)
public class ItemsMod {

    public static final String MOD_ID = "itensmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ItemsMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Registries
        ModItems.ITEMS.register(modEventBus);
        ModCreativeTabs.CREATIVE_TABS.register(modEventBus);

        // Eventos globais Forge
        MinecraftForge.EVENT_BUS.register(this);

        LOGGER.info("ItemsMod inicializado com sucesso!");
    }
}

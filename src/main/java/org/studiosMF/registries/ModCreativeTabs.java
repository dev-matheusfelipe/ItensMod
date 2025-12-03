package org.studiosMF.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.studiosMF.ItemsMod;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ItemsMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ITENS_MOD_TAB =
            CREATIVE_TABS.register("itensmod_tab", () ->
                    CreativeModeTab.builder()
                            .icon(() -> new ItemStack(ModItems.LEGENDARY_LANTERN.get()))
                            .title(Component.literal("Itens Lendários"))
                            .displayItems((params, output) -> {
                                output.accept(ModItems.LEGENDARY_PICKAXE.get());
                                output.accept(ModItems.LEGENDARY_COMPASS.get());
                                output.accept(ModItems.LEGENDARY_LANTERN.get());
                                output.accept(ModItems.LEGENDARY_SWORD.get());
                                output.accept(ModItems.LEGENDARY_POTATO.get());
                                output.accept(ModItems.LEGENDARY_BOOT.get());
                                output.accept(ModItems.LEGENDARY_TWIN_HELMET.get());
                                output.accept(ModItems.LEGENDARY_FEATHER.get());
                            })
                            .build()
            );

    public static void register() {
        CREATIVE_TABS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}

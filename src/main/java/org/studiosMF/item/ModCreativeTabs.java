package org.studiosMF.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.studiosMF.Itens; // Importe sua classe principal

public class ModCreativeTabs {

    // O Deferred Register para Abas Criativas
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Itens.MOD_ID); // Usamos o MODID da sua classe principal

    // Definição da sua Aba Lendária
    public static final RegistryObject<CreativeModeTab> ITENSMOD_TAB = CREATIVE_MODE_TABS.register("itensmod_tab", () -> CreativeModeTab.builder()

            // Título que aparece ao passar o mouse (usará a tradução "itemGroup.itensmod_tab")
            .title(Component.translatable("itemGroup." + Itens.MOD_ID + "_tab"))

            // Ícone da Aba: Usamos o Lampião Lendário como ícone principal
            // Note o uso do .get() aqui. É necessário, mas você pode usar o @SuppressWarnings("deprecation")
            .icon(() -> new ItemStack(ModItems.LAMPION_LEGENDARIO.get()))

            // Adiciona o conteúdo à aba
            .displayItems((parameters, output) -> {
                // Adicione seus itens registrados (use .get() no RegistryObject)
                output.accept(ModItems.LAMPION_LEGENDARIO.get());
                output.accept(ModItems.BOTA_LEGENDARIA.get());
                output.accept(ModItems.BATATA_LENDARIA.get());
                output.accept(ModItems.PICARETA_LEGENDARIA.get());

                // ... Quando tiver mais, adicione aqui (Ex: ModItems.ESPADA_CACADOR.get())

            }).build());
}
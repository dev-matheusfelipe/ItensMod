package org.studiosMF.registries;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.studiosMF.ItemsMod;
import org.studiosMF.items.*;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ItemsMod.MOD_ID);

    // Batata do Bobo
    public static final RegistryObject<Item> LEGENDARY_POTATO =
            ITEMS.register("legendary_potato",
                    () -> new LegendaryPotatoItem(new Item.Properties()));

    // Bota Lendária
    public static final RegistryObject<Item> LEGENDARY_BOOT =
            ITEMS.register("legendary_boot",
                    () -> new LegendaryBootItem(
                            ModArmorMaterials.LEGENDARY,
                            new Item.Properties()
                    ));

    // Pena Lendária
    public static final RegistryObject<Item> LEGENDARY_FEATHER =
            ITEMS.register("legendary_feather",
                    () -> new LegendaryFeatherItem(new Item.Properties()));

    // Lampião Lendário
    public static final RegistryObject<Item> LEGENDARY_LANTERN =
            ITEMS.register("legendary_lantern",
                    () -> new LegendaryLanternItem(new Item.Properties()));

    // Bússola Lendária
    public static final RegistryObject<Item> LEGENDARY_COMPASS =
            ITEMS.register("legendary_compass",
                    () -> new LegendaryCompassItem(new Item.Properties()));

    // Picareta Lendária
    public static final RegistryObject<Item> LEGENDARY_PICKAXE =
            ITEMS.register("legendary_pickaxe",
                    () -> new LegendaryPickaxeItem(
                            ModToolTiers.LEGENDARY_TOOL_TIER,
                            new Item.Properties()
                    ));

    // Espada do Caçador
    public static final RegistryObject<Item> LEGENDARY_SWORD =
            ITEMS.register("legendary_sword",
                    () -> new LegendarySwordItem(
                            ModToolTiers.LEGENDARY_TOOL_TIER,
                            new Item.Properties()
                    ));

    // Capacete dos Gêmeos
    public static final RegistryObject<Item> LEGENDARY_TWIN_HELMET =
            ITEMS.register("legendary_twin_helmet",
                    () -> new LegendaryTwinHelmetItem(
                            ModArmorMaterials.LEGENDARY,
                            new Item.Properties()
                    ));
}

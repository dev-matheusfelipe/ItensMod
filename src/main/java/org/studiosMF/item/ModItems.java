package org.studiosMF.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// Classe responsável por registrar todos os itens do Mod no Forge.
public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, "itensmod");

    // ====================================================================
    // --- UTILITY/WEAPONS ---
    // Todos com .stacksTo(1) e .durability(0) para serem indestrutíveis e únicos
    // ====================================================================

    // 1. Lampião Lendário (Invisibilidade)
    public static final RegistryObject<Item> LAMPION_LEGENDARIO = ITEMS.register("lampion_lendario",
            () -> new LampionLendarioItem(new Item.Properties().stacksTo(1).durability(0)));

    // 2. Tridente Lendário (Raio e Cansaço)
    public static final RegistryObject<Item> TRIDENTE_LEGENDARIO = ITEMS.register("tridente_lendario",
            () -> new TridenteLendarioItem(new Item.Properties().stacksTo(1).durability(0)));

    // 3. Espada do Caçador (Transferência de Lampião)
    public static final RegistryObject<Item> HUNTERS_SWORD = ITEMS.register("hunters_sword",
            // Usa o construtor customizado da HuntersSword, que já define o Tier/Propriedades
            () -> new HuntersSword());

    // 4. Picareta Lendária (Velocidade e Restrição de Coleta)
    public static final RegistryObject<Item> PICARETA_LEGENDARIA = ITEMS.register("picareta_lendaria",
            // Usa Tiers.NETHERITE para velocidade, mas propriedades customizadas
            () -> new PicaretaLendariaItem(Tiers.NETHERITE, 0, -2.8F, new Item.Properties().durability(0)));

    // ====================================================================
    // --- ARMOR/CURSED ITEMS ---
    // ====================================================================

    // 5. Bota Lendária (Velocidade V)
    public static final RegistryObject<Item> BOTA_LEGENDARIA = ITEMS.register("bota_lendaria",
            () -> new BotaLendariaItem(new Item.Properties().stacksTo(1).durability(0)));

    // 6. Batata Lendária (Lentidão V e Nulificação)
    public static final RegistryObject<Item> BATATA_LENDARIA = ITEMS.register("batata_lendaria",
            () -> new BatataLendariaItem(new Item.Properties().stacksTo(1).durability(0)));

    // 7. Capacete do Sol (Força V - Dia)
    public static final RegistryObject<Item> CAPACETE_SOL = ITEMS.register("capacete_sol",
            () -> new CapaceteSolItem(new Item.Properties().stacksTo(1).durability(0)));

    // 8. Capacete da Lua (Resistência V - Noite)
    public static final RegistryObject<Item> CAPACETE_LUA = ITEMS.register("capacete_lua",
            () -> new CapaceteLuaItem(new Item.Properties().stacksTo(1).durability(0)));
}
package org.studiosMF.util;

import net.minecraft.world.entity.player.Player;
import java.util.HashMap;
import java.util.Map;

public class TwinLinkState {

    private static final Map<Player, Player> TWIN_MAP = new HashMap<>();

    public static void setTwin(Player p1, Player p2) {
        TWIN_MAP.put(p1, p2);
        TWIN_MAP.put(p2, p1);
    }

    public static Player getTwin(Player p) {
        return TWIN_MAP.get(p);
    }

    public static void clearTwin(Player p) {
        Player twin = TWIN_MAP.remove(p);
        if (twin != null) {
            TWIN_MAP.remove(twin);
        }
    }

    public static boolean hasTwin(Player p) {
        return TWIN_MAP.containsKey(p);
    }
}

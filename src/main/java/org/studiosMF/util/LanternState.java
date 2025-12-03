package org.studiosMF.util;

import net.minecraft.world.entity.player.Player;
import java.util.HashSet;
import java.util.Set;

public class LanternState {

    private static final Set<Player> HOLDING = new HashSet<>();
    private static final Set<Player> COMPASS_ACTIVE = new HashSet<>();

    public static void setHoldingLantern(Player p, boolean v) {
        if (v) HOLDING.add(p);
        else HOLDING.remove(p);
    }

    public static boolean isHoldingLantern(Player p) {
        return HOLDING.contains(p);
    }

    public static void setCompassActive(Player p, boolean v) {
        if (v) COMPASS_ACTIVE.add(p);
        else COMPASS_ACTIVE.remove(p);
    }

    public static boolean isCompassActive(Player p) {
        return COMPASS_ACTIVE.contains(p);
    }

    public static void reset(Player p) {
        HOLDING.remove(p);
        COMPASS_ACTIVE.remove(p);
    }
}

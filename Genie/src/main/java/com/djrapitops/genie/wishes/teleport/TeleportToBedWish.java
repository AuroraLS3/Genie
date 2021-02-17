/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.genie.wishes.teleport;

import com.djrapitops.genie.Genie;
import com.djrapitops.genie.wishes.Wish;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author AuroraLS3
 */
public class TeleportToBedWish extends Wish {

    public TeleportToBedWish() {
        super("teleport, to, home", "tp, to, home", "tp, to, bed", "teleport, to, bed", "to, home", "to, bed");
    }

    @Override
    public boolean fulfillWish(Player p) {
        Location location = p.getBedSpawnLocation();
        if (location == null) {
            return false;
        }
        if (!Genie.getInstance().isWorldAllowed(location.getWorld())) {
            return false;
        }
        p.teleport(location);
        return true;
    }

}

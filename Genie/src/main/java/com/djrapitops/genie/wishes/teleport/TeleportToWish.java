/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.genie.wishes.teleport;

import com.djrapitops.genie.Genie;
import com.djrapitops.genie.wishes.PlayerSpecificWish;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author AuroraLS3
 */
public class TeleportToWish extends PlayerSpecificWish {

    public TeleportToWish() {
        super("{playername}, teleport, to", "{playername}, tp, to", "to, {playername}");

    }

    @Override
    public boolean fulfillWish(Player p) {
        UUID tpTarget = storage.get(p.getUniqueId());
        if (tpTarget == null) {
            return false;
        }
        Player target = p.getServer().getPlayer(tpTarget);
        if (target == null) {
            return false;
        }
        Location location = target.getLocation();
        if (!Genie.getInstance().isWorldAllowed(location.getWorld())) {
            return false;
        }
        p.teleport(location);
        return true;
    }

}

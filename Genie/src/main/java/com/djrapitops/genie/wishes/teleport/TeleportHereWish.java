/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.genie.wishes.teleport;

import com.djrapitops.genie.wishes.PlayerSpecificWish;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author Rsl1122
 */
public class TeleportHereWish extends PlayerSpecificWish {

    public TeleportHereWish() {
        super("{playername}, teleport, here", "{playername}, tp, here", "{playername}, come, here", "{playername}, here");

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
        Location location = p.getLocation();
        target.teleport(location);
        return true;
    }
}

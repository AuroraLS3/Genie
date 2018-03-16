/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.genie.wishes;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.bukkit.Bukkit.getOnlinePlayers;

/**
 * @author Risto
 */
public abstract class PlayerSpecificWish extends Wish {

    protected Map<UUID, UUID> storage;

    public PlayerSpecificWish(String... name) {
        super(name);
        storage = new HashMap<>();
    }

    public void placeInStore(UUID player, UUID storedValue) {
        storage.put(player, storedValue);
    }

    public void clearFromStore(UUID player) {
        storage.remove(player);
    }

    public UUID getUUIDForPlayerInMatch(String[] aliases, String bestMatch) {
        for (String alias : aliases) {
            if (!alias.contains("{playername}")) {
                continue;
            }
            for (Player p : getOnlinePlayers()) {
                if (alias.toLowerCase().replace("{playername}", p.getName().toLowerCase()).equals(bestMatch)) {
                    return p.getUniqueId();
                }
            }
        }
        return null;
    }
}

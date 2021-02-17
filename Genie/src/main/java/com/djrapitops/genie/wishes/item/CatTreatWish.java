/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.genie.wishes.item;

import com.djrapitops.genie.wishes.Wish;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * @author AuroraLS3
 */
public class CatTreatWish extends Wish {

    public CatTreatWish() {
        super("heal, cat", "feed, cat", "help, cat", "like, cat", "cat, treat", "cattreat", "lot, fish", "cattreats", "cat, treats");
    }

    @Override
    public boolean fulfillWish(Player p) {
        return new ItemWish(Material.SALMON, 20).fulfillWish(p);
    }

}

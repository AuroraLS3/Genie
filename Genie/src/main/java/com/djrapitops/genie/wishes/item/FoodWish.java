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
 * @author Rsl1122
 */
public class FoodWish extends Wish {

    public FoodWish() {
        super("Food", "Hungry", "Fed", "Sated");
    }

    @Override
    public boolean fulfillWish(Player p) {
        return new ItemWish(Material.BAKED_POTATO, 20).fulfillWish(p);
    }

}

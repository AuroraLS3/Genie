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
public class FarmWish extends Wish {

    public FarmWish() {
        super("Farm", "Seeds");
    }

    @Override
    public boolean fulfillWish(Player p) {
        new ItemWish(Material.IRON_HOE).fulfillWish(p);
        return new ItemWish(Material.SEEDS, 64).fulfillWish(p);
    }

}

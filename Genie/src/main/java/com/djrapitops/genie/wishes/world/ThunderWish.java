/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.genie.wishes.world;

import com.djrapitops.genie.wishes.Wish;
import org.bukkit.entity.Player;

/**
 * @author Rsl1122
 */
public class ThunderWish extends Wish {

    public ThunderWish() {
        super("Thunder", "Storm", "Stormy", "Raining", "Rain", "Thunderstorm", "Lightning");
    }

    @Override
    public boolean fulfillWish(Player p) {
        p.getWorld().setStorm(true);
        p.getWorld().setThundering(true);
        return true;
    }

}

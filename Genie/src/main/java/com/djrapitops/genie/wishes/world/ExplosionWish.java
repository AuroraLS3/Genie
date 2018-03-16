/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.genie.wishes.world;

import com.djrapitops.genie.wishes.Wish;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * @author Rsl1122
 */
public class ExplosionWish extends Wish {

    public ExplosionWish() {
        super("Explosion", "Blow up", "Explode", "Boom", "Crater", "Exploded");
    }

    @Override
    public boolean fulfillWish(Player p) {
        Location loc = p.getLocation();
        World w = loc.getWorld();
        return w.createExplosion(loc, 6);
    }

}

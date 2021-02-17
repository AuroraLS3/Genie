/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.genie.wishes.mob;

import com.djrapitops.genie.wishes.Wish;
import com.djrapitops.genie.wishes.item.ItemWish;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

/**
 * @author AuroraLS3
 */
public class DogWish extends Wish {

    public DogWish() {
        super("Dog", "Pet", "Doggy", "Doggie", "Puppy", "Woof", "Friend", "Friends");
    }

    @Override
    public boolean fulfillWish(Player p) {
        return new SpawnMobWish(EntityType.WOLF).fulfillWish(p)
                && new ItemWish(Material.BONE, 20).fulfillWish(p);
    }

}

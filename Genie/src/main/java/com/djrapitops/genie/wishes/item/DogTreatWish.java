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
public class DogTreatWish extends Wish {

    public DogTreatWish() {
        super("heal, dog", "feed, dog", "help, dog", "like, dog", "dog, treat", "dogtreat", "dog, treats", "dogtreats");
    }

    @Override
    public boolean fulfillWish(Player p) {
        return new ItemWish(Material.PORKCHOP, 20).fulfillWish(p);
    }

}

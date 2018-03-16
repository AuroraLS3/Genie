/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.genie.wishes.item;

import com.djrapitops.genie.wishes.Wish;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rsl1122
 */
public class ArmorWish extends Wish {

    private final List<Wish> itemWishes;

    public ArmorWish(String armorMaterial) {
        super("Armor, " + armorMaterial, armorMaterial + ", Armor");
        itemWishes = new ArrayList<>();
        itemWishes.add(new ItemWish(Material.valueOf(armorMaterial.toUpperCase() + "_BOOTS")));
        itemWishes.add(new ItemWish(Material.valueOf(armorMaterial.toUpperCase() + "_CHESTPLATE")));
        itemWishes.add(new ItemWish(Material.valueOf(armorMaterial.toUpperCase() + "_HELMET")));
        itemWishes.add(new ItemWish(Material.valueOf(armorMaterial.toUpperCase() + "_LEGGINGS")));
    }

    @Override
    public boolean fulfillWish(Player p) {
        for (Wish itemWish : itemWishes) {
            if (!itemWish.fulfillWish(p)) {
                return false;
            }
        }
        return true;
    }

}

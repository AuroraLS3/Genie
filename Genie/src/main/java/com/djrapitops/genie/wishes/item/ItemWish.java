/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.genie.wishes.item;

import com.djrapitops.genie.utilities.FormatUtils;
import com.djrapitops.genie.wishes.Wish;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author Rsl1122
 */
public class ItemWish extends Wish {

    private final Material material;
    private final int amount;

    public ItemWish(Material material) {
        this(material, 1);
    }

    public ItemWish(Material material, int amount) {
        super(FormatUtils.getProperName(material.name()));
        this.material = material;
        this.amount = amount;
    }

    @Override
    public boolean fulfillWish(Player p) {
        try {
            p.getWorld().dropItem(p.getLocation(), new ItemStack(material, amount));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

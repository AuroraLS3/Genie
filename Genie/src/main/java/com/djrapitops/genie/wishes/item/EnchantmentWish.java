/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.genie.wishes.item;

import com.djrapitops.genie.utilities.FormatUtils;
import com.djrapitops.genie.wishes.Wish;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author AuroraLS3
 */
public class EnchantmentWish extends Wish {

    private final Enchantment enchant;
    private final int level;

    public EnchantmentWish(Enchantment enchantment, String level) {
        super(FormatUtils.getEnchantmentName(enchantment, level));
        this.enchant = enchantment;
        this.level = FormatUtils.getLevel(level);
    }

    @Override
    public boolean fulfillWish(Player p) {
        try {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
            item.addUnsafeEnchantment(enchant, level);
            p.getWorld().dropItem(p.getLocation(), item);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

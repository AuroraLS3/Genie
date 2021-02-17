/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.genie.wishes.potion;

import com.djrapitops.genie.utilities.FormatUtils;
import com.djrapitops.genie.wishes.Wish;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * @author AuroraLS3
 */
public class PotionEffectWish extends Wish {

    private final PotionEffectType type;

    public PotionEffectWish(PotionEffectType type) {
        super(FormatUtils.getPotionNames(type));
        this.type = type;
    }

    @Override
    public boolean fulfillWish(Player p) {
        p.addPotionEffect(new PotionEffect(type, 60 * 20, 1));
        return true;
    }

}

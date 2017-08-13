/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.genie.wishes.mob;

import com.djrapitops.genie.utilities.FormatUtils;
import com.djrapitops.genie.wishes.Wish;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

/**
 *
 * @author Rsl1122
 */
public class SpawnMobRidingOnWish extends Wish {

    private final EntityType mobToSpawn;
    private final EntityType mobToStack;

    public SpawnMobRidingOnWish(EntityType mobToSpawn, EntityType mobToStack) {
        super(getProperMobNames(mobToStack.name(), mobToSpawn.name()));
        this.mobToSpawn = mobToSpawn;
        this.mobToStack = mobToStack;
    }

    private static String[] getProperMobNames(String mobName, String stackMobName) {
        String[] first = FormatUtils.getProperName(mobName);
        String[] second = FormatUtils.getProperName(stackMobName);
        List<String> names = new ArrayList<>();
        for (String mid : new String[]{", on, ", ", riding, "}) {
            names.add(first[0] + mid + second[0]);
            names.add(first[0] + mid + second[1]);
            names.add(first[1] + mid + second[0]);
            names.add(first[1] + mid + second[1]);
        }
        return names.toArray(new String[names.size()]);
    }

    @Override
    public boolean fulfillWish(Player p) {
        Location aboveHead = p.getLocation().add(new Location(p.getWorld(), 0, 2, 0));
        Entity spawned = p.getWorld().spawnEntity(aboveHead, mobToSpawn);
        Entity toStack = p.getWorld().spawnEntity(aboveHead, mobToStack);
        try {
        spawned.addPassenger(toStack);
        }catch (NoSuchMethodError ex){
        	spawned.setPassenger(toStack);
        }
        return true;
    }
}

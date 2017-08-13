package com.djrapitops.genie.listeners;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.djrapitops.genie.Genie;
import com.djrapitops.genie.Settings;
import com.djrapitops.genie.lamp.LampManager;
import com.djrapitops.genie.wishes.WishManager;

/**
 *
 * @author Rsl1122
 */
public class DeathListener implements Listener {

    private final Genie plugin;
    private Map<Location, Integer> recentDrops;
    private WishManager wm;
    public DeathListener(Genie plugin) {
    	wm = new WishManager(plugin);
        this.plugin = plugin;
        recentDrops = new HashMap<>();
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onDeath(EntityDeathEvent event) {
        LivingEntity dead = event.getEntity();
        if (dead instanceof Player) {
            return;
        }
     
        if (wm.getPreventedEntities().contains(dead.getType())|| wm.getNonHostileEntities().contains(dead.getType())) {
            return;
        }
        Location loc = dead.getLocation();
        boolean recentlyDroppedHere = recentDrops.containsKey(loc);
        if (recentlyDroppedHere) {
            return;
        }
        World world = loc.getWorld();
        if (!plugin.isWorldAllowed(world)) {
            return;
        }
        Biome biome = world.getBiome(loc.getBlockX(), loc.getBlockZ());
        Random r = new Random();
        int desertChance = (int) plugin.getConfig().getDouble(Settings.DROPRATE_DESERT.getPath()) * 1000000;
        int outsideChance = (int) plugin.getConfig().getDouble(Settings.DROPRATE_OUTSIDE.getPath()) * 1000000;
        LampManager lampManager = plugin.getLampManager();
        int decidingNumber = r.nextInt(100000000);
        boolean dropped = false;
        switch (biome) {
            case DESERT:
            case DESERT_HILLS:
                if (decidingNumber <= desertChance) {
                    event.getDrops().add(lampManager.newLamp());
                    dropped = true;
                }
                break;
            default:
                if (decidingNumber <= outsideChance) {
                    event.getDrops().add(lampManager.newLamp());
                    dropped = true;
                }
                break;
        }
        if (dropped) {
            if (!recentlyDroppedHere) {
                recentDrops.put(loc, 1);
            }
        }
    }
}

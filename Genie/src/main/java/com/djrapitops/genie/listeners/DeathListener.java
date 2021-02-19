package com.djrapitops.genie.listeners;

import com.djrapitops.genie.Genie;
import com.djrapitops.genie.Settings;
import com.djrapitops.genie.lamp.LampManager;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.*;

/**
 * @author AuroraLS3
 */
public class DeathListener implements Listener {

    private final Genie plugin;
    private final Map<Location, Integer> recentDrops;
    private final Set<EntityType> nonDroppingEntities;

    public DeathListener(Genie plugin) {
        this.plugin = plugin;
        recentDrops = new HashMap<>();

        nonDroppingEntities = Collections.newSetFromMap(new EnumMap<>(EntityType.class));

        nonDroppingEntities.add(EntityType.AREA_EFFECT_CLOUD);
        nonDroppingEntities.add(EntityType.ARMOR_STAND);
        nonDroppingEntities.add(EntityType.ARROW);
        nonDroppingEntities.add(EntityType.BAT);
        nonDroppingEntities.add(EntityType.BEE);
        nonDroppingEntities.add(EntityType.BOAT);
        nonDroppingEntities.add(EntityType.CAT);
        nonDroppingEntities.add(EntityType.CHICKEN);
        nonDroppingEntities.add(EntityType.COD);
        nonDroppingEntities.add(EntityType.COW);
        nonDroppingEntities.add(EntityType.DOLPHIN);
        nonDroppingEntities.add(EntityType.DONKEY);
        nonDroppingEntities.add(EntityType.DRAGON_FIREBALL);
        nonDroppingEntities.add(EntityType.DROPPED_ITEM);
        nonDroppingEntities.add(EntityType.EGG);
        nonDroppingEntities.add(EntityType.ENDER_CRYSTAL);
        nonDroppingEntities.add(EntityType.ENDER_SIGNAL);
        nonDroppingEntities.add(EntityType.EVOKER_FANGS);
        nonDroppingEntities.add(EntityType.EXPERIENCE_ORB);
        nonDroppingEntities.add(EntityType.FALLING_BLOCK);
        nonDroppingEntities.add(EntityType.FIREBALL);
        nonDroppingEntities.add(EntityType.FIREWORK);
        nonDroppingEntities.add(EntityType.FISHING_HOOK);
        nonDroppingEntities.add(EntityType.ITEM_FRAME);
        nonDroppingEntities.add(EntityType.HORSE);
        nonDroppingEntities.add(EntityType.LEASH_HITCH);
        nonDroppingEntities.add(EntityType.LIGHTNING);
        nonDroppingEntities.add(EntityType.LLAMA);
        nonDroppingEntities.add(EntityType.LLAMA_SPIT);
        nonDroppingEntities.add(EntityType.MINECART);
        nonDroppingEntities.add(EntityType.MINECART_CHEST);
        nonDroppingEntities.add(EntityType.MINECART_COMMAND);
        nonDroppingEntities.add(EntityType.MINECART_FURNACE);
        nonDroppingEntities.add(EntityType.MINECART_HOPPER);
        nonDroppingEntities.add(EntityType.MINECART_MOB_SPAWNER);
        nonDroppingEntities.add(EntityType.MINECART_TNT);
        nonDroppingEntities.add(EntityType.MULE);
        nonDroppingEntities.add(EntityType.MUSHROOM_COW);
        nonDroppingEntities.add(EntityType.OCELOT);
        nonDroppingEntities.add(EntityType.PAINTING);
        nonDroppingEntities.add(EntityType.PANDA);
        nonDroppingEntities.add(EntityType.PARROT);
        nonDroppingEntities.add(EntityType.PIG);
        nonDroppingEntities.add(EntityType.PLAYER);
        nonDroppingEntities.add(EntityType.POLAR_BEAR);
        nonDroppingEntities.add(EntityType.PRIMED_TNT);
        nonDroppingEntities.add(EntityType.PUFFERFISH);
        nonDroppingEntities.add(EntityType.RABBIT);
        nonDroppingEntities.add(EntityType.SALMON);
        nonDroppingEntities.add(EntityType.SHEEP);
        nonDroppingEntities.add(EntityType.SNOWBALL);
        nonDroppingEntities.add(EntityType.SNOWMAN);
        nonDroppingEntities.add(EntityType.SPECTRAL_ARROW);
        nonDroppingEntities.add(EntityType.SPLASH_POTION);
        nonDroppingEntities.add(EntityType.SQUID);
        nonDroppingEntities.add(EntityType.THROWN_EXP_BOTTLE);
        nonDroppingEntities.add(EntityType.TRADER_LLAMA);
        nonDroppingEntities.add(EntityType.TRIDENT);
        nonDroppingEntities.add(EntityType.TROPICAL_FISH);
        nonDroppingEntities.add(EntityType.TURTLE);
        nonDroppingEntities.add(EntityType.UNKNOWN);
        nonDroppingEntities.add(EntityType.VILLAGER);
        nonDroppingEntities.add(EntityType.WANDERING_TRADER);
        nonDroppingEntities.add(EntityType.WOLF);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onDeath(EntityDeathEvent event) {
        LivingEntity dead = event.getEntity();
        if (dead instanceof Player) {
            return;
        }

        if (nonDroppingEntities.contains(dead.getType())) {
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
        Biome biome = loc.getBlock().getBiome();
        Random r = new Random();
        int desertChance = (int) plugin.getConfig().getDouble(Settings.DROPRATE_DESERT.getPath()) * 1000000;
        int outsideChance = (int) plugin.getConfig().getDouble(Settings.DROPRATE_OUTSIDE.getPath()) * 1000000;
        LampManager lampManager = plugin.getLampManager();
        int decidingNumber = r.nextInt(100000000);
        boolean dropped = false;
        switch (biome) {
            case BEACH:
            case DESERT:
            case DESERT_HILLS:
            case DESERT_LAKES:
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
            recentDrops.put(loc, 1);
        }
    }
}

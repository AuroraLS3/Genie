/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.genie.utilities;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Risto
 */
public class FormatUtils {

    public static String[] getProperName(String enumName) {
        String name = enumName.replace("_ITEM", "");
        String replacedWSpace = name.replace("_", " ");
        String replacedWNone = name.replace("_", "");
        return new String[]{replacedWSpace, replacedWNone};
    }

    public static String[] getMobNames(EntityType type) {
        Map<String, String[]> names = new HashMap<>();
        names.put("IRON_GOLEM", new String[]{"iron golem", "irongolem", "golem", "help", "protect, me"});
        names.put("SNOWMAN", new String[]{"snowman", "snow golem", "snowgolem"});
        names.put("HORSE", new String[]{"horse", "horsie"});
        names.put("LLAMA", new String[]{"llama", "lama"});
        names.put("PIG_ZOMBIE", new String[]{"zombie pig", "pigzombie", "pig zombie", "zombiepig", "zombie pigman", "pigman"});
        String[] name = names.get(type.name());
        if (name == null) {
            name = getProperName(type.name());
        }
        return name;
    }

    public static String[] getPotionNames(PotionEffectType type) {
        Map<String, String[]> names = new HashMap<>();
        names.put("BLINDNESS", new String[]{"Blindness", "Blind"});
        names.put("CONFUSION", new String[]{"Confusion", "Confused"});
        names.put("DAMAGE_RESISTANCE", new String[]{"Tough", "Damage Resistance", "Damage resistant"});
        names.put("FAST_DIGGING", new String[]{"Fast digging", "dig fast"});
        names.put("FIRE_RESISTANCE", new String[]{"FIRE RESISTANCE", "Fire resistant"});
        names.put("HARM", new String[]{"Harmed", "Hurt", "Harm"});
        names.put("HEALTH_BOOST", new String[]{"More, health", "health, boost"});
        names.put("HUNGER", new String[]{"Hunger"});
        names.put("INCREASE_DAMAGE", new String[]{"Increase Damage", "Increased Damage", "Strength", "Power", "Strong"});
        names.put("INVISIBILITY", new String[]{"Invisibility", "Invisible"});
        names.put("JUMP", new String[]{"Jump", "Jump higher"});
        names.put("LEVITATION", new String[]{"Levitating", "Floating", "Levitate", "Float", "Levitation"});
        names.put("LUCK", new String[]{"Lucky", "Luck"});
        names.put("NIGHT_VISION", new String[]{"NIGHT VISION", "See dark"});
        names.put("SATURATION", new String[]{"not hungry"});
        names.put("SPEED", new String[]{"Speed", "Faster", "fast", "Sanic", "Sonic"});
        names.put("WATER_BREATHING", new String[]{"WATER BREATHING", "Breath, underwater"});
        names.put("REGENERATION", new String[]{"Regeneration", "Regen, Health", "Regenerating", "Health", "Heal", "Healing"});
        String[] name = names.get(type.getName());
        if (name == null) {
            name = getProperName(type.getName());
        }
        return name;
    }

    public static String[] getEnchantmentName(Enchantment e, String level) {
        Map<String, String[]> names = new HashMap<>();
        names.put("ARROW_DAMAGE", new String[]{"Arrow damage", "Power", "Bow Damage"});
        names.put("ARROW_FIRE", new String[]{"Arrow fire", "Flame", "Bow Fire", "Firearrow"});
        names.put("ARROW_KNOCKBACK", new String[]{"Arrow knockback", "Punch"});
        names.put("ARROW_INFINITE", new String[]{"Arrow infinite", "infinity", "Infinite arrows"});
        names.put("DAMAGE_ALL", new String[]{"all damage", "sharpness", "more damage"});
        names.put("DAMAGE_ARTHROPODS", new String[]{"damage arthopods", "bane arthopods", "spider damage"});
        names.put("DAMAGE_UNDEAD", new String[]{"damage undead", "smite", "zombie damage", "skeleton damage"});
        names.put("DURABILITY", new String[]{"durability", "unbreaking"});
        names.put("DIG_SPEED", new String[]{"dig faster", "dig speed", "efficiency"});
        names.put("FIRE_ASPECT", new String[]{"fire aspect", "flame sword"});
        names.put("KNOCKBACK", new String[]{"knockback", "sword punch"});
        names.put("LOOT_BONUS_BLOCKS", new String[]{"fortune", "mining luck"});
        names.put("LOOT_BONUS_MOBS", new String[]{"looting", "loot luck", "more loot"});
        names.put("PROTECTION_EXPLOSIONS", new String[]{"explosion protection", "blast protection"});
        names.put("PROTECTION_ENVIRONMENTAL", new String[]{"protection"});
        names.put("PROTECTION_FALL", new String[]{"fall protection", "feather fall", "feather falling"});
        names.put("PROTECTION_PROJECTILE", new String[]{"arrow protection", "arrows protection", "projectile protection"});
        names.put("PROTECTION_FIRE", new String[]{"fire protection", "fire immunity"});
        names.put("SILK_TOUCH", new String[]{"silk touch"});
        names.put("WATER_WORKER", new String[]{"aqua affinity", "water worker", "work underwater"});
        names.put("DEPTH_STRIDER", new String[]{"water speed", "depth strider", "water walker"});
        String[] name = names.get(e.getName());
        if (name == null) {
            name = getProperName(e.getName());
        }
        for (int i = 0; i < name.length; i++) {
            name[i] = name[i] + " " + level;
        }
        return name;
    }

    public static int getLevel(String level) {
        switch (level) {
            case "X":
                return 10;
            case "IX":
                return 9;
            case "IIX":
            case "VIII":
                return 8;
            case "VII":
                return 7;
            case "VI":
                return 6;
            case "V":
                return 5;
            case "IV":
                return 4;
            case "III":
                return 3;
            case "II":
                return 2;
            case "I":
                return 1;
            default:
                break;
        }
        try {
            return Integer.parseInt(level);
        } catch (NumberFormatException e) {
            return 1;
        }
    }
}

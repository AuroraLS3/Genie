package com.djrapitops.genie.lamp;

import com.djrapitops.plugin.api.utility.log.Log;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.UUID;

/**
 * @author Rsl1122
 */
public class LampItem extends ItemStack {

    private final String lore1 = ChatColor.RESET + "" + ChatColor.DARK_AQUA + "Hold to make a wish";
    private final String lore2 = ChatColor.RESET + "" + ChatColor.DARK_AQUA + "Rub to summon Genie";
    private final String lore3start = ChatColor.RESET + "" + ChatColor.COLOR_CHAR + ":";

    /**
     * Used to get a empty LampItem for the variables above.
     */
    private LampItem() {

    }

    @SuppressWarnings("deprecation")
    public LampItem(UUID lampID) {
        super(Material.GOLD_INGOT);

        ItemMeta meta = this.getItemMeta();

        try {
            meta.setUnbreakable(true);
        } catch (NoSuchMethodError ex) {
            meta.spigot().setUnbreakable(true);
        }

        meta.setDisplayName("" + ChatColor.RESET + ChatColor.GOLD + "Genie Lamp");
        meta.setLore(Arrays.asList(lore1, lore2, lore3start + getHiddenUUID(lampID)));
        setItemMeta(meta);
        super.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 1);
    }

    public static boolean isLampItem(ItemStack item) {
        LampItem lampVariables = new LampItem();
        String lore1 = lampVariables.lore1;
        String lore2 = lampVariables.lore2;
        return item != null
                && item.getEnchantments().get(Enchantment.PROTECTION_FALL) != null
                && item.hasItemMeta()
                && item.getItemMeta().hasLore()
                && item.getItemMeta().getLore().size() >= 3
                && lore1.equals(item.getItemMeta().getLore().get(0))
                && lore2.equals(item.getItemMeta().getLore().get(1))
                && item.getItemMeta().getLore().get(2) != null;
    }

    public static UUID getLampUUID(String thirdLoreLine) {
        try {
            String uuidString = thirdLoreLine.replace(String.valueOf(ChatColor.COLOR_CHAR), "").split(":")[1];
            return UUID.fromString(uuidString);
        } catch (Exception e) {
            Log.toLog("getLampUUID", e);
        }
        return null;
    }

    private String getHiddenUUID(UUID lampID) {
        StringBuilder hiddenUUID = new StringBuilder();
        for (char c : lampID.toString().toCharArray()) {
            hiddenUUID.append(ChatColor.COLOR_CHAR).append(c);
        }
        return hiddenUUID.toString();
    }
}

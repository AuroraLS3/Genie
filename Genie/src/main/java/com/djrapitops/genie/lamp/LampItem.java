package com.djrapitops.genie.lamp;

import com.djrapitops.plugin.api.utility.log.Log;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

/**
 * @author AuroraLS3
 */
public class LampItem extends ItemStack {

    private static final String LORE_1 = ChatColor.RESET + "" + ChatColor.DARK_AQUA + "Hold to make a wish";
    private static final String LORE_2 = ChatColor.RESET + "" + ChatColor.DARK_AQUA + "Rub to summon Genie";

    public static NamespacedKey UUID_KEY;

    /**
     * Used to get a empty LampItem for the variables above.
     */
    private LampItem() {

    }

    public LampItem(UUID lampID) {
        super(Material.GOLD_INGOT);

        ItemMeta meta = this.getItemMeta();

        try {
            meta.setUnbreakable(true);
        } catch (NoSuchMethodError ex) {
            meta.setUnbreakable(true);
        }

        meta.setDisplayName("" + ChatColor.RESET + ChatColor.GOLD + "Genie Lamp");
        meta.setLore(Arrays.asList(LORE_1, LORE_2));
        meta.getPersistentDataContainer().set(UUID_KEY, PersistentDataType.STRING, lampID.toString());

        setItemMeta(meta);

        super.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 1);
    }

    public static boolean isLampItem(ItemStack item) {
        return item != null
                && item.getEnchantments().get(Enchantment.PROTECTION_FALL) != null
                && item.hasItemMeta()
                && item.getItemMeta().hasLore()
                && item.getItemMeta().getLore().size() >= 2
                && item.getItemMeta().getPersistentDataContainer().get(UUID_KEY, PersistentDataType.STRING) != null;
    }

    public static Optional<UUID> getLampUUID(ItemMeta itemMeta) {
        try {
            return Optional.ofNullable(itemMeta.getPersistentDataContainer().get(UUID_KEY, PersistentDataType.STRING))
                    .map(UUID::fromString);
        } catch (Exception e) {
            Log.toLog("getLampUUID", e);
        }
        return Optional.empty();
    }

    private String getHiddenUUID(UUID lampID) {
        StringBuilder hiddenUUID = new StringBuilder();
        for (char c : lampID.toString().toCharArray()) {
            hiddenUUID.append(ChatColor.COLOR_CHAR).append(c);
        }
        return hiddenUUID.toString();
    }
}

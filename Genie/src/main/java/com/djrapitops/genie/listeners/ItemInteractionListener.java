package com.djrapitops.genie.listeners;

import com.djrapitops.genie.Genie;
import com.djrapitops.genie.MessageType;
import com.djrapitops.genie.Messages;
import com.djrapitops.genie.lamp.Lamp;
import com.djrapitops.genie.lamp.LampItem;
import com.djrapitops.genie.lamp.LampManager;
import com.djrapitops.plugin.settings.ColorScheme;
import com.djrapitops.plugin.task.AbsRunnable;
import com.djrapitops.plugin.task.RunnableFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * @author AuroraLS3
 */
public class ItemInteractionListener implements Listener {

    private final Genie plugin;

    public ItemInteractionListener(Genie plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInteract(PlayerInteractEvent event) {
        Action action = event.getAction();
        if (action == Action.PHYSICAL) {
            return;
        }
        ItemStack item = event.getItem();
        if (item == null || !LampItem.isLampItem(item)) {
            return;
        }
        Player player = event.getPlayer();
        RunnableFactory.createNew(new AbsRunnable("Lamp Wish Count Check Event") {
            @Override
            public void run() {
                try {
                    ColorScheme color = plugin.getColorScheme();
                    String lampIDLine = item.getItemMeta().getLore().get(2);
                    UUID lampUUID = LampItem.getLampUUID(lampIDLine);
                    LampManager lampManager = plugin.getLampManager();
                    Lamp lamp = lampManager.getLamp(lampUUID);
                    Messages msg = plugin.getMsg();
                    String prefix = color.getMainColor() + "[Genie] " + color.getSecondaryColor();
                    if (!lamp.hasWishesLeft()) {
                        player.sendMessage(prefix + msg.getMessage(MessageType.OUT_OF_WISHES));
                        item.getItemMeta().setUnbreakable(false);
                        return;
                    }
                    player.sendMessage(prefix + msg.getMessage(MessageType.SUMMON) + " " + msg.getMessage(MessageType.HELP_WISH));
                    String wishesLeft = color.getTertiaryColor() + "" + lamp.getWishes() + color.getSecondaryColor();
                    player.sendMessage(prefix + msg.getMessage(MessageType.WISHES_LEFT).replace("WISHES", wishesLeft));
                } finally {
                    this.cancel();
                }
            }
        }).runTaskAsynchronously();
    }
}

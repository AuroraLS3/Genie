package com.djrapitops.genie.command.commands;

import com.djrapitops.genie.Genie;
import com.djrapitops.genie.lamp.LampItem;
import com.djrapitops.genie.lamp.LampManager;
import com.djrapitops.genie.utilities.Check;
import com.djrapitops.plugin.command.CommandType;
import com.djrapitops.plugin.command.CommandUtils;
import com.djrapitops.plugin.command.ISender;
import com.djrapitops.plugin.command.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getOnlinePlayers;

/**
 * Command used to give a player the lamp.
 *
 * @author Rsl1122
 */
public class GiveLampCommand extends SubCommand {

    private final Genie plugin;

    public GiveLampCommand(Genie plugin) {
        super("give, givelamp, lamp, g", CommandType.PLAYER_OR_ARGS, "genie.admin", "Gives the lamp to user or given player", "[player] [wishes]");
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(ISender sender, String commandLabel, String[] args) {
        final String notFound = ChatColor.RED + "Player not found. (Is the player online? Name is case sensitive!)";
        final String lampDropped = ChatColor.GREEN + "[Genie] Lamp Dropped!";

        LampManager lampManager = plugin.getLampManager();
        Player receiver = getReceiver(args, sender);

        if (!Check.isTrue(receiver != null, notFound, sender)) {
            return true;
        }

        int wishes = getWishAmount(args);
        final LampItem newLamp = lampManager.newLamp(wishes);

        lampManager.dropLamp(receiver.getLocation(), newLamp);
        sender.sendMessage(lampDropped);
        return true;
    }

    private int getWishAmount(String[] args) {
        int wishes = 3;
        for (String arg : args) {
            try {
                wishes = Integer.parseInt(arg);
            } catch (Throwable e) {
                // Use 3 if not found.
            }
        }
        return wishes;
    }

    private Player getReceiver(String[] args, ISender sender) {
        Player receiver;
        if (args.length == 0 && CommandUtils.isPlayer(sender)) {
            receiver = (Player) sender.getSender();
        } else {
            String name = args[0];
            return getOnlinePlayers().stream()
                    .filter(player -> name.equals(player.getName()))
                    .findFirst().orElse(null);
        }
        return receiver;
    }
}

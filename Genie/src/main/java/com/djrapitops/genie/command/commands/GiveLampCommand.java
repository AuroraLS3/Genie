package com.djrapitops.genie.command.commands;

import com.djrapitops.genie.Genie;
import com.djrapitops.genie.lamp.LampItem;
import com.djrapitops.genie.lamp.LampManager;
import com.djrapitops.genie.utilities.Check;
import com.djrapitops.plugin.api.utility.UUIDFetcher;
import com.djrapitops.plugin.command.CommandType;
import com.djrapitops.plugin.command.CommandUtils;
import com.djrapitops.plugin.command.ISender;
import com.djrapitops.plugin.command.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

import static org.bukkit.Bukkit.getPlayer;

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
        Player reciever = getReciever(args, sender);

        if (!Check.isTrue(reciever != null, notFound, sender)) {
            return true;
        }

        int wishes = getWishAmount(args);
        final LampItem newLamp = lampManager.newLamp(wishes);

        lampManager.dropLamp(reciever.getLocation(), newLamp);
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

    private Player getReciever(String[] args, ISender sender) {
        Player reciever;
        if (args.length == 0 && CommandUtils.isPlayer(sender)) {
            reciever = (Player) sender.getSender();
        } else {
            UUID uuid = null;
            try {
                uuid = UUIDFetcher.getUUIDOf(args[0]);
            } catch (Exception ignored) {
            }
            if (uuid == null) {
                if (CommandUtils.isPlayer(sender)) {
                    return (Player) sender.getSender();
                }
                return null;
            }
            reciever = getPlayer(uuid);
        }
        return reciever;
    }
}

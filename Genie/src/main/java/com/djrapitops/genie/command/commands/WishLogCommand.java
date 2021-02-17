package com.djrapitops.genie.command.commands;

import com.djrapitops.genie.Genie;
import com.djrapitops.genie.file.WishLog;
import com.djrapitops.genie.utilities.Check;
import com.djrapitops.plugin.api.utility.log.Log;
import com.djrapitops.plugin.command.CommandType;
import com.djrapitops.plugin.command.ISender;
import com.djrapitops.plugin.command.SubCommand;
import com.djrapitops.plugin.settings.ColorScheme;
import com.djrapitops.plugin.task.AbsRunnable;
import com.djrapitops.plugin.task.RunnableFactory;
import com.djrapitops.plugin.utilities.Verify;
import org.bukkit.ChatColor;

import java.io.IOException;
import java.util.List;

/**
 * @author AuroraLS3
 */
public class WishLogCommand extends SubCommand {

    private final Genie plugin;

    public WishLogCommand(Genie plugin) {
        super("wishlog, log, wl", CommandType.PLAYER_OR_ARGS, "genie.admin", "See all wishes made by a player", "<playername>");
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(ISender sender, String commandLabel, String[] args) {
        ColorScheme color = plugin.getColorScheme();
        final String mColor = color.getMainColor();
        final String sColor = color.getSecondaryColor();
        final String prefix = mColor + "[Genie] " + sColor;

        if (!Check.isTrue(args.length >= 1, prefix + "Not enough Arguments.", sender)) {
            return true;
        }

        String name = args[0];

        WishLog wishLog = plugin.getWishLog();
        plugin.getRunnableFactory();
        RunnableFactory.createNew(new AbsRunnable("WishLog read task") {
            @Override
            public void run() {
                List<String> wishes = null;
                try {
                    wishes = wishLog.getWishesBy(name);
                } catch (IOException ex) {
                    Log.toLog(this.getClass().getName(), ex);
                    sender.sendMessage(ChatColor.RED + "Error occurred: " + ex);
                }
                final boolean notEmpty = !Verify.isEmpty(wishes);

                if (!Check.isTrue(notEmpty, prefix + name + " has 0 wishes logged.", sender)) {
                    return;
                }

                sender.sendMessage(prefix + name + " has " + color.getTertiaryColor() + wishes.size() + sColor + " wishes logged.");
                wishes.stream()
                        .map(logLine -> sColor + logLine)
                        .forEach(sender::sendMessage);

            }
        }).runTaskAsynchronously();
        return true;
    }
}

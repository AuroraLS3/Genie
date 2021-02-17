package com.djrapitops.genie.command.commands;

import com.djrapitops.genie.Genie;
import com.djrapitops.genie.utilities.Check;
import com.djrapitops.genie.wishes.Wish;
import com.djrapitops.plugin.command.CommandType;
import com.djrapitops.plugin.command.ISender;
import com.djrapitops.plugin.command.SubCommand;
import com.djrapitops.plugin.settings.ColorScheme;
import org.bukkit.ChatColor;

import java.util.Arrays;

/**
 * Command used to test a wish.
 *
 * @author AuroraLS3
 */
public class TestWishCommand extends SubCommand {

    private final Genie plugin;

    public TestWishCommand(Genie plugin) {
        super("test, check, testwish", CommandType.ALL_WITH_ARGS, "genie.admin", "Test a wish", "<wish>");
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(ISender sender, String commandLabel, String[] args) {
        if (!Check.isTrue(args.length >= 1, ChatColor.RED + "Command Requires at least one argument.", sender)) {
            return true;
        }

        ColorScheme color = plugin.getColorScheme();
        final String mColor = color.getMainColor();
        final String sColor = color.getSecondaryColor();
        final String prefix = mColor + "[Genie] " + sColor;

        String wish = getWish(args);
        Wish matchingWish = plugin.getWishManager().getMatchingWish(wish, null);

        if (matchingWish == null) {
            sender.sendMessage(prefix + "Did not match any wish.");
        } else {
            sender.sendMessage(prefix + "Matching wish: " + Arrays.toString(matchingWish.getAliases()));
        }
        return true;
    }

    private String getWish(String[] args) {
        StringBuilder b = new StringBuilder();
        for (String arg : args) {
            b.append(arg).append(" ");
        }
        return b.toString().trim().toLowerCase();
    }
}

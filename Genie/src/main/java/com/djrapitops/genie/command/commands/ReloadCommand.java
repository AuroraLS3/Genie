package com.djrapitops.genie.command.commands;

import com.djrapitops.genie.Genie;
import com.djrapitops.plugin.command.CommandType;
import com.djrapitops.plugin.command.ISender;
import com.djrapitops.plugin.command.SubCommand;
import com.djrapitops.plugin.settings.ColorScheme;

/**
 * Command used to reload genie.
 *
 * @author AuroraLS3
 */
public class ReloadCommand extends SubCommand {

    private final Genie plugin;

    public ReloadCommand(Genie plugin) {
        super("reload", CommandType.ALL, "genie.admin", "Reload Genie config");
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(ISender sender, String commandLabel, String[] args) {
        ColorScheme color = plugin.getColorScheme();
        final String mColor = color.getMainColor();
        final String sColor = color.getSecondaryColor();
        final String prefix = mColor + "[Genie] " + sColor;

        plugin.onDisable();
        plugin.onEnable();

        sender.sendMessage(prefix + "Reloaded!");
        return true;
    }
}

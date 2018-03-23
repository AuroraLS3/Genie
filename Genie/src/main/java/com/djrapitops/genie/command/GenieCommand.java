package com.djrapitops.genie.command;

import com.djrapitops.genie.Genie;
import com.djrapitops.genie.command.commands.GiveLampCommand;
import com.djrapitops.genie.command.commands.TestWishCommand;
import com.djrapitops.genie.command.commands.WishLogCommand;
import com.djrapitops.plugin.command.CommandType;
import com.djrapitops.plugin.command.TreeCommand;
import com.djrapitops.plugin.command.defaultcmds.StatusCommand;

/**
 * Genie admin command
 *
 * @author Rsl1122
 */
public class GenieCommand extends TreeCommand<Genie> {

    public GenieCommand(Genie plugin) {
        super(plugin, "genie", CommandType.CONSOLE, "genie.admin", "", "genie");
    }

    @Override
    public void addCommands() {
        add(
                new GiveLampCommand(plugin),
                new TestWishCommand(plugin),
                new WishLogCommand(plugin),
                new StatusCommand<>(plugin, this.getPermission(), plugin.getColorScheme())
        );
    }
}

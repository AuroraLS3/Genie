package com.djrapitops.genie.wishes;

import com.djrapitops.genie.Genie;
import com.djrapitops.genie.Log;
import com.djrapitops.plugin.api.Priority;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class PlayerSpecificCommandWish extends Wish {

    private final Genie plugin;
    private final String command;

    public PlayerSpecificCommandWish(Genie plugin, String command, String... aliases) {
        super(aliases);
        this.plugin = plugin;
        this.command = command;
    }

    @Override
    public boolean fulfillWish(Player p) {
        Server server = plugin.getServer();
        String command = this.command.replace("{playername}", p.getName());
        Log.info("Fulfilling Command Wish: " + command);
        boolean executeSuccess = server.dispatchCommand(server.getConsoleSender(), command);
        if (!executeSuccess) {
            String message = "Command may be incorrectly set up: " + this.command;
            plugin.getNotificationCenter().addNotification(Priority.LOW, message);
            Log.error(message);
        }
        return executeSuccess;
    }
}

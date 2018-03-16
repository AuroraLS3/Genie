package com.djrapitops.genie;

import com.djrapitops.genie.command.GenieCommand;
import com.djrapitops.genie.file.LampStorage;
import com.djrapitops.genie.file.UnfulfilledWishStorage;
import com.djrapitops.genie.file.WishConfigSectionHandler;
import com.djrapitops.genie.file.WishLog;
import com.djrapitops.genie.lamp.LampManager;
import com.djrapitops.genie.listeners.ChatListener;
import com.djrapitops.genie.listeners.DeathListener;
import com.djrapitops.genie.listeners.ItemInteractionListener;
import com.djrapitops.genie.wishes.WishManager;
import com.djrapitops.plugin.BukkitPlugin;
import com.djrapitops.plugin.StaticHolder;
import com.djrapitops.plugin.api.systems.TaskCenter;
import com.djrapitops.plugin.api.utility.Version;
import com.djrapitops.plugin.api.utility.log.Log;
import com.djrapitops.plugin.settings.ColorScheme;
import com.djrapitops.plugin.utilities.Verify;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main class.
 *
 * @author Rsl1122
 */
public class Genie extends BukkitPlugin {

    private API api;
    private WishLog wishLog;
    private WishManager wishManager;
    private WishConfigSectionHandler wishConfigSectionHandler;
    private LampManager lampManager;

    private UnfulfilledWishStorage unfulfilledWishStore;

    private List<String> worldBlacklist;
    private Messages messages;

    public static Genie getInstance() {
        return (Genie) StaticHolder.getInstance(Genie.class);
    }

    public static API getAPI() {
        return getInstance().api;
    }

    @Override
    public void onEnable() {
        super.onEnable();

        getConfig().options().copyDefaults(true);
        getConfig().options().header("Genie Config");
        saveConfig();

        Log.setDebugMode(getConfig().getString(Settings.DEBUG.getPath()));

        checkForNewVersion();

        updateWorldBlacklist();

        wishConfigSectionHandler = new WishConfigSectionHandler(this);
        wishLog = new WishLog(this);
        wishManager = new WishManager(this);
        messages = new Messages();

        try {
            LampStorage lampStorage = new LampStorage(this);
            unfulfilledWishStore = new UnfulfilledWishStorage(this);
            lampManager = new LampManager(this, lampStorage);
            Verify.nullCheck(wishLog, wishManager, messages, lampManager, worldBlacklist);
        } catch (NullPointerException | IOException | InvalidConfigurationException ex) {
            Log.error("Plugin initialization has failed, disabling plugin.");
            Log.toLog(this.getClass().getName(), ex);
            onDisable();
            return;
        }

        registerListener(new ChatListener(this));
        registerListener(new DeathListener(this));
        registerListener(new ItemInteractionListener(this));

        registerCommand("genie", new GenieCommand(this));

        api = new API(this);

        Log.info("Plugin Enabled.");
    }

    private void checkForNewVersion() {
        try {
            if (Version.checkVersion(getVersion(), "https://raw.githubusercontent.com/Rsl1122/Genie/master/Genie/src/main/resources/plugin.yml")
                    || Version.checkVersion(getVersion(), "https://www.spigotmc.org/resources/genie.43260/")) {
                Log.info("New version available at: https://www.spigotmc.org/resources/genie.43260/");
            }
        } catch (NoClassDefFoundError | IOException e) {
            Log.info("Error checking for new version: " + e.getMessage());
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        TaskCenter.cancelAllKnownTasks(Genie.class);
        Log.info("Plugin Disabled.");
    }

    private void updateWorldBlacklist() {
        String path = Settings.WORLD_BLACKLIST.getPath();
        worldBlacklist = getConfig().getStringList(path).stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }

    public boolean isWorldAllowed(World w) {
        return !worldBlacklist.contains(w.getName().toLowerCase());
    }

    public WishLog getWishLog() {
        return wishLog;
    }

    public LampManager getLampManager() {
        return lampManager;
    }

    public WishConfigSectionHandler getWishConfigSectionHandler() {
        return wishConfigSectionHandler;
    }

    public WishManager getWishManager() {
        return wishManager;
    }

    public Messages getMsg() {
        return messages;
    }

    public UnfulfilledWishStorage getUnfulfilledWishStore() {
        return unfulfilledWishStore;
    }

    public ColorScheme getColorScheme() {
        return new ColorScheme(ChatColor.DARK_AQUA, ChatColor.GRAY, ChatColor.AQUA);
    }

    @Override
    public void onReload() {

    }

    @Override
    public String getVersion() {
        return getDescription().getVersion();
    }
}

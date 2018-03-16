/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.genie.file;

import com.djrapitops.genie.Genie;
import com.djrapitops.plugin.api.utility.log.FileLogger;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Risto
 */
public class WishLog {

    private final Genie plugin;

    public WishLog(Genie plugin) {
        this.plugin = plugin;
    }

    public void madeAWish(Player p, String wish) {
        try {
            FileLogger.appendToFile(new File(plugin.getDataFolder(), "Wishlog.txt"), p.getName() + ": " + wish);
        } catch (IOException e) {
            /* Ignored */
        }
    }

    public List<String> getWishesBy(String playerName) throws IOException {
        return Files.lines(new File(plugin.getDataFolder(), "Wishlog.txt").toPath())
                .filter(l -> l.toLowerCase().contains(playerName.toLowerCase()))
                .collect(Collectors.toList());
    }
}

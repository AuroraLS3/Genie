/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.genie.file;

import com.djrapitops.genie.Genie;
import com.djrapitops.plugin.api.TimeAmount;
import com.djrapitops.plugin.api.utility.log.FileLogger;
import com.djrapitops.plugin.task.AbsRunnable;
import com.djrapitops.plugin.task.RunnableFactory;
import com.djrapitops.plugin.utilities.FormatUtils;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author AuroraLS3
 */
public class WishLog {

    private final Genie plugin;

    public WishLog(Genie plugin) {
        this.plugin = plugin;
    }

    public void madeAWish(Player p, String wish) {
        File wishLog = new File(plugin.getDataFolder(), "Wishlog.txt");
        RunnableFactory.createNew(new AbsRunnable("Wishlog write") {
            @Override
            public void run() {
                try {
                    String msg = "[" + FormatUtils.formatTimeStamp(TimeAmount.currentMs()) + "] " + p.getName() + ": " + wish;
                    Files.write(wishLog.toPath(), Collections.singletonList(msg), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    FileLogger.appendToFile(wishLog, msg);
                } catch (IOException e) {
                    /* Ignored */
                } finally {
                    this.cancel();
                }
            }
        }).runTaskAsynchronously();
    }

    public List<String> getWishesBy(String playerName) throws IOException {
        return Files.lines(new File(plugin.getDataFolder(), "Wishlog.txt").toPath())
                .filter(l -> l.toLowerCase().contains(playerName.toLowerCase()))
                .collect(Collectors.toList());
    }
}

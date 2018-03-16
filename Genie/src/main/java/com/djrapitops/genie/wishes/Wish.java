package com.djrapitops.genie.wishes;

import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getOnlinePlayers;

/**
 * Abstract class representing a possible wish.
 *
 * @author Rsl1122
 */
public abstract class Wish {

    private final String[] aliases;

    protected Wish(String... name) {
        this.aliases = new String[name.length];
        for (int i = 0; i < name.length; i++) {
            aliases[i] = name[i].toLowerCase().trim();
        }
    }

    public abstract boolean fulfillWish(Player p);

    public String getName() {
        return aliases[0].split(", ")[0];
    }

    public String[] getAliases() {
        return aliases;
    }

    public String getBestMatch(String wish) {
        double percentage = 2.0;
        String bestMatch = "";
        for (String alias : aliases) {
            if (alias.contains("{playername}")) {
                for (Player p : getOnlinePlayers()) {
                    String aliasWPlayerName = alias.replace("{playername}", p.getName().toLowerCase());
                    double aliasPercentage = getRelativeDiffPercentage(aliasWPlayerName, wish);
                    if (aliasPercentage < percentage) {
                        bestMatch = aliasWPlayerName;
                        percentage = aliasPercentage;
                    }
                }
            } else {
                double aliasPercentage = getRelativeDiffPercentage(alias, wish);
                if (aliasPercentage < percentage) {
                    bestMatch = alias;
                    percentage = aliasPercentage;
                }
            }
        }
        return bestMatch;
    }

    public double getRelativeDiffPercentage(String alias, String wish) {
        String[] wishParts = wish.split(" ");
        String removed = alias.toLowerCase().replace(", ", "");
        for (String wishPart : wishParts) {
            removed = removed.replaceFirst(wishPart, "");
        }
        removed = removed.replace(" ", "");
        int remaining = removed.length();
        return remaining * 1.0 / alias.replace(", ", "").length();
    }
}

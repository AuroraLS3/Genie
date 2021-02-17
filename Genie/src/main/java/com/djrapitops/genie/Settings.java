/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.genie;

/**
 * @author AuroraLS3
 */
public enum Settings {
    DEBUG("Settings.Debug"),
    DROPRATE_DESERT("Settings.DesertDropRate"),
    DROPRATE_OUTSIDE("Settings.OutsideDropRate"),
    ANNOUNCE_WISH_FULFILL("Settings.AnnounceWishFulfillmentToEveryone"),
    WORLD_BLACKLIST("Settings.BlacklistedWorlds"),
    COMMAND_LIST("Customization.CommandWishes");

    private final String path;

    Settings(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return path;
    }
}

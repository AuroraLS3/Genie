package com.djrapitops.genie.file;

import com.djrapitops.genie.Genie;
import com.djrapitops.plugin.api.config.Config;
import com.djrapitops.plugin.api.utility.log.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for saving and loading lamp data.
 *
 * @author Rsl1122
 */
public class UnfulfilledWishStorage extends Config {

    private final List<String> wishes;

    public UnfulfilledWishStorage(Genie plugin) {
        super(new File(getStorageFolder(plugin), "unfulfilled.yml"), getDefaults());
        wishes = loadWishes();
        if (wishes.size() >= 250) {
            Log.info("Genie/storage/unfulfilled.yml has " + wishes.size() + " unfulfilled wishes, please send them to the developer");
        }
    }

    private static File getStorageFolder(Genie plugin) {
        File storage = new File(plugin.getDataFolder(), "storage");
        storage.mkdirs();
        return storage;
    }

    public void addWish(String wish) {
        if (wishes.contains(wish)) {
            return;
        }
        wishes.add(wish);
        try {
            saveWishes(wishes);
        } catch (IOException ex) {
            Log.toLog(this.getClass().getName(), ex);
        }
    }

    private static List<String> getDefaults() {
        List<String> defaults = new ArrayList<>();
        defaults.add("UnfulfilledWishes:");
        defaults.add("  - Default");
        return defaults;
    }

    private List<String> loadWishes() {
        return getStringList("UnfulfilledWishes");
    }

    private void saveWishes(List<String> wishes) throws IOException {
        set("UnfulfilledWishes", wishes);
        save();
    }
}

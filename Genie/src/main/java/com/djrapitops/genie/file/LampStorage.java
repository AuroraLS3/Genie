package com.djrapitops.genie.file;

import com.djrapitops.genie.Genie;
import com.djrapitops.genie.lamp.Lamp;
import com.djrapitops.plugin.api.config.Config;
import com.djrapitops.plugin.api.config.ConfigNode;
import com.djrapitops.plugin.api.utility.log.Log;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Class responsible for saving and loading lamp data.
 *
 * @author AuroraLS3
 */
public class LampStorage extends Config {

    public LampStorage(Genie plugin) {
        super(new File(getStorageFolder(plugin), "lamps.yml"));
    }

    private static File getStorageFolder(Genie plugin) {
        File storage = new File(plugin.getDataFolder(), "storage");
        storage.mkdirs();
        return storage;
    }

    public void addLamp(Lamp lamp) {
        try {
            String lampIDString = lamp.getLampID().toString();
            set("Lamps." + lampIDString + ".wishes", lamp.getWishes());
            save();
        } catch (IOException ex) {
            Log.toLog(this.getClass().getName(), ex);
        }
    }

    public Map<UUID, Lamp> loadLamps() {
        Map<UUID, Lamp> lamps = new HashMap<>();

        ConfigNode lampsSection = getConfigNode("Lamps");
        for (String key : lampsSection.getKeysInOrder()) {
            int wishes = lampsSection.getInt(key + ".wishes");
            UUID lampId = UUID.fromString(key);
            lamps.put(lampId, new Lamp(lampId, wishes));
        }

        return lamps;
    }

    public void wishUsed(Lamp lamp) throws IOException {
        set("Lamps." + lamp.getLampID().toString() + ".wishes", lamp.getWishes());
        save();
    }

    public void genieFreed(Lamp lamp) throws IOException {
        while (lamp.hasWishesLeft()) {
            lamp.useWish();
        }
        lamp.useWish(); // TO -1
        wishUsed(lamp);
    }
}

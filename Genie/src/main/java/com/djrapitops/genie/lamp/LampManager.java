package com.djrapitops.genie.lamp;

import com.djrapitops.genie.file.LampStorage;
import com.djrapitops.plugin.api.utility.log.Log;
import org.bukkit.Location;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * Class for each lamp in the world.
 *
 * @author Rsl1122
 */
public class LampManager {

    private final LampStorage store;
    private final Map<UUID, Lamp> lamps;

    public LampManager(LampStorage store) {
        this.store = store;
        lamps = store.loadLamps();
    }

    private UUID newLampID() {
        UUID uuid = UUID.randomUUID();
        if (lamps.containsKey(uuid)) {
            uuid = newLampID();
        }
        return uuid;
    }

    public void dropLamp(Location loc, LampItem item) {
        loc.getWorld().dropItem(loc, item);
    }

    public LampItem newLamp(int wishes) {
        UUID lampID = newLampID();
        Lamp lamp = new Lamp(lampID, wishes);
        addNewLamp(lamp);
        return new LampItem(lampID);
    }

    public LampItem newLamp() {
        return newLamp(3);
    }

    public Lamp getLamp(UUID lampID) {
        return lamps.get(lampID);
    }

    private void addNewLamp(Lamp lamp) {
        lamps.put(lamp.getLampID(), lamp);
        store.addLamp(lamp);
    }

    public void wish(Lamp lamp) {
        lamp.useWish();
        try {
            store.wishUsed(lamp);
        } catch (IOException ex) {
            Log.toLog(this.getClass().getName(), ex);
        }
    }
}

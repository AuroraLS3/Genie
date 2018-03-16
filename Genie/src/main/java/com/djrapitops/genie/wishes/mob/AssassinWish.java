package com.djrapitops.genie.wishes.mob;

import com.djrapitops.genie.Genie;
import com.djrapitops.genie.wishes.PlayerSpecificWish;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author Rsl1122
 */
public class AssassinWish extends PlayerSpecificWish {

    public AssassinWish() {
        super("Kill, {playername}", "Assasinate, {playername}", "Assassinate, {playername}", "Destroy, {playername}", "");
    }

    @Override
    public boolean fulfillWish(Player p) {
        UUID tpTarget = storage.get(p.getUniqueId());
        if (tpTarget == null) {
            return false;
        }
        Player target = p.getServer().getPlayer(tpTarget);
        if (target == null) {
            return false;
        }
        Location location = target.getLocation();
        if (!Genie.getInstance().isWorldAllowed(location.getWorld())) {
            return false;
        }
        for (int i = 0; i < 5; i++) {
            new SpawnMobWish(EntityType.WITHER_SKELETON).fulfillWish(target);
        }
        return true;
    }
}

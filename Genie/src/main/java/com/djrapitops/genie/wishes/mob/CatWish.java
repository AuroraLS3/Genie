package com.djrapitops.genie.wishes.mob;

import com.djrapitops.genie.wishes.Wish;
import com.djrapitops.genie.wishes.item.ItemWish;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

/**
 * @author AuroraLS3
 */
public class CatWish extends Wish {

    public CatWish() {
        super("Cat", "Pussy", "Meow", "Kitty", "Girlfriend", "Boyfriend");
    }

    @Override
    public boolean fulfillWish(Player p) {
        return new SpawnMobWish(EntityType.OCELOT).fulfillWish(p)
                && new ItemWish(Material.SALMON, 20).fulfillWish(p);
    }

}

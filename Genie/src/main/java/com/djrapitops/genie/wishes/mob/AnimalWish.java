package com.djrapitops.genie.wishes.mob;

import com.djrapitops.genie.wishes.Wish;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rsl1122
 */
public class AnimalWish extends Wish {

    private final List<Wish> mobWishes;

    public AnimalWish() {
        super("Animals");
        mobWishes = new ArrayList<>();
        addWishes();
    }

    @Override
    public boolean fulfillWish(Player p) {
        for (Wish mobWish : mobWishes) {
            for (int i = 0; i < 2; i++) {
                mobWish.fulfillWish(p);
            }
        }
        return true;
    }

    private void addWishes() {
        EntityType[] e = new EntityType[]{
                EntityType.COW,
                EntityType.CHICKEN,
                EntityType.PIG,
                EntityType.SHEEP};
        for (EntityType farmAnimal : e) {
            mobWishes.add(new SpawnMobWish(farmAnimal));
        }
    }
}

package com.djrapitops.genie.wishes;

import java.util.Comparator;

/**
 * @author AuroraLS3
 */
public class WishComparator implements Comparator<Wish> {

    @Override
    public int compare(Wish o1, Wish o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

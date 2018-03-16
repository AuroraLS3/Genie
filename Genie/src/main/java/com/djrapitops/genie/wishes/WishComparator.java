package com.djrapitops.genie.wishes;

import java.util.Comparator;

/**
 * @author Rsl1122
 */
public class WishComparator implements Comparator<Wish> {

    @Override
    public int compare(Wish o1, Wish o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

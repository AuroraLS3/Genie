package com.djrapitops.genie.wishes;

import java.util.Comparator;

/**
 * Sorts the wishes with the lowest remaining characters after wish words were
 * removed.
 *
 * @author AuroraLS3
 */
public class WishMatchComparator implements Comparator<Wish> {

    private final String wish;

    public WishMatchComparator(String wish) {
        this.wish = wish;
    }

    @Override
    // Order by wish replacement, higher replacement number first.
    public int compare(Wish o1, Wish o2) {
        String match1 = o1.getBestMatch(wish);
        String match2 = o2.getBestMatch(wish);
        int matchComparison = Double.compare(o1.getRelativeDiffPercentage(match1, wish), o2.getRelativeDiffPercentage(match2, wish));
        if (matchComparison == 0) {
            return compareReplaceLength(match1, match2);
        } else {
            return matchComparison;
        }
    }

    // Order by length, longer first.
    private int compareReplaceLength(String match1, String match2) {
        int lengthComparison = Integer.compare(match2.length(), match1.length());
        if (lengthComparison == 0) {
            return compareFirstMatchingWord(match1, match2);
        } else {
            return lengthComparison;
        }
    }

    // Order by first match, first first.
    private int compareFirstMatchingWord(String match1, String match2) {
        String[] wishParts = wish.split(" ");
        String wishOne = match1.replace(", ", "");
        String wishTwo = match2.replace(", ", "");
        for (String part : wishParts) {
            int indexOfOne = wishOne.indexOf(part);
            int indexOfTwo = wishTwo.indexOf(part);

            if (indexOfOne == -1 && indexOfTwo == -1 || indexOfOne == indexOfTwo) {
                continue;
            }

            return Integer.compare(indexOfOne, indexOfTwo);
        }
        return 0;
    }
}

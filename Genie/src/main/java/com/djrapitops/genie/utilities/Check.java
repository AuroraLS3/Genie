package com.djrapitops.genie.utilities;

import com.djrapitops.plugin.command.ISender;

/**
 * Class containing static check methods with message sending capabilities if
 * the check is false.
 *
 * @author AuroraLS3
 */
public class Check {

    /**
     * If check is false, send message to sender.
     *
     * @param condition Condition.
     * @param message   Message to send if Condition is false
     * @param sender    Sender to send message to
     * @return Condition
     */
    public static boolean isTrue(boolean condition, String message, ISender sender) {
        if (!condition) {
            sender.sendMessage(message);
        }
        return condition;
    }

}

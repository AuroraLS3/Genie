package com.djrapitops.genie.utilities;

import com.djrapitops.genie.Log;
import com.djrapitops.javaplugin.command.sender.ISender;

/**
 * Class containing static check methods with message sending capabilities if
 * the check is false.
 *
 * @author Rsl1122
 */
public class Check {

    /**
     * If check is false, send message.
     *
     * @param condition Condition.
     * @param message Message to send if Condition is false
     * @return Condition
     */
    public static boolean isTrue(boolean condition, String message) {
        if (!condition) {
            Log.infoColor(message);
        }
        return condition;
    }

    /**
     * If check is false, send message to sender.
     *
     * @param condition Condition.
     * @param message Message to send if Condition is false
     * @param sender Sender to send message to
     * @return Condition
     */
    public static boolean isTrue(boolean condition, String message, ISender sender) {
        if (!condition) {
            sender.sendMessage(message);
        }
        return condition;
    }

    /**
     * If check is false, send error message.
     *
     * @param condition Condition.
     * @param message Message to send if Condition is false
     * @return Condition
     */
    public static boolean isTrue_Error(boolean condition, String message) {
        if (!condition) {
            Log.error(message);
            Log.toLog(message, Log.getErrorsFilename());
        }
        return condition;
    }
}

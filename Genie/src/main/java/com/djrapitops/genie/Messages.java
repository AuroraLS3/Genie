package com.djrapitops.genie;

import com.djrapitops.genie.file.MessageHandler;

import java.util.*;

/**
 * Class that contains message parts and variations to make genie more live
 * like.
 *
 * @author Rsl1122
 */
public class Messages {

    private final Map<MessageType, List<String>> messages;
    private final Random random;
    private MessageHandler msgHandler;

    public Messages() {
        messages = new HashMap<>();
        for (MessageType type : MessageType.values()) {
            messages.put(type, new ArrayList<>());
        }
        msgHandler = new MessageHandler(Genie.getInstance());
        random = new Random();
        addMessages();
    }

    public String getMessage(MessageType type) {
        List<String> list = messages.get(type);
        return list.get(getRandomNumber(list.size()));
    }

    private int getRandomNumber(int bound) {
        return random.nextInt(bound);
    }

    public void addMsg(MessageType type, String msg) {
        messages.get(type).add(msg);
    }

    private void addMessages() {
        for (String sm : msgHandler.getSummonMessages()) {
            addMsg(MessageType.SUMMON, sm);
        }

        for (String sm : msgHandler.getFulfillMessages()) {
            addMsg(MessageType.FULFILL, sm);
        }

        for (String sm : msgHandler.getHelpMessages()) {
            addMsg(MessageType.HELP_WISH, sm);
        }

        for (String sm : msgHandler.getWishesLeftMessages()) {
            addMsg(MessageType.WISHES_LEFT, sm);
        }

        for (String sm : msgHandler.getNoWishesMessages()) {
            addMsg(MessageType.OUT_OF_WISHES, sm);
        }

        for (String sm : msgHandler.getCannotFulfillMessages()) {
            addMsg(MessageType.CANNOT_FIND, sm);
        }
    }
}

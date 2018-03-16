package com.djrapitops.genie.file;

import com.djrapitops.genie.Genie;
import com.djrapitops.plugin.api.config.Config;
import com.djrapitops.plugin.api.config.ConfigNode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MessageHandler extends Config {

    public MessageHandler(Genie plugin) {
        super(new File(plugin.getDataFolder(), "messages.yml"), getDefaults());
    }

    private static List<String> getDefaults() {
        List<String> defaults = new ArrayList<>();

        defaults.add("Messages:");

        List<String> summonMsgList = new ArrayList<>();
        summonMsgList.add("Behold, you have summoned me, Genie!");
        summonMsgList.add("Behold, I am the Genie of the lamp!");
        summonMsgList.add("Look at me I'm Mr. MEESEEKS!");
        summonMsgList.add("BEWARE THE WRATH OF THE OLD GENIE, Just kiddin'");
        summonMsgList.add("Thanks for letting me out of there!");
        summonMsgList.add("Thank yaa for freeing me from me lamp eh!");
        summonMsgList.add("I have slept for thousands of years, but now you woke me from my nap!");
        summonMsgList.add("What if I told you, that I am a Genie?");
        summonMsgList.add("Thank you for helping me with my geometry homework.");
        summonMsgList.add("Ok, ok, you released me from this lamp bla bla bla.");
        summonMsgList.add("How about another wish to help your situation?");
        summonMsgList.add("Who has called the BUTT GENIE?");
        summonMsgList.add("SHAZAM!");
        summonMsgList.add("KAZAAM!");
        summonMsgList.add("Alright Sparky, here's the deal.");
        summonMsgList.add("Poof! Waddya need?");
        defaults.add("  Summon-Messages:");
        for (String s : summonMsgList) {
            defaults.add("  - " + s);
        }

        List<String> fulfillMsgList = new ArrayList<>();
        fulfillMsgList.add("Your word is my command!");
        fulfillMsgList.add("Here you go");
        fulfillMsgList.add("The genie has granted your wish");
        fulfillMsgList.add("How about them apples?");
        fulfillMsgList.add("I think you might have wanted this");
        fulfillMsgList.add("This is the best I can do");
        fulfillMsgList.add("Is this enough?");
        fulfillMsgList.add("How about this, do you think it'll do?");
        fulfillMsgList.add("Maybe you want this and don't know it yet.");
        fulfillMsgList.add("Applause please!");
        defaults.add("  Fulfill-Messages:");
        for (String s : fulfillMsgList) {
            defaults.add("  - " + s);
        }

        List<String> helpMsgList = new ArrayList<>();
        helpMsgList.add("Hold the lamp & speak your wish!");
        helpMsgList.add("Hold the lamp & speak thy wish!");
        helpMsgList.add("Hold that lamp & let me know your wish");
        helpMsgList.add("Tell me your wish");
        helpMsgList.add("How about a wish?");
        helpMsgList.add("I'd like to hear your wish, just hold the lamp.");
        helpMsgList.add("Hold the lamp while speaking.");
        helpMsgList.add("Bla bla bla.");
        helpMsgList.add("Speak your mind, release your wish.");
        helpMsgList.add("You only had to rub the lamp, but I appreciate you going the extra mile.");
        defaults.add("  Help-Messages:");
        for (String s : helpMsgList) {
            defaults.add("  - " + s);
        }

        List<String> wishesRemainingMsgList = new ArrayList<>();
        wishesRemainingMsgList.add("You have WISHES wishes left.");
        wishesRemainingMsgList.add("I can still grant WISHES wishes.");
        wishesRemainingMsgList.add("WISHES wishes to go.");
        wishesRemainingMsgList.add("How many wishes left? WISHES");
        wishesRemainingMsgList.add("About WISHES wishes..");
        wishesRemainingMsgList.add("You still got WISHES wishes left.");
        wishesRemainingMsgList.add("I can grant you WISHES wishes.");
        wishesRemainingMsgList.add("WISHES wishes left.");
        defaults.add("  Wishes-Remaining-Messages:");
        for (String s : wishesRemainingMsgList) {
            defaults.add("  - " + s);
        }

        List<String> noWishesLeftMsgList = new ArrayList<>();
        noWishesLeftMsgList.add("No wishes left.");
        noWishesLeftMsgList.add("This lamp has no wishes.");
        noWishesLeftMsgList.add("Zero, Nada, Non' - Wishes left.");
        noWishesLeftMsgList.add("I'm afraid it's my time to go kids, this lamp is out of wishes.");
        noWishesLeftMsgList.add("You used all of your wishes.");
        noWishesLeftMsgList.add("I'm all out of wishes.");
        noWishesLeftMsgList.add("You're out of wishes.");
        noWishesLeftMsgList.add("0 wishes left.");
        noWishesLeftMsgList.add("That was your last wish.");
        noWishesLeftMsgList.add("Excuse me, I am in the shower. I think you used your wishes already.");
        defaults.add("  Out-Of-Wishes-Messages:");
        for (String s : noWishesLeftMsgList) {
            defaults.add("  - " + s);
        }

        List<String> cannotFulfillMsgList = new ArrayList<>();
        cannotFulfillMsgList.add("Gadzooks! I don't think that can be done, have another try.");
        cannotFulfillMsgList.add("Sorry, I do not know how to fulfill your wish.");
        cannotFulfillMsgList.add("Sorry, I do not know how to fulfill that wish.");
        cannotFulfillMsgList.add("Could you think of something less impossible?");
        cannotFulfillMsgList.add("That may be beyond my capabilities");
        cannotFulfillMsgList.add("Please ask for the dev to add that to my abilities. ");
        defaults.add("  Cannot-Fulfill-Messages:");
        for (String s : cannotFulfillMsgList) {
            defaults.add("  - " + s);
        }

        return defaults;
    }

    private ConfigNode getMessagesSection() {
        return getConfigNode("Messages");
    }

    public List<String> getSummonMessages() {
        ConfigNode section = getMessagesSection();
        return new ArrayList<>(section.getStringList("Summon-Messages"));
    }

    public List<String> getFulfillMessages() {
        ConfigNode section = getMessagesSection();
        return new ArrayList<>(section.getStringList("Fulfill-Messages"));
    }

    public List<String> getHelpMessages() {
        ConfigNode section = getMessagesSection();
        return new ArrayList<>(section.getStringList("Help-Messages"));
    }

    public List<String> getNoWishesMessages() {
        ConfigNode section = getMessagesSection();
        return new ArrayList<>(section.getStringList("Out-Of-Wishes-Messages"));
    }

    public List<String> getCannotFulfillMessages() {
        ConfigNode section = getMessagesSection();
        return new ArrayList<>(section.getStringList("Cannot-Fulfill-Messages"));
    }

    public List<String> getWishesLeftMessages() {
        ConfigNode section = getMessagesSection();
        return new ArrayList<>(section.getStringList("Wishes-Remaining-Messages"));
    }
}

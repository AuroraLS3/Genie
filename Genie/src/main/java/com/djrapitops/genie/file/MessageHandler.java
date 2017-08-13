package com.djrapitops.genie.file;

import com.djrapitops.genie.Genie;
import com.djrapitops.genie.Log;
import com.djrapitops.plugin.config.BukkitConfig;
import com.djrapitops.plugin.utilities.Verify;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessageHandler extends BukkitConfig<Genie> {

    private File messageFile;
    private FileConfiguration msgConfig;

    public MessageHandler(Genie plugin) throws IOException, InvalidConfigurationException {
        super(plugin, "messages.yml");
        super.createFile();
        FileConfiguration config = super.getConfig();
        config.options().copyDefaults(true);
        addDefaults(config);
        save();
    }

    private void addDefaults(FileConfiguration config) {
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
        config.addDefault("Summon-Messages", summonMsgList);

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
        config.addDefault("Fulfill-Messages", fulfillMsgList);

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
        config.addDefault("Help-Messages", helpMsgList);

        List<String> wishesRemainingMsgList = new ArrayList<>();
        wishesRemainingMsgList.add("You have WISHES wishes left.");
        wishesRemainingMsgList.add("I can still grant WISHES wishes.");
        wishesRemainingMsgList.add("WISHES wishes to go.");
        wishesRemainingMsgList.add("How many wishes left? WISHES");
        wishesRemainingMsgList.add("About WISHES wishes..");
        wishesRemainingMsgList.add("You still got WISHES wishes left.");
        wishesRemainingMsgList.add("I can grant you WISHES wishes.");
        wishesRemainingMsgList.add("WISHES wishes left.");
        config.addDefault("Wishes-Remaining-Messages", wishesRemainingMsgList);

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
        config.addDefault("Out-Of-Wishes-Messages", noWishesLeftMsgList);

        List<String> cannotFulfillMsgList = new ArrayList<>();
        cannotFulfillMsgList.add("Gadzooks! I don't think that can be done, have another try.");
        cannotFulfillMsgList.add("Sorry, I do not know how to fulfill your wish.");
        cannotFulfillMsgList.add("Sorry, I do not know how to fulfill that wish.");
        cannotFulfillMsgList.add("Could you think of something less impossible?");
        cannotFulfillMsgList.add("That may be beyond my capabilities");
        cannotFulfillMsgList.add("Please ask for the dev to add that to my abilities. ");
        config.addDefault("Cannot-Fulfill-Messages", cannotFulfillMsgList);
    }

    private ConfigurationSection getMessagesSection() {
        return getConfig().getConfigurationSection("Messages");
    }

    public List<String> getSummonMessages() {
        ConfigurationSection section = getMessagesSection();
        return new ArrayList<>(section.getStringList("Summon-Messages"));
    }

    public List<String> getFulfillMessages() {
        ConfigurationSection section = getMessagesSection();
        return new ArrayList<>(section.getStringList("Fulfill-Messages"));
    }

    public List<String> getHelpMessages() {
        ConfigurationSection section = getMessagesSection();
        return new ArrayList<>(section.getStringList("Help-Messages"));
    }

    public List<String> getNoWishesMessages() {
        ConfigurationSection section = getMessagesSection();
        return new ArrayList<>(section.getStringList("Out-Of-Wishes-Messages"));
    }

    public List<String> getCannotFulfillMessages() {
        ConfigurationSection section = getMessagesSection();
        return new ArrayList<>(section.getStringList("Cannot-Fulfill-Messages"));
    }

    public List<String> getWishesLeftMessages() {
        ConfigurationSection section = getMessagesSection();
        return new ArrayList<>(section.getStringList("Wishes-Remaining-Messages"));
    }
}

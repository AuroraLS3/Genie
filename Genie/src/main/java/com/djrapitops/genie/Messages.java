package com.djrapitops.genie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.djrapitops.genie.file.MessageHandler;

/**
 * Class that contains message parts and variations to make genie more live
 * like.
 *
 * @author Rsl1122
 */
public class Messages {

    private final Map<MessageType, List<String>> messages;
    private final Random random;

    public Messages() {
        messages = new HashMap<>();
        for (MessageType type : MessageType.values()) {
            messages.put(type, new ArrayList<>());
        }
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

    MessageHandler Mhandler = new MessageHandler(Genie.getInstance());
    private void addMessages() {
    	if (!Mhandler.exists()){
    		Mhandler.setupfile();
    		
    		//Summon Messages   		
    		List<String> summonMlist = new ArrayList<String>();
    		summonMlist.add("Behold, you have summoned me, Genie!");
    		summonMlist.add("Behold, I am the Genie of the lamp!");
    		summonMlist.add("Look at me I'm Mr. MEESEEKS!");
    		summonMlist.add("BEWARE THE WRATH OF THE OLD GENIE, Just kiddin'");
    		summonMlist.add("Thanks for letting me out of there!");
    		summonMlist.add("Thank yaa for freeing me from me lamp eh!");
    		summonMlist.add("I have slept for thousands of years, but now you woke me from my nap!");
    		summonMlist.add("What if I told you, that I am a Genie?");
    		summonMlist.add("Thank you for helping me with my geometry homework.");
    		summonMlist.add("Ok, ok, you released me from this lamp bla bla bla.");
    		summonMlist.add("How about another wish to help your situation?");
    		summonMlist.add("Who has called the BUTT GENIE?");
    		summonMlist.add("SHAZAM!");
    		summonMlist.add("KAZAAM!");
    		summonMlist.add("Alright Sparky, here's the deal.");
    		summonMlist.add("Poof! Waddya need?");
    		Mhandler.addMessages(summonMlist,"Summon-Messages");
    		
    		
    		
    		//Fulfill Messages    		
    		List<String> fulfillMlist = new ArrayList<String>();
    		fulfillMlist.add("Your word is my command!");
    		fulfillMlist.add("Here you go");
    		fulfillMlist.add("The genie has granted your wish");
    		fulfillMlist.add("How about them apples?");
    		fulfillMlist.add("I think you might have wanted this");
    		fulfillMlist.add("This is the best I can do");
    		fulfillMlist.add("Is this enough?");
    		fulfillMlist.add("How about this, do you think it'll do?");
    		fulfillMlist.add("Maybe you want this and don't know it yet.");
    		fulfillMlist.add("Applause please!");
    		Mhandler.addMessages(fulfillMlist,"Fulfill-Messages");
    	   

    		//Wish help messages
    		List<String> helpMlist = new ArrayList<String>();
    		helpMlist.add("Hold the lamp & speak your wish!");
    		helpMlist.add("Hold the lamp & speak thy wish!");
    		helpMlist.add("Hold that lamp & let me know your wish");
    		helpMlist.add("Tell me your wish");
    		helpMlist.add("How about a wish?");
    		helpMlist.add("I'd like to hear your wish, just hold the lamp.");
    		helpMlist.add("Hold the lamp while speaking.");
    		helpMlist.add("Bla bla bla.");
    		helpMlist.add("Speak your mind, release your wish.");
    		helpMlist.add("You only had to rub the lamp, but I appreciate you going the extra mile.");
    		Mhandler.addMessages(helpMlist,"Help-Messages");
    		
    		
    		//Wishes remaining messages
    		List<String> wishesleftMlist = new ArrayList<String>();
    		wishesleftMlist.add("You have WISHES wishes left.");
    		wishesleftMlist.add("I can still grant WISHES wishes.");
    		wishesleftMlist.add("WISHES wishes to go.");
    		wishesleftMlist.add("How many wishes left? WISHES");
    		wishesleftMlist.add("About WISHES wishes..");
    		wishesleftMlist.add("You still got WISHES wishes left.");
    		wishesleftMlist.add("I can grant you WISHES wishes.");
    		wishesleftMlist.add("WISHES wishes left.");
    	    	
    		Mhandler.addMessages(wishesleftMlist,"Wishes-Remaining-Messages");
    		
    		
    		
    		//No wishes messages
    		List<String> NowishMlist = new ArrayList<String>();
    		NowishMlist.add("No wishes left.");
    		NowishMlist.add("This lamp has no wishes.");
    		NowishMlist.add("Zero, Nada, Non' - Wishes left.");
    		NowishMlist.add("I'm afraid it's my time to go kids, this lamp is out of wishes.");
    		NowishMlist.add("You used all of your wishes.");
    		NowishMlist.add("I'm all out of wishes.");
    		NowishMlist.add("You're out of wishes.");
    		NowishMlist.add("0 wishes left.");
    		NowishMlist.add("That was your last wish.");
    		NowishMlist.add("Excuse me, I am in the shower. I think you used your wishes already.");
    		Mhandler.addMessages(NowishMlist,"Out-Of-Wishes-Messages");
    		
    		
    		//Cannot find wish messages
    		List<String> cannotMlist = new ArrayList<String>();
    		cannotMlist.add("Gadzooks! I don't think that can be done, have another try.");
    		cannotMlist.add("Sorry, I do not know how to fulfill your wish.");
    		cannotMlist.add("Sorry, I do not know how to fulfill that wish.");
    		cannotMlist.add("Could you think of something less impossible?");
    		cannotMlist.add("That may be beyond my capabilities");
    		cannotMlist.add("Please ask for the dev to add that to my abilities. ");
    		Mhandler.addMessages(cannotMlist,"Cannot-Fulfill-Messages");
    		
    	}
    	
    	
    	
    
     
    	for (String sm : Mhandler.getSummonMessages()){
    		addMsg(MessageType.SUMMON,sm);
    	}
    	
    	for (String sm : Mhandler.getFulfillMessages()){
    		addMsg(MessageType.FULFILL,sm);
    	}
    	
    	for (String sm : Mhandler.getHelpMessages()){
    		addMsg(MessageType.HELP_WISH,sm);
    	}
     
    	for (String sm : Mhandler.getWishesLeftMessages()){
    		addMsg(MessageType.WISHES_LEFT,sm);
    	}
       
        for (String sm : Mhandler.getNoWishesMessages()){
    		addMsg(MessageType.OUT_OF_WISHES,sm);
    	}
     
        
        for (String sm : Mhandler.getCannotFulfillMessages()){
    		addMsg(MessageType.CANNOT_FIND,sm);
    	}
      
    }

}

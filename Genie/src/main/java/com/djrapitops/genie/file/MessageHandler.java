package com.djrapitops.genie.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import com.djrapitops.genie.Genie;

public class MessageHandler {
	
	
	private final Genie plugin;
	 File messagefile;
	 
	  public MessageHandler(Genie plugin) {
	        this.plugin = plugin;
	    }
	  YamlConfiguration Mconfig;
	  
	  
	  public void addMessages(List<String> messages,String section){
		  messagefile = new File(plugin.getDataFolder().getAbsolutePath()+"/" + "messages.yml").getAbsoluteFile();
		  Mconfig = YamlConfiguration.loadConfiguration(messagefile);
		  List<String> messagelist = Mconfig.getConfigurationSection("Messages").getStringList(section);
			  messagelist.addAll(messages);
			 Mconfig.getConfigurationSection("Messages").set(section, (List<String>)messagelist);
		  try {
			Mconfig.save(messagefile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		  
	  }
	  
	  
	  public void setupfile(){
		  try {
				new File(plugin.getDataFolder().getAbsolutePath()+"/" + "messages.yml").createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			messagefile = new File(plugin.getDataFolder().getAbsolutePath()+"/" + "messages.yml").getAbsoluteFile();
			 Mconfig = YamlConfiguration.loadConfiguration(messagefile);
			 Mconfig.createSection("Messages");

		
			try {
				Mconfig.save(messagefile);
			} catch (IOException e) {
				e.printStackTrace();
			}
	  }
	  
	  private ConfigurationSection getMessagesSection() {
		  messagefile = new File(plugin.getDataFolder().getAbsolutePath()+"/" + "messages.yml").getAbsoluteFile();
	        Mconfig = YamlConfiguration.loadConfiguration(messagefile);
	        return Mconfig.getConfigurationSection("Messages");
	  }
	  
	  
	  public ArrayList<String> getSummonMessages(){
		ConfigurationSection section = getMessagesSection();
		return new ArrayList<String>(section.getStringList("Summon-Messages"));
	  }
	  
	  public ArrayList<String> getFulfillMessages(){
			ConfigurationSection section = getMessagesSection();
			return new ArrayList<String>(section.getStringList("Fulfill-Messages"));
		  }
	  
	  public ArrayList<String> getHelpMessages(){
			ConfigurationSection section = getMessagesSection();
			return new ArrayList<String>(section.getStringList("Help-Messages"));
		  }
	  
	  public ArrayList<String> getNoWishesMessages(){
			ConfigurationSection section = getMessagesSection();
			return new ArrayList<String>(section.getStringList("Out-Of-Wishes-Messages"));
		  }
	  
	  public ArrayList<String> getCannotFulfillMessages(){
			ConfigurationSection section = getMessagesSection();
			return new ArrayList<String>(section.getStringList("Cannot-Fulfill-Messages"));
		  }
	  public ArrayList<String> getWishesLeftMessages(){
			ConfigurationSection section = getMessagesSection();
			return new ArrayList<String>(section.getStringList("Wishes-Remaining-Messages"));
		  }
	  
	 public Boolean exists(){
		 messagefile = new File(plugin.getDataFolder().getAbsolutePath()+"/" + "messages.yml").getAbsoluteFile();
		return messagefile.exists();
	 }

}

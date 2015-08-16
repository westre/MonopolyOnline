package com.westre.monopoly;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.westre.monopoly.menu.Lobby;
import com.westre.monopoly.menu.MainMenu;
import com.westre.monopoly.menu.MultiplayerMenu;
import com.westre.monopoly.menu.OptionsMenu;
import com.westre.multiplayer.MonopolyClient;
import com.westre.multiplayer.MonopolyPlayer;

public class Game extends StateBasedGame {
	
	public static final String gameName = "Monopoly Online";
	
	public static final int menu = 0;
	public static final int play = 1;
	public static final int options = 2;
	public static final int multiplayer = 3;
	public static final int lobby = 4;
	
	private static ArrayList<String> messages = new ArrayList<String>();
	private static String messageBox;
	private static int messageBoxTimer = 500;
	
	public static String mouseText = "undefined";
	public static String debugText = "undefined";
	
	public static AppGameContainer appGC;
	public static Properties prop;
	public static MonopolyPlayer localPlayer; // client side programming
	public static MonopolyClient localClient; // client to server gateway
	
	public static String playerName = "none";
	
	public static void main(String[] args) {
		try {
			appGC = new AppGameContainer(new Game(gameName));
			appGC.setDisplayMode(1280, 720, false);
			appGC.setAlwaysRender(true); // debug
			appGC.setTargetFrameRate(60);
	        appGC.setVSync(true);
			appGC.start();
		}
		catch(SlickException e) {
			e.printStackTrace();
		}
	}
	
	public Game(String name) {
		super(name);
		
		//Player player = new Player("westre");
		
		this.addState(new MainMenu(menu));
		this.addState(new OptionsMenu(options));
		this.addState(new Play(play));
		this.addState(new MultiplayerMenu(multiplayer));
		this.addState(new Lobby(lobby));
		
		this.enterState(menu);
		
		boolean hasOptionsFile = false;
		
		prop = new Properties();
		try {
			//load a properties file
			prop.load(new FileInputStream("options.cfg"));
			hasOptionsFile = true;
		} 
		catch (IOException ex) {
			try {
				File f = new File("options.cfg");
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(!hasOptionsFile) {
			try {
	    		//set the properties value
	    		prop.setProperty("playername", "undefined");
	 
	    		//save properties to project root folder
	    		prop.store(new FileOutputStream("options.cfg"), null);
	 
	    	} catch (IOException ex) {
	    		ex.printStackTrace();
	        }
		}
		else {
			try {
				//load a properties file
				prop.load(new FileInputStream("options.cfg"));

				//get the property value and print it out
				playerName = prop.getProperty("playername");
				System.out.println("PN: " + playerName);

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void initStatesList(GameContainer gc) throws SlickException {
		
	}
	
	public static void standardise(GameContainer gc) {
		gc.setMinimumLogicUpdateInterval(48);
		gc.setMaximumLogicUpdateInterval(48);
		gc.setTargetFrameRate(60);
	}
	
	public static void updateMessageBox() {
		if(Game.messages.size() > 0) {
			
			Game.messageBoxTimer -= 1;
			
			if(Game.messageBoxTimer < 1) {
				Game.messageBoxTimer = 500;
				Game.messages.remove(0);
			}
			
			String newText = "";
			
			for(String message : Game.messages) {
				newText += message + "\n";
			}

			Game.debugText = newText;
		}
	}
	
	public static void sendEventMessage(String message) {
		Game.messages.add(message);
	}

	public static String getMessageBox() {
		return messageBox;
	}

	public static void setMessageBox(String messageBox) {
		Game.messageBox = messageBox;
	}
}

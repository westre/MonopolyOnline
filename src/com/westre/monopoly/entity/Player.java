package com.westre.monopoly.entity;

import java.util.ArrayList;

import com.westre.monopoly.Game;

public class Player {
	
	private String name;
	private ArrayList<String> messages = new ArrayList<String>();
	
	private String messageBox;
	private int messageBoxTimer = 100;

	
	public Player(String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public void updateMessageBox() {
		if(messages.size() > 0) {
			
			this.messageBoxTimer -= 1;
			
			if(this.messageBoxTimer < 1) {
				this.messageBoxTimer = 100;
				messages.remove(0);
			}
			
			String newText = "";
			
			for(String message : messages) {
				newText += message + "\n";
			}

			Game.debugText = newText;
		}
	}
	
	public void sendEventMessage(String message) {
		messages.add(message);
	}


	public String getMessageBox() {
		return messageBox;
	}


	public void setMessageBox(String messageBox) {
		this.messageBox = messageBox;
	}
	
}

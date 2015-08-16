package com.westre.monopoly.misc;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class ChatManager {
	
	private ArrayList<String> messages = new ArrayList<String>();
	private int x, y, width, height;
	private Graphics g;
	
	public ChatManager(Graphics g, int x, int y, int width, int height) {
		super();
		
		this.g = g;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		messages.add("test1");
		messages.add("test2");
		messages.add("test3");
		messages.add("test4");
	}
	
	public void render() {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
		g.setColor(Color.black);
		int yMessage = this.y;
		for(String message : messages) {
			g.drawString(message, this.x + 10, yMessage);
			yMessage += 20;
		}	
	}
	
	public void addMessage(String string) {
		messages.add(string);
		if(messages.size() == 21)
			messages.remove(0);
	}
}

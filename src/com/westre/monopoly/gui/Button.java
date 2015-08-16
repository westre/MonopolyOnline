package com.westre.monopoly.gui;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;

import com.westre.monopoly.interfaces.ButtonHandler;

public class Button {
	
	private int state;
	private Graphics graphics;
	private String buttonName;
	private int x, y, width, height;
	private TrueTypeFont font;
	private ButtonHandler buttonHandler;
	
	private static boolean pressedButton = false;
	private static ArrayList<Button> buttons = new ArrayList<Button>();
	
	public Button(int state, Graphics graphics, String buttonName, int x, int y, int width, int height, ButtonHandler buttonHandler) {
		this.state = state;
		this.graphics = graphics;
		this.buttonName = buttonName;
	
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		font = new TrueTypeFont(new Font("Verdana", Font.PLAIN, 16), false);
		this.buttonHandler = buttonHandler;
		System.out.println("Added button: " + buttonName);
		buttons.add(this);
	}

	public void draw() {	
		//graphics.drawRect(x, y, width, height);
		graphics.setColor(Color.black);
		graphics.drawRoundRect(x, y, width, height, 15);
		graphics.setColor(Color.white);
		graphics.drawString(buttonName, x + (width - font.getWidth(buttonName)) / 2, y + (height - font.getHeight(buttonName)) / 2);
	}
	
	public static ArrayList<Button> getButtons() {
		return buttons;
	}
	
	public int[] getButtonCoordinates() {
		int[] coords = { x, x + width, y, y + height };
		//System.out.println("Supplied data Button: " + getButtonName() + "x: " + x + ", x+width: " + (x+width) + ", y-height: " + (y-height) + ", y:" + y);
		return coords;
	}
	
	public String getButtonName() {
		return buttonName;
	}
	
	public ButtonHandler getButtonHandler() {
		return buttonHandler;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	public static void checkClick(Input input, int state, int xPos, int yPos) {
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON) && !pressedButton) {
			for(Button button : Button.getButtons()) {
				if(button.getState() == state) {
					int coords[] = button.getButtonCoordinates();

					if(xPos >= coords[0] && xPos <= coords[1] && yPos >= coords[2] && yPos <= coords[3]) {
						button.getButtonHandler().onClick();
						break;
					}
				}
			}
			pressedButton = true;
		}
		else {
			pressedButton = false;
		}
	}
}

package com.westre.monopoly.menu;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import com.westre.monopoly.Game;

public class BaseMenu {
	
	private GameContainer gc;
	private Graphics g;
	
	private Image background, logo;
	private TrueTypeFont font;
	
	
	
	public BaseMenu(int state) {
		
	}

	public void init(GameContainer gc) throws SlickException {
		this.gc = gc;
		this.g = gc.getGraphics();
		
		background = new Image("res/monopoly_background.jpg");
		logo = new Image("res/monopoly_man.png");
		logo.setFilter(Image.FILTER_LINEAR);
		logo = logo.getScaledCopy(3);
		
		font = new TrueTypeFont(new Font("Ariel", Font.BOLD, 55), true);
	}
	
	public void render() {	
		background.draw();
		logo.draw(100, 100);
		
		Game.updateMessageBox();
		font.drawString((gc.getWidth() - font.getWidth("Monopoly Online")) / 2, 100, "Monopoly Online", Color.red);
		g.drawString(Game.mouseText, 50, 50);
		g.drawString(Game.debugText, 50, 100);
	}
	
}

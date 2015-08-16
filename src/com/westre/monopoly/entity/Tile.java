package com.westre.monopoly.entity;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Tile {
	
	private int id;
	private float x, y;
	private int width, height;
	private String name;
	private TileState tileState;
	
	private static boolean pressedTile = false;
	private static ArrayList<Tile> tiles = new ArrayList<Tile>();
	
	public enum TileState {
		SQUARE, HORIZONTAL, VERTICAL;
	}
		
	public Tile(int id, TileState tileState, String name, float x, float y, int width, int height) {
		this.id = id;
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.tileState = tileState;
		
		tiles.add(this);
	}
	
	public float[] getTileCoordinates() {
		float[] coords = { (float) x, x + width, (float) y, y + height };
		return coords;
	}
	
	public static ArrayList<Tile> getTiles() {
		return tiles;
	}
	
	public static void checkClick(Input input, int xPos, int yPos) {
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON) && !pressedTile) {
			for(Tile tile : Tile.getTiles()) {
				// add gamestate protection just like button?
				float coords[] = tile.getTileCoordinates();

				if(xPos >= coords[0] && xPos <= coords[1] && yPos >= coords[2] && yPos <= coords[3]) {
					System.out.println("pressed: " + tile.getName());
					tile.setName("arse");
					break;
				}
			}
			pressedTile = true;
		}
		else {
			pressedTile = false;
		}
	}
	
	public static Tile checkHover(Input input, int xPos, int yPos) {
		for(Tile tile : Tile.getTiles()) {
			// add gamestate protection just like button?
			float coords[] = tile.getTileCoordinates();

			if(xPos >= coords[0] && xPos <= coords[1] && yPos >= coords[2] && yPos <= coords[3]) {
				return tile;
			}
		}
		return null;
	}
	
	public void draw(Graphics g) {	
		if(tileState == TileState.SQUARE) {
			g.drawString(name, x + (width / 2), y + (height / 2));
		}
		else if(tileState == TileState.HORIZONTAL) {
			g.drawString(name, x, y + (height / 2));
		}
		else if(tileState == TileState.VERTICAL) {
			g.drawString(name, x + (width / 2), y);
		}
	}
	
	public void drawTooltip(Graphics g, int xPos, int yPos) {
		//System.out.println("Drawing at: " + xPos + ", ypos: " + yPos);
		
		g.setColor(new Color (0.2f, 0.2f, 0.2f, 0.8f));
		g.fillRect(xPos + 10, yPos + 10, 200, 150);
		g.setColor(Color.white);
		
		g.drawString("Tile " + getId() + "\nStreet Name: " + getName(), xPos + 10, yPos + 10);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TileState getTileState() {
		return tileState;
	}

	public void setTileState(TileState tileState) {
		this.tileState = tileState;
	}
}

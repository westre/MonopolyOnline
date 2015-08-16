package com.westre.monopoly.menu;

import java.io.FileOutputStream;
import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import com.westre.monopoly.Game;
import com.westre.monopoly.gui.Button;
import com.westre.monopoly.interfaces.ButtonHandler;


public class OptionsMenu extends BaseMenu implements GameState {
	
	private Button nameButton, mainMenuButton;
	private TextField playerName;
	private int state;
	
	public OptionsMenu(int state) {
		super(state);
		this.state = state;
	}
	
	@Override
	public void init(GameContainer gc, final StateBasedGame sbg) throws SlickException {
		super.init(gc);
		
		playerName = new TextField(gc, gc.getDefaultFont(), gc.getWidth() / 2 - 150, gc.getHeight() / 2 - 50, 300, 20);
		playerName.setBorderColor(Color.black);
		playerName.setBackgroundColor(Color.white);
		playerName.setTextColor(Color.black);
	      
		nameButton = new Button(getID(), gc.getGraphics(), "Set Name", gc.getWidth() / 2 - 150, gc.getHeight() / 2, 300, 100, new ButtonHandler() {
			public void onClick() {
				Game.sendEventMessage("You have set your name as: " + playerName.getText());
				Game.prop.setProperty("playername", playerName.getText());
				try {
					Game.prop.store(new FileOutputStream("options.cfg"), null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});	
		
		mainMenuButton = new Button(getID(), gc.getGraphics(), "Back to Main Menu", gc.getWidth() / 2 - 150, gc.getHeight() / 2 + 150, 300, 100, new ButtonHandler() {
			public void onClick() {
				sbg.enterState(0);
			}
		});	
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {	
		super.render();
		playerName.render(gc, g);
		
		nameButton.draw();
		mainMenuButton.draw();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Game.standardise(gc);
		
		Input input = gc.getInput();
		
		int xPos = input.getAbsoluteMouseX();
		int yPos = input.getAbsoluteMouseY();
		
		Button.checkClick(input, getID(), xPos, yPos);
		
		Game.mouseText = "Mouse X: " + xPos + ", Mouse Y: " + yPos;
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame arg1)
			throws SlickException {
	}


	@Override
	public void leave(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	public int getID() {
		return state;
	}

	@Override
	public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputEnded() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputStarted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setInput(Input arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int arg0, char arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(int arg0, char arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerButtonPressed(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerButtonReleased(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerDownPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerDownReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerLeftPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerLeftReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerRightPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerRightReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerUpPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerUpReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isRenderPaused() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUpdatePaused() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pauseRender() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pauseUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRenderPaused(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUpdatePaused(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unpauseRender() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unpauseUpdate() {
		// TODO Auto-generated method stub
		
	}

}

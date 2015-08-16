package com.westre.monopoly.menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import com.westre.monopoly.Game;
import com.westre.monopoly.gui.Button;
import com.westre.monopoly.interfaces.ButtonHandler;

public class MainMenu extends BaseMenu implements GameState {
	
	// hold reference to state based game, FIND ALTERNATIVE TO THIS!!
	public static StateBasedGame sbg;
	
	private Button debugGame, startGame, options, exitGame;
	private int state;
	
	public MainMenu(int state) {
		super(state);
		this.state = state;
	}
	
	@Override
	public void init(GameContainer gc, final StateBasedGame sbg) throws SlickException {
		super.init(gc);
		
		MainMenu.sbg = sbg;
		
		debugGame = new Button(getID(), gc.getGraphics(), "Debug Game", gc.getWidth() / 2 - 600, gc.getHeight() / 2 - 150, 300, 100, new ButtonHandler() {
			public void onClick() {
				sbg.enterState(1);
				//player.sendEventMessage("Get ready to rumble!");
			}
		});	
		
		startGame = new Button(getID(), gc.getGraphics(), "Multiplayer", gc.getWidth() / 2 - 150, gc.getHeight() / 2 - 150, 300, 100, new ButtonHandler() {
			public void onClick() {
				sbg.enterState(3);
			}
		});	
		
		options = new Button(getID(), gc.getGraphics(), "Options", gc.getWidth() / 2 - 150, gc.getHeight() / 2, 300, 100, new ButtonHandler() {
			public void onClick() {
				sbg.enterState(2);
			}
		});	
		
		exitGame = new Button(getID(), gc.getGraphics(), "Exit game", gc.getWidth() / 2 - 150, gc.getHeight() / 2 + 150, 300, 100, new ButtonHandler() {
			public void onClick() {
				System.exit(0);
			}
		});

	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {	
		super.render();
		
		debugGame.draw();
		startGame.draw();
		exitGame.draw();
		options.draw();
		
		//player.updateMessageBox();
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

	@Override
	public void enter(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub

	}
	

	@Override
	public void leave(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub

	}
}

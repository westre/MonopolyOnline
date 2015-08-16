package com.westre.monopoly.menu;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import com.westre.monopoly.Game;
import com.westre.monopoly.gui.Button;
import com.westre.monopoly.interfaces.ButtonHandler;
import com.westre.multiplayer.MonopolyClient;
import com.westre.multiplayer.MonopolyServer;


public class MultiplayerMenu extends BaseMenu implements GameState {
	
	private Button joinServer, startServer, exitMenu;
	private int state;
	
	public MultiplayerMenu(int state) {
		super(state);
		this.state = state;
	}
	
	@Override
	public void init(GameContainer gc, final StateBasedGame sbg) throws SlickException {
		super.init(gc);
		
		joinServer = new Button(getID(), gc.getGraphics(), "Join a Game", gc.getWidth() / 2 - 150, gc.getHeight() / 2 - 150, 300, 100, new ButtonHandler() {
			public void onClick() {
				Game.sendEventMessage("You are attempting to join the dev server.");
				
				//NetworkClient nc = new NetworkClient();	
				
				// we create this to use client methods
				//Game.clientGateway = new Online.ClientReference(nc.getClient());
				
				// sbg.enterState(1);
				//if(nc != null) sbg.enterState(4);
				
				JFrame j = new JFrame();
				j.setAlwaysOnTop(true);
				j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				j.setVisible(true);
				j.setVisible(false);
				JOptionPane.showInputDialog(j, "Host:", "Connect to server", JOptionPane.QUESTION_MESSAGE, null, null, "localhost");
		          
				Game.localClient = new MonopolyClient();
				if(Game.localClient != null) sbg.enterState(4);
			}
		});	
		
		startServer = new Button(getID(), gc.getGraphics(), "Start Server", gc.getWidth() / 2 - 150, gc.getHeight() / 2, 300, 100, new ButtonHandler() {
			public void onClick() {
				Game.sendEventMessage("Starting server..");
				new MonopolyServer();
			}
		});	
		
		exitMenu = new Button(getID(), gc.getGraphics(), "Exit to Main Menu", gc.getWidth() / 2 - 150, gc.getHeight() / 2 + 150, 300, 100, new ButtonHandler() {
			public void onClick() {
				sbg.enterState(0);
			}
		});	
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {	
		super.render();

		joinServer.draw();
		startServer.draw();
		exitMenu.draw();
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

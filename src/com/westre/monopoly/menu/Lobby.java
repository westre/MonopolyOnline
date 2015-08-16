package com.westre.monopoly.menu;

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
import com.westre.monopoly.misc.ChatManager;
import com.westre.multiplayer.MonopolyNetwork;

public class Lobby extends BaseMenu implements GameState {
	
	private int state;
	private TextField chatBox;
	private Button startGame;
	
	public static ChatManager lobbyChatManager;
	
	public Lobby(int state) {
		super(state);
		this.state = state;
	}
	
	@Override
	public void enter(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return state;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		super.init(gc);
		
		chatBox = new TextField(gc, gc.getDefaultFont(), 20, 680, 940, 20);
		chatBox.setBorderColor(Color.black);
		chatBox.setBackgroundColor(Color.white);
		chatBox.setTextColor(Color.black);
		
		lobbyChatManager = new ChatManager(gc.getGraphics(), 20, 20, 940, 660);
		
		startGame = new Button(getID(), gc.getGraphics(), "Start game", 980, 600, 200, 100, new ButtonHandler() {
			public void onClick() {
				Game.sendEventMessage("Starting game..");
				
				// send start game packet TODO host only!!
				//Online.NetworkListener.StartGame startGame = new Online.NetworkListener.StartGame();
				//startGame.start = true;
				//Game.clientGateway.getClient().sendTCP(startGame);
				
				MonopolyNetwork.StartLobby lobbyGame = new MonopolyNetwork.StartLobby();
				lobbyGame.start = true;
				Game.localClient.getClient().sendTCP(lobbyGame);
			}
		});	
	}

	@Override
	public void leave(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		super.render();
		
		// chatbox
		//g.fillRect(20, 20, 940, 660);
		chatBox.render(gc, g);
			
		g.fillRect(980, 20, 280, 400);
		startGame.draw();
		
		lobbyChatManager.render();
		g.setColor(Color.white);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		Game.standardise(gc);
		
		Input input = gc.getInput();
		
		int xPos = input.getAbsoluteMouseX();
		int yPos = input.getAbsoluteMouseY();
		
		Button.checkClick(input, getID(), xPos, yPos);
		
		Game.mouseText = "Mouse X: " + xPos + ", Mouse Y: " + yPos;
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

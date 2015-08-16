package com.westre.monopoly;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import com.westre.monopoly.entity.Tile;
import com.westre.monopoly.entity.Tile.TileState;
import com.westre.monopoly.misc.ChatManager;
import com.westre.multiplayer.MonopolyNetwork.UpdatePlayer;
import com.westre.multiplayer.MonopolyPlayer;

public class Play implements GameState {
	
	private int state, oldX, oldY;
	private Image board, cursor;
	private String mouseText = "undefined";
	private TextField chat;
	private ChatManager chatManager;
	
	public Play(int state) {
		this.state = state;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		gc.getGraphics().setBackground(Color.gray);
		
		board = new Image("res/plainboard.png");
		cursor = new Image("res/cursor.png");
		chatManager = new ChatManager(gc.getGraphics(), 960, 100, 320, 400);
		
		/* Bottom row */
		float x = 750f;
		new Tile(0, TileState.SQUARE, "Start", 827.5f, 620f, 130, 100);
		for(int i = 1; i < 10; i++) {
			new Tile(i, TileState.HORIZONTAL, String.valueOf(i), x, 620f, 80, 100);
			x -= 77.5f;
		}
		new Tile(10, TileState.SQUARE, "In Jail/Just Visiting", 0f, 620f, 130, 100);
		
		/* Left row */
		float y = 562.5f;
		for(int i = 11; i < 20; i++) {
			new Tile(i, TileState.VERTICAL, String.valueOf(i), 0f, y, 130, 59);
			y -= 58f;
		}
		new Tile(20, TileState.SQUARE, "Free Parking", 0f, 0f, 130, 100);
		
		/* Upper row */
		x = 130f;
		for(int i = 21; i < 30; i++) {
			new Tile(i, TileState.HORIZONTAL, String.valueOf(i), x, 0f, 80, 100);
			x += 77.5f;
		}
		new Tile(30, TileState.SQUARE, "Free Parking", x, 0f, 130, 100);
		
		/* Right row */
		y = 100f;
		for(int i = 31; i < 40; i++) {
			new Tile(i, TileState.VERTICAL, String.valueOf(i), 830f, y, 130, 59);
			y += 58f;
		}
		
		chat = new TextField(gc, gc.getDefaultFont(), 960, 510, 320, 25);
		chat.setBorderColor(Color.black);
		chat.setBackgroundColor(Color.white);
		chat.setTextColor(Color.black);
		chat.addListener(new ComponentListener() {
			public void componentActivated(AbstractComponent ac) {
				if(chat.getText().length() != 0) {
					chatManager.addMessage(chat.getText());
					chat.setText("");
				}
			}
		});
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {		
		board.draw();
		
		Input input = gc.getInput();
		
		int xPos = input.getAbsoluteMouseX();
		int yPos = input.getAbsoluteMouseY();
		
		//g.setColor(Color.white);
		//g.fillRect(960, 100, 320, 400);
		//g.setColor(Color.black);
		
		chatManager.render();
			
		g.setColor(Color.white);
		chat.render(gc, g);
		
		g.setColor(Color.black);
		for(Tile tile : Tile.getTiles()) {
			tile.draw(g);
		}
		
		if(Tile.checkHover(input, xPos, yPos) != null) {
			Tile tile = Tile.checkHover(input, xPos, yPos);
			tile.drawTooltip(g, xPos, yPos);
		}
				
		g.drawString(mouseText, gc.getWidth() / 2, gc.getHeight() / 2);
		
		
		
		/*counter++;
		if(counter == 10) {
			for(MonopolyPlayer client : Game.localClient.playerMap.values()) {
			    g.fillRect(client.x, client.y, 50, 50);
				System.out.println("Drawing: ID: " + client.id + "X: " + client.x + "Y: " + client.y);
			}
			counter = 0;
		}*/
		
		for(MonopolyPlayer client : Game.localClient.players) {
			if(client.id != Game.localClient.getClient().getID()) {
				//g.fillRect(client.x, client.y, 50, 50);
				//g.fillOval(50, 50, 100, 100);
				g.drawImage(cursor, client.x, client.y);
				g.drawString(client.name, client.x + 10, client.y);
			}
		}
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Game.standardise(gc);

		Input input = gc.getInput();
		
		int xPos = input.getAbsoluteMouseX();
		int yPos = input.getAbsoluteMouseY();
		
		Tile.checkClick(input, xPos, yPos);
		
		if(Tile.checkHover(input, xPos, yPos) != null) {
			//Tile tile = Tile.checkHover(input, xPos, yPos);
			//tile.setName("lol");
			//System.out.println("valid");
		}
		
		mouseText = "Mouse X: " + xPos + ", Mouse Y: " + yPos;
		
		if(oldX != xPos || oldY != yPos) {
			UpdatePlayer playerCursorLocation = new UpdatePlayer();
			playerCursorLocation.id = Game.localClient.getClient().getID();
			playerCursorLocation.x = xPos;
			playerCursorLocation.y = yPos;
			Game.localClient.getClient().sendTCP(playerCursorLocation);
			
			oldX = xPos;
			oldY = yPos;
			System.out.println("Sent Player ID: " + playerCursorLocation.id + " with X as : " + playerCursorLocation.x + " and Y as: " + playerCursorLocation.y);
		}	
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

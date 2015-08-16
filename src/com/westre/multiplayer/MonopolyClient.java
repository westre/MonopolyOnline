package com.westre.multiplayer;

import java.io.IOException;
import java.util.ArrayList;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.westre.monopoly.Game;
import com.westre.monopoly.menu.MainMenu;
import com.westre.multiplayer.MonopolyNetwork.AddPlayer;
import com.westre.multiplayer.MonopolyNetwork.RemovePlayer;
import com.westre.multiplayer.MonopolyNetwork.StartLobby;
import com.westre.multiplayer.MonopolyNetwork.UpdatePlayer;
import com.westre.multiplayer.MonopolyServer.ServerState;

public class MonopolyClient {
	
	private Client client;
	
    public ArrayList<MonopolyPlayer> players = new ArrayList<MonopolyPlayer>(50);
    
	public MonopolyClient() {
		client = new Client();
        client.start();

        // For consistency, the classes to be sent over the network are
        // registered by the same method for both the client and server.
        MonopolyNetwork.register(client);

        // ThreadedListener runs the listener methods on a different thread.
        client.addListener(new Listener() {
        	public void connected (Connection connection) {
        		
        	}

        	public void received (Connection connection, Object object) {
        		if (object instanceof AddPlayer) {        			
        			AddPlayer msg = (AddPlayer) object;
        			players.add(msg.player);
        			return;
        		}

        		if (object instanceof UpdatePlayer) {
        			UpdatePlayer msg = (UpdatePlayer) object;
        			
        			for(MonopolyPlayer player : players) {
        				if(player.id == msg.id) { // hashmap for id is a good idea but rect (Play.java) flickers?	
        					player.x = msg.x;
        					player.y = msg.y;
        				}
        			}
        			//System.out.println(character.name + " moved to " + character.x + ", " + character.y);
        			return;
        		}

        		if (object instanceof RemovePlayer) {
        			for(MonopolyPlayer mp : players) {
        				System.out.println("Active P: " + mp.id );
        			}
        			
        			RemovePlayer msg = (RemovePlayer) object;
        			System.out.println("Wants to remove: " + msg.id);
        			players.remove(msg.id - 1); // yup...
        			return;
        		}
        		
        		if(object instanceof StartLobby) {
					StartLobby startGame = (StartLobby) object;
					if(startGame.start) {
						MonopolyServer.status = ServerState.IN_SESSION;
						MainMenu.sbg.enterState(1);
					}
				}
        	}

        	public void disconnected (Connection connection) {
        		System.exit(0);
        	}
        });
        
    	client.start();
		
		try {
			client.connect(5000, "127.0.0.1", 54555);
			Game.sendEventMessage("You have connected!");
			
		} catch (IOException e) {
			Game.sendEventMessage("ERROR: " + e.getMessage());
		}
		
	}
	
	public Client getClient() {
		return client;
	}
}

package com.westre.multiplayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.westre.monopoly.Game;
import com.westre.monopoly.server.GUIServer;
import com.westre.multiplayer.MonopolyNetwork.AddPlayer;
import com.westre.multiplayer.MonopolyNetwork.RemovePlayer;
import com.westre.multiplayer.MonopolyNetwork.StartLobby;
import com.westre.multiplayer.MonopolyNetwork.UpdatePlayer;

public class MonopolyServer {
	private Server server;
	private GUIServer gui;
	
    public static ArrayList<MonopolyPlayer> onlinePlayers = new ArrayList<MonopolyPlayer>();
    
    public enum ServerState {
		LOBBY, IN_SESSION, END;
	}
    public static ServerState status = ServerState.LOBBY; 
    
    public MonopolyServer() {
		gui = new GUIServer(MonopolyNetwork.port);
		
		server = new Server() {
			protected Connection newConnection () {
				return new ServerPlayerConnection();
			}
		};
    
		MonopolyNetwork.register(server);
		
		server.addListener(new Listener() {
			
			public void connected (Connection connection) {
				Game.localPlayer = new MonopolyPlayer();
				Game.localPlayer.id = connection.getID();
				Game.localPlayer.name = "Player: " + connection.getID();
				
				gui.sendMessage(Game.localPlayer.name + " has joined the server with ID: " + Game.localPlayer.id);
				gui.addPlayerName(Game.localPlayer.name);
				
				// Reject if already logged in.
				for(MonopolyPlayer activePlayer : onlinePlayers) {
					if(activePlayer.name.equals(Game.localPlayer.name)) {
						connection.close();
						return;
					}
				}
				
				// Add existing players to the joined in player. [GETS ALL ACTIVE PLAYERS -> PUTS IT IN LOCAL PLAYER's MAP]
                for(MonopolyPlayer activePlayers : onlinePlayers) {
                	// one by one add the existing player and add it through the AddPlayer object, in the client it processes it and puts it in the player's map
                    AddPlayer addPlayer = new AddPlayer();
                    addPlayer.player = activePlayers;
                    connection.sendTCP(addPlayer);
                }

                // Add the logged in player to the servers' map [GETS NEWLY JOINED IN PLAYER -> PUTS IT IN SERVER'S MAP]
                onlinePlayers.add(Game.localPlayer);

                // Add logged in character to all connections. [GETS THE NEW JOINED PLAYER -> PUT THE NEWLY JOINED PLAYER IN ALL THE EXISTING PLAYER'S MAP]
                AddPlayer addPlayer = new AddPlayer();
                addPlayer.player = Game.localPlayer;
                server.sendToAllTCP(addPlayer);
				return;
			}
			
			public void received (Connection c, Object object) {
				// We know all connections for this server are actually ServerPlayerConnections. Make it a MonopolyPlayer for temporary editing
				//ServerPlayerConnection connection = (ServerPlayerConnection) c;
				//MonopolyPlayer player = connection.player;
				
				// workds
				if (object instanceof UpdatePlayer) {
					UpdatePlayer msg = (UpdatePlayer) object;

					server.sendToAllTCP(msg);
				}
				
				if(object instanceof StartLobby) {
					StartLobby startGame = (StartLobby) object;

					MonopolyServer.status = ServerState.IN_SESSION;
					server.sendToAllTCP(startGame);
				}
			}
			
			public void disconnected (Connection c) {
				Iterator<MonopolyPlayer> players = onlinePlayers.iterator();
				while(players.hasNext()) {
					MonopolyPlayer player = players.next();
					if(player.id == c.getID()) {
						System.out.println("Someone disconnected: " + player.name);
						
						gui.removePlayerName(player.name);
						
						players.remove();
						
						RemovePlayer removePlayer = new RemovePlayer();
						removePlayer.id = player.id;
						server.sendToAllTCP(removePlayer);
					}
				}
			}
		});
		
		try {	
			server.bind(MonopolyNetwork.port);
			server.start();
			System.out.println("Server started");
		} catch (IOException e) {
			e.printStackTrace();
		}	
    }
    
    public MonopolyPlayer getPlayer (int id) {
		for(MonopolyPlayer player : onlinePlayers)
			if(player.id == id) return player;
		return null;
	}
    
    // All connections are ServerPlayerConnection, we moeten dit doen met een omweg anders komen we niet bij MonopolyPlayer
    static class ServerPlayerConnection extends Connection {
        public MonopolyPlayer player;
    }
}

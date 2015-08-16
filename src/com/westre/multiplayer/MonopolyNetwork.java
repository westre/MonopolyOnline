package com.westre.multiplayer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class MonopolyNetwork {
	static public final int port = 54555;

	// This registers objects that are going to be sent over the network.
	static public void register(EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		kryo.register(AddPlayer.class);
		kryo.register(UpdatePlayer.class);
		kryo.register(RemovePlayer.class);
		kryo.register(MonopolyPlayer.class);
		kryo.register(MovePlayer.class);
		kryo.register(StartLobby.class);
	}
	
	static public class UpdatePlayer {
		public int id, x, y;
	}

	static public class AddPlayer {
		public MonopolyPlayer player;
	}

	static public class RemovePlayer {
		public int id;
	}

	static public class MovePlayer {
		public int x, y;
	}
	
	static public class StartLobby {
		public boolean start;
	}
}

package gl22_hl57.game_server.model;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.UUID;
import common.IChatServer;
import common.IChatroom;
import common.OurDataPacket;
import common.msg.IChatMsg;

/**
 * Chat room
 * @author Gengwu Li, Henry Lin
 * @version 1.0, Dec 9, 2016
 */
public class ChatRoom implements IChatroom {

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = -6713318093867654346L;
	
	/**
	 * Room name
	 */
	String name;
	
	/**
	 * room unique id
	 */
	UUID id;
	
	/**
	 * Chatservers in the chatroom
	 */
	HashSet<IChatServer> chatservers;
	
	/**
	 * User ids to remove duplicates
	 */
	HashSet<UUID> userIds;

	/**
	 * Constructor
	 * @param name Room name
	 */
	public ChatRoom(String name) {
		this.name = name;
		this.id = UUID.randomUUID();
		chatservers = new HashSet<>();
		userIds = new HashSet<>();
	}

	/**
	 * Get room id
	 */
	@Override
	public UUID getId() {
		return id;
	}

	/**
	 * Get room name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Get chat servers
	 */
	@Override
	public HashSet<IChatServer> getChatServers() {
		return chatservers;
	}

	/**
	 * Add chatserver to chatroom
	 */
	@Override
	public boolean addChatServer(IChatServer chatStub) {
		UUID uuid = null;
		try {
			uuid = chatStub.getUser().getId();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (uuid==null || userIds.contains(uuid)) {
			return false;
		}
		userIds.add(uuid);
		return chatservers.add(chatStub);
	}

	/**
	 * Remove chatserver
	 */
	@Override
	public boolean removeChatServer(IChatServer chatServer) {
		System.out.println("In Chatroom, removeChatServer()");
		UUID uuid = null;
		try {
			uuid = chatServer.getUser().getId();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (uuid==null || !userIds.contains(uuid)) {
			return false;
		}
		userIds.remove(uuid);
		return chatservers.remove(chatServer);
	}

	@Override
	public <S> void send(OurDataPacket<? extends IChatMsg, S> dp) {
		try {
			for (IChatServer chatServer : chatservers) {
				chatServer.receive(dp);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}

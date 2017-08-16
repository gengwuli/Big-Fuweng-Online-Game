package gl22_hl57.game_server.model;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.UUID;
import common.IChatServer;
import common.IChatroom;
import common.IUser;
import common.OurDataPacket;
import common.msg.IUserMsg;
import common.msg.chat.IAddMeMsg;
import common.msg.user.IInvite2RoomMsg;
import gl22_hl57.game_server.model.adpt.IUser2ModelAdpt;
import gl22_hl57.game_server.model.msg.chat.AddMeMsg;

/**
 * User
 * @author Gengwu Li, Henry Lin
 * @version 1.0, Dec 9, 2016
 */
public class User implements IUser {
	/**
	 * User name 
	 */
	String username;
	
	/**
	 * Unique id
	 */
	UUID id;
	
	/**
	 * Ip
	 */
	String ip;
	
	/**
	 * Chat rooms
	 */
	HashSet<IChatroom> chatrooms;
	
	/**
	 * User to model adapter
	 */
	IUser2ModelAdpt user2ModelAdpt;
	
	/**
	 * Constructor
	 * @param username User name
	 * @param ip Ip
	 * @param user2ModelAdpt User to model adapter
	 */
	public User(String username, String ip, IUser2ModelAdpt user2ModelAdpt) {
		this.username = username;
		this.ip = ip;
		id = UUID.randomUUID();
		this.user2ModelAdpt = user2ModelAdpt;
		chatrooms = new HashSet<>();
	}
	
	/**
	 * Receive a data packet
	 */
	@Override
	public <S> void receive(OurDataPacket<? extends IUserMsg, S> dp) throws RemoteException {
		IInvite2RoomMsg invite2RoomMsg = (IInvite2RoomMsg) dp.getData();
		IChatroom chatroom = invite2RoomMsg.getChatroom();
		ChatServer chatServer = new ChatServer(this, chatroom);
		user2ModelAdpt.createMiniMVC(chatServer);
		OurDataPacket<IAddMeMsg, IChatServer> dataPacket = 
				new OurDataPacket<IAddMeMsg, IChatServer>(IAddMeMsg.class, new AddMeMsg(), chatServer);
		chatroom.send(dataPacket);
	}

	/**
	 * Auto connect back, not used in this project
	 */
	@Override
	public void connectBack(IUser userStub) throws RemoteException {
		System.out.println(userStub.getName() + "@" + userStub.getIP());
	}

	/**
	 * Get user id
	 */
	@Override
	public UUID getId() throws RemoteException {
		return id;
	}

	/**
	 * Get user name
	 */
	@Override
	public String getName() throws RemoteException {
		return username;
	}

	/**
	 * Get ip
	 */
	@Override
	public String getIP() throws RemoteException {
		return ip;
	}

	/**
	 * Get chat rooms
	 */
	@Override
	public HashSet<IChatroom> getChatrooms() throws RemoteException {
		return chatrooms;
	}
	
	/**
	 * Add room
	 * @param Room to add
	 * @return Whether that room is added correctly
	 */
	public boolean addRoom(IChatroom room) {
		return chatrooms.add(room);
	}
	
}

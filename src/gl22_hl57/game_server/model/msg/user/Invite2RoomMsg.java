package gl22_hl57.game_server.model.msg.user;

import common.IChatroom;
import common.msg.user.IInvite2RoomMsg;

/**
 * Invite to room message
 * @author Gengwu Li, Henry Lin
 * @version 1.0, Dec 9, 2016
 */
public class Invite2RoomMsg implements IInvite2RoomMsg {

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 8561323740954386313L;

	/**
	 * Chatroom where to invite the chatserver in
	 */
	IChatroom chatroom;
	
	/**
	 * Constructor
	 * @param chatroom Chatroom to inject
	 */
	public Invite2RoomMsg(IChatroom chatroom) {
		this.chatroom = chatroom;
	}
	
	/**
	 * Get chatroom
	 */
	@Override
	public IChatroom getChatroom() {
		return chatroom;
	}

}

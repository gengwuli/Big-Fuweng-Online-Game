package gl22_hl57.game_server.model.msg.user;

import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.IChatroom;
import common.ICmd2ModelAdapter;
import common.IUser;
import common.OurDataPacket;
import common.msg.chat.IAddMeMsg;
import common.msg.user.IInvite2RoomMsg;
import gl22_hl57.game_server.model.msg.chat.AddMeMsg;

/**
 * Invite to room command
 * @author Gengwu Li, Henry Lin
 * @version 1.0, Dec 9, 2016
 */
public class Invite2RoomCmd extends AOurDataPacketAlgoCmd<IInvite2RoomMsg, IUser> {

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 1713394399428082849L;

	/**
	 * Command to model adapter
	 */
	transient ICmd2ModelAdapter cmd2ModelAdpter;
	
	/**
	 * Constructor
	 * @param cmd2ModelAdpter Command to model adapter to inject
	 */
	public Invite2RoomCmd(ICmd2ModelAdapter cmd2ModelAdpter) {
		this.cmd2ModelAdpter = cmd2ModelAdpter;
	}
	
	/**
	 * Command execute
	 */
	@Override
	public Void apply(Class<?> index, OurDataPacket<IInvite2RoomMsg, IUser> host, Object... params) {
		IInvite2RoomMsg invite2RoomMsg = host.getData();
		IChatroom chatroom = invite2RoomMsg.getChatroom();
		OurDataPacket<IAddMeMsg, IChatServer> dataPacket = 
				new OurDataPacket<IAddMeMsg, IChatServer>(IAddMeMsg.class, new AddMeMsg(), cmd2ModelAdpter.getChatServer());
		chatroom.send(dataPacket);
		return null;
	}

	/**
	 * Command to model adapter setter
	 */
	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpter) {
		this.cmd2ModelAdpter = cmd2ModelAdpter;
	}
}

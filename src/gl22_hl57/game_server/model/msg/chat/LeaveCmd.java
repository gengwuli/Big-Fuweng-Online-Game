package gl22_hl57.game_server.model.msg.chat;

import java.rmi.RemoteException;
import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.ICmd2ModelAdapter;
import common.OurDataPacket;
import common.msg.chat.ILeaveMsg;
import gl22_hl57.game_server.model.ChatServer;

/**
 * Leave command
 * @author Gengwu Li, Henry Lin
 * @version 1.0, Dec 9, 2016
 */
public class LeaveCmd extends AOurDataPacketAlgoCmd<ILeaveMsg, IChatServer> {

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 9033331815239246609L;

	/**
	 * Command to model adapter, no need to serialize
	 */
	transient ICmd2ModelAdapter cmd2ModelAdpter;
	
	/**
	 * Constructor
	 * @param cmd2ModelAdapter The command to model adapter to inject
	 */
	public LeaveCmd(ICmd2ModelAdapter cmd2ModelAdapter) {
		this.cmd2ModelAdpter = cmd2ModelAdapter;
	}
	
	/**
	 * Command execute
	 */
	@Override
	public Void apply(Class<?> index, OurDataPacket<ILeaveMsg, IChatServer> host, Object... params) {
		try {
			ChatServer chatServer = (ChatServer) cmd2ModelAdpter.getChatServer();
			if (chatServer.getUser().getId().equals(host.getSender().getUser().getId())) {
				return null;
			}
			chatServer.getChatroom().removeChatServer(host.getSender());
			chatServer.refresh();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Command to model adapter settor
	 */
	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpter = cmd2ModelAdpt;
	}

}

package gl22_hl57.game_server.model.msg.chat;

import java.rmi.RemoteException;
import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.ICmd2ModelAdapter;
import common.OurDataPacket;
import common.msg.chat.IAddMeMsg;
import gl22_hl57.game_server.model.ChatServer;

/**
 * Add me command 
 * @author Gengwu Li, Henry Lin
 * @version 1.0, Dec 9, 2016
 */
public class AddMeCmd extends AOurDataPacketAlgoCmd<IAddMeMsg, IChatServer> {

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 7839777114272693152L;

	/**
	 * Must be transient so that this object won't get serialized when transferring
	 * over the internet
	 */
	transient ICmd2ModelAdapter cmd2ModelAdpter;
	
	/**
	 * Constructor
	 * @param cmd2ModelAdpter The command to model adapter to inject
	 */
	public AddMeCmd(ICmd2ModelAdapter cmd2ModelAdpter) {
		this.cmd2ModelAdpter = cmd2ModelAdpter;
	}
	
	/**
	 * Excute the command
	 */
	@Override
	public Void apply(Class<?> index, OurDataPacket<IAddMeMsg, IChatServer> host, Object... params) {
		IChatServer sender = host.getSender();
		try {
			// Add to room
			cmd2ModelAdpter.getChatServer().getChatroom().addChatServer(sender);
			// refresh the view
			ChatServer chatServer = (ChatServer) cmd2ModelAdpter.getChatServer();
			chatServer.refresh();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Set command to model adapter
	 */
	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpter = cmd2ModelAdpt;
	}
}

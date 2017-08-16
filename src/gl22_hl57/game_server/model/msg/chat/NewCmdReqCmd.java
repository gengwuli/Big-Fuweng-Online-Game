package gl22_hl57.game_server.model.msg.chat;

import java.rmi.RemoteException;
import java.util.UUID;

import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.ICmd2ModelAdapter;
import common.OurDataPacket;
import common.msg.chat.IAddCmdMsg;
import common.msg.chat.INewCmdReqMsg;
import gl22_hl57.game_server.model.ChatServer;
import provided.datapacket.DataPacketAlgo;

/**
 * New command request command
 * @author Gengwu Li, Henry Lin
 * @version 1.0, Dec 9, 2016
 */
public class NewCmdReqCmd extends AOurDataPacketAlgoCmd<INewCmdReqMsg, IChatServer> {

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 4196432435893133847L;
	
	/**
	 * Command to model adapter, no need to serialize
	 */
	transient ICmd2ModelAdapter cmd2ModelAdpter;
	
	/**
	 * Constructor
	 * @param cmd2ModelAdpter The command to model adapter
	 */
	public NewCmdReqCmd(ICmd2ModelAdapter cmd2ModelAdpter) {
		this.cmd2ModelAdpter = cmd2ModelAdpter;
	}
	
	/**
	 * Command execute
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Void apply(Class<?> index, OurDataPacket<INewCmdReqMsg, IChatServer> host, Object... params) {
		// Generate in remote chatserver as authentication
		UUID uuid = host.getData().getUUID();
		ChatServer chatServer = (ChatServer) cmd2ModelAdpter.getChatServer();
		DataPacketAlgo<Void, Object> algo = chatServer.getAlgo();
		System.out.println("Request command for: " + host.getData().getReqClassIdx());
		
		// Get the command and send back to requester
		AOurDataPacketAlgoCmd<?, IChatServer> cmd = (AOurDataPacketAlgoCmd<?, IChatServer>) algo.getCmd(host.getData().getReqClassIdx());
		IAddCmdMsg addCmdMsg = new AddCmdMsg(cmd, host.getData().getReqClassIdx(), uuid);
		
		// Marshall command and send back
		OurDataPacket<IAddCmdMsg, IChatServer> dataPacket = new OurDataPacket<IAddCmdMsg, IChatServer>(IAddCmdMsg.class, addCmdMsg, cmd2ModelAdpter.getChatServer());
		try {
			host.getSender().receive(dataPacket);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Command to model adapter setter
	 * @param cmd2ModelAdpt Command to model adapter
	 */
	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpter = cmd2ModelAdpt;
	}

}

package gl22_hl57.game_server.model.msg.chat;

import java.util.UUID;
import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.ICmd2ModelAdapter;
import common.OurDataPacket;
import common.msg.chat.IAddCmdMsg;
import gl22_hl57.game_server.model.ChatServer;
import provided.datapacket.DataPacketAlgo;
import provided.mixedData.MixedDataKey;

/**
 * AddCmd command corresponding to AddCmd message
 * @author Gengwu Li, Henry Lin
 * @version 1.0, Dec 9, 2016
 */
public class AddCmdCmd extends AOurDataPacketAlgoCmd<IAddCmdMsg, IChatServer> {

	/**
	 * Serial unique ID for transmiting in the internet
	 */
	private static final long serialVersionUID = 5357639501822296655L;

	/**
	 * Command to model adapter
	 */
	transient ICmd2ModelAdapter cmd2ModelAdapter;

	/**
	 * Constructor
	 * @param cmd2ModelAdapter Command to model adapter
	 */
	public AddCmdCmd(ICmd2ModelAdapter cmd2ModelAdapter) {
		this.cmd2ModelAdapter = cmd2ModelAdapter;
	}

	@Override
	public Void apply(Class<?> index, OurDataPacket<IAddCmdMsg, IChatServer> host, Object... params) {
		// Get the algorithm 
		ChatServer chatServer = (ChatServer) cmd2ModelAdapter.getChatServer();
		DataPacketAlgo<Void, Object> algo = chatServer.getAlgo();
		
		IAddCmdMsg addCmdMsg = host.getData();
		AOurDataPacketAlgoCmd<?, IChatServer> cmd = addCmdMsg.getCmd();
		// Inject my local command to model adapter
		cmd.setCmd2ModelAdpt(cmd2ModelAdapter);
		
		//Save the command to my algorithm
		algo.setCmd(addCmdMsg.getClassIdx(), cmd);
		
		// UUID for retrieving the before unkown data packet
		UUID uuid = addCmdMsg.getUUID();
		MixedDataKey<Object> key = new MixedDataKey<>(uuid, "", Object.class);
		@SuppressWarnings("unchecked")
		OurDataPacket<?, IChatServer> dataPacket = (OurDataPacket<?, IChatServer>) cmd2ModelAdapter
				.getFromLocalDict(key);
		
		// In case we get a null datapacket
		if (dataPacket == null) {
			System.out.println("Error: null datapacket in AddCmdCmd!");
		} else {
			dataPacket.execute(algo);
		}
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdapter = cmd2ModelAdpt;
	}
}

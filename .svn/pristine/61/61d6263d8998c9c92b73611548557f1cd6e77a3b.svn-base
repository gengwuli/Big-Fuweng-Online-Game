package gl22_hl57.game_server.model.msg.chat;

import java.util.UUID;

import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.msg.chat.IAddCmdMsg;

/**
 * Add command message
 * 
 * @author Gengwu Li, Henry Lin
 * @version 1.0, Dec 9, 2016
 */
public class AddCmdMsg implements IAddCmdMsg {

	/**
	 * Serial unique ID for serializing object and transfer over the network
	 */
	private static final long serialVersionUID = -8409920110029165259L;

	/**
	 * Command you need the chatserver to add
	 */
	AOurDataPacketAlgoCmd<?, IChatServer> cmd;

	/**
	 * Class type
	 */
	Class<?> clazz;

	/**
	 * Unique id
	 */
	UUID uuid;

	/**
	 * Constructor
	 * 
	 * @param cmd
	 *            Command to add
	 * @param clazz
	 *            Class type
	 * @param uuid
	 *            Unique identification
	 */
	public AddCmdMsg(AOurDataPacketAlgoCmd<?, IChatServer> cmd, Class<?> clazz, UUID uuid) {
		this.cmd = cmd;
		this.clazz = clazz;
		this.uuid = uuid;
	}

	/**
	 * Command gettor
	 */
	@Override
	public AOurDataPacketAlgoCmd<?, IChatServer> getCmd() {
		return cmd;
	}

	/**
	 * Class type gettor
	 */
	@Override
	public Class<?> getClassIdx() {
		return clazz;
	}

	/**
	 * UUID gettor
	 */
	@Override
	public UUID getUUID() {
		return uuid;
	}

}

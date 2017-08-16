package gl22_hl57.game_server.model.msg.chat;

import java.util.UUID;

import common.msg.chat.INewCmdReqMsg;

/**
 * New command request message
 * @author Gengwu Li, Henry Lin
 * @version 1.0, Dec 9, 2016
 */
public class NewCmdReqMsg implements INewCmdReqMsg {

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = -1784694132827054623L;
	
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
	 * @param clazz Class type
	 * @param uuid Unique id
	 */
	public NewCmdReqMsg(Class<?> clazz, UUID uuid) {
		this.clazz = clazz;
		this.uuid = uuid;
	}
	
	/**
	 * Class index requester
	 */
	@Override
	public Class<?> getReqClassIdx() {
		return this.clazz;
	}

	/**
	 * UUID getter
	 */
	@Override
	public UUID getUUID() {
		return this.uuid;
	}

}

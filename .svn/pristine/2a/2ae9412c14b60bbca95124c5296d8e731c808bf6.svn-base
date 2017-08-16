package gl22_hl57.game_client.model.msg;

import java.util.UUID;
import common.msg.IChatMsg;

/**
 * An abstract game message class
 * @author Gengwu Li, Henry Lin
 */
public abstract class AGameMsg implements IChatMsg {

	/**
	 * Generated serial version id
	 */
	private static final long serialVersionUID = 7204739603970559817L;

	/**
	 * UUID of the game
	 */
	private UUID gameId;
	
	/**
	 * Construct of AGameMsg, given UUID of the game
	 * @param gameUUID UUID of the game
	 */
	public AGameMsg(UUID gameUUID) {
		gameId = gameUUID;
	}
	
	/**
	 * Getter of UUID of the game
	 * @return UUID of the game
	 */
	public UUID getGameId() {
		return gameId;
	}

}

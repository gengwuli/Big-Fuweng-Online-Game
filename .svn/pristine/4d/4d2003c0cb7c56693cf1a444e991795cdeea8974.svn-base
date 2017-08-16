package gl22_hl57.game_server.model.msg.chat;

import java.util.UUID;
import gl22_hl57.game_client.model.msg.AGameMsg;

/**
 * Dice message
 * @author Gengwu Li, Henry Lin
 * @version 1.0, Dec 9, 2016
 */
public class DiceMsg extends AGameMsg {
	/**
	 * Serial number
	 */
	private static final long serialVersionUID = -3343768810980848417L;
	
//	/**
//	 * Dice uuid for fetching the adapter in the chatserver mixed dictionary
//	 */
//	UUID gameId;
	
	/**
	 * Two dices to show
	 */
	String[] imageIcons;
	
	/**
	 * How many steps couted by the dice
	 */
	int steps;

	/**
	 * Constructor
	 * @param gameUUID uuid of game
	 * @param steps	Steps to go
	 * @param imageIcons Image icons
	 */
	public DiceMsg(UUID gameUUID, int steps, String... imageIcons) {
		super(gameUUID);
		this.imageIcons = imageIcons;
		this.steps = steps;
	}

//	/**
//	 * Game id gettor
//	 * @return The game ID
//	 */
//	public UUID getGameId() {
//		return gameId;
//	}

	/**
	 * Steps gettor
	 * @return The steps indicated by two dices
	 */
	public int getSteps() {
		return steps;
	}

	/**
	 * Get image urls
	 * @return The image urls
	 */
	public String[] getContent() {
		return imageIcons;
	}
}
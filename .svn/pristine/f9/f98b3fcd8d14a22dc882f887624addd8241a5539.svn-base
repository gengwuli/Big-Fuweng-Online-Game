package gl22_hl57.game_client.model.msg;

import java.util.UUID;

/**
 * A game message which is used to notify if a team/player won the game
 * @author Gengwu Li, Henry Lin
 */
public class EndGameMsg extends AGameMsg {

	/**
	 * Generated serial version id
	 */
	private static final long serialVersionUID = -6146086028148518079L;

	/**
	 * Boolean indicator to show if a player win the game
	 */
	private boolean won;
		
	/**
	 * Construct of EndGameMsg message
	 * @param gameUUID uuid of the game
	 * @param isWinner true if the player won
	 */
	public EndGameMsg(UUID gameUUID, boolean isWinner) {
		super(gameUUID);
		won = isWinner;
	}
		
	/**
	 * Check if the player who receives this won the game
	 * @return the boolean indicator to show if a player win the game
	 */
	public boolean didWin() {
		return won;
	}
}

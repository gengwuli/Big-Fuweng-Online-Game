package gl22_hl57.game_client.model.msg;

import java.util.UUID;

/**
 * A game message which contains update info of a city in the game
 * @author Gengwu Li, Henry Lin
 */
public class CityUpdateInfo extends AGameMsg {

	/**
	 * Generated serial version id
	 */
	private static final long serialVersionUID = -3732148905819291372L;

	/**
	 * Index of updating city
	 */
	private int cityIdx;
	
	/**
	 * UUID of Owner(Avatar/IChatServer)
	 */
	private UUID ownerId;
	
	/**
	 * Constructor of CityUpdateInfo message
	 * @param gameId UUID of the game
	 * @param updatedCityIdx index of updating city
	 * @param newOwnerId UUID of Owner(Avatar/IChatServer)
	 */
	public CityUpdateInfo(UUID gameId, int updatedCityIdx, UUID newOwnerId) {
		super(gameId);
		cityIdx = updatedCityIdx;
		ownerId = newOwnerId;
	}

	/**
	 * Getter of Index of updating city
	 * @return Index of updating city
	 */
	public int getCityIdx() {
		return cityIdx;
	}

	/**
	 * Getter of UUID of Owner(Avatar/IChatServer)
	 * @return UUID of Owner(Avatar/IChatServer)
	 */
	public UUID getOwnerId() {
		return ownerId;
	}
}
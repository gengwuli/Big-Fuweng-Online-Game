package gl22_hl57.game_client.model.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import common.IChatServer;
import gl22_hl57.game_client.model.CityData;
import gl22_hl57.game_client.model.Team;

/**
 * A game message which contains init game data
 * @author Gengwu Li, Henry Lin
 */
public class GameInfo extends AGameMsg {

	/**
	 * Generated serial version id
	 */
	private static final long serialVersionUID = 6660449293455937998L;
	
	/**
	 * List of IChatServers in the game
	 */
	private List<IChatServer> players = new ArrayList<>();
	
	/**
	 * List of Teams in the game
	 */
	private List<Team> teams = new ArrayList<>();
	
	/**
	 * List of the city data
	 */
	private List<CityData> cities = new ArrayList<>();
		
	/**
	 * Constructor of GameInfo
	 * @param gameUUID uuid of the game
	 * @param gamePlayers List of IChatServers in the game
	 * @param gameTeams List of Teams in the game
	 * @param cities List of the city data in the game
	 */
	public GameInfo(UUID gameUUID, List<IChatServer> gamePlayers, List<Team> gameTeams, List<CityData> cities) {
		super(gameUUID);
		players.addAll(gamePlayers);
		teams.addAll(gameTeams);
		this.cities = cities;
	}
	
	/**
	 * Getter of list of city data
	 * @return List of the cities in the game
	 */
	public List<CityData> getCities() {
		return cities;
	}
	
	/**
	 * Getter of list of IChatServers in the game
	 * @return List of IChatServers in the game
	 */
	public IChatServer[] getPlayers() {
		return players.toArray(new IChatServer[0]);
	}
	
	/**
	 * Getter of list of Teams in the game
	 * @return List of Teams in the game
	 */
	public Team[] getTeams() {
		return teams.toArray(new Team[0]);
	}
	
}

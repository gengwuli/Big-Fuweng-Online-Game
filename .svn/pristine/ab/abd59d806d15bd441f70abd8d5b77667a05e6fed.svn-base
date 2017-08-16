package gl22_hl57.game_client.model;

import common.IChatServer;
import gl22_hl57.game_client.model.msg.AvatarUpdateInfo;
import gl22_hl57.game_client.model.msg.CityUpdateInfo;

/**
 * An adapter which enables IChatserver talk to game
 * @author Gengwu Li, Henry Lin
 * @version 1.0, Dec 9, 2016
 */
public interface IPlayer2GameAdapter {
		
	/**
	 * Update an avatar 
	 * @param info avatar update info
	 */
	public void updateAvatar(AvatarUpdateInfo info);
	
	/**
	 * Update a city
	 * @param info city update info
	 */
	public void updateCity(CityUpdateInfo info);
	
	/**
	 * 
	 */
	public void startTurn();
	
	public void end(boolean didWin);
	
	public void finishRollingAnimation(IChatServer sender, int steps);
}

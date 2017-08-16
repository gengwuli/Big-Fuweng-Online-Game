package gl22_hl57.game_client.view;

import java.util.List;
import gov.nasa.worldwind.render.PointPlacemark;

/**
 * An adapter which enables game view talk to game model
 * @author Gengwu Li, Henry Lin
 * @version 1.0, Dec 9, 2016
 * @param <TAvatarListItem> avatar list item
 */
public interface IGameView2ModelAdapter<TAvatarListItem> {
	
	/**
	 * get all city markers from model
	 * @return all city markers from model
	 */
	public List<? extends PointPlacemark> getCityMarkers();
	
	/**
	 * get all avatar markers from view
	 * @return all avatar markers from view
	 */
	public List<? extends PointPlacemark> getAvatarMarkers();
	
	/**
	 * Move my avatar by stepNum steps
	 * @param stepNum number of steps to be moved
	 */
	public void moveAvatars(int stepNum);
	
	/**
	 * Check the status of a city where my avatar is in
	 */
	public void checkCityStatus();
	
	/**
	 * Buy the city where my avatar is in
	 */
	public void buyTheCity();
	
	/**
	 * Get all my teammates
	 * @return
	 */
	public TAvatarListItem[] getTeammates();
	
	/**
	 * Finish my turn 
	 */
	public void finishedTurn();
	
	/**
	 * Share my money with a teammate
	 * @param aTeammate a teammate to share money with
	 * @param amountShared the amount of the money to share
	 */
	public void shareMoney(TAvatarListItem aTeammate, int amountShared);

	/**
	 * Show dice animation
	 * @param dices faces of dices we just rolled 
	 */
	public void showDice(int... dices);
}

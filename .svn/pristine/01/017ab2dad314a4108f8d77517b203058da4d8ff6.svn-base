package gl22_hl57.game_client.model;

import gov.nasa.worldwind.geom.Position;

/**
 * An adapter which enables game model talk to game view
 * @author Gengwu Li, Henry Lin
 * @version 1.0, Dec 9, 2016
 */
public interface IGameModel2ViewAdapter {
	
	/**
	 * Prompt a dalog
	 * @param title title of a dialog
	 * @param context context of dialog
	 * @param isSelected true if ConfirmDialog, false if MessageDialog
	 */
	public void promptDialog(String title, String context, boolean isSelection);
	
	/**
	 * Update money in textfield
	 * @param money money this avatar has 
	 */
	public void updateMoney(int money);
	
	/**
	 * Go to a position
	 * @param pos 
	 * @param isFlying
	 */
	public void goTo(Position pos, boolean isFlying);

	/**
	 * Start my turn
	 */
	public void startTurn();
	
	/**
	 * update the view a little bit 
	 */
	public void update();
	
	/**
	 * @param title
	 * @param context
	 * @param didWin true if won, false if lost
	 */
	public void showEndGameMsg(String title, String context, boolean didWin);
}

package gl22_hl57.game_server.view;

import javax.swing.ImageIcon;

import common.IChatServer;

/**
 * Mini view to model adapter
 * @author Gengwu Li, Henry Lin
 * @version 1.0, Dec 9, 2016
 */
public interface IMini2ModelAdpt {

	/**
	 * Send a message to remote room
	 * @param s The remote stub
	 * @param text The text to be sent
	 */
	void addToRemoteRoom(IChatServer s, String text);

	/**
	 * Invite user
	 * @param ip User ip address
	 */
	void invite(String ip);

	/**
	 * Leave a room
	 */
	void leave();

	/**
	 * Send a Image to remote room
	 * @param chatServer Remote chat server
	 * @param icon Image icon
	 */
	void sendImage(IChatServer chatServer, ImageIcon icon);

	void sendGame();
	
	void startGame();

}
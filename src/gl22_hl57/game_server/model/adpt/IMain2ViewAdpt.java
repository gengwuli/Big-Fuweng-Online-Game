package gl22_hl57.game_server.model.adpt;

import common.IChatServer;

/**
 * Main model to view adapter
 * @author Gengwu Li, Henry Lin
 * @version 1.0, Dec 9, 2016
 */
public interface IMain2ViewAdpt {

	/**
	 * Make mini mvc
	 * @param chatServer The chat server associate to that mvc
	 * @return The mini mvc adapter
	 */
	IMiniMVCAdpt makeMiniMVC(IChatServer chatServer);

	/**
	 * Append information to info panel
	 * @param string The string to be appended
	 */
	void appendInfo(String string);

	/**
	 * Close all rooms
	 */
	void closeAllRooms();
}

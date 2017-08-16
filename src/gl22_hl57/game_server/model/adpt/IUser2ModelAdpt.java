package gl22_hl57.game_server.model.adpt;

import gl22_hl57.game_server.model.ChatServer;

/**
 * User to model adapter
 * @author Gengwu Li, Henry Lin
 * @version 1.0, Dec 9, 2016
 */
public interface IUser2ModelAdpt {

	/**
	 * Create a mini mvc
	 * @param chatServer The chat server to be added to the mini mvc
	 */
	void createMiniMVC(ChatServer chatServer);
}

package gl22_hl57.game_server.model.msg.chat;

import javax.swing.ImageIcon;

import common.msg.IChatMsg;

/**
 * IMage message
 * @author Gengwu Li, Henry Lin
 * @version 1.0, Dec 9, 2016
 */
public class ImageMsg implements IChatMsg {
	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 5406431519972400526L;
	
	/**
	 * Image icon associated with this message
	 */
	ImageIcon imageIcon;

	/**
	 * Constroctor
	 * @param imageIcon Image icon to inject
	 */
	public ImageMsg(ImageIcon imageIcon) {
		this.imageIcon = imageIcon;
	}

	/**
	 * Get image
	 * @return The image icon in this message
	 */
	public ImageIcon getContent() {
		return imageIcon;
	}
}

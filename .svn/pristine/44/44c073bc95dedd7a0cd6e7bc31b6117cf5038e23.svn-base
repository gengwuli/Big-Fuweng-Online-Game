package gl22_hl57.game_server.model.msg.chat;

import common.msg.chat.ITextMsg;

/**
 * Text message
 * @author Gengwu Li, Henry Lin
 * @version 1.0, Dec 9, 2016
 */
public class TextMsg implements ITextMsg {

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = -7425913092021551214L;
	
	/**
	 * Text bound to this message
	 */
	String text;
	
	/**
	 * Constructor
	 * @param text Text to inject
	 */
	public TextMsg(String text) {
		this.text = text;
	}
	
	/**
	 * Get text in the message
	 */
	@Override
	public String getContent() {
		return text;
	}
}

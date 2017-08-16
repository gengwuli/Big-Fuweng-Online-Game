package gl22_hl57.game_server.model.msg.chat;

import java.awt.Color;
import java.rmi.RemoteException;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;
import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.ICmd2ModelAdapter;
import common.IComponentFactory;
import common.IUser;
import common.OurDataPacket;
import common.msg.chat.ITextMsg;

/**
 * Text command
 * @author Gengwu Li, Henry Lin
 * @version 1.0, Dec 9, 2016
 */
public class TextCmd extends AOurDataPacketAlgoCmd<ITextMsg, IChatServer> {

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 8715648417305414827L;

	/**
	 * Command to model adapter
	 */
	transient ICmd2ModelAdapter cmd2ModelAdpter;

	/**
	 * Constructor
	 * @param cmd2ModelAdpter command to model adapter
	 */
	public TextCmd(ICmd2ModelAdapter cmd2ModelAdpter) {
		this.cmd2ModelAdpter = cmd2ModelAdpter;
	}
	
	/**
	 * Command execute
	 */
	@SuppressWarnings("serial")
	@Override
	public Void apply(Class<?> index, OurDataPacket<ITextMsg, IChatServer> host, Object... params) {
		ITextMsg textMsg = host.getData();
		cmd2ModelAdpter.buildComponentInScrollable(new IComponentFactory() {
			public JComponent make() {
				IUser user;
				JLabel label = null;
				try {
					user = host.getSender().getUser();
					label = new JLabel(user.getName() + "@" + user.getIP() + ":" + textMsg.getContent());
					label.setOpaque(true);
					label.setBackground(Color.white);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				return label;
			}
		});
		cmd2ModelAdpter.buildComponentInScrollable(new IComponentFactory() {
			
			@Override
			public JComponent make() {
				return (JComponent) Box.createVerticalStrut(10);
			}
		});
		return null;
	}

	/**
	 * Command to model adapter setter
	 */
	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpter) {
		this.cmd2ModelAdpter = cmd2ModelAdpter;
	}

}

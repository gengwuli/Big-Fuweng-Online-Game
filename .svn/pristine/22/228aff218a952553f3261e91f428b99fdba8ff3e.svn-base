package gl22_hl57.game_server.model.msg.chat;

import java.awt.Color;
import java.rmi.RemoteException;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.ICmd2ModelAdapter;
import common.IComponentFactory;
import common.IUser;
import common.OurDataPacket;

/**
 * Image command
 * @author Gengwu Li, Henry Lin
 * @version 1.0, Dec 9, 2016
 */
public class ImageCmd extends AOurDataPacketAlgoCmd<ImageMsg, IChatServer> {

	/**
	 * Serial version number
	 */
	private static final long serialVersionUID = -6101438227439368099L;

	/**
	 * Command to model adapter, no need to serialize
	 */
	transient ICmd2ModelAdapter cmd2ModelAdpter;

	/**
	 * Constructor
	 * @param cmd2ModelAdpter The command to model adapter to inject
	 */
	public ImageCmd(ICmd2ModelAdapter cmd2ModelAdpter) {
		this.cmd2ModelAdpter = cmd2ModelAdpter;
	}

	/**
	 * Command execute
	 */
	@SuppressWarnings("serial")
	@Override
	public Void apply(Class<?> index, OurDataPacket<ImageMsg, IChatServer> host, Object... params) {
		ImageIcon img = host.getData().getContent();
		
		// Build components in the panel, one info, one image, one padding
		cmd2ModelAdpter.buildComponentInScrollable(new IComponentFactory() {
			@Override
			public JComponent make() {
				try {
					IUser user = host.getSender().getUser();
					JLabel label = new JLabel(user.getName() + "@" + user.getIP() + ":");
					label.setOpaque(true);
					label.setBackground(Color.white);
					return label;
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				return new JLabel("Error!");
			}
		});
		
		cmd2ModelAdpter.buildComponentInScrollable(new IComponentFactory() {
			public JComponent make() {
				return new JLabel(img);
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
	 * Command to model adapter settor
	 */
	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpter) {
		this.cmd2ModelAdpter = cmd2ModelAdpter;
	}

}

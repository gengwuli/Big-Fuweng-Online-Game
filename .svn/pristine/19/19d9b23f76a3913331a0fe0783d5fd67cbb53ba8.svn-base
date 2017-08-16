package gl22_hl57.game_server.model.msg.chat;

import java.awt.Color;
import java.awt.Rectangle;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.ICmd2ModelAdapter;
import common.OurDataPacket;
import gl22_hl57.game_client.model.IPlayer2GameAdapter;
import provided.mixedData.MixedDataKey;

/**
 * Dice command 
 * @author Gengwu Li, Henry Lin
 * @version 1.0, Dec 9, 2016
 */
public class DiceCmd extends AOurDataPacketAlgoCmd<DiceMsg, IChatServer> {
	/**
	 * Serial number
	 */
	private static final long serialVersionUID = -7613456112635497896L;
	
	/**
	 * Command to model adapter, no need to serialize this
	 */
	transient ICmd2ModelAdapter cmd2ModelAdapter;

	/**
	 * Constructor
	 * @param cmd2ModelAdapter Command to model adapter inject
	 */
	public DiceCmd(ICmd2ModelAdapter cmd2ModelAdapter) {
		this.cmd2ModelAdapter = cmd2ModelAdapter;
	}

	/**
	 * Command execute 
	 */
	@Override
	public Void apply(Class<?> index, OurDataPacket<DiceMsg, IChatServer> host, Object... params) {
		// We have multiple images
		String[] imgUrls = host.getData().getContent();
		ImageIcon imageIcon = null;
		Timer timer = new Timer();
		List<JFrame> frames = new ArrayList<>();
		
		// Set the window transparent and attach the image icon on it
		for (int i = 0; i < imgUrls.length; i++) {
			try {
				imageIcon = new ImageIcon(new URL(imgUrls[i]));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			// Need to flush the image so won't buffered
			imageIcon.getImage().flush();
			JFrame frame = new JFrame();
			frame.setUndecorated(true);
			frame.setAlwaysOnTop(true);
			frame.setBackground(new Color(0, 0, 0, 0));
			frame.add(new JLabel(imageIcon));
			frame.pack();
			frame.setLocationRelativeTo(null);
			Rectangle bounds = frame.getBounds();
			frame.setBounds(bounds.x - 50 + i * 100, bounds.y, bounds.width, bounds.height);
			frame.setVisible(true);
			imageIcon.getImage().flush();
			frames.add(frame);
		}
		// Close the window and continue after 4 seconds
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				frames.get(0).dispose();
				frames.get(1).dispose();
				MixedDataKey<IPlayer2GameAdapter> gameKey = new MixedDataKey<IPlayer2GameAdapter>(
						host.getData().getGameId(), "", IPlayer2GameAdapter.class);
				IPlayer2GameAdapter game = cmd2ModelAdapter.getFromLocalDict(gameKey);
				if (game != null)
					game.finishRollingAnimation(host.getSender(), host.getData().getSteps());
			}
		}, 4000);
		return null;
	}

	/**
	 * Set command to model adapter
	 */
	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdapter = cmd2ModelAdpt;
	}
}
package gl22_hl57.game_client.model.msg;

import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.ICmd2ModelAdapter;
import common.OurDataPacket;
import gl22_hl57.game_client.model.IPlayer2GameAdapter;
import provided.mixedData.MixedDataKey;

/**
 * A game command which is used to notify that the game ended
 * @author Gengwu Li, Henry Lin
 */
public class EndGameCmd extends AOurDataPacketAlgoCmd<EndGameMsg, IChatServer> {

	/**
	 * Generated serial version id
	 */
	private static final long serialVersionUID = -304954244933543178L;

	/**
	 * An adapter which enables this command to talk to mini model
	 */
	private transient ICmd2ModelAdapter model;

	/**
	 * Constructor of EndGameCmd, given an ICmd2ModelAdapter.
	 * @param cmd2ModelAdpt an adapter which is used to talk to mini model
	 */
	public EndGameCmd(ICmd2ModelAdapter cmd2ModelAdpt) {
		this.setCmd2ModelAdpt(cmd2ModelAdpt);
	}

	@Override
	public Void apply(Class<?> index, OurDataPacket<EndGameMsg, IChatServer> host, Object... params) {
		System.out.println("EndGameCmd applies.");
		EndGameMsg info = host.getData();
		System.out.println("Did we win game(id:"+info.getGameId()+")? " + info.didWin());
		MixedDataKey<IPlayer2GameAdapter> gameKey = new MixedDataKey<IPlayer2GameAdapter>(info.getGameId(), "", IPlayer2GameAdapter.class);
		IPlayer2GameAdapter game = model.getFromLocalDict(gameKey);
		if (game!=null)
			game.end(info.didWin());
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		model = cmd2ModelAdpt;
	}

}

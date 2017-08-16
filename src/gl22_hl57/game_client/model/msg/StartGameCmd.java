package gl22_hl57.game_client.model.msg;

import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.ICmd2ModelAdapter;
import common.OurDataPacket;
import gl22_hl57.game_client.model.IPlayer2GameAdapter;
import provided.mixedData.MixedDataKey;

/**
 * A game command which is used to start the game
 * @author Gengwu Li, Henry Lin
 */
public class StartGameCmd extends AOurDataPacketAlgoCmd<StartGameMsg, IChatServer> {

	/**
	 * Generated serial version id
	 */
	private static final long serialVersionUID = -90539225049914041L;

	/**
	 * An adapter which enables this command to talk to mini model
	 */
	private transient ICmd2ModelAdapter model;
	
	/**
	 * Constructor of StartGameCmd, given an ICmd2ModelAdapter.
	 * @param cmd2ModelAdpt an adapter which is used to talk to mini model
	 */
	public StartGameCmd(ICmd2ModelAdapter cmd2ModelAdpt) {
		this.setCmd2ModelAdpt(cmd2ModelAdpt);
	}
	
	@Override
	public Void apply(Class<?> index, OurDataPacket<StartGameMsg, IChatServer> host, Object... params) {
		System.out.println("StartGameCmd applies.");
		StartGameMsg info = host.getData();
		MixedDataKey<IPlayer2GameAdapter> gameKey = new MixedDataKey<IPlayer2GameAdapter>(info.getGameId(), "", IPlayer2GameAdapter.class);
		IPlayer2GameAdapter game = model.getFromLocalDict(gameKey);
		if (game!=null)
			game.startTurn();
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		model = cmd2ModelAdpt;		
	}


}

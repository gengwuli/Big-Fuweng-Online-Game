package gl22_hl57.game_client.model.msg;

import java.rmi.RemoteException;

import javax.swing.JComponent;

import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.ICmd2ModelAdapter;
import common.IComponentFactory;
import common.OurDataPacket;
import gl22_hl57.game_client.controller.GameController;
import gl22_hl57.game_client.model.IPlayer2GameAdapter;
import provided.mixedData.MixedDataKey;

/**
 * A game command which is used to create Game MVC and init game data
 * @author Gengwu Li, Henry Lin
 */
public class GameInfoCmd extends AOurDataPacketAlgoCmd<GameInfo, IChatServer> {

	/**
	 * Generated serial version id
	 */
	private static final long serialVersionUID = -8551076757704507669L;

	/**
	 * An adapter which enables this command to talk to mini model
	 */
	private transient ICmd2ModelAdapter model;

	/**
	 * Constructor of GameInfoCmd, given an ICmd2ModelAdapter.
	 * @param cmd2ModelAdpt an adapter which is used to talk to mini model
	 */
	public GameInfoCmd(ICmd2ModelAdapter cmd2ModelAdpt) {
		this.setCmd2ModelAdpt(cmd2ModelAdpt);
	}

	@Override
	public Void apply(Class<?> index, OurDataPacket<GameInfo, IChatServer> host, Object... params) {
		System.out.println("GameInfoCmd applies.");


		GameInfo info = host.getData();
		GameController game;
		try {
			game = new GameController(){

				private static final long serialVersionUID = 4113035407535316564L;
				{
					gameId = info.getGameId();
					
					IPlayer2GameAdapter game = new IPlayer2GameAdapter(){

						@Override
						public void updateAvatar(AvatarUpdateInfo info) {
							gameModel.updateAvatar(info);
						}

						@Override
						public void updateCity(CityUpdateInfo info) {
							gameModel.updateCity(info);
						}

						@Override
						public void startTurn() {
							gameModel.startTurn();
						}

						@Override
						public void end(boolean didWin) {
							gameModel.end(didWin);
						}

						@Override
						public void finishRollingAnimation(IChatServer sender, int steps) {
							gameModel.startMovingAfterRolling(sender, steps);
						}
						
					};
					
					// init game data (players)
					gameModel.setGameId(gameId);
					// must first init city data, then avatar. So, we know where to put avatars
					gameModel.initCityData(info.getCities());					
					gameModel.initAvatarData(info.getTeams(), info.getPlayers(), model.getChatServer().getUser().getId());
					// store something we need into MDD
					MixedDataKey<IPlayer2GameAdapter> gameKey = new MixedDataKey<IPlayer2GameAdapter>(gameId, "", IPlayer2GameAdapter.class);
					model.putIntoLocalDict(gameKey, game);
					model.buildComponentInNonScrollable(new IComponentFactory(){
						@Override
						public JComponent make() {
							return gameView;
						}
					});
				}
				
			};
			game.start();
		} catch (RemoteException e) {
			System.out.println("Creating Mini-MVC encounters an error.");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		model = cmd2ModelAdpt;
	}

}
package gl22_hl57.game_server.model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

import javax.swing.JComponent;

import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.IChatroom;
import common.ICmd2ModelAdapter;
import common.IComponentFactory;
import common.IUser;
import common.OurDataPacket;
import common.msg.IChatMsg;
import common.msg.chat.IAddCmdMsg;
import common.msg.chat.IAddMeMsg;
import common.msg.chat.ILeaveMsg;
import common.msg.chat.INewCmdReqMsg;
import common.msg.chat.ITextMsg;
import common.msg.user.IInvite2RoomMsg;
import gl22_hl57.game_client.model.msg.*;
import gl22_hl57.game_server.model.adpt.IMini2ViewAdpt;
import gl22_hl57.game_server.model.msg.chat.AddCmdCmd;
import gl22_hl57.game_server.model.msg.chat.AddMeCmd;
import gl22_hl57.game_server.model.msg.chat.DiceCmd;
import gl22_hl57.game_server.model.msg.chat.DiceMsg;
import gl22_hl57.game_server.model.msg.chat.ImageCmd;
import gl22_hl57.game_server.model.msg.chat.ImageMsg;
import gl22_hl57.game_server.model.msg.chat.LeaveCmd;
import gl22_hl57.game_server.model.msg.chat.NewCmdReqCmd;
import gl22_hl57.game_server.model.msg.chat.NewCmdReqMsg;
import gl22_hl57.game_server.model.msg.chat.TextCmd;
import gl22_hl57.game_server.model.msg.user.Invite2RoomCmd;
import provided.datapacket.DataPacketAlgo;
import provided.mixedData.MixedDataDictionary;
import provided.mixedData.MixedDataKey;

/**
 * Chat server object contains the chatroom and user Represents a remote object,
 * can be instantiated directly and sent as a stub to be used by the client
 * 
 * @author Gengwu Li, Henry Lin
 * @version 1.0, Dec 7, 2016
 */
public class ChatServer extends UnicastRemoteObject implements IChatServer {

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 382418868833091261L;
	
	/**
	 * User
	 */
	private IUser user;
	
	/**
	 * Chat room
	 */
	private IChatroom chatroom;
	
	/**
	 * Mini chatserver to view adapter
	 */
	private IMini2ViewAdpt mini2ViewAdpt;
	
	/**
	 * Mixed data dictionary
	 */
	private MixedDataDictionary mixedDataDictionary = new MixedDataDictionary();

	/**
	 * Local command to model adapter
	 */
	ICmd2ModelAdapter localCmd2ModelAdapter = new ICmd2ModelAdapter() {

		/**
		 * Not used in my implementation, might used by other groups implementation
		 */
		@Override
		public <T extends IChatMsg> void sendMsg2LocalChatroom(Class<T> index, T msg) {
			chatroom.send(new OurDataPacket<T, IChatServer>(index, msg, ChatServer.this));
		}

		/**
		 * Put to local dictionary
		 */
		@Override
		public <T> boolean putIntoLocalDict(MixedDataKey<T> key, T value) {
			boolean ret = false;
			if (!mixedDataDictionary.containsKey(key)) {
				ret = true;
				mixedDataDictionary.put(key, value);
			}
			return ret;
		}

		/**
		 * Get from local dictionary
		 */
		@Override
		public <T> T getFromLocalDict(MixedDataKey<T> key) {
			return mixedDataDictionary.get(key);
		}

		/**
		 * Get chatserver
		 */
		@Override
		public IChatServer getChatServer() {
			return ChatServer.this;
		}

		/**
		 * Build component in scrollable
		 */
		@Override
		public void buildComponentInScrollable(IComponentFactory fac) {
			JComponent component = fac.make();
			mini2ViewAdpt.getPnlShow().add(component);
			mini2ViewAdpt.getPnlShow().revalidate();
		}

		/**
		 * Build component in non scrollable
		 */
		@Override
		public void buildComponentInNonScrollable(IComponentFactory fac) {
			JComponent component = fac.make();
			mini2ViewAdpt.getPnlShow().add(component);
			mini2ViewAdpt.getPnlShow().revalidate();
		}
	};

	/**
	 * Algorithm
	 */
	@SuppressWarnings("serial")
	DataPacketAlgo<Void, Object> algo = new DataPacketAlgo<Void, Object>(
			new AOurDataPacketAlgoCmd<Object, IChatServer>() {
				transient ICmd2ModelAdapter cmd2ModelAdapter;
				{
					cmd2ModelAdapter = localCmd2ModelAdapter;
				}

				@Override
				public Void apply(Class<?> index, OurDataPacket<Object, IChatServer> host, Object... params) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							//Unknown data packet handler
							UUID uuid = UUID.randomUUID();
							// First generate a uuid with which to cache to dictionary
							MixedDataKey<Object> unknownMsgKey = new MixedDataKey<>(uuid, "", Object.class);
							cmd2ModelAdapter.putIntoLocalDict(unknownMsgKey, host);
							
							// Send new command request message to sender
							INewCmdReqMsg msg = new NewCmdReqMsg(index, uuid);
							OurDataPacket<INewCmdReqMsg, IChatServer> dataPacket = new OurDataPacket<INewCmdReqMsg, IChatServer>(
									INewCmdReqMsg.class, msg, cmd2ModelAdapter.getChatServer());
							try {
								host.getSender().receive(dataPacket);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}).start();
					return null;
				}

				@Override
				public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
					this.cmd2ModelAdapter = cmd2ModelAdpt;
				}
			});

	/**
	 * Constructor
	 * @param user User to inject
	 * @param chatroom Chat room to inject
	 * @throws RemoteException Remote exception, Note ChatServer extends UnicastRemoteObject
	 */
	public ChatServer(IUser user, IChatroom chatroom) throws RemoteException {
		this.user = user;
		this.chatroom = chatroom;
		initChatServer();
	}

	/**
	 * Set a list of command
	 */
	private void initChatServer() {
		algo.setCmd(IAddCmdMsg.class, new AddCmdCmd(localCmd2ModelAdapter));
		algo.setCmd(IAddMeMsg.class, new AddMeCmd(localCmd2ModelAdapter));
		algo.setCmd(ILeaveMsg.class, new LeaveCmd(localCmd2ModelAdapter));
		algo.setCmd(INewCmdReqMsg.class, new NewCmdReqCmd(localCmd2ModelAdapter));
		algo.setCmd(IInvite2RoomMsg.class, new Invite2RoomCmd(localCmd2ModelAdapter));
		algo.setCmd(ITextMsg.class, new TextCmd(localCmd2ModelAdapter));
		algo.setCmd(ImageMsg.class, new ImageCmd(localCmd2ModelAdapter));
		algo.setCmd(GameInfo.class, new GameInfoCmd(localCmd2ModelAdapter));
		algo.setCmd(StartGameMsg.class, new StartGameCmd(localCmd2ModelAdapter));
		algo.setCmd(AvatarUpdateInfo.class, new AvatarUpdateCmd(localCmd2ModelAdapter));
		algo.setCmd(CityUpdateInfo.class, new CityUpdateCmd(localCmd2ModelAdapter));
		algo.setCmd(EndGameMsg.class, new EndGameCmd(localCmd2ModelAdapter));
		algo.setCmd(DiceMsg.class, new DiceCmd(localCmd2ModelAdapter));
	}

	/**
	 * Receive a packet and execute
	 */
	@Override
	public <S> void receive(OurDataPacket<? extends IChatMsg, S> dp) throws RemoteException {
		dp.execute(algo);
	}

	/**
	 * Get user
	 */
	@Override
	public IUser getUser() throws RemoteException {
		return user;
	}

	/**
	 * Get chat room
	 */
	@Override
	public IChatroom getChatroom() throws RemoteException {
		return chatroom;
	}

	/**
	 * Get Data packat algorithm
	 * @return The data packet algorithm
	 */
	public DataPacketAlgo<Void, Object> getAlgo() {
		return algo;
	}

	/**
	 * Set mini to view adapter
	 * @param iMini2ViewAdpt The mini to view adapter to set
	 */
	public void setAdpt(IMini2ViewAdpt iMini2ViewAdpt) {
		this.mini2ViewAdpt = iMini2ViewAdpt;
	}

	/**
	 * Refresh the user list in the view
	 */
	public void refresh() {
		mini2ViewAdpt.addToList();
	}
	
	/**
	 * Test whether two ChatServer are equal
	 */
	@Override
	public boolean equals(Object obj) {
		try {
			return ((IChatServer)obj).getUser().getId().equals(user.getId());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}
}
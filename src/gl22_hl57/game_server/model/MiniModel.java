package gl22_hl57.game_server.model;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import javax.swing.ImageIcon;
import common.IChatServer;
import common.IUser;
import common.OurDataPacket;
import common.msg.chat.ILeaveMsg;
import common.msg.chat.ITextMsg;
import common.msg.user.IInvite2RoomMsg;
import gl22_hl57.game_client.model.CityData;
import gl22_hl57.game_client.model.Team;
import gl22_hl57.game_client.model.msg.GameInfo;
import gl22_hl57.game_client.model.msg.StartGameMsg;
import gl22_hl57.game_server.model.adpt.IMini2ViewAdpt;
import gl22_hl57.game_server.model.msg.chat.ImageMsg;
import gl22_hl57.game_server.model.msg.chat.LeaveMsg;
import gl22_hl57.game_server.model.msg.chat.TextMsg;
import gl22_hl57.game_server.model.msg.user.Invite2RoomMsg;
import provided.rmiUtils.RMIUtils;
import provided.util.IVoidLambda;

/**
 * Mini model
 * @author Gengwu Li, Henry Lin
 * @version 1.0, Dec 7, 2016
 */
public class MiniModel {
	/**
	 * Mini to view adapter
	 */
	IMini2ViewAdpt mini2ViewAdpt;

	/**
	 * Chat server
	 */
	IChatServer chatServer;

	/**
	 * RMI utility tool
	 */
	RMIUtils utils = new RMIUtils(new IVoidLambda<String>() {
		@Override
		public void apply(String... params) {
			System.out.println(params);
		}
	});
	
	private UUID lastGameId;

	/**
	 * Constructor
	 * 
	 * @param mini2ViewAdpt
	 *            Mini to view adapter
	 * @param chatServer
	 *            Chat server
	 */
	public MiniModel(IMini2ViewAdpt mini2ViewAdpt, IChatServer chatServer) {
		this.mini2ViewAdpt = mini2ViewAdpt;
		this.chatServer = chatServer;
	}

	/**
	 * Send message to remote room
	 * 
	 * @param s
	 *            Chat server stub
	 * @param 
	 *            Text to be sent
	 */
	public void sendText(IChatServer s, String text) {
		new Thread(() -> {
			OurDataPacket<ITextMsg, IChatServer> dataPacket = new OurDataPacket<>(ITextMsg.class, new TextMsg(text),
					chatServer);
			try {
				s.receive(dataPacket);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}).start();
	}

	/**
	 * Invite remote user
	 * 
	 * @param remoteUser
	 *            Remote user to be invited
	 */
	public void invite(IUser remoteUser) {
		System.out.println("MiniModel invite:" + remoteUser.toString());
		new Thread(() -> {
			try {
				IInvite2RoomMsg invite2RoomMsg = new Invite2RoomMsg(chatServer.getChatroom());
				OurDataPacket<IInvite2RoomMsg, IChatServer> dataPacket = new OurDataPacket<IInvite2RoomMsg, IChatServer>(
						IInvite2RoomMsg.class, invite2RoomMsg, chatServer);
				remoteUser.receive(dataPacket);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}).start();

	}

	/**
	 * Leave the room.
	 */
	public void leave() {
		new Thread(() -> {
			System.out.println("MiniModel leave called");
			mini2ViewAdpt.remove(chatServer);
			OurDataPacket<ILeaveMsg, IChatServer> dataPacket = new OurDataPacket<ILeaveMsg, IChatServer>(
					ILeaveMsg.class, new LeaveMsg(), chatServer);
			try {
				chatServer.getChatroom().send(dataPacket);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}).start();
	}

	/**
	 * Send image to remote user
	 * 
	 * @param s
	 *            Remote chat server stub
	 * @param imageIcon
	 *            Image Icon
	 */
	public void sendImage(IChatServer s, ImageIcon imageIcon) {
		new Thread(() -> {
			OurDataPacket<ImageMsg, IChatServer> dataPacket = new OurDataPacket<>(ImageMsg.class,
					new ImageMsg(imageIcon), chatServer);
			try {
				s.receive(dataPacket);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	/**
	 * Send a game ChatServer
	 */
	public void sendGame() {
		List<IChatServer> players = null;
		try {
			players = new ArrayList<>(this.chatServer.getChatroom().getChatServers());
		} catch (RemoteException e) {
			System.out.println("Failed getting chatservers while starting game.");
			e.printStackTrace();
		}
		if (players!=null) {
			lastGameId = UUID.randomUUID();
			// read game data
			List<CityData> cityList = readGameCityData();
			List<Team> teams = Arrays.asList(new Team(Color.RED), new Team(Color.GREEN), new Team(Color.BLUE));
			GameInfo info = new GameInfo(lastGameId, players, teams, cityList);
			OurDataPacket<GameInfo, IChatServer> gameInfoDp = new OurDataPacket<GameInfo, IChatServer>(GameInfo.class, info, this.chatServer);
			for (IChatServer player : players) {
				new Thread(()->{
					try {
						player.receive(gameInfoDp);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}).start();
			}
		}
	}
	
	/**
	 * Start the game
	 */
	public void startGame() {
		if (lastGameId==null) {
			return;
		}
		// start game from myself
		StartGameMsg startGame = new StartGameMsg(lastGameId);
		OurDataPacket<StartGameMsg, IChatServer> startGameDp = new OurDataPacket<StartGameMsg, IChatServer>(StartGameMsg.class, startGame, this.chatServer);
		new Thread(()->{
			try {
				this.chatServer.receive(startGameDp);
			} catch (RemoteException e) {
				System.out.println("Failed sending start game msg to myself");
				e.printStackTrace();
			}
		}).start();
		lastGameId = null;
	}
	
	/**
	 * Read game city data
	 * @return A list of city data
	 */
	private List<CityData> readGameCityData() {
		List<CityData> cityList = new ArrayList<>(); // city list (route)
        // A csv file name.
		String filename = "gl22_hl57/game_client/resources/c_worldcities.csv";
		BufferedReader reader = null;
		try {
			// open file input stream
			reader = new BufferedReader(new FileReader(filename));
			String line = null; // line storing a line from csv
			Scanner scanner = null; // scanner retrieving data from csv
			// variables storing city fields from csv	
			String cityName = null, countryName = null;
			double latitude = 0, longitude = 0;
			int price = 0;
			System.out.println("Reading city data...");
			// read file line by line
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				scanner = new Scanner(line);
				scanner.useDelimiter(",");
				cityName = scanner.next();
				countryName = scanner.next();
				latitude = scanner.nextDouble();
				longitude = scanner.nextDouble();
				price = scanner.nextInt();
				cityList.add(new CityData(cityName, countryName, latitude, longitude, price));
			}
			//close reader
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed reading cities data, IOException: "+e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return cityList;
	}
}

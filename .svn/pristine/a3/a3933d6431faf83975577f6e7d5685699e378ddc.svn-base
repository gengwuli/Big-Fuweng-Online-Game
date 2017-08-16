package gl22_hl57.game_client.model;

import java.awt.Color;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import common.IChatServer;
import common.OurDataPacket;
import common.msg.IChatMsg;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Offset;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;

/**
 * An avatar class wraps IChatServer and extends PointPlacemark which is 
 * used to act as an player in the game.
 * @author Gengwu Li, Henry Lin
 * @version 1.0, Dec 9, 2016
 */
public class Avatar extends PointPlacemark implements Serializable {

	/**
	 * Generated serial version id
	 */
	private static final long serialVersionUID = 4506503661009020494L;

	/**
	 * Default Avatar for well-defined game behavior(unoccupied city)
	 */
	public static final Avatar DEFAULT_AVATAR = new Avatar();
	
	/**
	 * Private constructor only for DEFAULT_AVATAR (Singleton)
	 */
	private Avatar() {
		super(Position.fromDegrees(0d, 0d, 0d));
		myStub = null;
		name = "N/A";
		myId = UUID.nameUUIDFromBytes(new String("HL57_GL22_DEFAULT_AVATAR").getBytes());
		team = Team.DEFAULT_TEAM;
		money = 0;
		isBankrupted = false;
	}
	
	/**
	 * An IChatServer stub which this avatar represents
	 */
	private final IChatServer myStub;
	
	/**
	 * The name of the user
	 */
	private String name;
	
	/**
	 * The team that this avatar belongs to
	 */
	private Team team;
		
	/**
	 * The uuid of the avatar (IChatServer)
	 */
	private UUID myId;
	
	/**
	 * The amount of money that this avatar (IChatServer) has
	 */
	private int money;
	
	/**
	 * The bankrupted state of this avatar (IChatServer)
	 */
	private boolean isBankrupted;
	
	/**
	 * Indices of cities this avatar (IChatServer) owns
	 */
	private HashSet<Integer> propertyIndices = new HashSet<>();
	
	/**
	 * Constructor of Avatar
	 * @param stub the IChatServer stub of this avatar
	 * @param gameTeam the team which this avatar belongs to
	 * @param origin the initial position of the avatar
	 */
	public Avatar(IChatServer stub, Team gameTeam, Position origin) {
		super(origin);
		myStub = stub;
		try {
			name = stub.getUser().getName();
			myId = stub.getUser().getId();
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println("Failed getting name/UUID from IPlayer stub while constructing an avatar,"
					+ "using 'anonymous' as default username and randomUUID instead, remoteException: " + e);
			name = "anonymous";
			myId = UUID.randomUUID();
		}
		team = gameTeam;
		money = 100;
		isBankrupted = false;
		initMarker();
	}
	
	/**
	 * Init properties of the marker
	 */
	private void initMarker() {
        this.setLabelText(name);
        this.setLineEnabled(false);
        this.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
        this.setEnableBatchPicking(true); // enable label picking for this placemark
        // set up avatar(PointPlacemark)'s attribues
        PointPlacemarkAttributes ppAttrs = new PointPlacemarkAttributes();
        ppAttrs.setLabelMaterial(new Material(this.getTeamColor()));
        ppAttrs.setImageAddress("https://res.cloudinary.com/hdjcahswx/image/upload/v1481171293/hvbiqgesvn3pz0lyy60h.png");
        ppAttrs.setImageColor(this.getTeamColor()); // image color for different states
        ppAttrs.setScale(0.3);
        ppAttrs.setImageOffset(new Offset(100d, 30d, AVKey.PIXELS, AVKey.PIXELS));
        ppAttrs.setLabelOffset(new Offset(0.9d, 0d, AVKey.FRACTION, AVKey.FRACTION));
        this.setAttributes(ppAttrs);
	}

	/**
	 * Called by someone in the game to ask this avatar receive some game messages
	 * @param dp datapacket to send
	 * @throws RemoteException a RemoteException
	 */
	public void receive(OurDataPacket<? extends IChatMsg, IChatServer> dp) throws RemoteException {
		myStub.receive(dp);
	}
	
	/**
	 * Get all the money this avatar has left
	 * @return the amount of money that this avatar has
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * Change the amount of money this avatar has by amountChanged
	 * @param amountChanged the amount of money changed
	 */
	public void changeMoney(int amountChanged) {
		money += amountChanged;
		if (money<0) isBankrupted = true; 
	}

	/**
	 * Getter of the username
	 * @return the username of this avatar
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get all the teammates this avatar has, not including himself.
	 * @return all the teammates
	 */
	public List<Avatar> getTeammates() {
		List<Avatar> teamMembers = new ArrayList(team.getMembers());
		int i=0, memberNum = teamMembers.size();
		System.out.println("Get teammates, teammate#:"+memberNum);
		for (; i<memberNum; i++) {
			if (teamMembers.get(i).equals(this)) 
				break;
		}
		if (i<memberNum) // remove myself
			teamMembers.remove(i);
		return teamMembers;
	}
	
	/**
	 * Getter of UUID of the team
	 * @return uuid of the team that this avatar belongs to
	 */
	public UUID getTeamId() {
		return team.getTeamId();
	}
	
	/**
	 * Getter of team color
	 * @return team color
	 */
	public Color getTeamColor() {
		return team.getTeamColor();
	}

	/**
	 * Getter of UUID of IUser
	 * @return UUID of IUser
	 */
	public UUID getMyId() {
		return myId;
	}

	/**
	 * Getter of my IChatServer stub
	 * @return my IChatServer stub
	 */
	public IChatServer getMyStub() {
		return myStub;
	}
	
	/**
	 * True if this avatar is bankrupted, money less than 0
	 * @return a boolean indicates if this avatar is bankrupted
	 */
	public boolean isBankrupted() {
		return isBankrupted;
	}
	
	/**
	 * Add a property to this avatar
	 * @param cityIdx index of the city this avatar own
	 * @return true if the city has already include in this avatar
	 */
	public boolean addProperty(Integer cityIdx) {
		return propertyIndices.add(cityIdx);
	}
	
	/**
	 * Get all the indices of the properties this avatar has
	 * @return all the indices of the properties this avatar has
	 */
	public Integer[] getAllProperties() {
		return propertyIndices.toArray(new Integer[0]);
	}
	
	/**
	 * Release a property that this avatar has
	 * @param cityIdx index of a city
	 * @return true if this city was presented in the list
	 */
	public boolean releaseProperty(Integer cityIdx) {
		return propertyIndices.remove(cityIdx);
	}
	
	@Override
	public String toString() {
		return name + " $" + money;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((myId == null) ? 0 : myId.hashCode());
		result = prime * result + ((myStub == null) ? 0 : myStub.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj==null || !(obj instanceof Avatar))
			return false;
		return this.hashCode() == obj.hashCode();
	}

}

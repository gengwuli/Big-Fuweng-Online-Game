package gl22_hl57.game_client.model;

import java.awt.Color;
import java.io.Serializable;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Offset;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;

/**
 * An city class wraps city data and extends PointPlacemark which is a
 * property(marker) in the game
 * @author Gengwu Li, Henry Lin
 * @version 1.0, Dec 9, 2016
 */
public class City extends PointPlacemark implements Serializable {

	/**
	 * Generated serial version id
	 */
	private static final long serialVersionUID = 8082077734679553945L;
	
	/**
	 * An enum to indicate the state of a city
	 * @author Gengwu Li, Henry Lin
	 * @version 1.0, Dec 9, 2016
	 */
	public enum CityConstructingState {
	    /**
	     * Unoccupied city
	     */
	    UNOCCUPIED(1,"https://res.cloudinary.com/hdjcahswx/image/upload/v1481171451/b8ud5z01hrb3lw9hvi48.png"), 
	    /**
	     * A level-1 city which is owned by a player.
	     */
	    FLAG(2,"https://res.cloudinary.com/hdjcahswx/image/upload/v1481171451/b8ud5z01hrb3lw9hvi48.png"), 
	    /**
	     * A level-2 city which is owned by a player.
	     */
	    HOUSE(3, "https://res.cloudinary.com/hdjcahswx/image/upload/v1481171260/nqtmtiadkfiex13iudgl.png"), 
	    /**
	     * A level-4 city which is owned by a player.
	     */
	    SKYSCRAPER(4, "https://res.cloudinary.com/hdjcahswx/image/upload/v1481171431/xvphppbjnegkbzlqaheq.png");
		/**
		 * The weight of price/tolls of different states of a city
		 */
		private int weight;
		/**
		 * The image paths of different states of a city
		 */
		private String imagePath;
		/**
		 * Constructor of CityConstructingState
		 * @param weight weight of the state
		 * @param imagePath image path of the state
		 */
		private CityConstructingState(int weight, String imagePath) {
			this.weight = weight;
			this.imagePath = imagePath;
		}
	}
	
	/**
	 * Name of the city
	 */
	private String name;
	
	/**
	 * Name of the country this city belongs to
	 */
	private String country;
	
	/**
	 * Latitude of this city
	 */
	private double lat; 
	
	/**
	 * Longitude of this city
	 */
	private double lng;
	
	/**
	 * The level(weight/state) of this city
	 */
	private CityConstructingState state;
	
	/**
	 * The owner of this city
	 */
	private Avatar owner;
	
	/**
	 * The price of this city
	 */
	private int price = 0;
			
	/**
	 * Constructor of City
	 * @param data city data
	 */
	public City(CityData data) {
		super(Position.fromDegrees(data.getLat(), data.getLng(), 1e7));
		name = data.getName();
		country = data.getCountry();
		lat = data.getLat();
		lng = data.getLng();
		price = data.getPrice();
		initMarker();
		setOwner(Avatar.DEFAULT_AVATAR);
		setState(CityConstructingState.UNOCCUPIED);
	}

	/**
	 * Init properties of the marker
	 */
	private void initMarker() {
		this.setLabelText(getName());
//		this.setValue(AVKey.DISPLAY_NAME, toString());
		this.setLineEnabled(false);
		this.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
		this.setEnableBatchPicking(true); // enable label picking for this placemark
        PointPlacemarkAttributes ppAttrs = new PointPlacemarkAttributes();
//        ppAttrs.setImageAddress("../resources/WhiteFlag.png");
//        ppAttrs.setImageColor(new Color(1f, 1f, 1f, 0.8f)); // image color for different states
//        ppAttrs.setScale(0.1);
        ppAttrs.setLabelOffset(new Offset(0.9d, 0.6d, AVKey.FRACTION, AVKey.FRACTION));
        this.setAttributes(ppAttrs);
	}
	
	/**
	 * Get the owner of this city
	 * @return the owner
	 */
	public Avatar getOwner() {
		return owner;
	}

	/**
	 * Set the owner of this city, then change state of the city.
	 * @param newOwner the new owner of this city
	 */
	public void setOwner(Avatar newOwner) {
		owner = newOwner;
		if (owner==Avatar.DEFAULT_AVATAR) {
			setState(CityConstructingState.UNOCCUPIED);
		} else { 
			setState(CityConstructingState.FLAG);
		}
	}

	/**
	 * Set the state of this city
	 * @param newState the new state of this city
	 */
	private void setState(CityConstructingState newState) {
		state = newState;
		PointPlacemarkAttributes attrs = this.getAttributes();
		attrs.setImageAddress(state.imagePath); // change image based on states
		switch(state) 
		{
			case FLAG:
				attrs.setImageColor(owner.getTeamColor());
				attrs.setLabelMaterial(new Material(owner.getTeamColor()));
				break;
			case HOUSE:
		        attrs.setImageOffset(new Offset(100d, 100d, AVKey.PIXELS, AVKey.PIXELS));
				break;
			case SKYSCRAPER:
		        attrs.setScale(0.2);
				break;
			default:
		        attrs.setImageOffset(new Offset(0d, 0d, AVKey.PIXELS, AVKey.PIXELS));
				attrs.setImageColor(Color.LIGHT_GRAY);
				attrs.setLabelMaterial(new Material(Color.LIGHT_GRAY));
		        attrs.setScale(0.1);
		}
		this.setValue(AVKey.DISPLAY_NAME, toString());
	}
	
	/**
	 * Check if the city is upgradable.
	 * @return true if it's upgradable
	 */
	public boolean isUpgradable() {
		return state==CityConstructingState.FLAG || state==CityConstructingState.HOUSE;
	}
	
	/**
	 * Upgrade a city
	 */
	public void upgrade() {
		if (state==CityConstructingState.FLAG)
			setState(CityConstructingState.HOUSE);
		else if (state==CityConstructingState.HOUSE)
			setState(CityConstructingState.SKYSCRAPER);
	}
	
	/**
	 * Get the price of buying/upgrading
	 * @return the price of buying/upgrading
	 */
	public int getPrice() {
		return price*state.weight;
	}
	
	/**
	 * Get the amount of money for tolls
	 * @return the amount of money for tolls
	 */
	public int getTolls() {
		return price*state.weight;
	}

	/**
	 * Get the name of the city
	 * @return the name of the city
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the name of the country
	 * @return the name of the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Get the latitude of the city
	 * @return latitude of the city
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * Get the longitude of the city
	 * @return longitude of the city
	 */
	public double getLng() {
		return lng;
	}

	@Override
	public String toString() {
		return name  + " $" + price + "\n" + owner.getName();
//		return name + ", " + country;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj==null || !(obj instanceof City))
			return false;
		return this.hashCode()==obj.hashCode();
	}
		
}

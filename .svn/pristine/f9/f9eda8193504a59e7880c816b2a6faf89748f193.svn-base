package gl22_hl57.game_client.model;

import java.io.Serializable;

/**
 * A city class which contains info of a city
 * @author Gengwu Li, Henry Lin
 * @version 1.0, Dec 9, 2016
 */
public class CityData implements Serializable {

	/**
	 * Generated serial version id
	 */
	private static final long serialVersionUID = -7513974967401739464L;

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
	 * The initial price of this city
	 */
	private int price = 0;

	/**
	 * constructor of CityData
	 * @param cityName Name of the city
	 * @param countryName Name of the country this city belongs to
	 * @param latitude Latitude of this city
	 * @param longitude Longitude of this city
	 * @param value The initial price of this city
	 */
	public CityData(String cityName, String countryName, double latitude, double longitude, int value) {
		name = cityName;
		country = countryName;
		lat = latitude;
		lng = longitude;
		price = value;
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

	/**
	 * Get the price of buying/upgrading
	 * @return the initial price
	 */
	public int getPrice() {
		return price;
	}
}

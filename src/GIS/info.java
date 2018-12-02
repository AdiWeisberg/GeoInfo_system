package GIS;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Geom.Point3D;
/**
 * This class stores the client's additional information beside the geographic point. 
 * In addition, this class convert date-time to UTC time that used in Google-Earth maps. 
 * @author Naomi & Adi
 * @since 01/12/2018
 */
public class info implements Meta_data{
	private long UTC;
	private String strUTC;
	private String SSID;
	private String type;
	private String BSSID;
	private String capabillities;
	static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 
	 * @param SSID - the client's WIFI name.
	 * @param BSSID - the client's MAC address.
	 * @param capabillities - The client's device AuthMod.
	 * @param dateTime - The client's first-seen date at the GeoPoint.
	 * @param type - The client's Internet connection type.
	 * @throws ParseException - if the date time can't be convert.
	 */
	public info(String SSID,String BSSID,String capabillities,String dateTime, String type) throws ParseException{
		Date UTC = GMTtoUTC(dateTime, -2);// this time fits to Israel summer time. 
		Date oldDate = sdf.parse(dateTime);
		this.strUTC = sdf.format(UTC);
		this.UTC = UTC.getTime();//gets the client's first seen long type date in UTC time.
		this.SSID=SSID;
		this.type = type;
		this.BSSID=BSSID;
		this.capabillities=capabillities;
	}

	/**
	 * This function gets string of the client's first connection date-time and converts it to UTC-time by Israel time
	 * in order to use it for Google-Earth maps.
	 * @param dateTime - The client device's first seen time.
	 * @param hours - the GSM offset based on Israel summer time. 
	 * @return date convert to UTC time. 
	 * @throws ParseException - throw an Exception if it can't convert to date - Unparseable date case.
	 */
	public Date GMTtoUTC(String dateTime, int hours) throws ParseException {
		Date oldDate = sdf.parse(dateTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(oldDate);
		calendar.add(Calendar.HOUR_OF_DAY, hours);
		return calendar.getTime();
	}

	/**
	 * This function gets Auto-mood information from the client's device and update it. 
	 * @param capabillities - Auto-mood information from the client's device.
	 */
	public void setCapabillities(String capabillities) {
		this.capabillities = capabillities;
	}

	/**
	 * This function gets data from Info type and set the function current data to be the same as the input. 
	 * @param data - other information about the Geo-Point. (like datetime, SSID, BSSID..)
	 */
	public info(info data) {
		this.setUTC(data.getUTC());
		this.setSSID(data.getSSID());
		this.setType(data.getType());
		this.setBSSID(data.getBSSID());
		this.setCapabillities(data.getCapabillities());
		this.setStrUTC(data.getStrUTC());
	}

	/**
	 * This function gets string of the DateTime in UTC time.
	 * @return strUTC - String of the client's first seen UTC time. 
	 */
	public String getStrUTC() {
		return strUTC;
	}


	/**
	 * This function gets string of the DateTime in UTC time and update the current strUTC based on the input.
	 * @param strUTC
	 */
	public void setStrUTC(String strUTC) {
		this.strUTC = strUTC;
	}


	/**
	 * creates an empty constructor.
	 */
	public info() {
		this.UTC = 0;
		this.SSID = "";
		this.type = "";
		this.BSSID="";
		this.capabillities="";
		this.strUTC="";
	}

	/**
	 * gets UTC time of the client's first seen date. 
	 */
	@Override
	public long getUTC() {
		return this.UTC;
	}

	/**
	 * Not in use for this part of the project.
	 */
	@Override
	public Point3D get_Orientation() { //Boaz told us to keep it NULL at this point.
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return string of the client device's info beside the GeoPoint.
	 */
	@Override
	public String toString() {
		return  this.getSSID()+","+this.getBSSID()+","+this.getCapabillities()+","+this.getUTC()+","+this.getType()+","+this.strUTC;
	}

	/**
	 * This function gets UTC date in long and set the current data based on the input.
	 * @param UTC - Coordinated Universal Time
	 */
	public void setUTC(long UTC) {
		this.UTC = UTC;
	}

	/**
	 * @return string of the client's WIFI network name.
	 */
	public String getSSID() {
		return SSID;
	}

	/**
	 * This function set the client's WIFI network name.
	 * @param sSID - Service Set Identifier (the WIFI name).
	 */
	public void setSSID(String sSID) {
		SSID = sSID;
	}

	/**
	 * @return string of the client's Internet type.(WIFI\GSM..).
	 */
	public String getType() {
		return type;
	}

	/**
	 * This function the client's Internet type.(WIFI\GSM..). 
	 * @param type - the client's device Internet type.(WIFI\GSM..).
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the MAC address of the client's device.
	 */
	public String getBSSID() {
		return BSSID;
	}

	/**
	 * This function sets the MAC address of the client's device.
	 * @param bSSID - AP MAC address ,the MAC address of the client's device.
	 */
	public void setBSSID(String bSSID) {
		BSSID = bSSID;
	}
	/**
	 * @return string of the Auto-mood of the client's device.
	 */
	public String getCapabillities() {
		return capabillities;
	}

	@Override
	public Color getColor() {
		return null;
	}

	@Override
	public int getName() {
		return 0;
	}

	@Override
	public void setName(int name) {
		
	}
}
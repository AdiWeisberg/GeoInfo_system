package GIS;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.crypto.Data;

import Geom.Point3D;
/**
 * This class stores the client's additional information beside the geographic point. 
 * In addition, this class convert date-time to UTC time that used in Google-Earth maps. 
 * @author Naomi & Adi
 * @since 01/12/2018
 */
public class info implements Meta_data{
	private String type;
	//private Point3D point;
	private Date time;
	private long UTC;
	private int name;
	private int speed;
	private int radiusEat;
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
	public info(String type,int name, int speed,int radiusEat ) throws ParseException{
		this.type=type;
		
		this.name=name;
		this.speed=speed;
		this.radiusEat=radiusEat;
		this.time = curTime2UTC();
		this.UTC = time.getTime();
	}

	//Create time by computer time now.
		private Date curTime2UTC() throws ParseException { // based on Israel Summer time.
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			cal.add(Calendar.HOUR_OF_DAY, -2);
			String UTC = sdf.format(cal.getTime());
			return sdf.parse(UTC);
		}

	
	/**
	 * This function gets data from Info type and set the function current data to be the same as the input. 
	 * @param data - other information about the Geo-Point. (like datetime, SSID, BSSID..)
	 */
//	public info(info data) {
//		this.point=info.point;
//		this.name=name;
//		this.speed=speed;
//		this.radiusEat=radiusEat;
//		Date UTC = GMTtoUTC(time, -2);// this time fits to Israel summer time. 
//		Date oldDate = sdf.parse(time);
//		this.time= sdf.format(UTC);
//		this.UTC = UTC.getTime();//gets the client's first seen long type date in UTC time.
//	}

	/**
//	 * This function gets string of the DateTime in UTC time.
//	 * @return strUTC - String of the client's first seen UTC time. 
//	 */
//	public String getStrUTC() {
//		return this.time;
//	}


//	/**
//	 * This function gets string of the DateTime in UTC time and update the current strUTC based on the input.
//	 * @param strUTC
//	 */
//	public void setStrUTC(String strUTC) {
//		this.time = strUTC;
//	}


	/**
	 * creates an empty constructor.
	 * @throws ParseException 
	 */
	public info() throws ParseException {
	
		this.name=0;
		this.speed=0;
		this.radiusEat=0;
		//Date UTC = GMTtoUTC(time, -2);// this time fits to Israel summer time. 
		this.time = curTime2UTC();
		this.UTC = time.getTime();
	}

	

	public info(info data) throws ParseException {
		this(data.type,data.name,data.speed,data.radiusEat);
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getRadiusEat() {
		return radiusEat;
	}

	public void setRadiusEat(int radiusEat) {
		this.radiusEat = radiusEat;
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
		return null;
	}

	/**
	 * @return string of the client device's info beside the GeoPoint.
	 */
	@Override
	public String toString() {
		return this.name+","+this.speed+","+this.radiusEat+","+this.UTC+","+this.time;
	}

	/**
	 * This function gets UTC date in long and set the current data based on the input.
	 * @param UTC - Coordinated Universal Time
	 */
	public void setUTC(long UTC) {
		this.UTC = UTC;
	}

	@Override
	public int getName() {
		return this.name;
	}

	@Override
	public void setName(int name) {
		this.name=name;	}

	

	@Override
	public int getSpeedweight() {
		return this.speed;
	}

	@Override
	public int getRadius() {
		return this.radiusEat;
	}
	@Override
	public String getType() {
		return type;
	}

	
}
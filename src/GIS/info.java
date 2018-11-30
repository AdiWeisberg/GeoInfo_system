package GIS;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import Geom.Point3D;

public class info implements Meta_data{
	private long UTC;
	private String strUTC;
	private String SSID;
	private String type;
	private String BSSID;
	private String capabillities;
	

	public info(String SSID,String BSSID,String capabillities,String UTC, String type) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date = sdf.parse(UTC);
		this.strUTC = String.valueOf(date);
		this.UTC = date.getTime();
		this.SSID=SSID;
		this.type = type;
		this.BSSID=BSSID;
		this.capabillities=capabillities;
	}

	

	public void setCapabillities(String capabillities) {
		this.capabillities = capabillities;
	}

	public info(info data) {
		this.setUTC(data.getUTC());
		this.setSSID(data.getSSID());
		this.setType(data.getType());
		this.setBSSID(data.getBSSID());
		this.setCapabillities(data.getCapabillities());
		this.setStrUTC(data.getStrUTC());
	}
	
	public String getStrUTC() {
		return strUTC;
	}



	public void setStrUTC(String strUTC) {
		this.strUTC = strUTC;
	}



	public info() {
		this.UTC = 0;
		this.SSID = "";
		this.type = "";
		this.BSSID="";
		this.capabillities="";
		this.strUTC="";
	}
	
	@Override
	public long getUTC() {
		return this.UTC;
	}

	@Override
	public Point3D get_Orientation() { //Boaz told us to keep it NULL at this point.
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		return  this.getSSID()+","+this.getBSSID()+","+this.getCapabillities()+","+this.getUTC()+","+this.getType()+","+this.strUTC;
	}

	public void setUTC(long UTC) {
		this.UTC = UTC;
	}
	
	public String getSSID() {
		return SSID;
	}
	
	public void setSSID(String sSID) {
		SSID = sSID;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	public String getBSSID() {
		return BSSID;
	}

	public void setBSSID(String bSSID) {
		BSSID = bSSID;
	}

	public String getCapabillities() {
		return capabillities;
	}
}
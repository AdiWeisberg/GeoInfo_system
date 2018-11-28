package GIS;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import Geom.Point3D;

public class info implements Meta_data{
	private long UTC;
	private String SSID;
	private String type;

	public info(String UTC,String SSID, String type) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date = sdf.parse(UTC);
		this.UTC = date.getTime();
		this.SSID=SSID;
		this.type = type;
	}

	public info(info data) {
		this.setUTC(data.getUTC());
		this.setSSID(data.getSSID());
		this.setType(data.getType());
	}
	
	public info() {
		this.UTC = 0;
		this.SSID = "";
		this.type = "";
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
		return "SSID: "+this.getSSID()+" , UTC: "+this.getUTC()+" , Type: "+this.getType();
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
}
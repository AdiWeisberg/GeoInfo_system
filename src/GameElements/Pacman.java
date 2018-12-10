package GameElements;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import GIS.Meta_data;
import Geom.Point3D;

public class Pacman implements Meta_data {
	private Point3D point;
	private String time;
	private long UTC;
	private Color color;
	private String name;
	private int speed;
	private int radiusEat;
	static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public Pacman(Point3D point,String name,String time, Color color,int speed,int radiusEat ) throws ParseException{
		this.point=point;
		this.name=name;
		this.color=color;
		this.speed=speed;
		this.radiusEat=radiusEat;
		Date UTC = GMTtoUTC(time, -2);// this time fits to Israel summer time. 
		Date oldDate = sdf.parse(time);
		this.time= sdf.format(UTC);
		this.UTC = UTC.getTime();//gets the client's first seen long type date in UTC time.
	}
	
	public Date GMTtoUTC(String dateTime, int hours) throws ParseException {
		Date oldDate = sdf.parse(dateTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(oldDate);
		calendar.add(Calendar.HOUR_OF_DAY, hours);
		return calendar.getTime();
	}

	@Override
	public long getUTC() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getName() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setName(int name) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

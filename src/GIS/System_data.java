package GIS;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import Geom.Point3D;

public class System_data implements Meta_data{

	private int name;
	private Color color;
	private Date UTC;
	private long longUTC;
	private static int counter = 0;
	static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	public System_data() throws ParseException {
		this.name = ++counter;
		this.color = randColor();
		this.UTC = curTime2UTC();
		this.longUTC = UTC.getTime();
	}

	private Date curTime2UTC() throws ParseException { // based on Israel Summer time.
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		cal.add(Calendar.HOUR_OF_DAY, -2);
		String UTC = sdf.format(cal.getTime());
		return sdf.parse(UTC);
	}
	private Color randColor() {
		Random rand = new Random();

		// Java 'Color' class takes 3 floats, from 0 to 1.
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();

		Color randomColor = new Color(r, g, b);
		return randomColor;
	}
	@Override
	public long getUTC() {
		return this.longUTC;
	}

	@Override
	public int getName() {
		return name;
	}

	@Override
	public void setName(int name) {
		this.name = name;
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getStrUTC() {
		return sdf.format(this.UTC);
	}

	public void setLongUTC(long longUTC) {
		this.longUTC = longUTC;
	}

	public void setUTC(Date uTC) {
		UTC = uTC;
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toString() {
		return "name: "+this.getName()+" , Color: "+this.getColor()+" , Time: "+this.getStrUTC()+" ";
	}

}


package GIS;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import Geom.Point3D;
/**class System_data- Represents information of a layer or a collection of geographic layers. 
 * It contains: the time when the layer or collection is created,
the color in which it will be displayed in Google earth created in a random manner
 * @author Naomi and Adi*/
public class System_data implements Meta_data{

	private int name;
	private Color color;
	private Date UTC;
	private long longUTC;
	private static int counter = 0;
	static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/** constructor 
	 * @throws ParseException - Because you convert String information to a date then it can bounces an error if you can not convert*/
	public System_data() throws ParseException {
		this.name = ++counter;
		this.color = randColor();
		this.UTC = curTime2UTC();
		this.longUTC = UTC.getTime();
	}
	//Create time by computer time now.
	private Date curTime2UTC() throws ParseException { // based on Israel Summer time.
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		cal.add(Calendar.HOUR_OF_DAY, -2);
		String UTC = sdf.format(cal.getTime());
		return sdf.parse(UTC);
	}
	//Creates color in a random way.
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
		return "name: "+this.getName()+" , Time: "+this.getStrUTC()+" ";
	}
	@Override
	public String getType() {
		return null;
	}
	@Override
	public int getSpeedweight() {
		return 0;
	}
	@Override
	public int getRadius() {
		return 0;
	}
	@Override
	public int getID() {
		return 0;
	}
	@Override
	public boolean isEaten() {
		return false;
	}
	@Override
	public void setEaten(boolean isEaten) {
		
	}

}
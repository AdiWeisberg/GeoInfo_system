package GameElements;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;

import GIS.GIS_element;
import GIS.Meta_data;
import GIS.info;
import Geom.Geom_element;
import Geom.Point3D;
/**class Fruit- represent a object whit gps point, data(time) and id.
 * @author Naomi and Adi
 *
 */
public class Fruit  implements GIS_element{

	private Point3D point;
	private Meta_data data;
	private boolean isEaten;
	private int ID; // id for each fruit
	/** empty contractor*/
	public Fruit() {
	}
/**
 * Contractor
 * @param type- "f"/"F".
 * @param name - the index in the array.
 * @param point- gps point.
 * @param weight- weight of the fruit.
 * @param time -null
 * @param radius
 * @throws ParseException- for the time
 */
	public Fruit(String type,int name,Point3D point, int weight ,String time,int radius) throws ParseException{
		this();
		this.point=new Point3D(point);
		this.data=new info(type,name,weight,0);
		this.isEaten=false;
		this.ID = point.getID();
	}
	/** copy contractor
	 * @param p- other Point
	 * @throws ParseException -for the time */
	public Fruit(Point3D p) throws ParseException {
		this();
		this.point=p;
		data= new info("f",1,1,0);
		this.isEaten=false;
		this.ID = p.getID();
	}
	/**contractor*/
	public Fruit(Point3D point,Meta_data newData) {
		this();
		this.data=newData;
		this.point=new Point3D(point);
		this.isEaten=false;
		this.ID = point.getID();
	}
	/**interface copy contractor*/
	public Fruit(GIS_element fruit) {
		this.point=fruit.getPoint();
		this.data=fruit.getData();
		this.isEaten=((Fruit) fruit).isEaten();
		this.ID = fruit.getID();
	}
	public int getID() {
		return this.ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public boolean isEaten() {
		return isEaten;
	}

	public void setEaten(boolean isEaten) {
		this.isEaten = isEaten;
	}

	public void addMeta_data(Meta_data newData) {
		this.data=newData;
		this.point=newData.get_Orientation();
	}
	public Point3D getPoint() {
		return point;
	}
	
	public void setPoint(Point3D point) {
		this.point = new Point3D(point);
	}

	public Meta_data getData() {
		return data;
	}

	public void setData(Meta_data data) {
		this.data = data;
	}

	@Override
	public Geom_element getGeom() {
		return this.point;
	}

	@Override
	public void translate(Point3D vec) {
		// TODO Auto-generated method stub

	}

	public void set(Fruit fruit) {
		this.data=fruit.data;
		this.point=new Point3D (fruit.point);
		this.isEaten=fruit.isEaten;
		this.ID = fruit.getID();
	}
}
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
/**class Pacman- represent a object whit gps point, data(time), score and id.
 * @author Naomi and Adi*/
public class Pacman implements GIS_element {

	private Point3D point;
	private Meta_data data;	
	private int score;
	private int ID; // id for each pacman
	private String startTime;
	private String endTime;
public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
/** empty contractor*/
	public Pacman() {
		this.point = null;
		this.score = 0;
	}
/** copy contractor
 * @param p- other pacman
 * @throws ParseException- if the creating time did not work. */
	public Pacman(Pacman p) throws ParseException {
		this(p.getPoint());
	}

	public Pacman(String type,int name,Point3D point, int speed,int radiusEat ) throws ParseException{
		this();
		data= new info(type,name,speed,radiusEat);
		this.point = new Point3D(point);
		this.ID = point.getID();
	}
	public Pacman(Point3D p) throws ParseException {
		this();
		this.point=new Point3D(p);
		data= new info("p",1,1,1);
		this.ID = p.getID();
	}
	public Pacman(GIS_element pacman) {
		this();
		this.point=new Point3D(pacman.getPoint());
		this.data = pacman.getData();
		this.ID = pacman.getData().getID();
	}
	public Pacman(Point3D point,Meta_data newData) {
		this();
		this.data=newData;
		this.point=new Point3D(point);
		this.ID = point.getID();
	}
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
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
	public void setPoint(double x,double y,double z) {
		this.point.set_x(x);
		this.point.set_y(y);
		this.point.set_z(z);
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
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
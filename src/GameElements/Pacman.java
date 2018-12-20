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

public class Pacman implements GIS_element {


	private Point3D point;
	private Meta_data data;	
	private int score;


	public Pacman() {
	}

	public Pacman(Pacman p) throws ParseException {
		this(p.getPoint());
	}

	public Pacman(String type,int name,Point3D point, int speed,int radiusEat ) throws ParseException{
		this();
		data= new info(type,name,speed,radiusEat);
		this.point = new Point3D(point);

	}
	public Pacman(Point3D p) throws ParseException {
		this();
		this.point=new Point3D(p);
		data= new info("p",1,1,1);
	}
	public Pacman(GIS_element pacman) {
		this();
		this.point=new Point3D(pacman.getPoint());
		this.data = pacman.getData();
	}
	public Pacman(Point3D point,Meta_data newData) {
		this();
		this.data=newData;
		this.point=new Point3D(point);
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
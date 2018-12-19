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

public class Fruit  implements GIS_element{

	private Point3D point;
	private Meta_data data;
	private boolean isEaten;
	
	public Fruit() {
	}

	public Fruit(String type,int name,Point3D point, int weight ,String time,int radius) throws ParseException{
		this();
		this.point=point;
		this.data=new info(type,name,weight,0);
	}
	public Fruit(Point3D p) throws ParseException {
		this();
		this.point=p;
		data= new info("f",1,1,0);
	}
	public Fruit(Point3D point,Meta_data newData) {
		this();
		this.data=newData;
		this.point=point;
	}
	public Fruit(GIS_element fruit) {
		this.point=fruit.getPoint();
		this.data=fruit.getData();
	}
	public void addMeta_data(Meta_data newData) {
		this.data=newData;
		this.point=newData.get_Orientation();
	}
	public Point3D getPoint() {
		return point;
	}
	
	public void setPoint(Point3D point) {
		this.point = point;
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
}
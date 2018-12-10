package GameElements;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import GIS.GIS_element;
import GIS.Meta_data;
import GIS.info;
import Geom.Geom_element;
import Geom.Point3D;

public class Pacman implements GIS_element {
	private Point3D point;
	private Meta_data data;
	
	public Pacman(Point3D point,int name,String time, int speed,int radiusEat ) throws ParseException{
		data= new info(point,name,time,speed,radiusEat);
		this.point = new Point3D(point);
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

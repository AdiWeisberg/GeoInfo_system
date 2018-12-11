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

public class Fruit  implements GIS_element{

	private Point3D point;
	private Meta_data data;

	
	public Fruit(String type,int name,Point3D point, int weight ,String time,int radius) throws ParseException{
		this.point=point;
		this.data=new info(type,name,point,weight,0);
	}
	public Fruit(Meta_data newData) {
		this.data=newData;
		this.point=newData.get_Orientation();
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
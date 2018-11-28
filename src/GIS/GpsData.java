package GIS;

import Geom.Geom_element;
import Geom.Point3D;

public class GpsData implements GIS_element{

	private Point3D gpsPoint;
	private Meta_data data;

	public	GpsData(double _long,double _lat,double _alt,info data) {
		gpsPoint= new Point3D(_long,_lat,_alt);
		this.data=new info(data);
	}
	public	GpsData(Point3D p,info data) {
		this.data=new info(data);
		gpsPoint= new Point3D(p.x(),p.y(),p.z());
	}
	public	GpsData() {
		gpsPoint= new Point3D();
		data= new info();
	}
	@Override
	public Geom_element getGeom() {
		Geom_element p= new Point3D();
		return p;
	}

	public Meta_data getData() {
	
		return this.getData();
	}

	public void setData(Meta_data data) {
		this.data = data;
	}
	@Override
	public void translate(Point3D vec) {
		gpsPoint= new Point3D(vec.x(),vec.y(),vec.z());
	}
	
	public double get_long() {
		return gpsPoint.y();
	}
	public void set_long(double _long) {
		gpsPoint.set_y(_long);
	}
	public double get_lat() {
		return  gpsPoint.x();
	}
	public void set_lat(double _lat) {
		gpsPoint.set_x(  _lat); 
	}
	public double get_alt() {
		return gpsPoint.z();
	}
	public void set_alt(double _alt) {
		gpsPoint.set_z(_alt);
	}
	public String toString() {
		return "point:"+gpsPoint.x()+","+gpsPoint.y()+","+gpsPoint.z()+","+"Meta data:" +data.toString() ;
	}
	public String PointString() {
		return ""+gpsPoint.x()+","+gpsPoint.y()+","+gpsPoint.z();
	}
public String DataString() {
		return data.toString();
	}

}
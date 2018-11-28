package GIS;

import Geom.Geom_element;
import Geom.Point3D;

public class GpsData implements GIS_element{
	private double _long;
	private double _lat;
	private double _alt;
	private Meta_data data;

	public	GpsData(double _long,double _lat,double _alt,info data) {
		this._alt=_alt;
		this._lat=_lat;
		this._long=_long;
		this.data=new info(data);
	}
//	public	GpsData(Point3D p) {
//		this._lat=p.x();
//		this._long=p.y();
//		this._alt=p.z();
//	}
	public	GpsData() {
		this._alt=0;
		this._lat=0;
		this._long=0;
		data= new info();
	}
	@Override
	public Geom_element getGeom() {
		Geom_element p= new Point3D(this._lat, this._long, this._alt);
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
		// TODO Auto-generated method stub
		this._lat=vec.x();
		this._long=vec.y();
		this._alt=vec.z();
	}
	
	public double get_long() {
		return _long;
	}
	public void set_long(double _long) {
		this._long = _long;
	}
	public double get_lat() {
		return _lat;
	}
	public void set_lat(double _lat) {
		this._lat = _lat;
	}
	public double get_alt() {
		return _alt;
	}
	public void set_alt(double _alt) {
		this._alt = _alt;
	}
	public String toString() {
		return "point:"+this._lat+","+this._long+","+this._alt+","+"Meta data:" +data.toString() ;
	}
	


}
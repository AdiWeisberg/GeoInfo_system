package GIS;

import Geom.Geom_element;
import Geom.Point3D;
/**A class that represents a row from a csv file,
 *  has a point by class -Point3D and information by the info class.
 *  @implements GIS_element
 * @author Naomi and Adi*/
public class GpsData implements GIS_element{

	private Point3D gpsPoint;
	private Meta_data data;
/**
 * A constructor that receives 3 numbers that document a point and information
 * @param _long - long y
 * @param _lat - lat x
 * @param _alt - alt z
 * @param data
 */
	public	GpsData(double _long,double _lat,double _alt,info data) {
		gpsPoint= new Point3D(_long, _lat,_alt);
		this.data=new info(data);
	}
	/**
	 * A constructor that receives point3d that document a point and information
	 * @param p -Point3D 
	 * @param data - info
	 */
	public	GpsData(Point3D p,info data) {
		this.data=new info(data);
		gpsPoint= new Point3D(p.x(),p.y(),p.z());
	}
	
	/** An empty constructor*/
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
	
	/** Changing the point  @param vec- the new point */
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
	/**toString function */
	public String toString() {
		return ""+gpsPoint.x()+","+gpsPoint.y()+","+gpsPoint.z()+","+data.toString();
	}
	/** Turns the Point into one String @return String */
	public String PointString() {
		return ""+gpsPoint.x()+","+gpsPoint.y()+","+gpsPoint.z();
	}
	/** Turns the information into one String @return String */
	public String DataString() {
		return data.toString();
	}

}
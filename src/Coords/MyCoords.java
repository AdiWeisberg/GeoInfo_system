package Coords;

import Geom.Point3D;

public class MyCoords implements coords_converter  {
	private final int EarthRadius=6371000;
	private final double LonNorm=0.8470911;

	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		Point3D ans=trans_gpsToMeter(gps);
		ans.add(local_vector_in_meter);
		return ans;
	}

	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		Point3D p_meter=vector3D(gps0,gps1) ;
		double ans= Math.sqrt(Math.pow(p_meter.x(),2)+Math.pow(p_meter.y(),2));
		return ans;

	}

	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		Point3D p=new Point3D(-gps1.x(),-gps1.y(),-gps1.z());
		gps0.add(p);
		Point3D ans= trans_gpsToMeter(gps0);
		return ans;
	}

	@Override
	public  double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		double [] ans = new double [3];
		System.out.println(gps0.x());
		ans[0]=bearing(gps0.x(),gps0.y(),gps1.x(),gps1.y());
		double z= Math.abs(gps0.z()-gps1.z());
		ans[2]=distance3d(gps0,gps1);//dist
		ans[1]=Math.asin(z/ans[2]);//elevation

		return ans;
	}

	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		boolean flg=true;
		if(!(-180<p.x()&&p.x()<180))flg=false;
		if(!(-90<p.y()&&p.y()<90))flg=false;
		if(!(-450<p.z()&&p.z()>450))flg=false;
		return flg;
	}
	private  Point3D trans_gpsToMeter(Point3D gps) {
		double x,y,z=gps.z();
		x=(gps.x()*Math.PI)/180;
		y=(gps.y()*Math.PI)/180;
		x=Math.sin(x)*EarthRadius;
		y=Math.sin(y)*EarthRadius*LonNorm;
		Point3D ans=new Point3D (x,y,z);
		return ans;

	}
	protected static double bearing(double lat1, double lon1, double lat2, double lon2){
		  double longitude1 = lon1;
		  double longitude2 = lon2;
		  double latitude1 = Math.toRadians(lat1);
		  double latitude2 = Math.toRadians(lat2);
		  double longDiff= Math.toRadians(longitude2-longitude1);
		  double y= Math.sin(longDiff)*Math.cos(latitude2);
		  double x=Math.cos(latitude1)*Math.sin(latitude2)-Math.sin(latitude1)*Math.cos(latitude2)*Math.cos(longDiff);

		  return (Math.toDegrees(Math.atan2(y, x))+360)%360;
		}

}
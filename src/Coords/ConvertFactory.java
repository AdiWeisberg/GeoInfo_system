package Coords;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GUI.MyFrame;
import Geom.Point3D;
/**class ConvertFactor - Converts points from GPS to pixel and from pixel to gps,
 * and calculates distance between two pixel points (calculates distance on GPS).
 *Depends on the link-https://stackoverflow.com/questions/38748832/convert-longitude-and-latitude-coordinates-to-image-of-a-map-pixels-x-and-y-coor
 * @author Adi and Naomi.
 *
 */
public class ConvertFactory {


	//private BufferedImage myImg;
	static  int mapWidth , mapHeight;
	// offsets
	static final double mapLongitudeStart =  35.202574, mapLatitudeStart = 32.106046;
	// length of map in long/lat
	static final double mapLongitude = 35.212405 -mapLongitudeStart, 
			// invert because it decreases as you go down
			mapLatitude = mapLatitudeStart - 32.101858  ;

	public Point3D startPoint = new Point3D(35.202574,32.106046);
	public Point3D endPoint = new Point3D(35.212405,32.101858);

	/**constructor */
	public ConvertFactory(){
	
	}

	/**
	 * function GpsToPicsel - Gets a GPS point and converts it to a pixel point in the image.
	 * @param gps- gps point
	 * @return - pixel point
	 */
	public Point3D GpsToPicsel(Point3D gps, double width, double height) {
		Point3D GPS=new Point3D();
		// use offsets
		GPS.set_y( gps.y() - mapLongitudeStart);
		// do inverse because the latitude increases as we go up but the y decreases as we go up.
		// if we didn't do the inverse then all the y values would be negative.
		GPS.set_x( mapLatitudeStart-gps.x());

		// set x & y using conversion
		int x = (int) (width*(GPS.y()/mapLongitude));
		int y = (int) (height*(GPS.x()/mapLatitude));

		return new Point3D(x, y);
	}
	/**
	 * function PicselToGps - Gets a pixel point and converts it to GPS point
	 * @param picsel
	 * @return- gps point
	 */
	public Point3D PicselToGps(Point3D picsel, double width, double height) {
		double y= ((mapLongitude*picsel.x())/width)+mapLongitudeStart;
		double x= -((mapLatitude*picsel.y() )/height)+mapLatitudeStart;
		return new Point3D(x,y);
	}
	/**
	 * function distancePicsel-Calculates the distance between two pixel points by converting 
	 * to GPS point and calculating distance and conversion DOUBLE 
	 * @param picsel0- point 0
	 * @param picsel1 - point 1
	 * @return double of distance
	 */
	public double distancePicsel(Point3D picsel0,Point3D picsel1, int width, int height) {
		MyCoords myCoords= new MyCoords();
		Point3D gps0= PicselToGps(picsel0, width, height);
		Point3D gps1=PicselToGps(picsel1, width, height);
		return myCoords.distance3d(gps0, gps1);			
	}
	
	public static double distanceGPS(Point3D gps0,Point3D gps1) {
		MyCoords myCoords= new MyCoords();
		return myCoords.distance3d(gps0, gps1);		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Map();
		MyFrame	my= new MyFrame();
		ConvertFactory	conv= new ConvertFactory( );
		Point3D P=new Point3D(32.102495,35.207462);
		Point3D p1=new Point3D(720,544);
		Point3D p2= new Point3D(672,598);
		double answer= conv.distancePicsel(p1,p2,my.getWidth(),my.getHeight());

		System.out.println(answer);
	}

}
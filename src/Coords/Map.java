package Coords;

//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import Geom.Point3D;

/**
 * This class represents a map that contains image and 2 GPS points that helps to adjust the image of the map to the world map.
 * This class calls: 
 * [*] ImageFactory in order to set the map's image and handle it's functionality.
 * [*] ConvertFactory - in order to handle convert pixels to GPS points, GPS to pixels and to calculate distance between 2 coords.
 * @author Adi && Naomi
 */
public class Map{
	private BufferedImage img;
	private ConvertFactory cf;
	
	/**
	 * constructor for map that try to read an Image and calls ImageFactory to handle the image functionality.
	 */
	public Map() { // constructor
		try {
			this.img =ImageIO.read(new File("Ariel.jpg"));
		} catch (IOException e) {
			System.out.println("I'm in");
			e.printStackTrace();
			
		}
		new ImageFactory(this.img); // take care for the creation of the map image and it's functionality.
		cf = new ConvertFactory(this.getImg());
	}

	/**
	 * This function returns the image of the map.
	 * @return img - the image of the map.
	 */
	public BufferedImage getImg() {
		return img;
	}

	/**
	 * This function sets the image of the map. 
	 * @param img -  the image of the map. 
	 */
	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public static void main(String[] args) {
		Map map = new Map(); // creates Default map.
		Point3D P=new Point3D(32.102495,35.207462);
		System.out.println(map.getCf().GpsToPicsel(P));
		Point3D p1=new Point3D(720,544);
		System.out.println(map.getCf().PicselToGps(p1));
		Point3D p2= new Point3D(672,598);
		System.out.println(map.getCf().distancePicsel(p1,p2));

	}

	public ConvertFactory getCf() {
		return cf;
	}

	public void setCf(ConvertFactory cf) {
		this.cf = cf;
	}

}
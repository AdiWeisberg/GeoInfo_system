package File_format;

import java.awt.Image;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import Coords.Path;
import GIS.*;
import GUI.MyFrame;
import GameElements.Fruit;
import GameElements.Pacman;
import Geom.Point3D;

public class Path2KML {
	private static int counter = 0;
	private MyFrame frame;
	public Path2KML(ArrayList<Path> paths,MyFrame frame, String fileName) {
		writeFile(paths, fileName);
		this.frame=frame;
	}
	/**
	 * This function gets a geoPoint and returns it's Icon (based on the point's type).
	 * @param point - geo point
	 * @return Image of the point to display on google earth.
	 */
	//	private Image chooseIcon(GIS_element point) {
	//		MyFrame mf = new MyFrame();
	//		String type = point.getData().getType();
	//		if(type.equals("Pacman"))
	//			return mf.getPacmanIcon();
	//		else if(type.equals("Fruit"))
	//			return mf.getDountIcon();
	//		return null;
	//	}
	/**
	 * This function gets ArrayList of layers and converts it to KML file that can be display on google earth.
	 * @param a - gets ArrayList of layers 
	 * @param output - choose the name of the new KML file.
	 */
	public void writeFile(ArrayList<Path> pathes, String output) {
		ArrayList<String> content = new ArrayList<String>(); // the content in long String
		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document>"	
				//				+"<Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href>"
				//				+"</Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle>"
				//				+"<Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon>"
				//				+"</IconStyle></Style><Style id=\"green\"><IconStyle><Icon>"
				//	+"<href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style>"
				+"<name>GeoLayer</name>";
		content.add(kmlstart);

		String kmlend = "</Document></kml>";
		Iterator<Path> itr = pathes.iterator();
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(output);
			bw = new BufferedWriter(fw);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(itr.hasNext()) {
			Path currPath = itr.next();
			String kmlStartlayer = "<Folder><name>"+currPath.getPoints().get(0).getID()+"</name>\n"+
					"<LineStyle><color>#ff0000ff</color>"+"<width>15</width>\n";
			content.add(kmlStartlayer);
			String kmlelement = "<Folder><Placemark><name>"+currPath.getName()+"</name>";
			for(int i=0; i<currPath.size(); i++) {
				Point3D currPoint = currPath.getPoints().get(i);
				String GPSelement = currPoint.toString();
				//					Image pointIcon = chooseIcon(currPoint); // choose Pacman or Dount Icon.
				String[] gpsData =  GPSelement.split(","); // split by ','
				//start building String of coordinates

				kmlelement =
						"<name>"+currPoint.getID()+"</name>\n" +
								"<TimeStamp><when>"+currPoint.getTimeStamp()+"</when></TimeStamp>"+
								//"<description>Type: <b>"+currPoint+"</b><br/></description>\n"
								//+ "<Style id=\"icon\"><IconStyle><Icon><href>"+pointIcon+"</href></Icon></IconStyle></Style>\r\n"
								"<Point>\n" + 
								"<coordinates>\r"+gpsData[0]+","+gpsData[1]+","+gpsData[2]+"\n"+"</coordinates>" +
								"</Point>\n" +
								"</Placemark>\n";
				content.add(kmlelement);
			}

			String kmlEndlayer = "</LineStyle></Folder>\n";
			content.add(kmlEndlayer);
			content.add(kmlend);
		}
		String kml = content.toString().replace("[", "").replace("]", "");
		try {
			bw.write(kml);
			bw.close();
			System.out.println("Kml has been created! ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



}
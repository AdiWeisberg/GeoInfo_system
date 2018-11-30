package File_format;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import GIS.*;

public class JAVA_to_KML {

	public void writeFile(ArrayList<GIS_layer> a, String output) {
		ArrayList<String> content = new ArrayList<String>(); // the content in long String
		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document>"	
		+"<Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href>"
				+"</Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle>"
		+"<Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon>"
				+"</IconStyle></Style><Style id=\"green\"><IconStyle><Icon>"
		+"<href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style>"
				+"<name>GeoLayers</name>";
		content.add(kmlstart);

		String kmlend = "</Document></kml>";
		Iterator<GIS_layer> itr = a.iterator();
		try{
			FileWriter fw = new FileWriter(output); // path to the new address
			BufferedWriter bw = new BufferedWriter(fw);
			while(itr.hasNext()) {
				GIS_layer currLayer = itr.next();
				ArrayList<String> GPSelement = currLayer.layerString();
				String kmlStartlayer = "<Folder><name>"+currLayer.getName()+"</name>";
				content.add(kmlStartlayer);
				for (int i = 0; i < GPSelement.size(); i++) {
					String[] gpsData =  GPSelement.get(i).split(",");
					String kmlelement ="<Placemark>\n" +
							"<name>"+gpsData[3]+"</name>\n" +
							"<description>BSSID: <b>"+gpsData[4]+"</b><br/>Capabilities: <b>"
									+gpsData[5]+"</b><br/>Type: <b>"+gpsData[7]+"</b><br/>Timestamp: <b>"
									+gpsData[6]+"</b><br/>Date: <b>"+gpsData[8]+"</b></description>\n"
							+ "<styleUrl>#red</styleUrl>\r\n" + 
							"<Point>\r\n" + 
							"<coordinates>"+gpsData[0]+","+gpsData[1]+"</coordinates>" +
							"</Point>\n" +
							"</Placemark>\n";
					content.add(kmlelement);
				}
				String kmlEndlayer = "</Folder>\n";
				content.add(kmlEndlayer);
			} 
			content.add(kmlend);
			String csv = content.toString().replace("[", "").replace("]", "");
			bw.write(csv);
			bw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}

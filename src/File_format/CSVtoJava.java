package File_format;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

import GIS.GIS_layer;
import GIS.GpsData;
import GIS._layer;
import GIS.info;
/**
 * This class converts CSV files to java data structures.
 */

public class CSVtoJava {
//This class gets paths of csv files and convert it to GIS_layer(java class).
	/**
	 * This function takes the CSV files and read them into new layers for each CSV file. 
	 * 
	 * @param paths - ArrayList of CSV path files to convert.
	 * @return
	 * @throws ParseException - if the date time can't be convert.
	 */
	public ArrayList<GIS_layer> convert(ArrayList<String> paths) throws ParseException {
		Iterator<String> itr = paths.iterator();
		ArrayList<GIS_layer> newProject= new ArrayList<GIS_layer>();
		while(itr.hasNext()) {
			String filePath = itr.next();
			String line = "";
			String cvsSplitBy = ",";
			GIS_layer newLayer = new _layer();
			try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
			{
				line = br.readLine();
				line = br.readLine();
				while ((line = br.readLine()) != null) //after 2 line
				{
					String[] userInfo = line.split(cvsSplitBy);

					info data =new info(userInfo[1],userInfo[0],userInfo[2],userInfo[3], userInfo[10]);
					GpsData newData=new GpsData(Double.parseDouble(userInfo[7]),
							Double.parseDouble(userInfo[6]),Double.parseDouble(userInfo[8]),data);
					newLayer.add(newData);
					System.out.println(newData);
				}
				newProject.add(newLayer);

			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		return newProject;
	}
}

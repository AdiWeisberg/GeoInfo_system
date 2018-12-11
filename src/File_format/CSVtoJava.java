package File_format;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GpsData;
import GIS.Meta_data;
import GIS._layer;
import GIS.info;
import GameElements.Game;
import Geom.Point3D;
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
	public ArrayList<Meta_data> convert(ArrayList<String> paths) throws ParseException {
		Iterator<String> itr = paths.iterator();
		//ArrayList<Game> newProject= new ArrayList<Game>();
		ArrayList<Meta_data> newdata= new ArrayList<Meta_data>();
		while(itr.hasNext()) {
			String filePath = itr.next();
			String line = "";
			String cvsSplitBy = ",";
			//ArrayList<Meta_data> newdata= new ArrayList<Meta_data>();
			try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
			{
				//line = br.readLine();
				line = br.readLine();
				while ((line = br.readLine()) != null) //after 2 line
				{
					String[] userInfo = line.split(cvsSplitBy);
					
					//System.out.println(userInfo[0]+","+userInfo[1]+","+userInfo[2]+","+userInfo[3]+","+userInfo[4]+","+userInfo[5]);
					Point3D p=new Point3D(Double.parseDouble(userInfo[2]),Double.parseDouble(userInfo[3]),Double.parseDouble(userInfo[4]));
					info data =new info(userInfo[0],Integer.parseInt(userInfo[1]),p,Integer.parseInt(userInfo[5]),0);
					if(userInfo.length>6) {
						data.setRadiusEat(Integer.parseInt( userInfo[6]));
					//	System.out.println(userInfo[6]+",");
					}
					newdata.add(data);
					
				}
				//newProject.add(newdata);

			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		return newdata;
	}
}

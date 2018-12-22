package File_format;

import java.io.BufferedReader;
import java.io.File;
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
import GameElements.Fruit;
import GameElements.Game;
import GameElements.Pacman;
import Geom.Point3D;
/**
 * This class converts CSV path to java data structures.
 */

public class CSVtoJava {
	private int counter = -1;
	//This class gets paths of csv path and convert it to GIS_layer(java class).
	/**
	 * This function takes the CSV path and read them into new layers for each CSV file. 
	 * 
	 * @param path - ArrayList of CSV path path to convert.
	 * @return
	 * @throws ParseException - if the date time can't be convert.
	 */
	public ArrayList<GIS_element> convert(String path) throws ParseException {

		ArrayList<GIS_element> newData= new ArrayList<GIS_element>();
		//	while(itr.hasNext()) {
		//String filePath = itr.next();
		String line = "";
		String cvsSplitBy = ",";
		

		//ArrayList<Meta_data> newdata= new ArrayList<Meta_data>();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) 
		{
			//line = br.readLine();
			line = br.readLine();
			while ((line = br.readLine()) != null) //after 2 line
			{
				String[] userInfo = line.split(cvsSplitBy);


				//System.out.println(userInfo[0]+","+userInfo[1]+","+userInfo[2]+","+userInfo[3]+","+userInfo[4]+","+userInfo[5]);

				Point3D p=new Point3D(Double.parseDouble(userInfo[2]),Double.parseDouble(userInfo[3]),Double.parseDouble(userInfo[4]));
				p.setID(counter++);
				info data =new info(userInfo[0],Integer.parseInt(userInfo[1]),Integer.parseInt(userInfo[5]),0);
				if(userInfo.length>6) {
					data.setRadiusEat(Integer.parseInt( userInfo[6]));
					//	System.out.println(userInfo[6]+",");
				}
				GIS_element temp;
				if(data.getType().equals("f")||data.getType().equals("F")) {
					temp=new Fruit(p,data);
					newData.add(temp);
				}
				else if(data.getType().equals("p")||data.getType().equals("P")) {
					temp=new Pacman(p,data);
					newData.add(temp);
				}



			}
			//newProject.add(newdata);
			System.out.println("CSV to Java created successfully !!!");
		} catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("error in CSV to Java");
		}
		
		
		return newData;
	}
}

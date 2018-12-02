package File_format;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import GIS.*;

/**
 * The purpose of this class is to convert CSV file to java data structures.
 * The user asked to write a path to read the files from, And has 2 options:
 * 1) select path of 1 CSV file. 
 * 2) Select a folder path that contains one or more CSV files.
 * The function store all the CSV files paths in ArrayList and send them to convert (CSVtoJava).
 * @author Naomi && Adi
 *
 */

public class Csv2kml {

/**
 * This function store string of CSV files paths in ArrayList and add convert their data to layers in new project.
 * the function check if the path is valid, and if it is one of the two following: 
 * 1) CSV file. 
 * 2) Folder with list of files.
 * the function send the paths to further handling.
 * After the relevant data added to the java project, the function calls writeFile in order to convert 
 * data structures to KML file that can be displayed at Google-Earth.
 * @param path - gets path of CSV file or folder that contain CSV files.
 * @throws ParseException - if the date time can't be convert.
 */
	public static void createProject(String path) throws ParseException {
		ArrayList<String> paths= new ArrayList<String>();
		ArrayList<GIS_layer> p = new ArrayList<GIS_layer>();
		CSVtoJava ctj = new CSVtoJava();
		JAVA_to_KML jtk = new JAVA_to_KML();
		if((path.contains("\\"))){
			if(path.endsWith(".csv"))
				paths.add(path);
			else if(!path.contains(".")){
				MultiCSV mult =  new MultiCSV();
				paths.addAll(mult.scanCSV(path));
			}
			else System.err.println("the path is not valid, please try again.");
		}
		else System.err.println("the path is not valid, please try again.");
		p.addAll(ctj.convert(paths));
		project p1= new project(p);
		jtk.writeFile(p1.getLayers(), "newKml.kml");
	}

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		System.out.println("Please type a CSV file OR folder that containing CSV files: ");
		Scanner sc = new Scanner(System.in);
		String path = sc.nextLine();
		createProject(path); // \...\GeoInfo_System\FilesToTransfare

	}

}


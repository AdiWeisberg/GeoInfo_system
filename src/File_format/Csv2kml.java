package File_format;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import GIS.*;


public class Csv2kml {

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
			else System.err.println("the path is not valid, please try again.");;
		}
		else System.err.println("the path is not valid, please try again.");;
		p.addAll(ctj.convert(paths));
		project p1= new project(p);
		jtk.writeFile(p1.getLayers(), "newKml.kml");
	}

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		//System.out.println("Enter: ");
		//Scanner sc = new Scanner(System.in);
		//String path = sc.nextLine();
		createProject("C:\\Files");



	}

}


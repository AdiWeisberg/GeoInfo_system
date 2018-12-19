package File_format;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.Meta_data;
import GIS.info;
import GameElements.Fruit;
import GameElements.Game;
import GameElements.Pacman;
/**
 * link- https://examples.javacodegeeks.com/core-java/writeread-csv-files-in-java-example/
 * @author n&e
 *
 */
public class JavaToCSV {
	//Delimiter used in CSV file
		private static final String COMMA_DELIMITER = ",";
		private static final String NEW_LINE_SEPARATOR = "\n";
		private static int id;
		//CSV file header
		private static final String FILE_HEADER = "Type,id,Lat,Lon,Alt,Speed/Weight,Radius";

		public static void writeCsvFile(String fileName ,ArrayList<GIS_element> fruits,ArrayList<GIS_element> pacmans) {
			FileWriter fileWriter = null;
					
			try {
				fileWriter = new FileWriter(fileName);

				//Write the CSV file header
				fileWriter.append(FILE_HEADER.toString());
				
				//Add a new line separator after the header
				fileWriter.append(NEW_LINE_SEPARATOR);
				
				//Write a new student object list to the CSV file
				Iterator<GIS_element> itr0 = fruits.iterator();
				Iterator<GIS_element> itr1 = pacmans.iterator();
				while(itr0.hasNext()) {
					Fruit newFruit= (Fruit) itr0.next();
					newFruit.getData().setName(id++);
					fileWriter.append(String.valueOf(newFruit.getData().getType()));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(newFruit.getData().getName()));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(newFruit.getPoint().x()));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(newFruit.getPoint().y()));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(newFruit.getPoint().z()));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(newFruit.getData().getSpeedweight()));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(newFruit.getData().getRadius()));
					fileWriter.append(NEW_LINE_SEPARATOR);
				}
				while(itr1.hasNext()) {
					Pacman newPacman= (Pacman) itr1.next();
					newPacman.getData().setName(id++);
					fileWriter.append(String.valueOf(newPacman.getData().getType()));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(newPacman.getData().getName()));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(newPacman.getPoint().x()));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(newPacman.getPoint().y()));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(newPacman.getPoint().z()));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(newPacman.getData().getSpeedweight()));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(newPacman.getData().getRadius()));
					fileWriter.append(NEW_LINE_SEPARATOR);
				}

				
				
				System.out.println("CSV file was created successfully !!!");
				
			} catch (Exception e) {
				System.out.println("Error in CsvFileWriter !!!");
				e.printStackTrace();
			} finally {
				
				try {
					fileWriter.flush();
					fileWriter.close();
				} catch (IOException e) {
					System.out.println("Error while flushing/closing fileWriter !!!");
	                e.printStackTrace();
				}
				
			}
		}
	}
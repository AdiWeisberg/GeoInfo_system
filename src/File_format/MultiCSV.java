package File_format;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * This class gets path of a folder and scan all the files in it to find CSV files.
 * @author Naomi && Adi
 *
 */
public class MultiCSV {

	/**
	 * This function gets path of a folder and scan all the files recursibly in it to find CSV files.
	 * @param path - path of a folder
	 * @return ArrayList of CSV path files in string.
	 */
	public static ArrayList<String> scanCSV(String path){
		File directory = new File(path);
		String[] directoryContents = directory.list();
		ArrayList<String> fileLocations = new ArrayList<String>();
		return fileLocations = scanRecurs(directory,directoryContents,fileLocations,0);
	}

	public static ArrayList<String> scanRecurs(File directory, String[] directoryContents, ArrayList<String> fileLocations, int index){
		if(index == directoryContents.length)
			return fileLocations;

		String fileName = directoryContents[index];
		if(fileName.endsWith(".csv")) {
			File temp = new File(String.valueOf(directory),fileName);
			fileLocations.add(String.valueOf(temp));
			return scanRecurs(directory,directoryContents,fileLocations,++index);
		}
		else {
			return scanRecurs(directory,directoryContents,fileLocations,++index);

		}
	}

}


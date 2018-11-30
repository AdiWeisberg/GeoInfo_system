package File_format;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MultiCSV {

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

	//	public static void main(String[] args) {
	//
	//		ArrayList<String> bla = scanCSV("C:\\Users\\עדי\\eclipse-workspace\\GeoInfo_System\\FilesToTransfare");
	//		
	//		//print-TEST: 
	//		Iterator<String> itr = bla.iterator();
	//		while(itr.hasNext()) {
	//			System.out.println(itr.next());
	//		}






	//		for(String fileName: directoryContents) {
	//			File temp = new File(String.valueOf(directory),fileName);
	//			fileLocations.add(String.valueOf(temp));
	//		}
}


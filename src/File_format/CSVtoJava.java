package File_format;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import GIS.GIS_layer;
import GIS.GpsData;
import GIS._layer;
import GIS.info;

public class CSVtoJava {
	CSVtoJava(String newCSV) throws ParseException{
		String csvFile = newCSV;
		String line = "";
		String cvsSplitBy = ",";
		GIS_layer newLayer = new _layer();
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
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
				//System.out.println(newData);
				//System.out.println("MAC: " + userInfo[0] + " , SSID: " + userInfo[1] +
				//	" AuthMode: " + userInfo[2] + " FirstSeen: " + userInfo[3] +"Channel:"+userInfo[4]+"RSSI:"+userInfo[5] 
				//		+"CurrentLatitude:"+userInfo[6]+"CurrentLongitude:"+userInfo[7]+"AltitudeMeters:"+userInfo[8]
				//			+"AccuracyMeters:"+userInfo[9]+"Type:"+userInfo[10]);
			}

		} catch (IOException e) 
		{
			e.printStackTrace();
		}

	}
}

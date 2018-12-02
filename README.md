# GeoInfo_system
Geographic information system that collect information from CSV files and converts it to KML and display it on Google-Earth
 @author Naomi and Adi
#packets_Details:
1. Coords:
- interface: coords_converter: Conversions of points and additional calculations on points.
- implements coords_converter: MyCoords
2. file_format:
- Csv2kml:
- Csvtojava: Gets a tile file CSV and converts it into the Geographic Layers object in java.
- JAVA_to_KML: Converts the JAVA object to a KML file.
- MultiCSV: Gets a folder of files and enters it recursively and creates an array of all the files that are in the folder.
3. Geom:
- interface: Geom_element: Functions that calculate distance
-implements  Geom_element: Point3D
4. GIS:
-interface: GIS_layer:
-_layer: A geographic layer
-interface: GIS_element:
-GpsData: Point with information.
-interface: GIS_project:
-project:A geographic layers
-interface: Meta_data:
-info:Row information
-System_data: layer or layers information.
5. Test:
-MyCoordsTest

6.GeoInfo_system_Diagram
#Get_Started:
In order to start you may want to have CSV files to see results.
Open the CsvToKml class to run it and it will ask you to register routing to CSV file or folder,
run and then the computer will open a file with a new KML extension that you can open in Google Earth and see the layers The geographical areas.

#Understand_deeply: Take a look at the  class GeoInfo_system_Diagram and the class Test.

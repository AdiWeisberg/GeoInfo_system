# GeoInfo_system
Geographic information system that collect information from CSV files and converts it to KML and display it on Google-Earth
# what new?! you have play pacman game, you can run game of pacman and fruit and the algoritem will calculat the fastest routes!look at this in the GUI animation-MyFrame-->open Game---->run.
 @author Naomi and Adi
# packets_Details:
1. Coords:
- interface: coords_converter: Conversions of points and additional calculations on points.
- implements coords_converter: MyCoords
- ConvertFactory: calculat distanc and translate from gms to pixel and from pixel to gps.
-Map: add the image of the map and conect thr image to the ConvertFactory.
-Path: arrayList of the paths of the pacman walk.
2. file_format:
- Csv2kml:
- Csvtojava: Gets a tile file CSV and converts it into the Geographic Layers object in java.
- JAVA_to_KML: Converts the JAVA object to a KML file.
- MultiCSV: Gets a folder of files and enters it recursively and creates an array of all the files that are in the folder.
-CSVtoJava
-JavaToCSV
-Path2KML: gets a array of path from the Thead arraylist and makes it to kml file.
3.GameElement:
-Pacman
-Fruit
-Game: have two arraylist of GIS_element (fruit,pacman).
4. Geom:
- interface: Geom_element: Functions that calculate distance
-implements  Geom_element: Point3D
5. GIS:
-interface: GIS_layer:
-_layer: A geographic layer
-interface: GIS_element:
-GpsData: Point with information.
-interface: GIS_project:
-project:A geographic layers
-interface: Meta_data:
-info:Row information
-System_data: layer or layers information.
6.GUI:
-MyFranme
-ThreadPacman: implements Runnable, a object of one Thread PACMAN- produces a simulation of the walkmanws walk througt a fruit path.
7.Test:
-MyCoordsTest
-ConvertFactoryTest
-MapTest
-TestShortestPathAlgo
6.GeoInfo_system_Diagram

# Get_Started:
In order to start you may want to open the class MyFrame and run, it will open the map gui, you can choose from the following option:
-add pacman
-add fruit
-save game to csv file
-open game from csv file
- run animetiun of the algoritem in real time
-clear the map from the game.

# Understand_deeply: Take a look at the  class GeoInfo_system_Diagram and the class Test and the gui!-MyFrame.

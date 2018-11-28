package GIS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException; 
import java.io.PrintWriter; 
import java.text.SimpleDateFormat; 
import java.util.ArrayList; 
import java.util.Collection; 
import java.util.List; 
import java.util.Locale;

import org.boehn.kmlframework.kml.AltitudeModeEnum;
import org.boehn.kmlframework.kml.Container;
import org.boehn.kmlframework.kml.Document; 
import org.boehn.kmlframework.kml.Feature; 
import org.boehn.kmlframework.kml.Folder; 
import org.boehn.kmlframework.kml.Kml; 
import org.boehn.kmlframework.kml.KmlException; 
import org.boehn.kmlframework.kml.LineString; 
import org.boehn.kmlframework.kml.LineStyle; 
import org.boehn.kmlframework.kml.LinearRing; 
import org.boehn.kmlframework.kml.Placemark; 
import org.boehn.kmlframework.kml.Point; 
import org.boehn.kmlframework.kml.PolyStyle; 
import org.boehn.kmlframework.kml.StyleSelector; 
import org.boehn.kmlframework.kml.TimePrimitive; 
import org.boehn.kmlframework.kml.TimeSpan; 

import GIS.*;
import Geom.Point3D;
import Utils.*; 


public class Csv2kml implements Generator { 

	public void JAVAtoKML(String newCSV){

		private List<StyleSelector> styles = new ArrayList<StyleSelector>(); 
		private TimeLine timeLine; 
		private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", 
				Locale.US); 
		private Document document = new Document(); 
		//private GeoIntermediate rhumbIntermediate; 

		// 01-01-01 in millis before 1970-01-01 
		private static final double YearZeroinMillis = -62135773200000.0; 

		@Override
		public void generate(PrintWriter writer, final TimeLine timeLine, 
				final Collection<GIS_layer> layers) throws IOException { 

			this.timeLine = timeLine; 

			// We create a new KML Document 
			Kml kml = new Kml(); 
			kml.setXmlIndent(true); //*************************************
			kml.setGenerateObjectIds(false); 

			// We add a document to the kml 
			kml.setFeature(document); 

			for (GIS_layer layer : layers) { 
				document.addFeature(generateLayer(layer)); 
			} 

			// We generate the kml file 
			try { 
				kml.createKml(writer); 
			} catch (KmlException e) { 
				e.printStackTrace(); 
			} 
		} 

		private Feature generateLayer(final GIS_layer layer) { 
			Folder folder = new Folder(); 
			folder.setName(layer.getName()); 

			generateContent(folder, layer); 

			return folder; 
		} 

		private Feature generateContent(final Folder folder, 
				final GIS_layer layer) { 
			for (GIS_element item : layer.getItems()) { 
				folder.addFeature(generateItem(item)); 
			} 
			return null; 
		} 

		private Feature generateItem(final ArrayList<GIS_element>) { 
			if (item instanceof Line) { 
				return generateLine((Line) item); 
			} else if (item instanceof Polygon) { 
				return generatePolygon((Polygon) item); 
			} else if (item instanceof Place) { 
				return generatePlacemark((Place) item); 
			} 
			throw new IllegalArgumentException("unknown item type"); 
		} 

		private Feature generatePolygon(final Polygon polygon) { 

			Placemark placemark = new Placemark(polygon.getName()); 

			double startTime = polygon.getStartTime(); 
			double duration = polygon.getDuration(); 

			// Style 
			Style style = polygon.getPolyStyle(); 
			PolyStyle polyStyle = new PolyStyle(); 
			polyStyle.setOutline(false); 
			polyStyle.setColor(Utils.getKMLColor(style.getStrokeColor())); 
			style.setPolyStyle(polyStyle); 
			styles.add(style); 
			placemark.setStyleUrl(style.getId()); 
			document.setStyleSelectors(styles); 

			// Time 
			// Parse minus if date is BC 
			placemark.setTimePrimitive(new TimeSpan( 
					startTime < YearZeroinMillis ? "-" 
							+ formatter.format(startTime) : formatter 
							.format(startTime), duration > 0.0 ? (startTime 
									+ duration < YearZeroinMillis ? "-" 
									+ formatter.format(startTime + duration) : formatter 
									.format(startTime + duration)) : "")); 

			// Polygon OuterBoundaryIs LinearRing 
			LinearRing linearRing = new LinearRing(); 

			// linearRing.setCoordinates(); 
			linearRing.setCoordinates(Utils.convertToPoint(polygon 
					.getPolyCoordinates())); 

			org.boehn.kmlframework.kml.Polygon localPolygon = new org.boehn.kmlframework.kml.Polygon(); 
			localPolygon.setTessellate(true); 
			localPolygon.setOuterBoundary(linearRing); 
			placemark.setGeometry(localPolygon); 

			return placemark; 

		} 

		private Feature generateLine(final Line line) { 
			Feature feature; 

			if (line.getEndStyle() == null 
					|| line.getStartStyle().equals(line.getEndStyle())) { 
				double startTime = line.getStartTime(); 
				double endTime = line.getEndTime(); 
				double duration = line.getDuration(); 
				double maxAltitude = line.getMaxAltitude(); 

				double timeRange = endTime - startTime; 
				if (timeLine.isInstantaneous() || timeRange == 0.0) { 

					feature = generateLineSegment(line.getStartLocation(), line 
							.getEndLocation(), startTime, duration, line 
							.getStartStyle()); 

				} else { 

					Folder folder = new Folder(); 

					// Parse start and end point coordinates 
					double startLon = line.getStartLocation().getLongitude(); 
					double startLat = line.getStartLocation().getLatitude(); 
					double endLon = line.getEndLocation().getLongitude(); 
					double endLat = line.getEndLocation().getLatitude(); 

					int sliceCount = timeLine.getSliceCount(); 

					rhumbIntermediate = new GeoIntermediate(startLon, startLat, 
							endLon, endLat, sliceCount); 
					double coords[][] = rhumbIntermediate.getCoords(); 

					double lineSpan = timeLine.getEndTime() - startTime; 

					// Controls how fast the lines should get to their end point (to 
					// be in sync with each other) 
					double speed = 0.1; 
					int j = sliceCount; 

					double a = -2 * maxAltitude 
							/ (Math.pow(sliceCount, 2) - sliceCount); 
					double b = 2 * maxAltitude / (sliceCount - 1); 

					for (int i = 0; i < sliceCount; i++, j--) { 

						double startAltitude = a * Math.pow((double) i, 2) + b 
								* (double) i; 
						double endAltitude = a * Math.pow((double) (i + 1), 2) + b 
								* (double) (i + 1); 

						Placemark lineSegment = generateLineSegment( 
								new Coordinates(coords[i][0], coords[i][1], 
										startAltitude),// startCoordinates 
								new Coordinates(coords[i + 1][0], coords[i + 1][1], 
										endAltitude),// endCoordinates 
								// TODO improve this timing 
								startTime - (lineSpan / sliceCount) 
								* ((double) j * speed),// startTime 
								duration,// duration 
								line.getStartStyle()// Style 
								); 

						folder.addFeature(lineSegment); 

					} 

					feature = folder; 
				} 

			} else { 
				Folder folder = new Folder(); 
				for (int i = 0; i < divisionCount; i++) { 
					Placemark lineSegment = generateLineSegment(loc1, loc2, time, 
							duration, style); 
					folder.addFeature(lineSegment); 
				} 
				feature = folder; 
			} 

			feature.setName(line.getName()); 
			return feature; 
		} 

		private Placemark generateLineSegment(Point3D startCoordinates, 
				Point3D endCoordinates, double startTime, double duration, 
				Style style) { 

			LineStyle lineStyle = new LineStyle(); 
			lineStyle.setColor(Utils.getKMLColor(style.getStrokeColor())); 
			lineStyle.setWidth(style.getStrokeWidth()); 

			style.setLineStyle(lineStyle); 

			styles.add(style); 
			document.setStyleSelectors(styles); 

			LineString lineString = new LineString(); 
			lineString.setTessellate(true); 
			lineString.setAltitudeMode(AltitudeModeEnum.relativeToGround); 
			List<Point> points = new ArrayList<Point>(); 
			points.add(generatePoint(startCoordinates)); 
			points.add(generatePoint(endCoordinates)); 

			lineString.setCoordinates(points); 
			Placemark placemark = new Placemark(); 

			if (!Double.isNaN(duration)) { 

				// Parse minus if date is BC 
				TimePrimitive timePrimitive = new TimeSpan( 
						startTime < YearZeroinMillis ? "-" 
								+ formatter.format(startTime) : formatter 
								.format(startTime), duration > 0.0 ? (startTime 
										+ duration < YearZeroinMillis ? "-" 
										+ formatter.format(startTime + duration) 
										: formatter.format(startTime + duration)) : ""); 

				placemark.setTimePrimitive(timePrimitive); 
			} 

			placemark.setStyleUrl(style.getId()); 
			placemark.setGeometry(lineString); 
			return placemark; 

		} 

		private Placemark generatePlacemark(final Place place) { 
			Placemark placemark = new Placemark(); 
			placemark.setGeometry(generatePoint(place.getCoordinates())); 
			placemark.setName(place.getName()); 
			return placemark; 
		} 

		private Point generatePoint(final Point3D coordinates) {
			Point point = new Point(); 
			point.setAltitudeMode(AltitudeModeEnum.relativeToGround); 
			point.setAltitude( coordinates.z()); 
			point.setLongitude(coordinates.y()); 
			point.setLatitude(coordinates.x()); 
			return point; 
		} 

		@Override 
		public String toString() { 
			return "KML"; 
		} 

	}






	/**
	 * function that get address of csv file and convert it to GIS_layer(java class).
	 * @param newCSV
	 */


	public void CSVtoJAVA(String newCSV){
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

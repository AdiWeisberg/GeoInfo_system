package GIS;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import Geom.Geom_element;
/**
 * class_layer: Represents a geographical layer given by a  csv file.It has an ArrayList of GpsData, and Meta_data of the layer.
 * @author Naomi and Adi
 *@implements GIS_layer
 */
public class _layer implements GIS_layer {

	private ArrayList<GIS_element> layer;
	private Meta_data data;

	/** constructor 
	 * @throws ParseException - Because you convert String information to a date then it can bounces an error if you can not convert*/
	public _layer() throws ParseException {
		layer = new ArrayList<GIS_element>();  // 0 -> Point3D , 1 -> Info of the point
		this.data = new System_data();
	}
	/**adding GPSdata into the layer.
	 * @param GPSdata - new GIS_element
	 * @return boolean - if the adding succeeded-true else false. */
	@Override
	public boolean add(GIS_element GPSdata) {
		return layer.add(GPSdata);
	}
	/**adding  new Collection< extends GIS_element>  into the layer.
  @param arg0 - new Collection< extends GIS_element>
	 * @return boolean - if the adding succeeded-true else false. */
	@Override
	public boolean addAll(Collection<? extends GIS_element> arg0) {
		return layer.addAll(arg0);
	}
	@Override
	public void clear() {
		layer.clear();
		return;
	}
	@Override
	public boolean contains(Object o) {
		return layer.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return layer.containsAll(c);
	}

	@Override
	public boolean isEmpty() {

		return layer.isEmpty();
	}
	@Override
	public Iterator<GIS_element> iterator() {
		return layer.iterator();
	}
	@Override
	public boolean remove(Object o) {
		return layer.remove(o);
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		return layer.removeAll(c);
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		return layer.retainAll(c);
	}
	@Override
	public int size() {
		return layer.size();
	}
	@Override
	public Object[] toArray() {
		return layer.toArray();
	}
	@Override
	public <T> T[] toArray(T[] a) {
		return layer.toArray(a);
	}
	/**function get_all_metadata - Creates an array of the meta-data(info) of the GpsData in the layer.
	 * @return ArrayList<Meta_data> - the array of all the meta-data */
	@Override
	public ArrayList<Meta_data> get_all_metadata() {
		ArrayList<Meta_data> ans = new ArrayList<Meta_data>();
		for(int i=0; i<layer.size(); i++) {
			ans.add(layer.get(i).getData());
		}
		return ans;
	}
	/**function getItems - Creates an array of the Geom_element(Point3D) of the GpsData in the layer.
	 * @return ArrayList<Geom_element> - the array of all the Geom_element
	 */
	@Override
	public ArrayList<Geom_element> getItems() {
		ArrayList<Geom_element> ans = new ArrayList<Geom_element>();
		for(int i=0; i<layer.size(); i++) {
			ans.add(layer.get(i).getGeom());
		}
		return ans;
	}
	/**
	 * getLayer- @return  ArrayList<GIS_element> 
	 */
	@Override
	public ArrayList<GIS_element> getLayer() {
		return layer;
	}
	public GIS_element get_gpsData(int i) {
		return layer.get(i);
	}
	/**
	 * function layerString - @return String of all the GIS_element in the ArrayList.
	 */
	@Override
	public ArrayList<String> layerString() {
		ArrayList<String> strLayer = new ArrayList<String>();
		Iterator<GIS_element> itr = layer.iterator();
		while(itr.hasNext()) {
			strLayer.add(itr.next().toString()+"\n");
		}
		return strLayer;
	}
	/**
	 * @return Meta_data of the layer-name,time.. 
	 */
	@Override
	public Meta_data get_Meta_data() {
		return this.data;
	}

	//	public static void main(String[] args) throws ParseException {
	//		GIS_layer l1 = new _layer();
	//		GIS_layer l2 = new _layer();
	//		System.out.println(l1.get_Meta_data().toString());
	//		System.out.println(l2.get_Meta_data().toString());
	//	}
}
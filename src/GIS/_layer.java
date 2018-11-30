package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import Geom.Geom_element;

public class _layer implements GIS_layer {
	
	private ArrayList<GIS_element> layer;
	private int name;
	private static int counter = 0;

	public _layer() {
		layer = new ArrayList<GIS_element>();  // 0 -> Point3D , 1 -> Info of the point
		this.name = ++counter;
	}
	
	@Override
	public boolean add(GIS_element GPSdata) {
		return layer.add(GPSdata);
	}

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
	@Override
	public ArrayList<Meta_data> get_Meta_data() {
		ArrayList<Meta_data> ans = new ArrayList<Meta_data>();
		for(int i=0; i<layer.size(); i++) {
			ans.add(layer.get(i).getData());
		}
		return ans;
	}
	@Override
	public int getName() {
		return this.name;
	}
	@Override
	public ArrayList<Geom_element> getItems() {
		ArrayList<Geom_element> ans = new ArrayList<Geom_element>();
		for(int i=0; i<layer.size(); i++) {
			ans.add(layer.get(i).getGeom());
		}
		return ans;
	}
	@Override
	public void setName(int i) {
		this.name = i;
	}
	
	@Override
	public ArrayList<GIS_element> getLayer() {
		return layer;
	}
	public GIS_element get_gpsData(int i) {
		return layer.get(i);
	}
	
	@Override
	public ArrayList<String> layerString() {
		ArrayList<String> strLayer = new ArrayList<String>();
		Iterator<GIS_element> itr = layer.iterator();
		while(itr.hasNext()) {
			strLayer.add(itr.next().toString()+"\n");
		}
		return strLayer;
	}
}
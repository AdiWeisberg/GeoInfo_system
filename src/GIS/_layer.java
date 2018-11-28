package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class _layer implements GIS_layer {
	private  ArrayList<GIS_element> dataPoint;
	private ArrayList<Meta_data> infoArr;

	public _layer() {
		dataPoint = new ArrayList();
		infoArr = new ArrayList();
	}
	@Override
	public boolean add(GIS_element GPSdata) {
		boolean flg=false;
		 flg=this.add(GPSdata);
		 infoArr.add(GPSdata.getData());
		 return flg;
	}

	@Override
	public boolean addAll(Collection<? extends GIS_element> arg0) {
		boolean flg=false;
		Iterator<GIS_element> it = dataPoint.iterator();
		while(it.hasNext()) {
			flg=false;
			if(this.add(it.next()))flg=true;
		}
		return flg;
	}

	@Override
	public void clear() {
		dataPoint.clear();
		return;
	}
	
	@Override
	public boolean contains(Object o) {
		return dataPoint.contains(o);
	}
	
	@Override
	public boolean containsAll(Collection<?> c) {
		return dataPoint.containsAll(c);
	}
	
	@Override
	public boolean isEmpty() {
		
		return dataPoint.isEmpty();
	}
	@Override
	public Iterator<GIS_element> iterator() {
		return dataPoint.iterator();
	}
	@Override
	public boolean remove(Object o) {
		return dataPoint.remove(o);
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		return dataPoint.removeAll(c);
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		return dataPoint.retainAll(c);
	}
	@Override
	public int size() {
		return dataPoint.size();
	}
	@Override
	public Object[] toArray() {
		return dataPoint.toArray();
	}
	@Override
	public <T> T[] toArray(T[] a) {
		return dataPoint.toArray(a);
	}
	@Override
	public ArrayList<Meta_data> get_Meta_data() {
		return this.infoArr;
	}
}
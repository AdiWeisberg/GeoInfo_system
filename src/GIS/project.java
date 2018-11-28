package GIS;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class project implements GIS_project {

	private ArrayList<GIS_layer> layers;
	
	
	@Override
	public boolean add(GIS_layer e) {
		return layers.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_layer> c) {
		return layers.addAll(c);
	}

	@Override
	public void clear() {
		layers.clear();
		
	}

	@Override
	public boolean contains(Object o) {
		return layers.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return layers.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return layers.isEmpty();
	}

	@Override
	public Iterator<GIS_layer> iterator() {
		return layers.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return layers.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return layers.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return layers.retainAll(c);
	}

	@Override
	public int size() {
		return layers.size();
	}

	@Override
	public Object[] toArray() {
		return layers.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return layers.toArray(a);
	}

	@Override
	public ArrayList<Meta_data> get_Meta_data() {
		ArrayList<Meta_data> info_layers = null;
		Iterator<Meta_data> itr = info_layers.iterator();
		while(itr.hasNext())
			info_layers.add(itr.next());
		return info_layers;
	}

}
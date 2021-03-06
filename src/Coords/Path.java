package Coords;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import GIS.GIS_element;
import Geom.Point3D;
/**
 * function Path- represents an array of points with a name and counts the amount of dispositionsof the class created.
 * @author Naomi and Adi
 *
 */
public class Path implements Set<Point3D>{

	private ArrayList<Point3D>points;
	private int name;
	private static int counter;
/**Contractor*/
	public Path() {
		points= new ArrayList<Point3D>();
		this.name = counter++;
	}

	@Override
	public boolean add(Point3D arg0) {

		return points.add(new Point3D(arg0));
	}
	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	@Override
	public boolean addAll(Collection<? extends Point3D> arg0) {
		return points.addAll(arg0);
	}
	@Override
	public void clear() {
		points.clear();
	}
	@Override
	public boolean contains(Object arg0) {

		return points.contains(arg0);
	}
	@Override
	public boolean containsAll(Collection<?> arg0) {
		return points.containsAll(arg0);
	}
	@Override
	public boolean isEmpty() {
		return points.isEmpty();
	}
	@Override
	public Iterator<Point3D> iterator() {
		return points.iterator();
	}
	@Override
	public boolean remove(Object arg0) {
		return points.remove(arg0);
	}
	@Override
	public boolean removeAll(Collection<?> arg0) {
		return points.removeAll(arg0);
	}
	@Override
	public boolean retainAll(Collection<?> arg0) {
		return points.retainAll(arg0);
	}
	@Override
	public int size() {
		return points.size();
	}
	@Override
	public Object[] toArray() {
		return points.toArray();
	}
	@Override
	public <T> T[] toArray(T[] arg0) {
		return points.toArray(arg0);
	}
	public ArrayList<Point3D> getPoints() {
		return points;
	}
	public void setPoints(ArrayList<Point3D> points) {
		this.points = points;
	}
	public void add(double x, double y, double z) {
		Point3D temp= new Point3D(x,y,z);
		this.points.add(temp);
	}
	public String toString() {
		Iterator<Point3D> itr = points.iterator();
		String ans = ""; 
		while(itr.hasNext()) {
			ans += itr.next().toString()+"\n";
		}
		return ans;
	}

}

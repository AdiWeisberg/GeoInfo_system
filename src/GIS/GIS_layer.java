package GIS;
import java.util.ArrayList;
import java.util.Set;
import Geom.Geom_element;

public interface GIS_layer extends Set<GIS_element>{
	public ArrayList<Meta_data> get_Meta_data();

	public int getName();

	public ArrayList<Geom_element> getItems();

	public void setName(int i);

	public ArrayList<GIS_element> getLayer(); 
	
}

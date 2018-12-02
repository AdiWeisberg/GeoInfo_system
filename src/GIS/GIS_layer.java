package GIS;
import java.util.ArrayList;
import java.util.Set;
import Geom.Geom_element;

public interface GIS_layer extends Set<GIS_element>{
	public Meta_data get_Meta_data();
	public ArrayList<Meta_data> get_all_metadata();

	public ArrayList<Geom_element> getItems();

	public ArrayList<GIS_element> getLayer(); 
	
	public ArrayList<String> layerString();

}

package GameElements;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import File_format.CSVtoJava;
import File_format.JavaToCSV;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.Meta_data;
import GIS.info;
import Geom.Geom_element;

public class Game{
	private ArrayList<GIS_element> fruits;
	private ArrayList<GIS_element> pacmans;
	public Game(){
		fruits=new ArrayList<GIS_element>();
		pacmans=new ArrayList<GIS_element>();
	}
	public Game(ArrayList<Fruit>fruits,ArrayList<Pacman>pacmans){
		this.fruits=new ArrayList<GIS_element>(fruits);
		this.pacmans=new ArrayList<GIS_element>(pacmans);
	}
/**
 * Takes a CSV file and turns it into a game
 * @param paths-STRING array of files we'd like to convert
 * @throws ParseException- if the Conversion does not work. 
 */
	
	public void  csvToGame(String Path) throws ParseException {
		ArrayList<GIS_element> newData = new ArrayList<GIS_element>();
		newData.addAll( new CSVtoJava().convert(Path));
	
		setByCSV(newData);
		
	}
	//Inserts the values we took from the CSV file into the ArrayList of pacmans and the ArrayList of fruits.
	private void setByCSV(ArrayList<GIS_element> newData) {
		Iterator<GIS_element> itr = newData.iterator();
		while(itr.hasNext()) {
			GIS_element element= itr.next();
			if(element.getData().getType().equals("P")||element.getData().getType().equals("p")) {
				pacmans.add((Pacman)element);
			}
			else if(element.getData().getType().equals("F")||element.getData().getType().equals("f")) {
				fruits.add((Fruit)element);
			}
		}
	
		}
	
	/**
	 * Takes a game and converts it to a CSV file
	 * @return-STRING array of disconnections to CSV folders we created.
	 */
	public void GameTocsv( String fileName  ) {
		
			new JavaToCSV().writeCsvFile(fileName , this.fruits,this.pacmans);		
	}
	//geter seters add empty clear//
	public boolean addFruit(Fruit fruit) {
		return fruits.add(fruit);
	}
	public boolean addPacman(Pacman pacman) {
		return pacmans.add(pacman);
	}
	public boolean addAllPacmans(ArrayList<Pacman>pacmans) {
		return this.pacmans.addAll(pacmans);
	}
	public boolean addAllFruits(ArrayList<Fruit>fruits) {
		return this.fruits.addAll(fruits);
	}
	public boolean isemptyFruits() {
		return fruits.isEmpty();
	}
	public boolean isemptyPacmans() {
		return pacmans.isEmpty();
	}
	public void clearPacman() {
		pacmans.clear();
	}
	public void clearFruits() {
		fruits.clear();
	}
	public ArrayList<GIS_element> getFruits() {
		return fruits;
	}
	public void setFruits(ArrayList<GIS_element> fruits) {
		this.fruits = fruits;
	}
	public ArrayList<GIS_element> getPacmans() {
		return pacmans;
	}
	public void setPacmans(ArrayList<GIS_element> pacmans) {
		this.pacmans = pacmans;
	}
	public static void main(String[] args) throws ParseException {
		//ArrayList<String>paths= new ArrayList<String>();
		//Game g= new Game();
	//	paths.add("game_1543693911932_b.csv");
    // g.csvToGame(paths) ;
     // g.GameTocsv("new.csv",g.fruits,g.pacmans);
	}




}
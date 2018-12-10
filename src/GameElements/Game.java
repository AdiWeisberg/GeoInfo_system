package GameElements;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import File_format.CSVtoJava;
import File_format.JavaToCSV;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.Meta_data;
import Geom.Geom_element;

public class Game{
	private ArrayList<GIS_element> fruits;
	private ArrayList<GIS_element> pacmans;
	public Game(){
		fruits=new ArrayList();
		pacmans=new ArrayList();
	}
	public Game(ArrayList<Fruit>fruits,ArrayList<Pacman>pacmans){
		this.fruits=new  ArrayList(fruits);
		this.pacmans=new ArrayList(pacmans);
	}
/**
 * Takes a CSV file and turns it into a game
 * @param paths-STRING array of files we'd like to convert
 * @throws ParseException- if the Conversion does not work. 
 */
	public void csvToGame(ArrayList<String> paths) throws ParseException {
		new CSVtoJava().convert(paths);
	}
	/**
	 * Takes a game and converts it to a CSV file
	 * @return-STRING array of disconnections to CSV folders we created.
	 */
	public ArrayList<String> GameTocsv() {
		return	new JavaToCSV().JavaToCSV();		
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






}
package Algo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import Coords.ConvertFactory;
import Coords.Path;
import GUI.MyFrame;
import GameElements.Fruit;
import GameElements.Game;
import GameElements.Pacman;
import Geom.Point3D;

public class ShortestPathAlgo implements Set<Path> {
	private ArrayList<Path> paths;
	private Game game;
	private MyFrame frame;
	private ArrayList<Fruit> fruit;
	private ArrayList<Pacman> pacman;

	public ShortestPathAlgo(Game game,MyFrame frame) {
		this.frame=frame;
		this.game=game;
		paths= new ArrayList<Path>();
		fruit= new  ArrayList<Fruit>();
		fruit.addAll((Collection<? extends Fruit>) game.getFruits());
		pacman= new  ArrayList<Pacman>();
		pacman.addAll((Collection<? extends Pacman>) game.getPacmans());
		theFastestRoute();
	}

	public void theFastestRoute() {
		System.out.println("Pacmans: "+game.getPacmans().size());
		for(int i=0;i<(pacman.size());i++) {
			//create path for each pacman
			Path newPath=new Path();
			paths.add(newPath);
			paths.get(i).add(new Point3D(pacman.get(i).getPoint()));
		}

		//sort the pacman array from the quickest to the slowest. 
		//time to end the algo
		long t= System.currentTimeMillis();
		long end = t+180000;
		Fruit temp=	new Fruit();
		while((System.currentTimeMillis() < end)&&(fruit.size()>0)) {
			for(int i=0;i<(game.getPacmans().size())&&(fruit.size()>0);i++) {
				temp.set(theNearestFruit(pacman.get(i)));
				//int j=ifOtherPacmanIsMoreClose(temp, i);
				paths.get(i).add(temp.getPoint());
				fruit.remove(temp.getData().getName());
				pacman.get(i).setPoint(temp.getPoint());
				//Calculate the score.
				((Pacman) game.getPacmans().get(i)).setScore(temp.getData().getSpeedweight());
			}	
		}
		System.out.println(t);
		for(int i=0;i<(game.getPacmans().size());i++) {
			pacman.get(i).setPoint(this.paths.get(i).getPoints().get(0));
			System.out.println(this.paths.get(i).toString());
			
		}

	}
	
	
	
	public ArrayList<Fruit> getFruit() {
		return fruit;
	}

	public void setFruit(ArrayList<Fruit> fruit) {
		this.fruit = fruit;
	}

	public ArrayList<Pacman> getPacman() {
		return pacman;
	}

	public void setPacman(ArrayList<Pacman> pacman) {
		this.pacman = pacman;
	}

	/**
	 *  who is the nearest fruit for this pacman.
	 * @param pacman - the pacman.
	 * @return Fruit.
	 */
	public synchronized Fruit theNearestFruit(Pacman pacman) {
		new ConvertFactory(frame.getWidth(),frame.getHeight());
		Fruit nearest = new Fruit(this.fruit.get(0)); // set defult nearest fruit to the current pacman.
		double TheBestTime=0;
		//the index of the fruit in the ArrayList of fruits.
		nearest.getData().setName(0);
		double distance0=ConvertFactory.distanceGPS(pacman.getPoint(),nearest.getPoint());
		TheBestTime=(distance0)-pacman.getData().getRadius()/pacman.getData().getSpeedweight();
		for(int i=1;i< this.fruit.size();i++) {
			Fruit temp= new Fruit(this.fruit.get(i));
			double distance1= ConvertFactory.distanceGPS(pacman.getPoint(),temp.getPoint());
			double tempTime=(distance1)-pacman.getData().getRadius()/pacman.getData().getSpeedweight();
			if(tempTime<TheBestTime) {
				nearest.set(temp);
				nearest.getData().setName(i);
				TheBestTime = tempTime;
			}
		}		
		System.out.println("Best Time: "+TheBestTime);
		System.out.println("nearest: "+nearest.getPoint()+"\n");
		return nearest;
	}

//	private int ifOtherPacmanIsMoreClose(Fruit fruit, int j) {
//		double dis0=ConvertFactory.distanceGPS(paths.get(j).getPoints().get(paths.get(j).getPoints().size()-1),fruit.getPoint());
//		double thisPacman=(dis0)-game.getPacmans().get(j).getData().getRadius()/game.getPacmans().get(j).getData().getSpeedweight();
//		for(int i=0;i<game.getPacmans().size();i++ ) {
//			double dis1=ConvertFactory.distanceGPS(paths.get(i).getPoints().get(paths.get(i).getPoints().size()-1),fruit.getPoint());
//			double temPacman=(dis1)-game.getPacmans().get(i).getData().getRadius()/game.getPacmans().get(i).getData().getSpeedweight();
//			if(temPacman<thisPacman) {
//				j=i;
//				thisPacman=temPacman;
//			}
//		}
//		return j;
//	}
	
	public synchronized void addPathes(Path path, int i) {
		paths.get(i).setPoints(path.getPoints());
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public MyFrame getFrame() {
		return frame;
	}
	public void setFrame(MyFrame frame) {
		this.frame = frame;
	}

	public boolean isFruitLeft() {
		return game.getFruits().size() > 0;
	}

	public ArrayList<Path> getPaths() {
		return paths;
	}
	public void setPaths(ArrayList<Path> paths) {
		this.paths = paths;
	}
	@Override
	public boolean add(Path arg0) {
		return paths.add(arg0) ;
	}
	@Override
	public boolean addAll(Collection<? extends Path> arg0) {
		return paths.addAll(arg0);
	}
	@Override
	public void clear() {
		paths.clear();		
	}
	@Override
	public boolean contains(Object arg0) {
		return paths.contains(arg0);
	}
	@Override
	public boolean containsAll(Collection<?> arg0) {
		return paths.containsAll(arg0);
	}
	@Override
	public boolean isEmpty() {
		return paths.isEmpty();
	}
	@Override
	public Iterator<Path> iterator() {
		return paths.iterator();
	}
	@Override
	public boolean remove(Object arg0) {
		return paths.remove(arg0);
	}
	@Override
	public boolean removeAll(Collection<?> arg0) {
		return paths.removeAll(arg0);
	}
	@Override
	public boolean retainAll(Collection<?> arg0) {
		return paths.retainAll(arg0);
	}
	@Override
	public int size() {
		return paths.size();
	}
	@Override
	public Object[] toArray() {
		return paths.toArray();
	}
	@Override
	public <T> T[] toArray(T[] arg0) {
		return paths.toArray(arg0);
	}
	//main
	public static void main(String[] args) throws ParseException {
		Game game=new Game ();
		game.csvToGame("test3\\game_1543684662657.csv");
		MyFrame frame=new MyFrame();
		ShortestPathAlgo algo=new ShortestPathAlgo(game,frame);
		for(int i=0;i<algo.getPaths().size();i++) {
			System.out.println(i+1+": ");
			for(int j=0;j<algo.getPaths().get(i).getPoints().size();j++) {
				System.out.println();
				System.out.print(algo.getPaths().get(i).getPoints().get(j).x()+",");
				System.out.print(algo.getPaths().get(i).getPoints().get(j).y());
			}
			System.out.println(algo.getPaths().get(i).getPoints().size());

		}
	}

}
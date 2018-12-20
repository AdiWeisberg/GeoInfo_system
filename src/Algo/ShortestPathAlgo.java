package Algo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import Coords.ConvertFactory;
import Coords.Map;
import Coords.Path;
import GIS.GIS_element;
import GUI.MyFrame;
import GUI.ThreadPacman;
import GameElements.Fruit;
import GameElements.Game;
import GameElements.Pacman;
import Geom.Point3D;

public class ShortestPathAlgo implements Set<Path> {
	private ArrayList<Path> paths;
	private Game game;
	private MyFrame frame;
	private ArrayList<Thread> tp;
	
	public ShortestPathAlgo(Game game,MyFrame frame) {
		this.frame=frame;
		this.game=game;
		paths= new ArrayList<Path>(game.getPacmans().size());
		tp = new ArrayList<Thread>();
		theFastestRoute();
	}
	public void theFastestRoute() {
		
		for(int i=0;i<(game.getPacmans().size());i++) {
			
			//create path for each pacman
			Path newPath=new Path();
			newPath.add(game.getPacmans().get(i).getPoint().x(),game.getPacmans().get(i).getPoint().y(),0);
			paths.add(newPath);
			
			//create collection of threadpacmans and start the current thread: 
			ThreadPacman tpacman=new ThreadPacman((Pacman)game.getPacmans().get(i), newPath, i,this);
			tp.add(i,new Thread(tpacman));
			tp.get(i).start();
		}
		//sort the pacman array from the quickest to the slowest. 
		//time to end the algo
		long t= System.currentTimeMillis();
		long end = t+15000;
		while(System.currentTimeMillis() < end&&game.getFruits().size()>0) {
			
			//ArrayList<Integer> fruitIndex=new ArrayList<Integer>();
			for(int i=0;i<(game.getPacmans().size())&&game.getFruits().size()<=0;i++) {
				Fruit temp=	theNearestFruit((Pacman)game.getPacmans().get(i),paths.get(i).getPoints().get(paths.get(i).getPoints().size()-1));
				//int theFaster= ifOtherPacmanIsMoreClose(temp,i);
				paths.get(i).add(new Point3D(temp.getPoint()));
				((Pacman) game.getPacmans().get(i)).setScore(temp.getData().getSpeedweight());
			}	
		}
		System.out.println(t);
	}
	/**
	 *  who is the nearest fruit for this pacman.
	 * @param pacman - the pacman.
	 * @return Fruit.
	 */
	public synchronized Fruit theNearestFruit(Pacman pacman,Point3D path) {
		new ConvertFactory(1633,440);
	
		Fruit nearest= new Fruit(game.getFruits().get(0));
		//the index of the fruit in the ArrayList of fruits.
		nearest.getData().setName(0);
		double TheBestTime=(ConvertFactory.distanceGPS(path,nearest.getPoint())-pacman.getData().getRadius())/pacman.getData().getSpeedweight();
		for(int i=1;i< game.getFruits().size() ;i++) {
			if(!((Fruit) game.getFruits().get(i)).isEaten()) {
			Fruit temp= new Fruit(game.getFruits().get(i));
			double tempTime=((ConvertFactory.distanceGPS(path,temp.getPoint()))-pacman.getData().getRadius())/pacman.getData().getSpeedweight();

			if(tempTime<TheBestTime) {
				nearest.setData(temp.getData());
				nearest.setPoint(temp.getPoint());
				nearest.getData().setName(i);
			}
		}
		}
		((Fruit) game.getFruits().get(nearest.getData().getName())).setEaten(true);
		return nearest;
	}	
	private int ifOtherPacmanIsMoreClose(Fruit fruit, int j) {
		double thisPacman=(ConvertFactory.distanceGPS(paths.get(j).getPoints().get(paths.get(j).getPoints().size()-1),fruit.getPoint())-game.getPacmans().get(j).getData().getRadius())/game.getPacmans().get(j).getData().getSpeedweight();
		for(int i=0;i<game.getPacmans().size();i++ ) {
			double temPacman=(ConvertFactory.distanceGPS(paths.get(i).getPoints().get(paths.get(i).getPoints().size()-1),fruit.getPoint())-game.getPacmans().get(i).getData().getRadius())/game.getPacmans().get(i).getData().getSpeedweight();
			if(temPacman<thisPacman) {
				j=i;
				thisPacman=temPacman;
			}
		}
		return j;
	}
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
	public ArrayList<Thread> getTp() {
		return tp;
	}
	public void setTp(ArrayList<Thread> tp) {
		this.tp = tp;
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
			//		Game game=new Game ();
			//		game.csvToGame("test3\\game_1543684662657.csv");
			//		// ShortestPathAlgo algo=new ShortestPathAlgo(game,this.fra);
			//		 for(int i=0;i<algo.getPaths().size();i++) {
			//			 System.out.println(i+1+": ");
			//			 for(int j=0;j<algo.getPaths().get(i).getPoints().size();j++) {
			//				 System.out.println();
			//				 System.out.print(algo.getPaths().get(i).getPoints().get(j).x()+",");
			//				 System.out.print(algo.getPaths().get(i).getPoints().get(j).y()+"."+j);
			//			 }
			//			 System.out.println(algo.getPaths().get(i).getPoints().size());
			//			
			//		 }
		}

}

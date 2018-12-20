package GUI;

import java.util.ArrayList;

import Algo.ShortestPathAlgo;
import Coords.ConvertFactory;
import Coords.Path;
import GameElements.Fruit;
import GameElements.Pacman;

public class ThreadPacman implements Runnable{
	private Pacman pacman;
	private Path path;
	private int index;
	private double x1, y1, x2, y2, wayX, wayY; // start and end current points
	private ShortestPathAlgo algo;
	private long startTime;
	private long endTime;
	//	private Thread t;

	public ThreadPacman(Pacman pacman, Path path,int index,ShortestPathAlgo algo) {
		this.pacman = pacman;
		this.path = path;
		this.index=index;
		this.algo = algo;
		this.startTime= System.currentTimeMillis();
		long end = startTime+15000;
		//		this.t = new Thread();
	}


	@SuppressWarnings({ "deprecation", "static-access" })
	@Override
	public void run() {
		while(algo.isFruitLeft()) {
			
			//find nearest fruit
			Fruit fruit =new Fruit (this.algo.theNearestFruit(this.pacman));

			// update general score:
			pacman.setScore(pacman.getScore()+fruit.getData().getSpeedweight()); 
			((Pacman) algo.getGame().getPacmans().get(index)).setScore(pacman.getScore());

			double distance = ConvertFactory.distanceGPS(this.pacman.getPoint(), fruit.getPoint());
			//double walkTime = (distance-this.pacman.getPoint().getData().getRadius())/pacman.getData().getSpeedweight(); // based on the speed of this pacman
			int distribute =(int) distance/15;
			int i;

			for(i=1;i<=distribute;i++) {
				x1=pacman.getPoint().x();
				y1=pacman.getPoint().y();
				x2=fruit.getPoint().x();
				y2=fruit.getPoint().y();
				wayX = ((x1*i) + x2*(distribute-i))/distribute;
				wayY = ((y1*i) + y2*(distribute-i))/distribute;
				pacman.setPoint(wayX,wayY,0);
				path.add(pacman.getPoint());
				algo.getGame().getPacmans().set(index, this.pacman);
				algo.getFrame().repaint();
				try {
					algo.getTp().get(this.index).sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(i*15<distance) { // if the pacman is at radios that less then 15 to the fruit.
				this.path.getPoints().add(fruit.getPoint()); // move to the fruit
				pacman.setPoint(fruit.getPoint().x(),fruit.getPoint().y(),fruit.getPoint().z());
				algo.getFrame().repaint();

			}
			algo.addPathes(this.path,index,fruit.getData().getName()); // add path to array pathes and remove fruit

		}
		algo.getTp().get(this.index).stop();
	}



}

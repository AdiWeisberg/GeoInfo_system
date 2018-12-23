package GUI;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

import Algo.ShortestPathAlgo;
import Coords.ConvertFactory;
import Coords.Path;
import GIS.GIS_element;
import GameElements.Fruit;
import GameElements.Pacman;
import Geom.Point3D;

public class ThreadPacman implements Runnable{
	private Pacman pacman;
	private ArrayList<Fruit> fruitsPath;
	private Path walkPath;
	private int index;
	private double x1, y1, x2, y2, wayX, wayY; // start and end current points
	private ShortestPathAlgo algo;
	private long startTime;
	private long endTime; // the timeout for the thread
	private ConvertFactory cf;

	public ThreadPacman(Pacman pacman, Path pathFruit,int index,ShortestPathAlgo algo) {
		this.pacman = pacman;
		this.fruitsPath = toFruitArray(pathFruit);  // the first path is the pacman location.
		this.index=index;
		this.algo = algo;
		this.startTime = 0;
		this.endTime = 0;
		this.walkPath = new Path();
		this.cf = new ConvertFactory(algo.getFrame().getWidth(),algo.getFrame().getHeight()); //for the convert of the timestamp to UTC time

	}

	private ArrayList<Fruit> toFruitArray(Path pathFruit) {
		ArrayList<Fruit> fruitsPath = new ArrayList<Fruit>();
		Iterator<Point3D> it = pathFruit.iterator();
		while(it.hasNext()) {
			try {
				Point3D temp = it.next();
				Fruit nextFruit = new Fruit(temp);
				fruitsPath.add(nextFruit);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return fruitsPath;

	}
	public Path getWalkPath() {
		return walkPath;
	}

	public void setWalkPath(Path walkPath) {
		this.walkPath = walkPath;
	}

	@Override
	public void run() {
		int distCounter = 0;
		int itrCount = 0;
		this.startTime= System.currentTimeMillis();
		this.endTime = startTime+60000; // one minute max
		Iterator<Fruit> itr = fruitsPath.iterator();
		itr.next(); // pass the pacman point.
		while(itr.hasNext() && System.currentTimeMillis() < endTime) { // As long as I have more fruit AND a half minute had not passed
			Fruit fruit = itr.next(); //gets the next fruit that the pacman should eat
			itrCount++;
			//set the start and end points
			x1=pacman.getPoint().x();
			y1=pacman.getPoint().y();
			x2=fruit.getPoint().x();
			y2=fruit.getPoint().y();

			double distance = ConvertFactory.distanceGPS(this.pacman.getPoint(), fruit.getPoint());
			int steps =(int) distance/10; // how many steps i have.
			double walkTime = (distance-this.pacman.getData().getRadius())/pacman.getData().getSpeedweight(); // based on the speed of this pacman
			for(int i=steps;i>=0;i--) {
				distCounter +=10;
				wayX = ((x1*i) + x2*(steps-i) )/steps;
				wayY = ((y1*i) + y2*(steps-i))/steps;
				pacman.setPoint(wayX,wayY,0);
				doActions(false, null);
				Point3D p = new Point3D(pacman.getPoint());
				try {
					p.setTimeStamp(p.curTime2UTC());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				walkPath.add(p);
				try { Thread.sleep(1000); }   /* this will pause for 50 milliseconds */
				catch (InterruptedException e) { System.err.println("sleep exception"); }
			}
			//the pacman walks to the fruit if not reach it yet: 
			if(distCounter < distance)
				pacman.setPoint(fruit.getPoint());

			System.out.println("ID CHECK: "+fruit.getPoint().getID());
			doActions(true, fruit);
			try { Thread.sleep(1000); }   /* this will pause for 50 milliseconds */
			catch (InterruptedException e) { System.err.println("sleep exception"); }

		}
		//		Thread thisThread = algo.getFrame().getTp().get(index);
		//algo.getGame().getPacmans().set(index, this.pacman); // set the last location of the pacman after the game ended.
		//		Iterator<Point3D> itr1 = walkPath.iterator();
		//		while(itr1.hasNext()) {
		//			System.out.println(itr1.next().toString());
		//		}
		algo.addPathes(walkPath, index);  // update the full path for this pacman.
		stop();

	}

	public synchronized void doActions(Boolean bo, Fruit fruit) {
		if(bo) {
			Iterator<GIS_element> itr = this.algo.getGame().getFruits().iterator();
			while(itr.hasNext()) {
				GIS_element temp = itr.next();
				if(temp.getPoint().getID() == fruit.getPoint().getID()) {
					this.algo.getGame().getFruits().remove(temp);
					break;
				}
			}
		}
		algo.getFrame().repaint();
	}

	public void stop() {
		if (!Thread.interrupted())
			Thread.interrupted();
	}

}





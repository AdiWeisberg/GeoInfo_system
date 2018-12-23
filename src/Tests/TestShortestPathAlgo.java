package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Algo.ShortestPathAlgo;
import GUI.MyFrame;
import GameElements.Fruit;
import GameElements.Game;

class TestShortestPathAlgo {
	private ShortestPathAlgo algo;
	private static MyFrame frame;
	private static Game game;
	/**
	 * checks if we've got the right track for pacman 1.
	 */
	@Test
	public void testTheFastestRoute() {
		algo= new ShortestPathAlgo(game ,frame);
		//the first-pacman
		assertTrue(algo.getPaths().get(0).getPoints().get(0).x()>=32.1040172336448);
		assertTrue(algo.getPaths().get(0).getPoints().get(0).y()>=35.2049271284019);
		//2
		assertTrue(algo.getPaths().get(0).getPoints().get(1).x()>=32.1038737196261);
		assertTrue(algo.getPaths().get(0).getPoints().get(1).y()>=35.2069029330076);
		//3
		assertTrue(algo.getPaths().get(0).getPoints().get(2).x()>=32.1035736448598);
		assertTrue(algo.getPaths().get(0).getPoints().get(2).y()>=35.2086935059316);
		//4
		assertTrue(algo.getPaths().get(0).getPoints().get(3).x()>=32.1031039626168);
		assertTrue(algo.getPaths().get(0).getPoints().get(3).y()>=35.2095579204466);
	}
	@BeforeEach
	void setUp() throws Exception {
		game =new Game ();
		game.csvToGame("Test.csv");
		 frame=new MyFrame();	
	}
}

package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Coords.ConvertFactory;
import Coords.Map;
import Geom.Point3D;

class ConvertFactoryTest {
	
	private Point3D P;
	private  Point3D p1;
	private Point3D p2;
	private ConvertFactory conv;
	@Test
	void testGpsToPicsel() {
		Point3D answer= conv.GpsToPicsel(P);
		assertTrue(answer.x()<720.1);
		assertTrue(answer.x()>719.9);
		assertTrue(answer.y()<544.1);
		assertTrue(answer.y()>543.9);
		assertTrue(answer.z()==0.0);
	}
	@Test
	void testPicselToGps() {
		Point3D answer= conv.PicselToGps(p1);
		assertTrue(answer.x()>32.0);
		assertTrue(answer.x()<32.12);
		assertTrue(answer.y()>35.1);
		assertTrue(answer.y()<35.25);
		assertTrue(answer.z()==0.0);
	}
	
	@Test
	void testdistancePicsel() {
		double answer= conv.distancePicsel(p1,p2);
		assertTrue(answer>49.8);
		assertTrue(answer<50.1);
	}
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		 new Map();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		P=new Point3D(32.102495,35.207462);
		p1=new Point3D(720,544);
		p2= new Point3D(672,598);
	}

	@AfterEach
	void tearDown() throws Exception {
	}



}
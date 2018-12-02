package Tests; 

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Geom.Point3D;
import  Coords.MyCoords;
class TestMyCoords {
private static Point3D gps1= new Point3D ();
private static Point3D gps2= new Point3D ();
private static MyCoords  MyCoords=new MyCoords();
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}
	@Test//not accurate
	public void Testdistance3d(){
		double answer= MyCoords.distance3d(gps1,gps2);
		assertTrue(answer <= 493.0523088165011);
		assertTrue(answer>492.0);
	}
	@Test
	public void Testadd(){
		gps2.set_x(Math.sin((gps2.x()*Math.PI/180))*6371000);
		gps2.set_y(Math.sin((gps2.y()*Math.PI/180))*6371000*0.847091);
		Point3D answer= MyCoords.add(gps1,gps2);
		Point3D equal=new Point3D(6771991.545773456, 6222897.462543655, 1320.0);
		assertEquals(answer.toString(), (equal.toString()));
	}
	@Test//minos,not accurate
	public void Testvector3D(){
		Point3D answer= MyCoords.vector3D(gps2,gps1);
		Point3D equal=new Point3D(337.6989921,-359.2492069,-20);
		//assertThat(answer, is(equalTo(equal)));
		assertTrue(answer.x() <= equal.x());
		assertTrue(answer.y() >= equal.y());
		assertTrue(answer.z() >= equal.z());
		 equal.set_x(equal.x()-1);
		assertTrue(answer.x()>=equal.x());
		equal.set_y(equal.y()+1);
		assertTrue(answer.y() <= equal.y());
		equal.set_z(equal.z()+1);
		assertTrue(answer.z() <= equal.z());
		
	}
	@Test
	public void Testazimuth_elevation_dist(){
		double [] answer= MyCoords.azimuth_elevation_dist(gps1,gps2);
		assertTrue(answer[0]>=313.0924);
		assertTrue(answer[0]<314.0924);
		assertTrue(answer[1] <= -2.3253 );
		assertTrue(answer[1]>=-2.4);
		assertTrue(answer[2]>= 492.2447388943615);
		assertTrue(answer[2]<= 493.2447388943615);
	}
	
	@Test
	public void TestisValid_GPS_Point(){
		boolean answer1= MyCoords.isValid_GPS_Point(gps1);
		assertThat(answer1, is(equalTo(true)));
		double x,y,z=gps2.z();
		x=(gps2.x()*Math.PI)/180;
		y=(gps2.y()*Math.PI)/180;
		x=Math.sin(x)*6371000;
		y=Math.sin(y)*6371000*0.847091;
		Point3D ans=new Point3D (x,y,z);
		boolean answer2= MyCoords.isValid_GPS_Point(ans);
		assertThat(answer2, is(equalTo(false)));
	}

	@BeforeEach
	void setUp() throws Exception {
		 gps1= new Point3D (32.10332, 35.20904, 670);
		 gps2= new Point3D (32.10635,35.20523,650);
	}

}
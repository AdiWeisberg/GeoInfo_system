package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import File_format.Csv2kml;
import GIS.GIS_layer;
import GIS.System_data;
import GIS.project;
/**
 * 
 * @author naomi and adi
 *not working, need to have the files.
 */

class Csv2kmlTest {

	@Test
	void testNotVaild() {
		String path1 = "NotGoodInput";
		String path2 = "C:";
		String path3 = "C:\\Files\\Algorithms - Robert Sedgewick.pdf";
		String path4 = "C:\\Files\\imageTest.JPG";
		String path5 = "C:\\Files\\emptyFolder";
		
		try {
		Csv2kml.createProject(path1);
		Csv2kml.createProject(path2);
		Csv2kml.createProject(path3);
		Csv2kml.createProject(path4);
		
		} catch (Exception e) {
			System.err.println("The path is not valid, please try again.");
			assertEquals("The path is not valid, please try again.", e);
		}
		
	}
	
	@Test
	void testVaild() throws ParseException {
		String path = "C:\\Files";
		Csv2kml.createProject(path);
		System_data ds = new System_data();
		
		assertEquals(3,ds.getName()); // the function create 2 layers and project
		

	}

}

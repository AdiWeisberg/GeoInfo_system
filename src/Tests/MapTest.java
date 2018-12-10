package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

class MapTest {

	@Test
	void testValid() {
		try {
			BufferedImage img =ImageIO.read(new File("Ariel.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			assertNull(e);
		}
	}

	@Test
	void testNotValid() {
		//#1: check case 
		String path = "testFolder/Ariela.jpg";
		try {
			BufferedImage img =ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(),"Can't read input file!");
		}

		//#2:
		path = "testFolder/Ariela.pdf";
		try {
			BufferedImage img =ImageIO.read(new File(path));
			if(!path.endsWith("jpg") || !path.endsWith("png")) {
				throw new IOException("Can't read input file!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(),"Can't read input file!");
		}

		//#3:
		path = "Hellooo";
		try {
			BufferedImage img =ImageIO.read(new File(path));
			if(!path.endsWith("jpg") || !path.endsWith("png")) {
				throw new IOException("Can't read input file!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(),"Can't read input file!");
		}

	}




}

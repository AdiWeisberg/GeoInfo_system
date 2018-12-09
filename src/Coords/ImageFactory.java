package Coords;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Coords.Map;

public class ImageFactory{
	private static BufferedImage srcImg;
	
	public ImageFactory(BufferedImage img) {
		this.srcImg = img;
		EventQueue.invokeLater(new Runnable() {

			//private BufferedImage srcImg;

			@Override
			public void run() {
				ResizableImagePanel pane = new ResizableImagePanel();
				//this.srcImg =ImageIO.read(new File("Ariel.jpg"));
				pane.setImage(srcImg);
				JFrame frame = new JFrame("MapFrame");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(pane);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

}

class ResizableImagePanel extends JPanel  implements MouseListener {

	private Image img;
	private int x_ = -1;
	private int y_ = -1;

	public ResizableImagePanel() { // empty constructor
		this.addMouseListener(this);
	}

	public void setImage(Image value) {
		if (img != value) { 
			Image old = img; // save the old image
			this.img = value;
			firePropertyChange("image", old, img);
			revalidate(); // new component added and old one removed.
			repaint(); // redraw the map.
		}
	}

	public Image getImage() {
		return img;
	}

	@Override
	public Dimension getPreferredSize() { // Default size for corrupted image OR size of the current image.
		return img == null ? new Dimension(200, 200) : new Dimension(img.getWidth(this), img.getHeight(this));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (img != null) {
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouse Clicked");
		System.out.println("("+ e.getX() + "," + e.getY() +")");
		x_ = e.getX();
		y_ = e.getY();
		ResizableImagePanel pan = new ResizableImagePanel();
		pan.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("mouse entered");

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

class ResizableImagePanel extends JPanel {

	private Image img;
	private int x_ = -1; // Pixels Coordinats
	private int y_ = -1; // Pixels Coordinats

	public ResizableImagePanel() { // empty constructor
		//this.addMouseListener(this);
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
		return img == null ? new Dimension(500, 500) : new Dimension(img.getWidth(this), img.getHeight(this));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (img != null) {
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
	}

//	@Override
//	public void mouseClicked(MouseEvent e) {
//		System.out.println("mouse Clicked");
//		System.out.println("("+ e.getX() + "," + e.getY() +")");
//		x_ = e.getX();
//		y_ = e.getY();
//		ResizableImagePanel pan = new ResizableImagePanel();
//		pan.repaint();
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent e) {
//		System.out.println("mouse entered");
//
//	}
//
//	@Override
//	public void mouseExited(MouseEvent e) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void mousePressed(MouseEvent e) {
//		
//
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		// TODO Auto-generated method stub
//
//	}

}
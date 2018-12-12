
package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author annaf
 */
public class Menu extends JFrame implements MouseListener {
	// private variables
	private Container window;
	private ResizableImagePanel _panel;
	private Graphics _paper;
	private int x, y;
	private boolean isGamer;
	private ImageIcon img;
	private BufferedImage image;

	public Menu(){
		super("Map Demo"); //setTitle("Map Counter");  // "super" Frame sets its title
		//	Moves and resizes this component. 
		//	The new location of the top-left corner is  specified by x and y, 
		//	and the new size is specified by width and height
		//	setBounds(x,y,width,height)
		this.setBounds(0,0,1433,700); //setSize(1433,700);        // "super" Frame sets its initial window size
		//      Exit the program when the close-window button clicked
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();

	}

	public void setImage(ImageIcon value) {
		if (img != value) { 
			ImageIcon old = this.img; // save the old image
			this.img = value;
			firePropertyChange("image", old, img);
			revalidate(); // new component added and old one removed.
			repaint(); // redraw the map.
		}
	}

	public ImageIcon getImage() {
		return img;
	}


	//		public Dimension getPreferredSize() { // Default size for corrupted image OR size of the current image.
	//			return image == null ? new Dimension(200, 200) : new Dimension(image.getWidth(this), image.getHeight(this));
	//		}

	//	protected void paintComponent(Graphics g) {
	//		super.paintComponent(g);
	//		if (img != null) {
	//			Graphics2D g2d = (Graphics2D) g.create();
	//			g2d.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	//		}
	//	}

	public void createGui(){              				
		//	A Container is a component  that can contain other GUI components
		window = this.getContentPane(); 
		window.setLayout(new FlowLayout());

		//	Add "panel" to be used for drawing            
		_panel = new ResizableImagePanel();
		Dimension d= new Dimension(1433,642);
		_panel.setPreferredSize(d);		               
		window.add(_panel);

		// A menu-bar contains menus. A menu contains menu-items (or sub-Menu)
		JMenuBar menuBar;   // the menu-bar
		JMenu menu;         // each menu in the menu-bar
		JMenuItem menuItem1, menuItem2; // an item in a menu

		menuBar = new JMenuBar();

		// First Menu
		menu = new JMenu("Menu A");
		menu.setMnemonic(KeyEvent.VK_A);  // alt short-cut key
		menuBar.add(menu);  // the menu-bar adds this menu

		menuItem1 = new JMenuItem("Menu Item 1", KeyEvent.VK_F);
		menu.add(menuItem1); // the menu adds this item
		menuItem1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = true;
			}
		});
		menuItem2 = new JMenuItem("Menu Item 2", KeyEvent.VK_S);
		menu.add(menuItem2); // the menu adds this item
		menuItem2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = false;
			}
		});                     

		setJMenuBar(menuBar);  // "this" JFrame sets its menu-bar

		// Prepare an ImageIcon
		String imgMapFilename = "Ariel.jpg";    
		try {
			this.image = ImageIO.read(new File(imgMapFilename));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ImageIcon imgBck = new ImageIcon(this.image);
		JLabel labelMap = new JLabel();
		labelMap.setIcon(imgBck);
		_panel.add(labelMap);

		// panel (source) fires the MouseEvent.
		//panel adds "this" object as a MouseEvent listener.
		_panel.addMouseListener(this);
	}

	protected void paintElement() {
		//	The method getGraphics is called to obtain a Graphics object
		_paper = _panel.getGraphics();
		if(isGamer){
			_paper.setColor(Color.YELLOW);
			_paper.fillOval(x,y,10,10);
		} else {
			_paper.setColor(Color.RED);
			_paper.fillOval(x,y,10,10);
		}
		_paper.setFont(new Font("Monospaced", Font.PLAIN, 14));               
		_paper.drawString("("+Integer.toString(x)+", "+Integer.toString(y)+")",x,y-10);
	}

	//	public void mouseClicked(MouseEvent event){
	@Override
	public void mousePressed(MouseEvent event) {
		x = event.getX();
		y = event.getY();  
		paintElement();
	}
	// Not Used, but need to provide an empty body for compilation
	@Override
	public void mouseReleased(MouseEvent event){}
	@Override
	public void mouseClicked(MouseEvent event){
		System.out.println("mouse Clicked");
		System.out.println("("+ event.getX() + "," + event.getY() +")");
		x = event.getX();
		y = event.getY();
		ResizableImagePanel pan = new ResizableImagePanel();
		//_panel.repaint(); פה צריך לתקן את החלק של הגודל חלון בעת הזזה שלא מקושר טוב
		//paintElement();
	}
	@Override
	public void mouseExited(MouseEvent event){}
	@Override
	public void mouseEntered(MouseEvent event){
		System.out.println("mouse entered");
	}
	public static void main(String[] args) {
		Menu frame = new Menu();
		frame.setBounds(0, 0, 1433, 642);
		frame.createGui();
		frame.setVisible(true);
	}
}

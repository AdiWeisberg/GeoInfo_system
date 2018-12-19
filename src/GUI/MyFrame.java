package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import Algo.ShortestPathAlgo;

import java.util.Iterator;

import Coords.ConvertFactory;
import Coords.Map;
import Coords.Path;
import GIS.GIS_element;
import GameElements.Fruit;
import GameElements.Game;
import GameElements.Pacman;
import Geom.Point3D;

/**
 *
 * @author 
 */
public class MyFrame extends JFrame implements MouseListener{
	// private variables
	private int x, y;
	private static Game game;
	private int isGamer;//1=pacman,2=fruit,3=run,4=save,5=Open
	private BufferedImage image;
	private final JFileChooser openFileChosser;
	private MenuItem menuItem1, menuItem2,menuItem3,menuItem4,menuItem5,menuItem6,menuItem7,menuItem8; // an item in a menu
	private Image pacmanIcon, dountIcon;
	private Map map;
	private ShortestPathAlgo algo;

	//private Map map;
	//private final JFilesaver saveFile;

	public MyFrame(){
		super("Panter Map!"); //setTitle("Map Counter");  // "super" Frame sets its title
		this.map = new Map();
		this.setBounds(0,0,this.getWidth(),this.getHeight()); //setSize(1433,700);        // "super" Frame sets its initial window size
		//      Exit the program when the close-window button clicked
		try {
			this.image=map.getImg();
			this.pacmanIcon = ImageIO.read(new File("icons\\pacman.jpg"));
			this.dountIcon = ImageIO.read(new File("icons\\dount.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Can't read input file!");
			System.exit(-1);
		}

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		game =new Game();
		openFileChosser= new JFileChooser();
		openFileChosser.setCurrentDirectory(new File("C:\\Users\\n&e\\eclipse-workspace\\second_year\\GeoLnfo_System\\csvFile"));
		//openFileChosser.setFileFilter(new FileNameExtensionFilter("csv"));
		//map=new Map();
		//this.image=map.getImg();
		
		pack();
	}



	public void createGui(){              				
		//	A Container is a component  that can contain other GUI components
		//		window = this.getContentPane(); 
		this.setLayout(new FlowLayout());

		//	Add "panel" to be used for drawing            
		//		_panel = new ResizableImagePanel();
		Dimension d= new Dimension(1433,642);
		this.setPreferredSize(d);		               
		//		window.add(this);

		// A menu-bar contains menus. A menu contains menu-items (or sub-Menu)
		 MenuBar menuBar = new MenuBar();
		// First Menu
		Menu menu = new Menu("Menu");
		menuBar.add(menu);  // the menu-bar adds this menu
		//***add dounts to game: 
		menuItem1 = new MenuItem("Fruit");
		menu.add(menuItem1); // the menu adds this item
		menuItem1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer=2;
			}
		});

		//***add pacman to game: 
		menuItem2 = new MenuItem("Pacman");
		menu.add(menuItem2); // the menu adds this item
		menuItem2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				isGamer=1;
			}
		});

		//***run-game: 
		menuItem3 = new MenuItem("Run");
		menu.add(menuItem3); // the menu adds this item 
		menuItem3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer=3;
				runPath();
			}


		});

		//***save-game: 
		menuItem4 = new MenuItem("Save Game");
		menu.add(menuItem4); // the menu adds this item
		menuItem4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer=4;
				saveAs();

			}
		});

		//***open-game: 
		menuItem5 = new MenuItem("Open Game");
		menu.add(menuItem5); // the menu adds this item
		menuItem5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer=5;
				openFile();
				System.out.println("hiii3");
				repaint();
			}
		});

		//***clear-game: 
		menuItem6 = new MenuItem("Clear Game");
		menu.add(menuItem6); // the menu adds this item
		menuItem6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer=6;
				game.clearFruits();
				game.clearPacman();
				System.out.println("hiii4");
				repaint();
			}
		});

		//***KML-game: 
		menuItem7 = new MenuItem("save to KML");
		menu.add(menuItem7); // the menu adds this item 
		menuItem7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer=7;
			}
		});

		//***illustration of game: 
		menuItem8 = new MenuItem("run illustration");
		menu.add(menuItem8); // the menu adds this item 
		menuItem8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer=8;
			}
		});

		setMenuBar(menuBar);  // "this" JFrame sets its menu-bar
		// panel (source) fires the MouseEvent.
		//	panel adds "this" object as a MouseEvent listener.

		this.addMouseListener(this);
	}
	private void runPath() {
		this.algo= new ShortestPathAlgo(this.game,this);
		repaint();
	}
	private void openFile() {
		int returnValue = openFileChosser.showOpenDialog(this);
		if(returnValue==openFileChosser.APPROVE_OPTION) {
			try {
				game.csvToGame(openFileChosser.getSelectedFile().getPath());
			}catch(Exception ew) {
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent event) {
		System.out.println("mouse Clicked");
		System.out.println("("+ event.getX() + "," + event.getY() +")");
		x = event.getX();
		y = event.getY();
		Point3D p=new Point3D(x,y,0);
		Point3D P2= this.map.getCf().PicselToGps(p);
		System.out.println("("+ P2.x() + "," + P2.y() +")");
		//	_paper = this.getGraphics();
		//_paper.setFont(new Font("Monospaced", Font.PLAIN, 14));   
		if(isGamer==2){//fruit
			Fruit fruit;
			try {
				fruit = new Fruit(P2);
				game.addFruit(fruit);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			System.out.println("hiii1");
			this.getGraphics().drawImage(dountIcon, x,y, this);
			repaint();
		}

		else if(isGamer==1) {//pacman
			//p=new Point3D(x,y,0);
			System.out.println(this.getWidth() +" , "+ this.getHeight());
			Pacman pacman;
			try {
				pacman = new Pacman(P2);
				game.addPacman(pacman);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//this.getGraphics().drawImage(dountIcon, x,y, this);
			repaint();
		}
	}
	@Override
	public void mouseReleased(MouseEvent event){}

	@Override
	public void mouseClicked(MouseEvent event){
		
	}

	private void saveAs() {
		final JFileChooser saveAsFileChooser = new JFileChooser();
		FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("csv File", "csv");
		saveAsFileChooser.setFileFilter(extensionFilter);
		int actionDialog = saveAsFileChooser.showOpenDialog(this);
		if (actionDialog != JFileChooser.APPROVE_OPTION) {
			return;
		}

		File file = saveAsFileChooser.getSelectedFile();
		if (!file.getName().endsWith(".csv")) {
			file = new File(file.getAbsolutePath() + ".csv");
		}
		BufferedWriter outFile = null;

		//	outFile = new BufferedWriter(new FileWriter(file));
		game.GameTocsv(file.getName());
		//output.write(outFile);
	}

	@Override
	public void mouseExited(MouseEvent event){}

	@Override
	public void mouseEntered(MouseEvent event){
		System.out.println("mouse entered");
	}

	public void paint(Graphics g)
	{
		g.drawImage(image, 0, 0,this.getWidth(),this.getHeight(), this);

		Iterator<GIS_element> itr0= game.getFruits().iterator();
		Iterator<GIS_element> itr1 = game.getPacmans().iterator();


		//draw all the dounts to the screen: 
		while(itr0.hasNext()) {
			Fruit fruit = (Fruit)itr0.next();
			Point3D p = this.map.getCf().GpsToPicsel(fruit.getPoint());
			g.drawString("("+Integer.toString(x)+", "+Integer.toString(y)+")",x,y-10);
			g.drawImage(dountIcon, (int)p.x(),(int) p.y(), this);
		}

		//draw all the pacman to the screen:

		System.out.println("****");
		
		while(itr1.hasNext()) 
		{
			Pacman pacman = (Pacman)itr1.next();
			Point3D p = this.map.getCf().GpsToPicsel(new Point3D(pacman.getPoint()));
			System.out.println(p);
			g.drawString("("+Integer.toString(x)+", "+Integer.toString(y)+")",x,y-10);
			g.drawImage(pacmanIcon, (int)p.x(),(int) p.y(), this);
		}
		
		System.out.println("****");
		
		if(isGamer==3) {
		//draw all the Lines to the screen:
		Iterator<Path> itr3 = algo.getPaths().iterator();
		while(itr3.hasNext()) {
			Path path = itr3.next();
			Point3D start = this.map.getCf().GpsToPicsel(path.getPoints().get(0)); // start pacman
			for(int i=1; i<path.size(); i++) {
				Point3D end = this.map.getCf().GpsToPicsel(path.getPoints().get(i));
				g.setColor(Color.PINK);
				g.drawLine((int)start.x(), (int)start.y(), (int)end.x(), (int)end.y());
				start.set_x(end.x());
				start.set_y(end.y());
				start.set_z(end.z());
			}
			}
			
			//g.drawString("("+Integer.toString(x)+", "+Integer.toString(y)+")",x,y-10);
			//g.drawImage(pacmanIcon, (int)p.x(),(int) p.y(), this);
		}
		//g2.dispose();
		System.out.println("Number of pacmans: "+game.getPacmans().size());

	}
	public Image getPacmanIcon() {
		return pacmanIcon;
	}

	public void setPacmanIcon(Image pacmanIcon) {
		this.pacmanIcon = pacmanIcon;
	}

	public Image getDountIcon() {
		return dountIcon;
	}

	public void setDountIcon(Image dountIcon) {
		this.dountIcon = dountIcon;
	}

	public static void main(String[] args) {
		MyFrame f = new MyFrame();
		f.setBounds(0, 0, 1433, 642);
		f.createGui();
		f.setVisible(true);
	}
}
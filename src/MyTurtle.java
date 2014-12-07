import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

/*
 * MyTurtle is a superclass that has methods that can facilitate the production of mathematical patterns using turtle graphics.
 * Such methods include, penUp, penDown, set initial location, forward, getTile,setTile etc. All of the shapes that this turtle 
 * draws is stored in an ArrayList which can be accessed by calling getDrawings(). All MyTurtles must have the method curve(). This method
 * should be the method that effectively uses the MyTurtle functions to create whatever pattern the subclass may want to make. For example,
 * this could be the creation of a fractal. 
 */
public abstract class MyTurtle {
	// angle at which lines will be drawn
	private double tilt;
	// initial location of the turtle
	private Point2D.Double location;
	// penUp -> no lines drawn penDown -> lines are drawn
	private boolean pen;
	
	private static boolean PEN_UP = true;
	private static boolean PEN_DOWN = false;
	// set of all Shapes this draw has drawn
	private ArrayList<Shape> draw = new ArrayList<Shape>();
	
	/*
	 * A MyTurtle can be made using a combination of initial location, tilt, or nothing at all	
	 */
	public MyTurtle(Point2D.Double p, double t){
		location = p;
		tilt = t;
	}
	public MyTurtle(double x, double y, double t){
		this(new Point2D.Double(x,y),t);
	}
	public MyTurtle(Point2D.Double p){
		this(p,0);
	}
	public MyTurtle(double x, double y){
		this(x,y,0);
	}
	public MyTurtle(double t){
		this(0,0,t);
	}
	public MyTurtle(){
		this(0,0,0);
	}
	
	public void penUp(){
		pen = PEN_UP;
	}
	
	public void penDown(){
		pen = PEN_DOWN;
	}
	/*
	 * will move the turtle forward a given distance at whatever tilt it is currently at 
	 */
	public void forward(double distance){
		double deltaX = distance*Math.cos(Math.toRadians(tilt));
		double deltaY = distance*Math.sin(Math.toRadians(tilt));
		Point2D.Double newLocation = new Point2D.Double(location.x + deltaX, location.y + deltaY);
		if(pen == PEN_DOWN)
			draw.add(new Line2D.Double(location,newLocation));
		location = newLocation;
	}
	/*
	 * will add the given angle on to the current tilt angle. The method will automatically make sure that the 
	 * tilt remains between 0 and 360 inclusive
	 */
	public void setTilt(double angle){
		tilt = (tilt + angle)%360;
	}
	public double getTilt(){
		return tilt;
	}
	public void resetTilt(){
		tilt = 0;
	}
	public Point2D.Double getLocation(){
		return location;
	}
	
	public void setLocation(Point2D.Double loc){
		location = loc;
	}
	
	public ArrayList<Shape> getDrawings(){
		return draw;
	}
	// will clear the turtle's ArrayList of drawings
	public void clearDrawings(){
		draw.clear();
	}
	/*
	 * method that should be the primary means of creating whatever drawing/fractal the subclass may want to make
	 */
	public abstract void curve(int depth, int size);
	/*
	 * this method will create a JFrame to view the turtle's drawings. It uses the the class MyTurtleViewer which
	 * simply draws the Turtle's movements onto a JComponent which is then added to a JFrame
	 */
	public JFrame createFrame(){
		JFrame frame = new JFrame();
		JComponent comp = new MyTurtleViewer();
		frame.getContentPane().add(comp);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return frame;
	}
}

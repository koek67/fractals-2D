import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * MyTurtleViewer is a graphics runner class for MyTurtle. It can produce three types of MyTurtles: KochTurtle, SierpinskiTurtle, and DragonCurveTurtle.
 * This class is a viewer class that allows a user to vary the number of iterations for each fractal and observe how the concept of self-similarity
 * comes into play when producing a fractal.  
 */
public class MyTurtleViewer extends JComponent{
	// turtle object that will be drawn
	private MyTurtle turtle;
	
	// data for the slider that varies the number of iterations. The initial turtle is set to KochTurtle so the max no. of iterations
	// is set to 6
	private int sliderMax = 6;
	private JSlider slider = new JSlider(1,sliderMax,1);
	
	// data for the drop-down menu that allows a user to choose which fractal he or she wants to see
	private String[] turtleArray = {"Koch Curve","Sierpinski Triange","Dragon Curve"};
	private JComboBox comboBox = new JComboBox(turtleArray);
	
	// the panel that will hold the slider and the comboBox
	private JPanel componentPanel = new JPanel();
	
	// the currentLocation, depth, and size are set initially to work for the Koch Curve. Depth refers to the current iteration
	// the fractal is set to. Size refers to the length of the line for the curve
	private Point2D.Double currentLocation = new Point2D.Double(150,400);
	private int depth = 0;
	private int size = 500;
	
	// constructor initializes turtle to KochTurtle. This can later be changed to the other two fractals using the drop down menu
	public MyTurtleViewer(){
		turtle = new KochTurtle();
		turtle.setLocation(currentLocation);
	}
	/*
	 * the refresh method will set the background to black and the color of the turtle to pink
	 * this method will draw the shapes of the turtle it receives from the ArrayList of shapes that the turtle generates
	 */
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.BLACK);
		g2.drawRect(0,0,getWidth(),getHeight());
		g2.fillRect(0,0,getWidth(),getHeight());
		g2.setColor(Color.PINK);
		turtle.clearDrawings();
		turtle.setLocation(currentLocation);
		turtle.curve(depth, size);
		for(Shape s: turtle.getDrawings()){
			g2.draw(s);
		}

	}
	/*
	 * initializes all graphical components: JPanel with the JComboBox and JSlider
	 * included in this method are the addition of action and change listeners to the 
	 * JComboBox and JSlider
	 */
	public void init(){
		setLayout(new BorderLayout());
		
		comboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				JComboBox source = (JComboBox)arg0.getSource();
				int value = source.getSelectedIndex();
				switch(value){
				case 0:
					turtle = new KochTurtle();
					currentLocation = new Point2D.Double(150,400);
					size = 500;
					sliderMax = 6;
					slider.setMaximum(sliderMax);
					repaint();
					break;
				case 1:
					turtle = new SierpinskiTurtle();
					currentLocation = new Point2D.Double(150,400);
					size = 300;
					sliderMax = 7;
					slider.setMaximum(sliderMax);
					repaint();
					break;
				case 2:
					turtle = new DragonCurveTurtle();
					currentLocation = new Point2D.Double(400,300);
					size = 1;
					sliderMax = 17;
					slider.setMaximum(sliderMax);
					repaint();
					break;
				}
				repaint();
			}
		});
		
		slider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent arg0) {
				JSlider source = (JSlider)arg0.getSource();
				depth = source.getValue();
				repaint();
			}
			
		});
		
		componentPanel.add(comboBox);
		componentPanel.add(slider);
		this.add(componentPanel, BorderLayout.NORTH);
		JFrame f = turtle.createFrame();
		f.add(this);
		f.setVisible(true);
		f.setSize(800,600);
		f.setResizable(false);
		f.setTitle("Fractal Generator - Koushik Krishnan");
		repaint();
	}

	public static void main(String [] args){
		MyTurtleViewer t = new MyTurtleViewer();
		t.init();
	}
	

}

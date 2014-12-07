import java.awt.geom.Point2D;

/*
 * SierpinskiTurtle is a MyTurtle that draws the Sierpinski Triangle fractal. This fractal begins with an equilateral triangle. Then, the 
 * midpoints of each side is connected to form 4 more equilateral triangles. This process is repeated on the outer three triangles over and over again. 
 */
public class SierpinskiTurtle extends MyTurtle{
		
	public void curve(int depth, int size){
		if(depth < 1) return;
		if(depth == 1){
			setTilt(-60);
			forward(size);
			setTilt(120);
			forward(size);
			setTilt(120);
			forward(size);
			resetTilt();
		}
		else{
			Point2D.Double current = getLocation();
			double deltaX = size/4;
			double deltaY = deltaX*Math.sqrt(3);
			// do current location
			curve(depth-1,size/2);
			// top
			setLocation(new Point2D.Double(current.x + deltaX, current.y - deltaY));
			curve(depth-1,size/2);
			//right
			setLocation(new Point2D.Double(current.x + size/2, current.y));
			curve(depth-1, size/2);
		}
		
	}

}

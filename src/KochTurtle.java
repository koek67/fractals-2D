/*
 * Koch Turtle is a MyTurtle that can represent the Koch Curve fractal. This fractal involves taking a straight line, trisecting it, then 
 * making the middle third the base of an equilateral triangle. This process is repeated for all lines for each iteration.
 */
public class KochTurtle extends MyTurtle{
	
	public void curve(int depth, int size){
		if(depth < 1) return;
		if(depth == 1)
			forward(size);
		else{
			this.curve(depth - 1, size/3);
			setTilt(-60);
			this.curve(depth -1, size/3);
			setTilt(120);
			this.curve(depth -1, size/3);
			setTilt(-60);
			this.curve(depth -1, size/3);
		}
	}
}

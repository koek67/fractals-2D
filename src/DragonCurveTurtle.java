/*
 * DragonCurveTurtle is a MyTurtle that creates the Dragon Curve Fractal. The easiest way to describe this fractal is using a rectangular strip
 * of paper. Fold this paper length-wise over and over again then unravel it. The resulting shape will be the dragon curve fractal. It is almost 
 * impossible to recreate the true fractal using a strip of paper because folding a paper more than 5 times is quite challenging. This MyTurtle
 * shows what the paper would look like if it was folded many times over.
 */
public class DragonCurveTurtle extends MyTurtle{
	
	public void curve(int depth, int size){
		if(depth < 1) return;
		if(depth == 1){
			forward(size);
		}
		else{
			curve(depth - 1,size);
			setTilt(-90);
			reverseCurve(depth - 1, size);
		}
	}
	public void reverseCurve(int depth,int size){
		if(depth == 1){
			forward(size);
		}
		else{
			curve(depth - 1,size);
			setTilt(90);
			reverseCurve(depth - 1, size);
		}
	}

}

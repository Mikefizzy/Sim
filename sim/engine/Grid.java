package sim.engine;

import java.awt.Point;
import java.util.ArrayList;

public class Grid{
	private int width, height;
	private Point origin; 
	private Point[][] xz, yz, xy;
	private int offset;
	public Grid(int width, int height, Point origin, int offset){
		this.width = width;
		this.height = height;
		this.origin = origin;
		this.xz = (gridXz(offset));
		this.yz = gridYz(offset);
		this.xy = (gridXy(offset));
	}
	private Point[][] gridXz(int offset){
		this.offset = offset;
		Point[][]xz = new Point[origin.x/offset + origin.x%offset][];
		for(int k = origin.x; k>0; k-=offset){
			int index = (origin.x-k)/offset;
			int yBoundary = index*offset;
			int y =  origin.y + index*offset;
			Point[] currentVector = new Point[(y-yBoundary)/offset];
			int subscript = 0;
				yBoundary+=(y-yBoundary)%offset;
			for(;y>yBoundary;y-=offset){
				currentVector[subscript] = new Point(k,y);
				subscript++;
			}
			xz[index] = currentVector;
			
		}
		return xz;
	}
	private Point [][] gridYz(int offset){
		int xLen = width - origin.x;
		int yLen = origin.y/offset + origin.y%offset;
		Point[][] yz = new Point[xLen/offset - xLen%offset][];
		for(int k = origin.x; k<width; k+=offset){
			int index = (k-origin.x)/offset;
			Point[] currentVector = new Point[yLen];
			for(int y = 0; y<yLen; y++)
				currentVector[y] = new Point(k, origin.y - (y*offset));
			yz[index] = currentVector;
		}
		return yz;
	}
	/** 
	Generates the xy grid.
	*/
	private Point[][] gridXy(int offset){
		int x  = origin.x;
		int y  = origin.y;
		ArrayList<Point[]> xyList = new ArrayList<Point[]>();
		int index = 0;
		for(; y<height; y+=(offset/2)){
			index++;
			x-=offset/2;
			int bounds = width - index*offset/2;
			ArrayList<Point> current = new ArrayList<Point>();
			for(int x2 = x; x2<bounds; x2+=offset)
				current.add(new Point(x2, y));
			xyList.add(current.toArray(new Point[current.size()]));
		}
		return xyList.toArray(new Point[xyList.size()][]);
	}
	/** 
	Returns the length of the X axis.
	*/
	public int getLength(){
		return xy.length;
	}
	/** 
	 * Returns the length of the Y axis.
	*/
	public int getWidth(){
		return xy[0].length;
	}

	/** 
	Returns the point of origin. (0,0) in terms of the coordinate system.
	*/
	public Point getOrigin(){
		return origin;
	}
	/** 
	Returns a 2D array of the Xz coordinate plane.
	*/
	public Point[][] getXz() {
		return xz;
	}
	/** 
	Returns a 2D array of the Yz coordinate plane.
	*/
	public Point[][] getYz() {
		return yz;
	}
	/** 
	*Returns a 2D array of the Xy coordinate plane.
	*/
	public Point[][] getXy() {
		return xy;
	}
	public int getOffset() {
		return offset;
	}
	
}
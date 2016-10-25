package sim.entities;
import java.awt.Graphics;

import sim.engine.Grid;
public abstract class Particle {
	protected double x,y,z;
	protected int len;
	public abstract void render(Graphics g,Grid grid);
	public abstract void tick();
	public abstract void renderShadow(Graphics g, Grid grid);
	public Particle(int x, int y, int z, int len ){
		this.x = x;
		this.y = y;
		this.z = z;
		this.len = len;
	}

	public void bounds(Grid grid){
		int len  = this.len;
		if(x<len)
			x = len;
			
		if(y<len)
			y = len;
		
		if(x>=grid.getXy().length)
			x = (grid.getXy().length-1);
		if(y>=grid.getXy()[0].length)
			y = (grid.getXy()[0].length-1);
		if(z<0)
		z = (0);
		if(len<1)
			len = (1);
		else if(len>grid.getXy().length)
			len = (grid.getXy().length-1);
			
	}
	public int getX(){
		return (int) x;
	}
	public int getY(){
		return (int) y;	
		}
	public int getZ(){
		return (int) z;
	}
	public void setX(int n){
		x = n;
	}
	public void setY(int n){
		y = n;
	}
	public void setZ(int n){
		z = n;
	}
	public void setLen(int n){
		this.len = n;
	}
	public int getLen(){
		return len;
	}
}

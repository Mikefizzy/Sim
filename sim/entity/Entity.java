package sim.entity;

import java.awt.Graphics;
import java.util.ArrayList;

import sim.engine.Grid;

public abstract class Entity {
	public abstract void tick(ArrayList<Entity> entities);
	public abstract void render(Graphics g, Grid grid);
	public double x,y,z;
	public double velocity;
	private double grace = 0;
	public double collisionRadius;
	public double getGrace() {
		return grace;
	}
	public void incrementGrace(){
		grace++;
	}
	
}
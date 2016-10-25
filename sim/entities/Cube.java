package sim.entities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import sim.engine.Grid;
public abstract class Cube extends Particle{
	private Color faceColor, sideColor, topColor;
	public Cube(int x, int y, int z, int len, Color faceColor, Color sideColor, Color topColor){
		super(x, y, z, len);
		this.faceColor = faceColor;
		this.sideColor = sideColor;
		this.topColor = topColor;
	}
	public Cube(int x, int y, int z, int len) {
		this(x, y, z, len, Color.red, Color.pink, Color.BLUE);
	}
	public Cube(int x, int y,int len) {
		this(x, y, 0, len);
	}
	public abstract void onTick();
	public void render(Graphics g, Grid grid) {
		int offset = grid.getOffset();
		Point[][] xy = grid.getXy();
		int x = (int) this.x;
		int y = (int) this.y;
		int z = (int) this.z;
		Point v1 = xy[x][y];
		Point v2 = xy[x][y-len];
		Point v3 = xy[x-len][y];
		Point v4 = xy[x-len][y-len];
		Point v5 = new Point(v1.x, v1.y - len*offset);
		Point v6 = new Point(v2.x, v2.y - len*offset);
		Point v7 = new Point(v3.x, v3.y -len*offset);
		Point v8 = new Point(v4.x, v4.y - len*offset);
			int[][] face = {{v1.x, v2.x,v6.x,v5.x}, {v1.y -z , v2.y- z,v6.y -z ,v5.y -z}};;
			int[][] side = {{v1.x, v5.x, v7.x, v3.x},{v1.y -z, v5.y- z, v7.y -z, v3.y-z}};
			int[][] top =  {{v5.x, v6.x, v8.x, v7.x},{v5.y-z, v6.y-z, v8.y-z, v7.y-z}};
		g.setColor(faceColor);
		g.fillPolygon(face[0], face[1], 4);
		g.setColor(sideColor);
		g.fillPolygon(side[0], side[1], 4);
		g.setColor(topColor);
		g.fillPolygon(top[0], top[1], 4);
	}
	public void renderShadow(Graphics g, Grid grid){
		int x = (int) this.x;
		int y = (int) this.y;
		int z = (int) this.z;
		Point[][] xy = grid.getXy();
		Point v1 = xy[x][y];
		Point v2 = xy[x][y-len];
		Point v3 = xy[x-len][y];
		Point v4 = xy[x-len][y-len];
		if(z>0){
			int[][] shadow = {{v1.x, v2.x, v4.x, v3.x}, {v1.y, v2.y, v4.y, v3.y}};
			g.setColor(Color.black);
			g.fillPolygon(shadow[0], shadow[1], 4);
		}
	}
	public void tick() {
		onTick();
	}

}

package sim.engine;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;
public abstract class Engine implements Runnable{
	private int fps = 60;
	private volatile boolean running;
	private Gui gui;
	private Grid grid;
	public Engine(Grid grid,Gui gui, int fps){
		this.fps = fps;
		this.grid = grid;
		this.gui = gui;
		gui.init();
	}
	public Engine(Grid grid, Gui gui){
		this(grid, gui, 60);
	}
	public Engine(Grid grid){
		this(grid, Gui.getInstance());
		
	}
	public Engine(int offset){
		this(new Grid(Gui.WIDTH, Gui.HEIGHT, new Point((Gui.WIDTH/2)-100, Gui.HEIGHT/2), offset));
	}
	public void setFps(int n){
		this.fps  = n;
	}
		public void run() {
			onStart();
		if(!running){
			
			running = true;
			while(running){
				tick();
				render();
				sleep();
			}
		}
	}
		public abstract void onStart();
		public abstract void tick();
		public abstract void onRender(Graphics g);
	public void render(){
		Graphics g = gui.getGraphics();
		BufferStrategy bs = gui.getBs();
		g.clearRect(0, 0, Gui.WIDTH, Gui.HEIGHT);
		onRender(g);
		bs.show();
	}
	public void renderGrid(Graphics g, int offset){
		g.setColor(Color.BLACK);
		for(int x = 0; x<grid.getXy().length;x++)
			g.fillRect(grid.getXy()[x][0].x, grid.getXy()[x][0].y, 1, 1);
		
		for(int x = 0; x<grid.getXy()[0].length;x++)
			g.fillRect(grid.getXy()[0][x].x, grid.getXy()[0][x].y, 1, 1);
		
		for(int x  = 0; x<grid.getYz()[0].length; x++)
			g.fillRect(grid.getYz()[0][x].x, grid.getYz()[0][x].y, 1, 1);
		int[][] outerright = {{grid.getXy()[grid.getXy().length-1][grid.getXy()[0].length-1].x, Gui.WIDTH, Gui.WIDTH},
				{grid.getXy()[grid.getXy().length-1][grid.getXy()[0].length-1].y, grid.getOrigin().y, Gui.HEIGHT}};
		g.fillPolygon(outerright[0], outerright[1], 3);
	}
	private void sleep(){
		try {
			Thread.sleep(1000/fps);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
	}
	public Grid getGrid(){
		return grid;
	}
	public Gui getGui(){
		return gui;
	}
}

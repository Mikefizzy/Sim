package sim.engine;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Gui{
	public static final int WIDTH = 800, HEIGHT  = 600;
	private JFrame frame;
	private Canvas canvas;
	private BufferStrategy bs;
	private Listener listener;
	private Gui (){}
	private static Gui instance;
	public static Gui getInstance(){
		if(instance == null)
			instance = new Gui();
		return instance;
	}
	/** 
	Sets the Gui visible and initializes components.
	*/
	public void init(Listener listener){
		frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		canvas = new Canvas();
		canvas.setSize(WIDTH, HEIGHT);
		this.listener = listener;
		if(listener!=null){
		canvas.addKeyListener(listener);
		canvas.addMouseListener(listener);
		}
		frame.add(canvas);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
	}
	/** 
	Sets the Gui visible and initializes components.
	*/
	public void init(){
		this.init(null);
	}
	public BufferStrategy getBs(){
		return bs;
	}
	public Graphics getGraphics(){
		return bs.getDrawGraphics();
	}
	public Listener getListener() {
		return listener;
	}
	public void setVisible(boolean set){
	this.frame.setVisible(set);
	}
}

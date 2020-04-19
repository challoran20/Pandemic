package Pandemic;

import java.awt.Color;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

public class WorldController extends GraphicsProgram {
	
	private World theWorld;
	private GCanvas theWorldCanvas;
	public static final int APPLICATION_WIDTH = 200;
	public static final int APPLICATION_HEIGHT = 200;
	RandomGenerator rgen = new RandomGenerator ();
	
	public void run(){	
		setUpWorld();
		runWorld();
	}
	
	public void init(){
	    resize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}
	
	public void setUpWorld(){
		theWorld = new World(20,20);
		for (int i=0; i<50; i ++) {
			int randX = rgen.nextInt (0,theWorld.getWidth()-1);
			int randY = rgen.nextInt (0,theWorld.getHeight()-1);
			theWorld.getCreatureList().add (new Healthy (new Location (randX, randY), theWorld));
		}
		theWorld.getCreatureList().add (new InfectedAsymptomatic (new Location (1,1), theWorld));
		theWorldCanvas = this.getGCanvas();
		//System.out.print(theWorld.getCreatureList().get(49).getClass().getName());
		//System.out.print(theWorld.getCreatureList().get(50).getClass().getName());
	}
	
	public void runWorld(){
		drawWorld();
		for(int i=0; i<50;i++){
			theWorld.letTimePass();
			pause(500);
			drawWorld();
		}
	}	
	
	public void drawWorld(){
		drawBlankWorld();
		drawCreatures();
	}
	
	public void drawBlankWorld(){
		for(int row = 0 ; row<theWorld.getWidth(); row++)
			for(int col=0; col<theWorld.getHeight(); col++){
				GRect r = new GRect(row*10, col*10, 10, 10);
				r.setFillColor(Color.WHITE);
				r.setFilled(true);
				theWorldCanvas.add(r);
			}
	}
	
	public void drawCreatures(){
		for(LifeForm x: theWorld.getCreatureList()){
			GRect r = new GRect (x.getMyLocation().getX()*10, x.getMyLocation().getY()*10,10,10);
			r.setFillColor(x.getMyColor());
			r.setFilled(true);
			theWorldCanvas.add(r);
		}
	}
}

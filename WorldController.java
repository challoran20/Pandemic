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
		ArrayList<Location> locationList = new ArrayList <Location>();
		locationList.add(new Location (1,1));
		theWorld = new World(20,20);
		int randX = -1;
		int randY = -1;
		for (int i=0; i<49; i ++) {
			boolean validPosition = false;
			while (validPosition == false) {
				randX = rgen.nextInt (0,theWorld.getWidth()-1);
				randY = rgen.nextInt (0,theWorld.getHeight()-1);
				for (Location loc: locationList) {
					if (loc.getX() != randX || loc.getY() != randY) {
						validPosition = true;
					} else if (loc.getX() == randX && loc.getY() == randY) {
						validPosition = false;
						break;
					}
				}
			}
			if (validPosition == true) {
				Healthy healthy = new Healthy (new Location (randX, randY), theWorld);
				healthy.setAge(rgen.nextInt(0,80));
				theWorld.getCreatureList().add (healthy);
				locationList.add(new Location (randX,randY));
			}
		}
		theWorld.getCreatureList().add (new InfectedAsymptomatic (new Location (1,1), theWorld));
		theWorldCanvas = this.getGCanvas();
	}
	
	public void runWorld(){
		drawWorld();
		for(int i=0; i<30;i++){
			int currentSizeOfCreatureList = theWorld.getCreatureList().size();
			int totalSick = 0;
			for(int j=0; j< currentSizeOfCreatureList; j++) {
				if (theWorld.getCreatureList().get(j).getMyColor() == Color.RED || theWorld.getCreatureList().get(j).getMyColor() == Color.yellow) {
					totalSick ++;
				} 
			}
			System.out.println ("Number of sick people:" + totalSick);
			
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

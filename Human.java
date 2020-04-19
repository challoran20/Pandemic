package Pandemic;


import java.awt.Color;
import acm.util.RandomGenerator;


public abstract class Human extends LifeForm {
	
	RandomGenerator rgen = new RandomGenerator ();

	public Human(Location loc, World w) {
		super(loc, w);
		myColor = Color.green;
		myLifeSpan = 90;
	}
	
	public Human(int myLifeSpan, Location myLocation, Color myColor, World myWorld) {
		super(myLifeSpan, myLocation, myColor, myWorld);
	}
	
}
    

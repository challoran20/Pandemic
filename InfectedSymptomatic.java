package Pandemic;


import java.awt.Color;

public class InfectedSymptomatic extends Human {

	public InfectedSymptomatic(Location loc, World w) {
		super(loc, w);
		myColor = Color.red;
	}
	
	public InfectedSymptomatic(int myLifeSpan, Location myLocation, Color myColor, World myWorld) {
		super(myLifeSpan, myLocation, myColor, myWorld);
	}

	
	public void move () {
		
	}
	
	public void checkInfection() {
		int myX = myLocation.getX();
		int myY = myLocation.getY();
		int myAge = this.getAge();
		if (this.getAge() > 20 && this.getAge() <=50) {
			if (rgen.nextInt (1,100) <= 80) {
				myWorld.getCreatureList().remove(this);
				myWorld.getCreatureList().add(new Recovered(80-myAge, new Location (myX,myY), Color.orange, myWorld));
			}
		}
		if (this.getAge() > 50) {
			if (rgen.nextInt (1,100) > 80) {
				myWorld.getCreatureList().remove(this);
			}
		}
	}
	

}

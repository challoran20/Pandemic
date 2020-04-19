package Pandemic;

import java.awt.Color;

public class InfectedAsymptomatic extends Human {

	public InfectedAsymptomatic(Location loc, World w) {
		super(loc, w);
		myColor = Color.yellow;
	}
	
	public InfectedAsymptomatic(int myLifeSpan, Location myLocation, Color myColor, World myWorld) {
		super(myLifeSpan, myLocation, myColor, myWorld);
	}
		
	public void move () {
		boolean validPosition = true;
        int newX = this.getMyLocation().getX() + rgen.nextInt(-1,1);
        int newY = this.getMyLocation().getY() +  rgen.nextInt(-1,1);
        if (newX < 0 || newX >= myWorld.getWidth() || newY < 0 || newY >= myWorld.getHeight()) {
              validPosition = false;
              for (LifeForm c: myWorld.getCreatureList()){
                    if (c.getMyLocation().getX() == newX && c.getMyLocation().getY() == newY) {
                             validPosition = false;
                     }
                }
          }
         
       if (validPosition == true) {
   			myLocation.setX(newX);
   			myLocation.setY(newY);
        }
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
				myWorld.getCreatureList().add(new Recovered(80-myAge, new Location (myX,myY), Color.orange, myWorld));
			}
		}
	}
	
}

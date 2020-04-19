package Pandemic;

import java.awt.Color;

public class Healthy extends Human {

	public Healthy(Location loc, World w) {
		super(loc, w);
		myColor = Color.green;
	}
	
	public Healthy(int myLifeSpan, Location myLocation, Color myColor, World myWorld) {
		super(myLifeSpan, myLocation, myColor, myWorld);
	}
		
	public void checkInfection() {
		
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
	
	public void infect () {
		int myX = myLocation.getX();
		int myY = myLocation.getY();
		int myAge = this.getAge();
		for (int i = myWorld.creatureList.size() - 1; i >= 0; i--){
			LifeForm c = myWorld.creatureList.get(i);
			//System.out.print(c.getClass().getName());
			if (Math.abs(c.getMyLocation().getX() - myX)<=2 && Math.abs(c.getMyLocation().getY() - myY)<=2) {
				if (c.getClass().getName().equals("Pandemic.InfectedSymptomatic") || c.getClass().getName().equals("Pandemic.InfectedAsymptomatic")) {
				int randomPercent = rgen.nextInt(1,100);
					if (randomPercent > 30) {
						myWorld.getCreatureList().remove(this);
						int fiftyPercent = rgen.nextInt(1,2);
						if (fiftyPercent == 1) {
							myWorld.getCreatureList().add(new InfectedSymptomatic (80-myAge, new Location (myX,myY), Color.RED, myWorld));
						} else {
							myWorld.getCreatureList().add(new InfectedAsymptomatic (80-myAge, new Location (myX,myY), Color.yellow, myWorld));
						}
					}
				}
			}
		}
	}
	
}


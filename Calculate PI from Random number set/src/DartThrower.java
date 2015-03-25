package edu.vsu.csci693;

public class DartThrower implements Runnable 
{
	public long dartsInCircle;
	private long numberOfDarts;
	
	public DartThrower(long numDarts)
	{
		numberOfDarts = numDarts;
	}
	
	public void run() {
		dartsInCircle = 0;
		for(int i=0; i<numberOfDarts; i++)
		{
                    //get two random numbers x and y between 0 and 1
                       
	            double xPos = (Math.random() * (1 - 0)) + 0;
	            double yPos = (Math.random() * (1 - 0)) + 0;
                    
                    //System.out.println("xPos = " + xPos);
                    //System.out.println("yPos = " + yPos);
                    
                    //determine whether the coordinate (x,y) is within the circle
                    //(e.g. Math.sqrt(Math.pow(x-0.5, 2) + Math.pow(y-0.5, 2)))
                    
                    double Distance = Math.sqrt(Math.pow(xPos-0.5, 2) + Math.pow(yPos-0.5, 2));
                    
                        //if yes, increment dartsInCircle variable
                    
                        if (Distance < 0.5)
                         {
                            dartsInCircle++;
                            
                            //System.out.println("Distance = " + Distance + "\n");
                         }
                        //System.out.println("Darts in Circle = " + dartsInCircle + "\n");
	        }
			
	}               
}
	

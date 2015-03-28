package edu.vsu.csci693;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class NBodySimulatorWorker implements Runnable {
	
	private int numberOfBodies;
	private int timeStepsToSimulate;
	private int start;
	private int lengthPerThread;
	
	private static CyclicBarrier barrier;
	
	public NBodySimulatorWorker(int timeStepsToSimulate, int numberOfBodies, int threadId, int numberThreads)
	{
		this.numberOfBodies = numberOfBodies;
		this.timeStepsToSimulate = timeStepsToSimulate;
		
		//important calculation to divide work
		lengthPerThread = numberOfBodies / numberThreads;
		start = threadId * lengthPerThread;
		
                
                if(threadId == 0){
                    barrier = new CyclicBarrier(numberThreads);
                }
	}
	
	@Override
	public void run() {		
		for(int k=0; k<timeStepsToSimulate; k++) {	
			
			//arrays to store the forces acting on each planet in X and Y dimension
			double[] forcesX = new double[lengthPerThread];
			double[] forcesY = new double[lengthPerThread];
			
			//TODO: divide the work here
                        
			for(int j=start; j<start+lengthPerThread; j++)
			{
				double fx = 0.0;
				double fy = 0.0;
				
				//calculate the gravitational force (fx,fy) from all the other planets
				for(int i=0; i<numberOfBodies; i++) {
					if(i == j) continue;
					double d = distance(NBodySimulator.sharedBodiesArray[j].posX,
										NBodySimulator.sharedBodiesArray[j].posY,
										NBodySimulator.sharedBodiesArray[i].posX,
										NBodySimulator.sharedBodiesArray[i].posY);
					double f = force(NBodySimulator.sharedBodiesArray[j].mass, NBodySimulator.sharedBodiesArray[i].mass, d);
					fx += f * (NBodySimulator.sharedBodiesArray[j].posX - NBodySimulator.sharedBodiesArray[i].posX) / d;
					fy += f * (NBodySimulator.sharedBodiesArray[j].posY - NBodySimulator.sharedBodiesArray[i].posY) / d;										
				}

				forcesX[j-start] = fx;
				forcesY[j-start] = fy;
			}
			
			// first barrier
                        
			try {
				barrier.await();
	        } catch (InterruptedException ex) {
	        	return;
	        } catch (BrokenBarrierException ex) {
	        	return;
	        }
                        
                       // System.out.println("TimeStep " + k + " passed barrier 1");

			//TODO: divide the work here	
                        for(int j=start; j<start+lengthPerThread; j++)
			//for(int j=0; j<numberOfBodies; j++)
			{
				// TODO: calculate the new acceleration, velocity, & position for each planet, based on forcesX and forcesY
                                double ax = 0.0;
                                double ay = 0.0;
                                
                                ax = forcesX[j-start] / NBodySimulator.sharedBodiesArray[j].mass;
                                ay = forcesY[j-start] / NBodySimulator.sharedBodiesArray[j].mass;
                                
                                double vx = 0.0;
                                double vy = 0.0;
                                
                                vx = NBodySimulator.sharedBodiesArray[j].velX + ax;
                                vy = NBodySimulator.sharedBodiesArray[j].velY + ay;
                                
                                double px = 0.0;
                                double py = 0.0;
                                
                                px = NBodySimulator.sharedBodiesArray[j].posX + vx;
                                py = NBodySimulator.sharedBodiesArray[j].posY + vy;
                                
                               NBodySimulator.sharedBodiesArray[j].posX = px;
                               NBodySimulator.sharedBodiesArray[j].posY = py;
                               
                               NBodySimulator.sharedBodiesArray[j].velX = vx;
                               NBodySimulator.sharedBodiesArray[j].velX = vy;
                                  
			}
								
			// second barrier
                        
			try {
				barrier.await();
	        } catch (InterruptedException ex) {
	        	return;
	        } catch (BrokenBarrierException ex) {
	        	return;
	        } 
                	//System.out.println("TimeStep " + k + " passed barrier 2");
                        
		}	
                        
	}

	private double distance(double x1, double y1, double x2, double y2)
	{
	  double d = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	  return d;
	}

	/**
	  * return the magnitude of the gravitational force between two bodies of
	  * mass m1 and m2 that are distance r apart
	  */
	private double force(double m1, double m2, double r) {
	  double G = 6.67E-11;
	  double F = (G * m1 * m2) / r;
	  return F;
	}
}

package edu.vsu.csci693;

public class NBodySimulator 
{	
	public static CelestialBody[] sharedBodiesArray;
	
	public static long simulateNBodyInParallel(int timeStepsToSimulate, CelestialBody[] bodiesArray, int numberOfBodies, int numberOfThreads)
	{
		sharedBodiesArray = bodiesArray; //copies the reference	
		long startTime = System.nanoTime();
		
		//TODO: start the threads --------------------------------------------------------------------------------------------
                
                Thread [] threadHandles = new Thread [numberOfThreads];
                NBodySimulatorWorker [] workerHandles = new NBodySimulatorWorker [numberOfThreads];
            
		
		//do the work with numberOfThreads threads
                for(int i=0; i<numberOfThreads; i++)
		{
			workerHandles[i] = new NBodySimulatorWorker(timeStepsToSimulate, numberOfBodies, i, numberOfThreads);
			threadHandles[i] = new Thread(workerHandles[i]);
			threadHandles[i].start();
		}
                
                for(int i=0; i<numberOfThreads; i++)
		{
			try 
                        {
				threadHandles[i].join();
			} 
                        
                        catch (InterruptedException e) 
                        {
				//do nothing
			}
                }        
                
		//-----------------------------------------------------------------------------------------------------------------------
                
		long endTime = System.nanoTime();
		return endTime - startTime;
	}
	
	public static long simulateNBodyInSerial(int timeStepsToSimulate, CelestialBody[] bodiesArray, int numberOfBodies)
	{
		sharedBodiesArray = bodiesArray; //copies the reference
		long startTime = System.nanoTime();	
		
		NBodySimulatorWorker nbodyWorker = new NBodySimulatorWorker(timeStepsToSimulate, numberOfBodies, 0, 1);
		nbodyWorker.run();	
		
		long endTime = System.nanoTime();		
		return endTime - startTime;
	}

	public static CelestialBody[] generateRandomBodiesArray(int numberOfBodies)
	{
		CelestialBody[] array = new CelestialBody[numberOfBodies];
		for(int i=0; i<numberOfBodies; i++) {
			 CelestialBody body = new CelestialBody();
			 body.name = "planet" + i;
			 body.mass = Math.random();
			 body.posX = Math.random(); 
			 body.posY = Math.random();
			 body.velX = 0.0;
			 body.velY = 0.0;
			 array[i] = body;			
		}
		return array;
	}
}

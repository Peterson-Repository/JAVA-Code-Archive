package edu.vsu.csci693;

public class PIEstimator 
{
	public static long EstimatePIInParallel(long numberOfDarts, int numberOfThreads)
	{
		Thread [] threadHandles = new Thread [numberOfThreads];
		DartThrower [] workerHandles = new DartThrower [numberOfThreads];
		
		long startTime = System.nanoTime();
                long ThreadStartTime = System.nanoTime();
		for(int i=0; i<numberOfThreads; i++)
		{
			workerHandles[i] = new DartThrower(numberOfDarts/numberOfThreads);
			threadHandles[i] = new Thread(workerHandles[i]);
			threadHandles[i].start();
		}
                long ThreadsEndTime = System.nanoTime();
                
                Long TotalThreadStart = ThreadsEndTime - ThreadStartTime;
                System.out.println("Time To Start Threads = " + TotalThreadStart);

                
                long ExThreadStartTime = System.nanoTime();
		
                long allThreadsDartsInCircle = 0;
		for(int i=0; i<numberOfThreads; i++)
		{
			try {
				threadHandles[i].join();
				allThreadsDartsInCircle += workerHandles[i].dartsInCircle;
			} catch (InterruptedException e) {
				//do nothing
			}
		}
                long ExThreadEndTime = System.nanoTime();
                
                Long TotalExThreadTime = ExThreadEndTime - ExThreadStartTime;
                System.out.println("Time To Execute Threads = " + TotalExThreadTime);

                
		long endTime = System.nanoTime();

		double PI = 4*(allThreadsDartsInCircle * 1.0 /numberOfDarts);
		//System.out.println("estimated PI = " + PI);
		
		return endTime - startTime;
	}
        
        
        
        
	
	public static long EstimatePIInSerial(long numberOfDarts)
	{
		long startTime = System.nanoTime();
		DartThrower worker = new DartThrower(numberOfDarts);
		//this executes in the same thread, doesn't start a new one
		worker.run();
		long endTime = System.nanoTime();
		
		double PI = 4*(worker.dartsInCircle * 1.0 /numberOfDarts);
		//System.out.println("estimated PI = " + PI);
		
		return endTime - startTime;
	}

}

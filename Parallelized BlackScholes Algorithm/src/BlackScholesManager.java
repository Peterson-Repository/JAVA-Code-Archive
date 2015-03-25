package edu.vsu.csci693;

public class BlackScholesManager 
{	
	public static long computeCallPriceParallel(StockOption option, long iterations, int numberOfThreads)
	{
		long startTime = System.nanoTime();
		
		//do the work with numberOfThreads threads
                //----------------------------------------------------
                
                Thread [] threadHandles = new Thread [numberOfThreads];
                BlackScholesWorker [] workerHandles = new BlackScholesWorker [numberOfThreads];
                
                 for(int i=0; i<numberOfThreads; i++)
		{
                        workerHandles[i] = new BlackScholesWorker(i, numberOfThreads, iterations);
			//workerHandles[i] = new BlackScholesWorker(0, i, numberOfThreads);
			threadHandles[i] = new Thread(workerHandles[i]);
			threadHandles[i].start();
		}
                //----------------------------------------------------------
		
		long endTime = System.nanoTime();
		
		return endTime - startTime;
	}
	
	public static long computeCallPriceSerial(StockOption option, long iterations)
	{
		long startTime = System.nanoTime();
		
		BlackScholesWorker bsThread = new BlackScholesWorker(0, 1, iterations);
		
		bsThread.setOption(option);
		
		bsThread.run();
		
		long endTime = System.nanoTime();
		
		return endTime - startTime;
	}
}

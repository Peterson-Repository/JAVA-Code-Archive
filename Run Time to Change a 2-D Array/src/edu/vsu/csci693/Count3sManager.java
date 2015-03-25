package edu.vsu.csci693;

import java.util.Random;


public class Count3sManager 
{
	private static final int RandomNumbersRange = 10;
	
        
        /**
         
         * 
         * @param arraySize
         * @return 
         */
	public static int [][] GenerateNumbersArray(int arraySize)
	{
		int [][] numbers = new int[arraySize][arraySize];
	        Random randomGenerator = new Random();	
                
		for(int i=0; i<arraySize; i++){
                    
                    for(int j=0; j<arraySize; j++){
                        
			numbers[i][j] = randomGenerator.nextInt(RandomNumbersRange);
                    }
                }
                
                
               
                int PrintNumRows = 10;
                int PrintNumColumns = 10;
                
                 System.out.println("Random 2D Array Created, Printing 1st " + PrintNumRows + " Rows and " + PrintNumColumns + " Columns." );
                System.out.println("-----------------------");
                
                for(int z=0; z<PrintNumRows; z++){
                    for(int q=0; z<PrintNumColumns; z++){
                    System.out.println("Row " + (z+1) + " Column " + (q+1) + " = " + numbers[z][q] + "\n");
                    }
                }
                
                //System.out.println("After Multiplication");
                //System.out.println("-----------------------");
		return numbers;
	}
	
        
        /**
         
         * 
         * @param numbers
         * @param numberOfThreads
         * @return 
         */
	public static long TimeCountingThreesInParallel(int arraySize, int [][] numbers, int numberOfThreads)
	{
		int totalThrees = 0;
		Thread [] threadHandles = new Thread [numberOfThreads];
		Count3sWorker [] workerHandles = new Count3sWorker [numberOfThreads];
		
		long startTime = System.nanoTime();
		for(int i=0; i<numberOfThreads; i++)
		{
			workerHandles[i] = new Count3sWorker(arraySize, numbers, i, numberOfThreads);
			threadHandles[i] = new Thread(workerHandles[i]);
			threadHandles[i].start();
		}
		
		for(int i=0; i<numberOfThreads; i++)
		{
			try 
                        {
				threadHandles[i].join();
				totalThrees += workerHandles[i].threes;
			} 
                        
                        catch (InterruptedException e) 
                        {
				//do nothing
			}
		}	
		long endTime = System.nanoTime();
		
		
		return endTime - startTime;
	}
	
        
}
package edu.vsu.csci693.test;


import edu.vsu.csci693.PIEstimator;
import org.junit.Test;

public class TimeCharacterization {
	
	@Test
	public void testTimeEstimatingPI() {
		int numberOfDarts = 10000000;
                
                int[] NumOfThreads = {2,4};
               
                
                for (int i = 0; i < NumOfThreads.length; i++){
                    
                    long DisplayStartTime = System.nanoTime();
                    
                    System.out.println("Running " + NumOfThreads[i] + " Thread");
                    
                    int numberOfThreads = NumOfThreads[i];
                
                    long parallelTime = PIEstimator.EstimatePIInParallel(numberOfDarts, numberOfThreads);
		
                   
                    long DisplayEndTime = System.nanoTime();
                    long TotalDisplayTime = DisplayEndTime - DisplayStartTime;
                    
                    System.out.println("Display Time " + TotalDisplayTime);
                    
                    System.out.println("Total Time From Start to finish " + (parallelTime + TotalDisplayTime) + "\n");
               
                    
                }
        }
}


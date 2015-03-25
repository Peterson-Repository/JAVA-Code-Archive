package edu.vsu.csci693.test;

import org.junit.Test;

import edu.vsu.csci693.Count3sManager;

// Run this test file to see Run time Results

public class Count3sTests {
	
	@Test
	public void testTimeCountingThreesInParallel_4_50000() {
		int numbersArraySize = 10;
		int numberOfThreads = 4;
		
		int [][] numbers = Count3sManager.GenerateNumbersArray(numbersArraySize);
		long parallelTime = Count3sManager.TimeCountingThreesInParallel(numbersArraySize, numbers, numberOfThreads);

                int PrintNumRows = 10;
                int PrintNumColumns = 10;
                
                System.out.println();
                System.out.println("After Multiplication Displaying 1st " + PrintNumRows + " Rows and " + PrintNumColumns + " Columns." );
                System.out.println("-----------------------");
         
                        for(int z=0; z<PrintNumRows; z++){
                            for(int q=0; z<PrintNumColumns; z++){
                                 System.out.println("Row " + (z+1) + " Column " + (q+1) + " = " + numbers[z][q] + "\n");
                            }
                        }
                        
                System.out.println("parallel time with " + numberOfThreads + " threads= " + parallelTime / 1000 + " microseconds");
                        
        }
}

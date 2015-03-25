package edu.vsu.csci693.test;

import org.junit.Test;

import edu.vsu.csci693.BlackScholesWorker;
import edu.vsu.csci693.BlackScholesManager;
import edu.vsu.csci693.StockOption;

/*
*  Information calculated based on closing data on Monday, June 9th 2003.
*
*      Microsoft:   share price:             23.75
*                   strike price:            15.00
*                   risk-free interest rate:  1%
*                   volatility:              35%          (historical estimate)
*                   time until expiration:    0.5 years
*                   (actual price =  9.10)
*
*       GE:         share price:             30.14
*                   strike price:            15.00
*                   risk-free interest rate   1%
*                   volatility:              33.2%         (historical estimate)
*                   time until expiration     0.25 years
*                   (actual = 14.50)
*/

public class BlackScholesTests {
	
	@Test
	public void testCallPricing() {
		int iterations = 10000000;
		int numberOfThreads = 16;
		int numberOfRuns = 5;
		
		StockOption msft = new StockOption(23.75, 15.00, 0.01, 0.35, 0.5); 
		
		
		long totalSerialTimeInMicro = 0;
		long totalParallelTimeInMicro = 0;
		
		for(int i=0; i<numberOfRuns; i++)
		{				
			totalSerialTimeInMicro += 
					BlackScholesManager.computeCallPriceSerial(msft, iterations) / 1000;
			totalParallelTimeInMicro += 
					BlackScholesManager.computeCallPriceParallel(msft, iterations, numberOfThreads) / 1000;
		}		
		
		System.out.println("serial time = " + totalSerialTimeInMicro / numberOfRuns + " microseconds");
		System.out.println("parallel time = " + totalParallelTimeInMicro / numberOfRuns + " microseconds" + "\n");
                System.out.println("Fair Price is " + msft.price);
                
                //int price = 0;
                //price = BlackScholesWorker.;
	}
}

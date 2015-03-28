package edu.vsu.csci693.test;

import org.junit.Assert;
import org.junit.Test;

import edu.vsu.csci693.CelestialBody;
import edu.vsu.csci693.NBodySimulator;


public class NBodySimulatorTests {
	
	@Test
	public void testTimeSimulatingNBody() {
		int numberOfBodies = 1008;
		int[] numberOfThreads = {2,4,8,16};
		//int numberOfRuns = 5;
		int timeStepsToSimulate = 100;
		
		long totalSerialTimeInMicro = 0;
		long totalParallelTimeInMicro = 0;
		
		CelestialBody[] bodiesSimulatedInParallel = NBodySimulator.generateRandomBodiesArray(numberOfBodies);
		CelestialBody[] bodiesSimulatedInSerial = cloneBodyArray(bodiesSimulatedInParallel,numberOfBodies);
		if(!areBodyArraysEqual(bodiesSimulatedInSerial,bodiesSimulatedInParallel,numberOfBodies))
		{
			Assert.fail("setup of bodies is wrong");
		}
                
                for (int i = 0; i < numberOfThreads.length; i++){
                
                    int numberOfRuns = 5;
                    
		for(int z=0; z<numberOfRuns; z++)
		{					
			totalSerialTimeInMicro += 
					NBodySimulator.simulateNBodyInSerial(timeStepsToSimulate, bodiesSimulatedInSerial, numberOfBodies) / 1000;
			totalParallelTimeInMicro += 
					NBodySimulator.simulateNBodyInParallel(timeStepsToSimulate, bodiesSimulatedInParallel, numberOfBodies, numberOfThreads[i]) / 1000;
			
                        
			if(!areBodyArraysEqual(bodiesSimulatedInSerial,bodiesSimulatedInParallel,numberOfBodies))
			{
				Assert.fail("serial and parallel arrays do not equal");
			}
                        

		}
                
		System.out.println("Average for " +  numberOfThreads[i] + " Threads");
		System.out.println("serial time = " + totalSerialTimeInMicro / numberOfRuns + " microseconds");
		System.out.println("parallel time = " + totalParallelTimeInMicro / numberOfRuns + " microseconds" + "\n");
                }
	}
	
	private CelestialBody[] cloneBodyArray(CelestialBody[] bodies, int numberOfBodies)
	{
		CelestialBody[] newBodies = new CelestialBody[numberOfBodies];
		for(int i=0; i<numberOfBodies; i++)
		{
			newBodies[i] = (CelestialBody) bodies[i].clone();
		}	
		return newBodies;
	}
	
	private boolean areBodyArraysEqual(CelestialBody[] bodiesFromSerial, CelestialBody[] bodiesFromParallel, int numberOfBodies)
	{
		for(int i=0; i<numberOfBodies; i++)
		{
			if(! bodiesFromSerial[i].equalsTo(bodiesFromParallel[i]))
			{
				return false;
			}
		}	
		return true;
	}
	
}

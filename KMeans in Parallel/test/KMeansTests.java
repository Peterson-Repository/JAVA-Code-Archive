package edu.vsu.csci693.test;

import org.junit.Assert;
import org.junit.Test;

import edu.vsu.csci693.Cluster;
import edu.vsu.csci693.KMeans;
import edu.vsu.csci693.Point;


public class KMeansTests {
		
	@Test
	
	public void testKMeans() {

                int numberOfPoints = 100000;//100000;
		int numberOfClusters = 8;
                int numberOfThreads = 8;
                //int [] numberOfThreadsArray = {2,4,8,16};
                
                
		
		Point[] points = GenerateRandomPoints(numberOfPoints);		
		Cluster[] clusters = KMeans.cluster(points, numberOfClusters);
		
		
		long ParallelTime = 0;
                long SerialTime = 0;
                
                //for (int i = 0; i < numberOfThreadsArray.length; i++){
                    
                     int numberOfRuns = 5;
                
                        for(int t=0; t<numberOfRuns; t++)
                        {			



                                SerialTime += 
                                                KMeans.simulateKMeansInSerial(numberOfPoints, numberOfClusters, clusters, points) / 1000;
                                
                                //int numberOfThreads = numberOfThreadsArray[t];
                                
                                ParallelTime += 
                                                KMeans.simulateKMeansInParallel(numberOfPoints, numberOfClusters, clusters, points, numberOfThreads) / 1000;
                                                //KMeans.simulateKMeansInParallel(numberOfPoints, numberOfClusters, clusters, points, numberOfThreads[i]) / 1000;

                                for(Point point : points)
                                {
                                        Assert.assertTrue(point.cluster != null);
                                }

                                for(Cluster cluster : clusters)
                                {
                                        Assert.assertTrue(cluster.centroid != null);
                                        //System.out.println("found a cluster with centroid " + cluster.centroid.X + "," + cluster.centroid.Y);
                                }
                       }   

                        
                long AvgSerialTime = SerialTime/numberOfRuns;
                long AvgParallelTime = ParallelTime/numberOfRuns;
        
		//System.out.println("Thread "+ numberOfThreadsArray[i]);
                System.out.println("Thread "+ numberOfThreads);
		System.out.println("serial time = " + AvgSerialTime + " microseconds");
		System.out.println("parallel time = " + AvgParallelTime+ " microseconds");
                
                // }
	}
        
	private Point[] GenerateRandomPoints(int numberOfPoints)
	{
		Point[] points = new Point[numberOfPoints];
		for(int i=0; i<numberOfPoints; i++)
		{
			points[i] = new Point();
			points[i].X = Math.random();
			points[i].Y = Math.random();
                        //System.out.println(points[i].X+" "+points[i].Y);
		}		
		return points;
	}
	
}

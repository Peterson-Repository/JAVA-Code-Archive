/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.vsu.csci693;

/**
 *
 * @author BDav8973
 */

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class KMeansWorker implements Runnable {
	
	private int numberOfPoints;
	private int numberOfCluster;
	private int start;
        private int start2;
	private int lengthPerThread;
        private int lengthPerThread2;
        private int centroids;
        private int threadId;
        private int numberThreads;
        
        
         //------------If Arrays are Passed to worker-----------------------------
	//private Point [] sharedPointsArrayWorker;
        // private Cluster [] sharedPointsClusterWorker;
        //-----------------------------------------------------------------------
	
	private static CyclicBarrier barrier;
	
	public KMeansWorker(int numberOfPoints, int numberOfClusters, int threadId, int numberThreads, Point[] points, Cluster [] clusters)
	{
		this.numberOfPoints = numberOfPoints;
		this.numberOfCluster = numberOfClusters;
                this.threadId = threadId;
                this.numberThreads = numberThreads;
                
		
		//important calculation to divide work
		lengthPerThread = numberOfPoints / numberThreads;
                centroids = numberOfClusters;
                lengthPerThread2 = centroids/numberThreads;
		start = threadId * lengthPerThread;
                start2 = threadId * lengthPerThread2;
                
		if (threadId==0)
                {
                    barrier = new CyclicBarrier(numberThreads);
                }
                
	}
	
	@Override
	public synchronized void run() {	
            int N=10;
            
            long AssignTotalTime = 0;
            long MoveTotalTime = 0;
            
            
		for(int k=0; k<N; k++) {
			
			//TODO: divide the work here
                    
			
			//for(int j=start; j<(start+lengthPerThread); j++)
			//{
                        //-----------------------------------------------------------------------------------------------------------
                                //long AssignTotalTemp;
                    
                               // long AssignStartTime = System.nanoTime();
                                
				assignPointsToClusters(KMeans.sharedPointsArray, KMeans.sharedClusterArray);
                                
				// first barrier
                                        try {
                                                barrier.await();
                                } catch (InterruptedException ex) {
                                        return;
                                } catch (BrokenBarrierException ex) {
                                        return;
                                }
                                        
                                        
                                //long AssignEndTime = System.nanoTime();
                                
                                //AssignTotalTemp = (AssignEndTime - AssignStartTime);
                                
                                //AssignTotalTime = (AssignTotalTime + AssignTotalTemp);
                                
                                
                        //------------------------------------------------------------------------------------------------------------------        
                                        
                                        
                               //long MoveTotalTemp;
                                        
                               //long MoveStartTime = System.nanoTime();
                               // System.out.println(KMeans.sharedClusterArray);
				moveClusterCentroids(KMeans.sharedClusterArray);
                               
   
                        			
                                        // second barrier
                                        try {
                                                barrier.await();
                                } catch (InterruptedException ex) {
                                        return;
                                } catch (BrokenBarrierException ex) {
                                        return;
                                }
                                        
                                //long MoveEndTime = System.nanoTime();
                               
                               //MoveTotalTemp = (MoveEndTime - MoveStartTime);
                               
                               //MoveTotalTime = (MoveTotalTime + MoveTotalTemp);
                               
                          //---------------------------------------------------------------------------------------------------------------------     
                        //}
		}
                
                //System.out.println();
                //System.out.println("Assigning Points Avg Time " + (AssignTotalTime/N));
                //System.out.println("Moving Points Avg Time " + (MoveTotalTime/N));
                //System.out.println();
	}
        
        
         //------------------------Copied From KMeans.java -------------------------------------------------------------------------
        
        
        private void moveClusterCentroids(Cluster[] clusters) {
            
            for(int j=start2; j<(start2+lengthPerThread2); j++){
                
		//for(Cluster cluster : clusters)
		//{
			double sumX = 0.0;
			double sumY = 0.0;
			
			//for(Point clusterPoint : cluster.points)
                        for(Point clusterPoint : clusters[j].points)
			{
				sumX += clusterPoint.X;
				sumY += clusterPoint.Y;
                                
                                
			}
			//System.out.println("Centroid printing... "+ sumX+" "+ sumY);
			//cluster.centroid.X = (sumX / cluster.points.size());
			//cluster.centroid.Y = (sumY / cluster.points.size());	
                        //System.out.println("Cluster printing... "+ cluster.centroid.X+" "+ cluster.centroid.Y);
                        
                        //System.out.println("Centroid printing... "+ sumX+" "+ sumY);
			clusters[j].centroid.X = (sumX / clusters[j].points.size());
			clusters[j].centroid.Y = (sumY / clusters[j].points.size());	
                        //System.out.println("Cluster printing... "+ cluster.centroid.X+" "+ cluster.centroid.Y);
                        
                        synchronized(KMeans.sharedClusterArray){
                        //cluster.points.clear();
                        clusters[j].points.clear();
                        }
		//}
            }
        }
        
        /*private void PointManager() {
                cluster.points = new CopyOnWriteArrayList() ;
        }*/
	private void assignPointsToClusters(Point[] points, Cluster[] clusters) {
		
            for(int k=start; k<(start+lengthPerThread); k++){
            
                //point.cluster = clusters[0];	
                points[k].cluster = clusters[0];	
		//for(Point point : points)
		//{
                 //   System.out.println(points);
			//initialize point to belong to cluster 0
                        // point.cluster = clusters[0];	
			points[k].cluster = clusters[0];	
			
			//assign point to cluster
			for(Cluster cluster: clusters)
			//{
			//	if(distance(cluster.centroid, point) < distance(point.cluster.centroid, point))
			//	{
			//		point.cluster = cluster;					
			//	}
			//}
                        
                        {
				if(distance(cluster.centroid, points[k]) < distance(points[k].cluster.centroid, points[k]))
				{
					points[k].cluster = cluster;					
				}
			}
			
                        
                        synchronized(KMeans.sharedClusterArray){
                        //point.cluster.clear();
			//add point to the list in the cluster
                            
			//point.cluster.points.add(point);
                        points[k].cluster.points.add(points[k]);
                        
                        //point.printPoint();
                        }
		//}
            }   
	}


	
	

	private static double distance(Point p1, Point p2)
	{
	  double d = Math.sqrt((p1.X-p2.X)*(p1.X-p2.X) + (p1.Y-p2.Y)*(p1.Y-p2.Y));
	  return d;
	}
        
     //---------------------------END OF Copied Methods--------------------------------------------------------------------------------
}
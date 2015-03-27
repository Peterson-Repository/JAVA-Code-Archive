package edu.vsu.csci693;

public class KMeans 
{	
    
    public static Point[] sharedPointsArray;
    public static Cluster[] sharedClusterArray;
    
    
//-------------------------Added Parallel Class--------------------------------------------	
	public static long simulateKMeansInParallel(int numberOfPoints, int numberOfClusters, Cluster [] clusterArray, Point[] pointsArray, int numberOfThreads)
	{
		sharedPointsArray = pointsArray; //copies the reference
                sharedClusterArray = clusterArray; //copies the reference
		long startTime = System.nanoTime();
		
		//TODO: do the work with numberOfThreads threads
                
                Thread [] threadHandles = new Thread [numberOfThreads]; // array of threads
		KMeansWorker [] kmeansWorker = new KMeansWorker[numberOfThreads];
		for(int i=0; i<numberOfThreads; i++)
		{
			
                        kmeansWorker[i] = new KMeansWorker(numberOfPoints, numberOfClusters, i, numberOfThreads, sharedPointsArray, sharedClusterArray);
                        //workerHandles[i] = new KMeansWorker(numberOfPoints, numberOfClusters, i, numberOfThreads, sharedPointsArray, sharedClustersArray);
			//System.out.println("Parallel Centroids");
                        threadHandles[i] = new Thread(kmeansWorker[i]);//grabs worker and wrap it in the thread
			threadHandles[i].start();
		}
		
		for(int i=0; i<numberOfThreads; i++)
		{
			try {
				threadHandles[i].join(); // wait for each thread to finish
				//sharedBodiesArray += nbodyWorker[i].finalNumber; 
			} catch (InterruptedException e) {
				//do nothing
			}
		}
		
		long endTime = System.nanoTime();
		return endTime - startTime;
	}
        //------------------------ End of Parallel Class -------------------------------------------
        
        
        
        
         //-----------------------Added Serial Class------------------------------------------------
	
	public static long simulateKMeansInSerial(int numberOfPoints, int numberOfClusters, Cluster[] clusterArray, Point[] pointsArray)
	{
		sharedPointsArray = pointsArray; //copies the reference
                sharedClusterArray = clusterArray; //copies the reference
		long startTime = System.nanoTime();	
		
		KMeansWorker kmeansWorker = new KMeansWorker(numberOfPoints, numberOfClusters, 0, 1, sharedPointsArray, sharedClusterArray);
		//System.out.println("Serial Centroids");
                kmeansWorker.run();	
		
		long endTime = System.nanoTime();		
		return endTime - startTime;
	}
        
        //------------------- End of Serial Class -----------------------------------------------
        
        
        
	public static Cluster[] cluster(Point[] points, int numberOfClusters)
	{
		Cluster[] clusters = new Cluster[numberOfClusters];
		for(int i=0; i<numberOfClusters; i++)
		{
			clusters[i] = new Cluster();
		}
		randomlyPlaceCentroids(clusters);
                
                System.out.println();
                
                //System.out.println("Original Randomly Placed Centroids  ----" + clusters);
		/*int N = 100;
						
		
		
		for(int i=0; i<N; i++)
		{
						
						
		}
		*/
		return clusters;
	}

private static void randomlyPlaceCentroids(Cluster[] clusters) {
					
		for(Cluster cluster: clusters)
		{
			cluster.centroid.X = Math.random();
			cluster.centroid.Y = Math.random();
                        
                        System.out.println("Original Randomly Placed Centroids  " + "(" + cluster.centroid.X +"  " + cluster.centroid.Y + ")");
		}
	}
	
	
}

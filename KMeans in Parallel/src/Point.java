package edu.vsu.csci693;

public class Point {
	public double X;
	public double Y;	
	public Cluster cluster;
        
	public Point()
	{
		X = -1;
		Y = -1;
		cluster = null;
	}
	
	
        public void printPoint() 
        {
               
                System.out.print(X+ " "+ Y);
           
        }
}

package edu.vsu.csci693;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
	public Point centroid;
	public List<Point> points;
        
	public Cluster()
	{
		centroid = new Point();
		points = new ArrayList<Point>();
	}
	
	
       /* public void printList() 
        {
            for(int i = 0; i < points.size(); i++) 
            {   
                System.out.print(points.get(i));
            }  
        }*/
        
}

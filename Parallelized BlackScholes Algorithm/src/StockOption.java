package edu.vsu.csci693;

public class StockOption {
	
	public double S; 
	public double X;
	public double r; 
	public double sigma;
	public double T;
		
	public double price;
	
	public StockOption(double S, double X, double r, double sigma, double T)
	{
		this.S = S;
		this.X = X;
		this.r = r;
		this.sigma = sigma;
		this.T = T;
		
		this.price = 0.0;
	}
        
        
	
}

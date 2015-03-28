package edu.vsu.csci693;

public class CelestialBody implements Cloneable {
	public String name;
	public double mass;
	public double posX;
	public double posY;
	public double velX;
	public double velY;
	
	public String message()
	{
		return "Body " + name + " is located at (" + posX + "," + posY + ")" + 
					"and traveling with velocity (" + velX + "," + velY + ")"; 		
	}
	
	public boolean equalsTo(CelestialBody anotherBody)
	{
		double epsilon = 0.00001;
		return (Math.abs(mass - anotherBody.mass) < epsilon) && 
				(Math.abs(posX - anotherBody.posX) < epsilon) && 
				(Math.abs(posY - anotherBody.posY) < epsilon) && 
				(Math.abs(velX - anotherBody.velX) < epsilon) && 
				(Math.abs(velY - anotherBody.velY) < epsilon);
	}
	
	public Object clone()
	{		
		CelestialBody cloned = new CelestialBody();
		cloned.name = name;
		cloned.mass = mass;
		cloned.posX = posX;
		cloned.posY = posY;
		cloned.velX = velX;
		cloned.velY = velY;
		return cloned;		
	}
}

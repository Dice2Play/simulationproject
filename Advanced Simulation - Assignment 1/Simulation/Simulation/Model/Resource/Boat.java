package Simulation.Model.Resource;

import Simulation.Enums.Resource_Type;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Boat extends Resource{

	final int capacity = 8;
	int amountOfPlacesTaken = 0;
	
	
	public Boat(String ID)
	{
		super(ID, Resource_Type.BOAT);	
	}
	
	void Seize(int timeUnitsRequired, int capacityNeeded)
	{
		// Call parent method
		super.Seize(timeUnitsRequired, capacityNeeded);
		
		// Seize spots
		SeizeSpots(capacityNeeded);
				
	}
		
	double GetOccupancy()
	{
		return (amountOfPlacesTaken/capacity);
	}
	
	
	// Seize spots
	void SeizeSpots(int amountOfSpots)
	{
		amountOfPlacesTaken = amountOfPlacesTaken + amountOfSpots;
	}
	
	void Release()
	{
		super.Release();
		Reset();
	}
	
	void Reset()
	{
		amountOfPlacesTaken = 0;
	}
	

}

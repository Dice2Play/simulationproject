package Simulation.Model.Resource;

import Simulation.Enums.Resource_Types;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Boat extends Resource{

	final int capacity = 8;
	int amountOfPlacesTaken = 0;
	
	
	public Boat(String ID)
	{
		super(ID, Resource_Types.BOAT);	
	}
	
	// Returns if amountOfSpots + amountOfPlacesTaken exceeds the capacity
	public boolean DoesFit(int amountOfSpots)
	{
		return (amountOfSpots + amountOfPlacesTaken) < capacity ;
	}
	
	public void Seize(int timeUnitsRequired, int capacityNeeded)
	{
		// Call parent method
		super.Seize(timeUnitsRequired, capacityNeeded);
		
		// Seize spots
		SeizeSpots(capacityNeeded);
				
	}
	
	// Seize spots
	public void SeizeSpots(int amountOfSpots)
	{
		amountOfPlacesTaken = amountOfPlacesTaken + amountOfSpots;
	}
	
	@Override
	public boolean CanSeize(int capacityNeeded)
	{
		if(super.IsAvailable() && DoesFit(capacityNeeded)) { return true;}
		else return false;
			
	}
}

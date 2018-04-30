package Simulation.Model.Resource;

public class Boat extends Resource{

	final int capacity = 8;
	int amountOfPlacesTaken = 0;
	
	public Boat(String ID)
	{
		super(ID);	
	}
	
	// Returns if amountOfSpots + amountOfPlacesTaken exceeds the capacity
	public boolean DoesFit(int amountOfSpots)
	{
		return (amountOfSpots + amountOfPlacesTaken) > capacity ;
	}
	
	// Seize spots
	public void SeizeSpots(int amountOfSpots)
	{
		amountOfPlacesTaken = amountOfPlacesTaken + amountOfSpots;
	}
}

package simulation.resource;

public abstract class Resource {

	int ID;
	Resource_Type type;
	boolean occupied;
	double cost = 0;
	double amountOfHoursOccupied;
	
	Resource(int ID, Resource_Type type)
	{
		this.ID = ID;
		this.type = type;
		this.occupied = false;
	}
	
	public void Seize()
	{
		occupied = true;
	}
	
	public boolean IsAvailable()
	{
		return !occupied;
	}
	
	public Resource_Type getResourceType()
	{
		return type;
	}
	
	public void AddAmountOfHoursOccupied(double amountOfHoursToAdd)
	{
		amountOfHoursOccupied =+ amountOfHoursToAdd;
	}
	
	public void AddCost(double amountOfCostToAdd)
	{
		cost += amountOfCostToAdd;
	}
	
	
}

package simulation.resource;

public abstract class Resource {

	private String ID;
	private Resource_Type type;
	private boolean occupied;
	private double cost = 0;
	private double amountOfHoursOccupied;
	
	public Resource(String ID, Resource_Type type)
	{
		this.ID = ID;
		this.type = type;
		this.occupied = false;
		
		// Register resource
		ResourceManager.GetInstance().RegisterResource(this);
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
	
	public void Release()
	{
		occupied = false;
	}
	
	public void AddCost(double amountOfCostToAdd)
	{
		cost += amountOfCostToAdd;
	}
	
	public String GetID()
	{
		return ID;
	}
	
}

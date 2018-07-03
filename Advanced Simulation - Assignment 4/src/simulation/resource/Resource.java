package simulation.resource;

public abstract class Resource {

	int ID;
	Resource_Type type;
	boolean occupied;
	double cost;
	
	Resource(int ID, Resource_Type type, double cost)
	{
		this.ID = ID;
		this.type = type;
		this.occupied = false;
		this.cost = cost;
	}
	
	public void Seize()
	{
		occupied = true;
	}
	
	public boolean isAvailable()
	{
		return !occupied;
	}
	
	public Resource_Type getResourceType()
	{
		return type;
	}
	
	
}

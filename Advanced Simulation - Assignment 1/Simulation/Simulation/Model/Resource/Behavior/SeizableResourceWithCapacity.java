package Simulation.Model.Resource.Behavior;

public class SeizableResourceWithCapacity extends SeizableResource {

	private double occupancyRate;
	private final int capacity;
	private int capacityTaken;
	
	SeizableResourceWithCapacity(int capacity)
	{
		this.capacity = capacity;
	}
	
	
	double GetOccupancy()
	{
		return this.GetOccupancyrate();
	}
	
	public double GetOccupancyrate() {
		return occupancyRate;
	}

	
	public void Seize(int timeUnitsRequired, int capacityNeeded)
	{
		super.Seize(timeUnitsRequired, capacityNeeded);
		
	}
	
	public boolean CanSeize()
	{
		return IsAvailable();
	}
}

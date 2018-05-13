package Simulation.Model.Resource;

import Simulation.Enums.Resource_Type;
import Simulation.Model.Time.TimeManager;

public class SeizableResource extends Resource{

	SeizableResource(String ID, Resource_Type type) {
		super(ID, type);
		// TODO Auto-generated constructor stub
	}


	private int startTimeOccupied;
	private int endTimeOccupied;
	private double occupancyRate;
	private boolean available;
	
	void Seize(int timeUnitsRequired, int capacityNeeded)
	{
		startTimeOccupied = TimeManager.GetTimeUnitsPassed();
		endTimeOccupied = startTimeOccupied + timeUnitsRequired; 
		SetAvailable(false);
	}
	
	boolean CanSeize()
	{
		return IsAvailable();
	}
	
	boolean CanRelease()
	{
		return TimeManager.GetTimeUnitsPassed() > endTimeOccupied;
	}
	
	void Release()
	{
		// Reset all
		SetAvailable(true);
	}
	
	boolean IsAvailable()
	{
		return available;
	}
	
	void SetAvailable(Boolean available)
	{
		this.available = available;
	}
	
	double GetOccupancy()
	{
		return this.GetOccupancyrate();
	}
	
	public double GetOccupancyrate() {
		return occupancyRate;
	}

	

	


	
	
	
}

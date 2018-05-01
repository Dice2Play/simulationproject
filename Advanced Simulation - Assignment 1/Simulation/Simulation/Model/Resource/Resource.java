package Simulation.Model.Resource;

import Simulation.Enums.Resource_Type;
import Simulation.Model.Time.TimeManager;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Resource {

	private int startTimeOccupied;
	private int endTimeOccupied;
	private boolean available = true;
	private String ID;
	private Resource_Type type;
	private TimeManager timeManager = new TimeManager();
	
	
	public Resource(String ID, Resource_Type type)
	{
		this.ID = ID;
		this.type = type;
	}
	
	public void Seize(int timeUnitsRequired, int capacityNeeded)
	{
		startTimeOccupied = timeManager.GetTimeUnitsPassed();
		endTimeOccupied = startTimeOccupied + timeUnitsRequired; 
		SetAvailable(false);
	}
	
	public boolean CanSeize()
	{
		return IsAvailable();
	}
	
	public boolean CanRelease()
	{
		return timeManager.GetTimeUnitsPassed() > endTimeOccupied;
	}
	
	public void Release()
	{
		// Update statistics
		
		// Reset all
		SetAvailable(true);
	}
	
	public Resource_Type GetResourceType()
	{
		return type;
	}
	
	protected boolean IsAvailable()
	{
		return available;
	}
	
	private void SetAvailable(Boolean available)
	{
		this.available = available;
	}
	
	public String GetID()
	{
		return ID;
	}
	
	
}

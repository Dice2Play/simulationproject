package Simulation.Model.Resource;

import Simulation.Enums.Resource_Types;
import Simulation.Model.Time.TimeManager;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Resource {

	private int startTimeOccupied;
	private int endTimeOccupied;
	private boolean available = true;
	private String ID;
	private Resource_Types type;
	private TimeManager timeManager = new TimeManager();
	
	
	public Resource(String ID, Resource_Types type)
	{
		this.ID = ID;
		this.type = type;
	}
	
	public void Seize(int timeUnitsRequired, int capacityNeeded)
	{
		startTimeOccupied = timeManager.AmountOfTimePassed();
		endTimeOccupied = startTimeOccupied + timeUnitsRequired; 
	}
	
	public boolean CanSeize(int capacityNeeded)
	{
		return false;
	}
	
	public boolean CanRelease()
	{
		return timeManager.AmountOfTimePassed() > endTimeOccupied;
	}
	
	public void Release()
	{
		// Update statistics
		
		// Reset all
		
	}
	
	public Resource_Types GetResourceType()
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

package Simulation.Model.Resource;

import Simulation.Enums.Resource_Type;
import Simulation.Model.Resource.Behavior.ISeizeBehavior;
import Simulation.Model.Time.TimeManager;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

class Resource {

	private int startTimeOccupied;
	private int endTimeOccupied;
	private boolean available = true;
	private String ID;
	private Resource_Type type;
	protected ISeizeBehavior seizeBehavior;
	
	
	
	Resource(String ID, Resource_Type type)
	{
		this.ID = ID;
		this.type = type;
	}
	
	void Seize(int timeUnitsRequired, int capacityNeeded)
	{
		startTimeOccupied = TimeManager.GetTimeUnitsPassed();
		endTimeOccupied = startTimeOccupied + timeUnitsRequired; 
		SetAvailable(false);
		seizeBehavior.Seize(capacityNeeded);
		
	}
	
	boolean CanSeize()
	{
		return IsAvailable();
	}
	
	boolean CanRelease()
	{
		return TimeManager.GetTimeUnitsPassed() >= endTimeOccupied;
	}
	
	void Release()
	{
		// Reset all
		seizeBehavior.Release();
		SetAvailable(true);
	}
	
	Resource_Type GetResourceType()
	{
		return type;
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
		return seizeBehavior.GetOccupancy();
	}
	
	String GetID()
	{
		return ID;
	}


	
}

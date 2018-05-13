package Simulation.Model.Resource.Behavior;

import Simulation.Enums.Resource_Type;
import Simulation.Model.Time.TimeManager;

class SeizableResource implements ISeizable{



	private int startTimeOccupied;
	private int endTimeOccupied;
	private boolean available;
	
	public void Seize(int timeUnitsRequired)
	{
		startTimeOccupied = TimeManager.GetTimeUnitsPassed();
		endTimeOccupied = startTimeOccupied + timeUnitsRequired; 
		SetAvailable(false);
	}
	
	public boolean CanSeize()
	{
		return IsAvailable();
	}
	
	boolean CanRelease()
	{
		return TimeManager.GetTimeUnitsPassed() > endTimeOccupied;
	}
	
	public void Release()
	{
		// Reset all
		SetAvailable(true);
	}
	
	public boolean IsAvailable()
	{
		return available;
	}
	
	void SetAvailable(Boolean available)
	{
		this.available = available;
	}
	

	

	


	
	
	
}

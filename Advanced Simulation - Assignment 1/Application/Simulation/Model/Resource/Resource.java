package Simulation.Model.Resource;

public class Resource {

	private int startTimeOccupied;
	private int endTimeOccupied;
	private boolean available =  false;
	private int ID;
	
	public Resource(int startTimeOccupied,
					int endTimeOccupied,
					int ID)
	{
		this.startTimeOccupied = startTimeOccupied;
		this.endTimeOccupied = endTimeOccupied;
		this.ID = ID;
	}
	
	public void Occupy(int startTime, int timeUnitsRequired)
	{
		
	}
	
	public void Release()
	{
		
	}
	
}

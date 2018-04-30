package Simulation.Model.Resource;

public class Resource {

	private int startTimeOccupied;
	private int endTimeOccupied;
	private boolean available =  false;
	private String ID;
	
	public Resource(String ID)
	{
		this.ID = ID;
	}
	
	public void Seize(int startTime, int timeUnitsRequired)
	{
		
	}
	
	public void Release()
	{
		
	}
	
}

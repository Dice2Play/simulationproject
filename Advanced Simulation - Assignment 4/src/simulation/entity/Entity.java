package simulation.entity;

public class Entity {

	private String ID;
	private Process currentProcess;
	private double arrivalTime;
	private double pickUpTime;
	private double calledTime;
	
	public Entity(String ID)
	{
		
	}
	
	public String GetID()
	{
		return ID;
	}
	
	public Process GetCurrentProcess()
	{
		return currentProcess;
	}
	
	public void SetCurrentProcess(Process currentProcess)
	{
		this.currentProcess = currentProcess;
	}
	
	
	
}

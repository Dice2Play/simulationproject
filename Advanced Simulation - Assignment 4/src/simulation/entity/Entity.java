package simulation.entity;

import simulation.process.SequenceObject;

public class Entity {

	private String ID;
	private SequenceObject currentSequenceObject;
	private boolean isAvailable = true;
	private double arrivalTime;
	private double pickUpTime;
	private double calledTime;
	private double cleaningTime;
	
	public Entity(String ID)
	{
		this.ID = ID;
	}
	
	public String GetID()
	{
		return ID;
	}
	
	public SequenceObject GetCurrentSequenceObject()
	{
		return currentSequenceObject;
	}
	
	public void SetCurrentSequenceObject(SequenceObject currentSequenceObject)
	{
		this.currentSequenceObject = currentSequenceObject;
	}
	
	public boolean IsAvailable()
	{
		return isAvailable;
	}
	
	public void Seize()
	{
		isAvailable = false;
	}
	
	public void Release()
	{
		isAvailable = true;
	}
	
	
	
}

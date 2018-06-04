package Simulation.Model.Process;

import java.util.ArrayList;

import Simulation.Enums.Resource_Type;
import Simulation.Interfaces.Tick_Listener;
import Simulation.Model.Process.Behavior.IProcessFireBehavior;
import Simulation.Model.Queue.QueueManager;
import Simulation.Model.Resource.ResourceManager;
import Simulation.Model.Time.TimeManager;

public abstract class Process implements Tick_Listener{

	protected final String ID;
	protected final int processTime; // Amount of time-units needed for completing process. 
	protected final Resource_Type type; 
	protected IProcessFireBehavior fireBehavior;
	protected boolean isFinished;
	protected double startTime;
	
	public Process(String ID, int processTime, Resource_Type type)
	{
		this.ID = ID;
		this.processTime = processTime;
		this.type = type;
		startTime = TimeManager.GetTimeUnitsPassed();
		
		// Set listener
		TimeManager.AddTickListener(this);
	}
	
	public void Reset()
	{
		isFinished = false;
	}
	
	public boolean CanFire()
	{
		return fireBehavior.CanFire();
	}
	
	public void Fire()
	{
		fireBehavior.Fire();
	}
		
	public boolean isFinished()
	{
		return isFinished;
	}
	
	private void setFinished()
	{
		isFinished = true;
		System.out.println(String.format("Proces %s has finished a cycle", ID));
	}
	
	private void canFinish(double timePassed)
	{
		if((startTime + processTime) <= timePassed)
		{
			setFinished();
		}
	}
	
	@Override
	public void Event_Tick(double timePassed)
	{
		canFinish(timePassed);
	}
	
	
	
	

	
	

	
	
	
}

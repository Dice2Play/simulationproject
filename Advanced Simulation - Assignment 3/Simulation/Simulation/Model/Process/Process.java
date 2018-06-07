package Simulation.Model.Process;

import java.util.ArrayList;

import Simulation.Enums.Resource_Type;
import Simulation.Enums.TimeManager_Subscriber;
import Simulation.Interfaces.Tick_Listener;
import Simulation.Model.Process.Behavior.IProcessFireBehavior;
import Simulation.Model.Queue.QueueManager;
import Simulation.Model.Resource.ResourceManager;
import Simulation.Model.Time.TimeManager;

public abstract class Process {

	protected final String ID;
	protected final int processTime; // Amount of time-units needed for completing process. 
	protected final Resource_Type type; 
	protected IProcessFireBehavior fireBehavior;
	protected boolean isFinished;
	protected double startTime;
	protected boolean isRunning;
	protected TimeManager_Subscriber timeManagerSubscriberType = TimeManager_Subscriber.MODEL;
	
	public Process(String ID, int processTime, Resource_Type type)
	{
		this.ID = ID;
		this.processTime = processTime;
		this.type = type;
	}
	
	public void Reset()
	{
		isFinished = false;
		startTime = 0;
	}
	
	public void Stop()
	{
		isRunning = false;
	}
	
	public void Start()
	{
		startTime = TimeManager.GetTimeUnitsPassed();
		isRunning = true;
	}
	
	public boolean CanFire()
	{
		return fireBehavior.CanFire();
	}
	
	public void Fire()
	{
		fireBehavior.Fire();
	}
		
	public boolean IsFinished()
	{
		return isFinished;
	}
	
	void SetFinished()
	{
		isFinished = true;
	}
	
	public boolean IsRunning()
	{
		return isRunning;
	}
	
	public String GetID()
	{
		return ID;
	}
	
	public boolean CanFinish()
	{
		if((startTime + processTime) <= TimeManager.GetTimeUnitsPassed())
		{
			return true;
		}
		
		return false;
	}

	
	
	
	

	
	

	
	
	
}

package simulation.process;

import java.util.ArrayList;
import java.util.List;

import simulation.entity.Entity;
import simulation.interfaces.DoubleCommand;
import simulation.interfaces.Tick_Listener;
import simulation.process.behavior.CanFireBehavior;
import simulation.process.behavior.CanFireEntity;
import simulation.process.behavior.CanFireProcessResourceAndEntity;
import simulation.process.behavior.DecisionFire;
import simulation.process.behavior.NextSequenceBasedOnChance;
import simulation.process.behavior.ProcessFire;
import simulation.process.behavior.RegularNextSequence;
import simulation.queue.Queue;
import simulation.resource.Resource;
import simulation.resource.ResourceManager;
import simulation.resource.Resource_Type;
import simulation.time.Event_Type;
import simulation.time.TimeEvent;
import simulation.time.TimeManager;

public class Process extends SequenceObject{

	private List<Resource_Type> typeOfResourcesNeeded = new ArrayList<Resource_Type>();
	private ArrayList<Resource> seizedResources = new ArrayList<Resource>();
	private double generatedProcessTime;
	private double processTime;
	private boolean isAvailable = true;
	private boolean isUsingCommandForGeneratingProcessTime = false;
	private DoubleCommand commandForGeneratingProcessTime;
	private boolean hasAlreadyGeneratedGeneratingTime = false;
	

	
	public Process(String ID, double processTime)
	{
		this(ID, Process_Priority.Normal, processTime);
	}
	
	public Process(String ID, Process_Priority processPriority, double processTime)
	{
		super(ID, processPriority);
		this.processTime = processTime;
		fireBehavior = new ProcessFire(this);
		canFireBehavior = new CanFireProcessResourceAndEntity(this);
		isUsingCommandForGeneratingProcessTime = false;
	}
	
	public Process(String ID, Process_Priority processPriority, DoubleCommand commandForGeneratingProcessTime)
	{
		super(ID, processPriority);
		this.commandForGeneratingProcessTime = commandForGeneratingProcessTime;
		fireBehavior = new ProcessFire(this);
		canFireBehavior = new CanFireProcessResourceAndEntity(this);
		nextSequenceBehavior = new RegularNextSequence(this);
		isUsingCommandForGeneratingProcessTime = true;
		
	}
	
	public void SetIsAvailable(boolean newValue)
	{
		isAvailable = newValue;
	}
			
	public void AddRequiredResource(Resource_Type typeOfResourceNeeded)
	{
		typeOfResourcesNeeded.add(typeOfResourceNeeded);
	}
	
	public boolean IsAvailable()
	{
		return isAvailable;
	}
	
	ArrayList<Resource> GetSeizedResources()
	{
		return seizedResources;
	}

	public boolean AreResourcesAvailable()
	{
		for(Resource_Type typeOfResource : typeOfResourcesNeeded)
		{
			if(!ResourceManager.GetInstance().CheckForAvailableResource(typeOfResource)) 
			{
				return false;
			}
		}
		
		// Default value
		return true;
	}
	
	public double GetProcessTime()
	{
		// If static (not using generating command) return processTime
		if(!isUsingCommandForGeneratingProcessTime) {return processTime;}
		
		else
		{ 
			// check if already an time has been generated
			if(!hasAlreadyGeneratedGeneratingTime)
			{
				generatedProcessTime = commandForGeneratingProcessTime.Execute();
				hasAlreadyGeneratedGeneratingTime = true;
			}
			
			return generatedProcessTime;
		}
	}
	
	/**
	 * Seize Process, Resources and Entity
	 */
	public void Seize()
	{
		SeizeProcess();
		SeizeResources();
		SeizeEntity();
	}
	
	private void SeizeEntity() {
		try {
			this.GetNextEntityFromQueue().Seize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void SeizeResources() {

		for(Resource_Type typeOfResourceNeeded : typeOfResourcesNeeded)
		{
			try {
				ResourceManager.GetInstance().GetAvailableResource(typeOfResourceNeeded).Seize();;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void SeizeProcess()
	{
		isAvailable = false;	
	}

	/**
	 * Release Process, Resources and Entity
	 */
	public void Release()
	{
		ReleaseProcess();
		ReleaseEntity();
		ReleaseResources();
		Reset();
	}

	private void ReleaseResources() {
		GetSeizedResources().forEach(x -> x.Release());	
	}

	private void ReleaseEntity() 
	{
		try {GetNextEntityFromQueue().Release();}
		catch (Exception e) {e.printStackTrace();}
	}

	private void ReleaseProcess()
	{
		isAvailable = true;
		
	}
	
	private void Reset()
	{
		if(isUsingCommandForGeneratingProcessTime) { hasAlreadyGeneratedGeneratingTime = false;}
	}

	
	

}

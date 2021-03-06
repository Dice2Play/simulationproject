package simulation.process;

import java.util.ArrayList;
import java.util.List;

import simulation.entity.Entity;
import simulation.interfaces.DoubleCommand;
import simulation.process.behavior.CanFireProcessResourceAndEntity;
import simulation.process.behavior.ProcessFire;
import simulation.process.behavior.ProcessNextSequence;
import simulation.process.behavior.RegularNextSequence;
import simulation.process.commands.GenerateProcessingTimeAccordingToDistributionCommand;
import simulation.resource.Resource;
import simulation.resource.ResourceManager;
import simulation.resource.Resource_Type;

public class Process extends SequenceObject{

	private List<Resource_Type> typeOfResourcesNeeded = new ArrayList<Resource_Type>();
	private ArrayList<Resource> seizedResources = new ArrayList<Resource>();
	private double generatedProcessTime;
	private double processTime;
	private boolean isAvailable = true;
	private boolean isUsingCommandForGeneratingProcessTime = false;
	private DoubleCommand commandForGeneratingProcessTime;
	private boolean hasAlreadyGeneratedGeneratingTime = false;
	private Entity seizedEntity;
	

	

	public Process(String ID, Process_Priority processPriority, double processTime)
	{
		super(ID, processPriority);
		this.processTime = processTime;
		fireBehavior = new ProcessFire(this);
		canFireBehavior = new CanFireProcessResourceAndEntity(this);
		nextSequenceBehavior = new ProcessNextSequence(this);
		isUsingCommandForGeneratingProcessTime = false;
	}
	
	public Process(String ID, Process_Priority processPriority, DoubleCommand commandForGeneratingProcessTime)
	{
		super(ID, processPriority);
		this.commandForGeneratingProcessTime = commandForGeneratingProcessTime;
		fireBehavior = new ProcessFire(this);
		canFireBehavior = new CanFireProcessResourceAndEntity(this);
		nextSequenceBehavior = new ProcessNextSequence(this);
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
		System.out.println("resouce sized!");
		SeizeResources();
		SeizeEntity();
	}
	
	private void SeizeEntity() {
		try {
			seizedEntity = GetNextEntityFromQueue();
			seizedEntity.Seize();
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
		try {GetSeizedEntity().Release();}
		catch (Exception e) {e.printStackTrace();}
	}

	private void ReleaseProcess()
	{
		isAvailable = true;
		
	}
	
	private void Reset()
	{
		if(isUsingCommandForGeneratingProcessTime) { hasAlreadyGeneratedGeneratingTime = false;}
		
		seizedEntity = null;
	}

	public Entity GetSeizedEntity() {
		
		return seizedEntity;
	}

	@Override
	public void Validate() throws Exception {
		if(typeOfResourcesNeeded.isEmpty()) { throw new Exception(String.format("VALIDATE MODEL ERROR: No resource has been set for %s", this.GetID()));}
		if(GetLinkedSequenceObjects().size() != 1){throw new Exception(String.format("VALIDATE MODEL ERROR: Exactly 1 Sequence Link need to be set for %s", this.GetID()));}
		
	}

	
	

}

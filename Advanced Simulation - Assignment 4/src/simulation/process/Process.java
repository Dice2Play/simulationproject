package simulation.process;

import java.util.ArrayList;
import java.util.List;

import simulation.entity.Entity;
import simulation.interfaces.DoubleCommand;
import simulation.process.behavior.CanFireProcessAndEntity;
import simulation.process.behavior.ProcessFire;
import simulation.process.behavior.RegularNextSequence;
import simulation.process.commands.GenerateProcessingTimeAccordingToDistributionCommand;
import simulation.resource.Resource;
import simulation.resource.ResourceManager;
import simulation.resource.Resource_Type;

public class Process extends SequenceObject{

	private double generatedProcessTime;
	private double processTime;
	private boolean isAvailable = true;
	private boolean isUsingCommandForGeneratingProcessTime = false;
	private DoubleCommand commandForGeneratingProcessTime;
	private boolean hasAlreadyGeneratedGeneratingTime = false;
	private Entity reservedEntity;
	

	

	public Process(String ID, Process_Priority processPriority, double processTime)
	{
		super(ID, processPriority);
		this.processTime = processTime;
		fireBehavior = new ProcessFire(this);
		canFireBehavior = new CanFireProcessAndEntity(this);
		nextSequenceBehavior = new RegularNextSequence(this);
		isUsingCommandForGeneratingProcessTime = false;
	}
	
	public Process(String ID, Process_Priority processPriority, DoubleCommand commandForGeneratingProcessTime)
	{
		super(ID, processPriority);
		this.commandForGeneratingProcessTime = commandForGeneratingProcessTime;
		fireBehavior = new ProcessFire(this);
		canFireBehavior = new CanFireProcessAndEntity(this);
		nextSequenceBehavior = new RegularNextSequence(this);
		isUsingCommandForGeneratingProcessTime = true;
		
	}
	
	public void SetIsAvailable(boolean newValue)
	{
		isAvailable = newValue;
	}
			

	
	public boolean IsAvailable()
	{
		return isAvailable;
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
		SeizeEntity();
	}
	
	private void SeizeEntity() {
		try {
			reservedEntity = GetFirstEntityFromQueue();
			reservedEntity.Seize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		Reset();
	}

	private void ReleaseEntity() 
	{
		try {GetReservedEntity().Release();}
		catch (Exception e) {e.printStackTrace();}
	}

	private void ReleaseProcess()
	{
		isAvailable = true;
		
	}
	
	private void Reset()
	{
		if(isUsingCommandForGeneratingProcessTime) { hasAlreadyGeneratedGeneratingTime = false;}
		
		reservedEntity = null;
	}

	public Entity GetReservedEntity() {
		
		return reservedEntity;
	}

	@Override
	public void Validate() throws Exception {
		
		if(GetLinkedSequenceObjects().size() != 1){throw new Exception(String.format("VALIDATE MODEL ERROR: Exactly 1 Sequence Link need to be set for %s", this.GetID()));}
		
	}

	
	

}

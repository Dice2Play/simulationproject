package simulation.process;

import java.util.ArrayList;

import simulation.entity.Entity;
import simulation.process.behavior.CanFireResourceAndEntity;
import simulation.process.behavior.CanFireResourceAndEntityAndProcess;
import simulation.process.behavior.RegularNextSequence;
import simulation.process.behavior.SeizeFire;
import simulation.resource.ResourceManager;
import simulation.resource.Resource_Type;
import simulation.time.TimeManager;

public class Seize extends SequenceObject {

	private ArrayList<Resource_Type> typesOfRequiredResources = new ArrayList<Resource_Type>();
	
	public Seize(String ID) {
		super(ID, Process_Priority.Normal);
		fireBehavior = new SeizeFire(this);
		canFireBehavior = new CanFireResourceAndEntityAndProcess(this);
		nextSequenceBehavior = new RegularNextSequence(this);
	}

	@Override
	public void Validate() throws Exception {
		//if(typesOfRequiredResources.isEmpty()) { throw new Exception(String.format("VALIDATE MODEL ERROR: No resource has been set for %s", this.GetID()));}
		if(GetLinkedSequenceObjects().size() != 1){throw new Exception(String.format("VALIDATE MODEL ERROR: Exactly 1 Sequence Link need to be set for %s", this.GetID()));}
	}
	
	public void AddRequiredResource(Resource_Type typeOfResourceNeeded)
	{
		typesOfRequiredResources.add(typeOfResourceNeeded);
	}
	
	/**
	 * Seizes the Resources listed in 'typesOfRequiredResources'
	 * @throws Exception 
	 */
	public void SeizeRequiredResources() throws Exception
	{
		for(Resource_Type typeOfResource : typesOfRequiredResources)
		{			
			ResourceManager.GetInstance().GetAvailableResource(typeOfResource).Seize();
		}
	}

	/**
	 * Checks whether the resources in typesOfRequiredResources are available
	 * @return
	 */
	public boolean AreRequiredResourcesAvailable() {

		for(Resource_Type typeOfResource : typesOfRequiredResources)
		{
			if(!ResourceManager.GetInstance().CheckForAvailableResource(typeOfResource))
			{
				return false;
			}
		}
		
		
		return true;
	}

	public void SetAssignedResourcesForEntity(Entity firstEntityFromQueue) throws Exception {
		
		for(Resource_Type typeOfRequiredResourceNeeded : typesOfRequiredResources)
		{
			firstEntityFromQueue.AssignResource(ResourceManager.GetInstance().GetAvailableResource(typeOfRequiredResourceNeeded));
		}
			
			
	}

	/*
	 * Check if next sequence object is available
	 */
	public boolean IsNextSequenceObjectAvailable() {
		return this.GetLinkedSequenceObjects().getFirst().GetIsSequenceObjectAvailable();
	}



	public void SetSeizeTimeForEntity(Entity firstEntityFromQueue) {
		firstEntityFromQueue.SetStartDaySeize(TimeManager.GetInstance().GetCurrentDay());
		firstEntityFromQueue.SetStartTimeSeize(TimeManager.GetInstance().GetCurrentTime());
	}


}

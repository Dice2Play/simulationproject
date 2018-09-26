package simulation.process.behavior;


import simulation.entity.Entity;
import simulation.process.Seize;

public class SeizeFire extends FireBehavior{

	private Seize seizeObject;
	
	public SeizeFire(Seize seizeObject)
	{
		this.seizeObject = seizeObject;
	}
	
	@Override
	public void Fire() {
		
		try 
		{
			// Get first entity from queue
			Entity firstEntityFromQueue = seizeObject.GetFirstEntityFromQueue();			
			
			
			seizeObject.GetNextSequenceBehavior().SetNextSequenceObjectForEntity(firstEntityFromQueue);
			seizeObject.SeizeRequiredResources();
			seizeObject.SetAssignedResourcesForEntity();
			seizeObject.SetStartTimeForResource(firstEntityFromQueue);
			seizeObject.RemoveFirstEntityFromQueue();
		} 
		
		
		catch (Exception e) {e.printStackTrace();}
		
		
	}

}

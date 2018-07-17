package simulation.process.behavior;

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
			seizeObject.GetNextSequenceBehavior().SetNextSequenceObjectForEntity();
			seizeObject.SeizeRequiredResources();
			seizeObject.SetAssignedResourcesForEntity();
			seizeObject.SetStartTimeForResource();
			seizeObject.RemoveFirstEntityFromQueue();
		} 
		
		
		catch (Exception e) {e.printStackTrace();}
		
		
	}

}

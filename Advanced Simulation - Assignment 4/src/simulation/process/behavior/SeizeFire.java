package simulation.process.behavior;


import simulation.process.SeizeResource;

public class SeizeFire extends FireBehavior{

	private SeizeResource seizeObject;
	
	public SeizeFire(SeizeResource seizeObject)
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

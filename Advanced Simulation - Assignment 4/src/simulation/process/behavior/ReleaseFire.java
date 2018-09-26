package simulation.process.behavior;

import simulation.entity.Entity;
import simulation.process.Release;
import simulation.process.Seize;

public class ReleaseFire extends FireBehavior{

	private Release releaseObject;
	
	public ReleaseFire(Release releaseObject)
	{
		this.releaseObject = releaseObject;
	}


	@Override
	public void Fire() {
		try
		{
			// Get first entity from queue
			Entity firstEntityFromQueue = releaseObject.GetFirstEntityFromQueue();			
			
			releaseObject.GetNextSequenceBehavior().SetNextSequenceObjectForEntity(firstEntityFromQueue);
			releaseObject.UpdateEntity(firstEntityFromQueue);
			releaseObject.ReleaseResources(firstEntityFromQueue);
			releaseObject.RemoveFirstEntityFromQueue();

		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
}


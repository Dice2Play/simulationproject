package simulation.process.behavior;

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
			releaseObject.GetNextSequenceBehavior().SetNextSequenceObjectForEntity();
			releaseObject.UpdateEntity();
			releaseObject.ReleaseResources();
			releaseObject.RemoveFirstEntityFromQueue();

		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
}


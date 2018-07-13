package simulation.process;

import java.util.ArrayList;
import java.util.List;

import simulation.resource.Resource;
import simulation.resource.ResourceManager;
import simulation.resource.Resource_Type;

public class Initiator extends SequenceObject{
	
	private List<Resource_Type> typeOfResourcesNeeded = new ArrayList<Resource_Type>();
	private ArrayList<Resource> seizedResources = new ArrayList<Resource>();
	
	public Initiator(String ID)
	{
		super(ID,Process_Priority.Normal);
	}

	/**
	 * Checks whether or not a parkingspot is available,
	 * if no parkingspot is available, set entity boolean to rejected.
	 * If their is a parkingspot available however, then add entity to next sequence object.
	 * @throws Exception 
	 */
	@Override
	public void Fire() throws Exception
	{
		super.Fire();
		// Release resources
		GetSeizedResources().forEach(x -> x.Release());
				
				// Update processing time for entity
				currentProcess.GetCurrentEntity().UpdateProcessingTime(currentProcess.GetProcessTime());
				
				// Set next process + Release entity
				currentProcess.SetNextSequenceObjectForEntity();
				
				// Set process available again for further firing
				currentProcess.SetIsAvailable(true);
				currentProcess.SetCurrentEntityToNull();
	}
	
	ArrayList<Resource> GetSeizedResources()
	{
		return seizedResources;
	}

	private boolean AreResourcesAvailable()
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
	
	@Override
	public void SetNextSequenceObjectForEntity() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean CanFire() {

		// iF no parking spot is available return false, remove entity from queue, and set entity boolean to rejected
		
		return IsThereANextEntityFromQueue() && AreResourcesAvailable();
	}

}

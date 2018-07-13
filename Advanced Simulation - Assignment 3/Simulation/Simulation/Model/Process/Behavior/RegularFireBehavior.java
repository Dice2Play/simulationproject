package Simulation.Model.Process.Behavior;

import Simulation.Enums.Resource_Type;
import Simulation.Model.Queue.QueueManager;
import Simulation.Model.Resource.ResourceManager;

public class RegularFireBehavior implements IProcessFireBehavior{

	private Resource_Type resourceTypeNeeded;
	private final int processTime;
	private final String ID;
	
	public RegularFireBehavior(int processTime, String ID,Resource_Type resourceTypeNeeded)
	{
		this.resourceTypeNeeded = resourceTypeNeeded;
		this.ID = ID;
		this.processTime =  processTime;
	}
	
	@Override
	public void Fire() {
		// Fire process
//		int resourceCapacityFilled = QueueManager.SeizeQueueObject(processTime, ResourceManager.GetCapacityOfResource(resourceTypeNeeded));
//		ResourceManager.SeizeResource(resourceTypeNeeded, resourceCapacityFilled, processTime, ID);
		
	}

	@Override
	public boolean CanFire() {
		if(resourceTypeNeeded == Resource_Type.NONE) {return true;}
		else return ResourceManager.CheckForAvailableResource(resourceTypeNeeded);
	}

}

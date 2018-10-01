package simulation.process.behavior;

public class CanFireResourceAndEntityAndProcess extends CanFireBehavior{

	private simulation.process.Seize seizeObject;
	
	public CanFireResourceAndEntityAndProcess(simulation.process.Seize seizeObject)
	{
		this.seizeObject = seizeObject;
	}
	
	/*
	 * Check whether:
	 * - Resources are available
	 * - NextSequenceObject is available
	 * - Entity in current sequenceobject' queue is available
	 */
	@Override
	public boolean CanFire() {
		return seizeObject.AreRequiredResourcesAvailable() &&
				seizeObject.IsThereANextEntityFromQueueForCurrentProcess() &&
				seizeObject.CheckForAvailableSequenceObject();
	}

}

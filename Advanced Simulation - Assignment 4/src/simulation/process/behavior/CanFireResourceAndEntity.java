package simulation.process.behavior;

public class CanFireResourceAndEntity extends CanFireBehavior{

	private simulation.process.Seize seizeObject;
	
	public CanFireResourceAndEntity(simulation.process.Seize seizeObject)
	{
		this.seizeObject = seizeObject;
	}
	
	@Override
	public boolean CanFire() {
		return seizeObject.AreRequiredResourcesAvailable() && seizeObject.IsThereANextEntityFromQueueForCurrentProcess();
	}

}

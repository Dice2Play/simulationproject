package simulation.resource;

public class CleaningSpot extends Resource{

	int usageCounter;
	boolean hasMaintenance;
	
	public CleaningSpot(String ID) {
		super(ID, Resource_Type.CLEANING_SPOT);
	}
	
	public void Seize()
	{
		super.Seize();
		usageCounter++;
	}
	
	public void Release()
	{
		if(usageCounter == 10)
		{
			// Set bool maintenance
			hasMaintenance = true;
			
			// Make new process "Maintenance"
			
		}
	}
	


}

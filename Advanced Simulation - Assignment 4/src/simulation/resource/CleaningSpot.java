package simulation.resource;

public class CleaningSpot extends Resource{

	int usageCounter;
	boolean hasMaintenance;
	
	CleaningSpot(int ID, Resource_Type type) {
		super(ID, type);
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

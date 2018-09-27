package simulation.resource;

import java.util.ArrayList;
import java.util.List;

public class ResourceManager {
	
	List<Resource> resources = new ArrayList<Resource>();
	static ResourceManager resourceManager = null;
	
	
	public static ResourceManager GetInstance()
	{
		if(resourceManager == null)
		{
			resourceManager = new ResourceManager(); 
		}
		
		return resourceManager;
	}
	
	public void Reset()
	{
		resources = new ArrayList<Resource>();
		resourceManager = null;
	}
	
	void RegisterResource(Resource resourceToRegister)
	{
		resources.add(resourceToRegister);
		
		System.out.println(String.format("RESOURCE MANAGER: Registered %s with ID %s", resourceToRegister.getResourceType(), resourceToRegister.GetID()));
	}
	
	public boolean CheckForAvailableResource(Resource_Type typeOfResourceNeeded)
	{
		boolean isResourceTypeAvailable = false;
		
		for(Resource resource : resources)
		{
			if((resource.getResourceType() == typeOfResourceNeeded) && resource.IsAvailable())
			{
				isResourceTypeAvailable = true;
				break;
			}
		}
		
		return isResourceTypeAvailable;
	}
	
	/**
	 * Assumption: There is at least one resource of input type available
	 * @param typeOfResourceNeeded : ENUM, type of resource needed
	 * @return available resource
	 * @throws Exception 
	 */
	public Resource GetAvailableResource(Resource_Type typeOfResourceNeeded) throws Exception
	{
		for(Resource resource : resources)
		{
			if((resource.getResourceType() == typeOfResourceNeeded) && resource.IsAvailable())
			{
				return resource;
			}
		}
		
		throw new Exception("There is no available resource");
	}
	

	public void SetAmountOfCashRegisters(int amount)
	{
		// Add cash registers
				for(int index = 0; index < amount; index++) {new CashRegister(String.format("CASH_REGISTER_%d", index + 1));}
	}
	
	public void SetAmountOfReservedParkingSpots(int amount)
	{
		for(int index = 0; index < amount; index++) {new ParkingSpotReserved(String.format("RESERVED_PARKING_SPOT_%d", index + 1));}
	}
	
	public void SetAmountOfNotReservedParkingSpots(int amount)
	{
		for(int index = 0; index < amount; index++) {new ParkingSpotNotReserved(String.format("NOT_RESERVED_PARKING_SPOT_%d", index + 1));}
	}
	
	public void SetAmountOfCleaningSpots(int amount)
	{
		// Add cleaning spots
				for(int index = 0; index < amount; index++) {new CleaningSpot(String.format("CLEANING_SPOT_%d", index + 1));}
	}
	
	public void SetAmountOfCleaners(int amount)
	{
		for(int index = 0; index < amount; index++) {new Cleaner(String.format("CLEANER_%d", index + 1));}
	}
	
	public void SetAmountOfAssistants(int amount)
	{
		for(int index = 0; index < amount; index++) {new Assistant(String.format("ASSISTANT_%d", index + 1));}
	}
	
	
}

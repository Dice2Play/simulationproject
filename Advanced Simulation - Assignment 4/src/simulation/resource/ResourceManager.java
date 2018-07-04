package simulation.resource;

import java.util.ArrayList;
import java.util.List;

public class ResourceManager {
	
	static List<Resource> resources = new ArrayList<Resource>();
	
	public static void AddResource(Resource resourceToAdd)
	{
		resources.add(resourceToAdd);
		
		System.out.println(String.format("RESOURCE MANAGER: Added %s with ID %s", resourceToAdd.getResourceType(), resourceToAdd.GetID()));
	}
	
	public static boolean CheckForAvailableResource(Resource_Type typeOfResourceNeeded)
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
	
	
	public static void SeizeResource(Resource_Type typeOfResourceNeeded)
	{
		for(Resource resource : resources)
		{
			if((resource.getResourceType() == typeOfResourceNeeded) && resource.IsAvailable())
			{
				resource.Seize();
			}
		}
	}
	
	// When multiple resources of the same kind need to be seized
	public static void SeizeResource(int quantityOfResourcesNeeded, Resource_Type typeOfResourceNeeded)
	{
		for(int index = 0; index < quantityOfResourcesNeeded; index++)
		{
			SeizeResource(typeOfResourceNeeded);
		}
	}
	
	
	
}

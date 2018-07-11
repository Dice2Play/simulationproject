package simulation.resource;

import java.util.ArrayList;
import java.util.List;

public class ResourceManager {
	
	List<Resource> resources = new ArrayList<Resource>();
	static ResourceManager resourceManager;
	
	private ResourceManager()
	{
		
	}
	
	public static ResourceManager GetInstance()
	{
		if(resourceManager == null)
		{
			resourceManager = new ResourceManager(); 
		}
		
		return resourceManager;
	}
	
	public void AddResource(Resource resourceToAdd)
	{
		resources.add(resourceToAdd);
		
		System.out.println(String.format("RESOURCE MANAGER: Added %s with ID %s", resourceToAdd.getResourceType(), resourceToAdd.GetID()));
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
	
	
	public void SeizeResource(Resource_Type typeOfResourceNeeded)
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
	public void SeizeResource(int quantityOfResourcesNeeded, Resource_Type typeOfResourceNeeded)
	{
		for(int index = 0; index < quantityOfResourcesNeeded; index++)
		{
			SeizeResource(typeOfResourceNeeded);
		}
	}
	
	
	
}

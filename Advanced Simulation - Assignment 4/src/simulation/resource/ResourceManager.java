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
	

	
	
	
}

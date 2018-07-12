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
	

	
	
	
}

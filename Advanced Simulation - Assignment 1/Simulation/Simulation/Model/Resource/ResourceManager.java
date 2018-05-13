package Simulation.Model.Resource;

import java.util.ArrayList;
import java.util.List;

import Simulation.Enums.Resource_Type;
import Simulation.Interfaces.ITick_Listener;
import Simulation.Model.Resource.Behavior.ISeizable;
import Simulation.Model.Time.TimeManager;

public class ResourceManager {

	private static List<ISeizable> resources = new ArrayList<ISeizable>();

	
	public static void AddResource(ISeizable resource)
	{
		resources.add(resource); 
	}
	
	public static boolean CheckForAvailableResource(Resource_Type typeOfResourceNeeded)
	{
		for(ISeizable res : resources)
		{
			if(res.GetResourceType() == typeOfResourceNeeded)
			{
				if(res.CanSeize())
				{
					return true;
				}
			}
		}
		
		// If no resources can be seized
		return false;
	}
	
	
	
	public static void SeizeResource(Resource_Type typeOfResourceNeeded, int capacityNeeded, int amountOfTimeNeeded, String processID)
	{
		for(Resource res : resources)
		{
			if(res.GetResourceType() == typeOfResourceNeeded)
			{
				if(res.CanSeize())
				{
					res.Seize(amountOfTimeNeeded, capacityNeeded);
					
					System.out.println("RESOURCE MANAGER: "
							+ "Process ID["+processID+"] "
							+ "seized resource ["+res.GetID()+"] "
							+ "of type["+typeOfResourceNeeded+"] "
							+ "for ["+ capacityNeeded+ "] capacity, "
							+ "for ["+amountOfTimeNeeded+"] timeunits." );
					
					// Stop loop
					break;
				}
			}
		}
	}
	
	public static boolean CheckIfAnyResourceCanBeReleased()
	{
		for(Resource res : resources)
		{
			if(res.CanRelease())
			{
				return true;
			}
		}
		
		// If no resources can be released
		return false;
	}
	
	public static void ReleaseResources()
	{
		for(Resource res : resources)
		{
			// If resource is not available but can release, release resource
			if(res.CanRelease() && (!res.IsAvailable())) 
			{
				res.Release();
				System.out.println("RESOURCE MANAGER: resource ["+res.GetID()+"] has been released.");
			}
		}
	}
	
	public static double GetResourceOccupancy()
	{
		// For each resource, get occupancy
		List<Double> resourceOccupancies = new ArrayList<Double>();
		resources.forEach(x -> resourceOccupancies.add(x.GetOccupancy()));
		
		// Return mean
		return Statistics.Statistics.GetMean(resourceOccupancies.toArray());
		
	}
	
	public static int GetCapacityOfResource(Resource_Type type)
	{
		return type.GetCapacity();
	}

	public static void Reset() {
		resources = new ArrayList<Resource>();
	}
	
	
	
	
	
}

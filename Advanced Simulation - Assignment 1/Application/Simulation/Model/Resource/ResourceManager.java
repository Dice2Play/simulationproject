package Simulation.Model.Resource;

import java.util.ArrayList;
import java.util.List;

import Simulation.Enums.Resource_Types;
import Simulation.Interfaces.Tick_Listener;
import Simulation.Model.Time.TimeManager;

public class ResourceManager implements Tick_Listener {

	private static List<Resource> resources = new ArrayList<Resource>();
	private static TimeManager timeManager = new TimeManager();
	
	
	public ResourceManager()
	{
		
		// Subscribe to event handlers
		timeManager.AddTickListener(this);
	}

	@Override
	public void Event_Tick(int timePassed) {
		// Check if any resource can be released
		if(CheckIfAnyResourceCanBeReleased())
		{
			ReleaseResources();
		}
	}
	
	public static void AddResource(Resource[] resourcesToAdd)
	{
		for(Resource resource: resourcesToAdd)
		{
			resources.add(resource);
		}
		
	}
	
	public static boolean CheckForAvailableResource(Resource_Types typeOfResourceNeeded, int capacityNeeded)
	{
		for(Resource res : resources)
		{
			if(res.GetResourceType() == typeOfResourceNeeded)
			{
				if(res.CanSeize(capacityNeeded))
				{
					return true;
				}
			}
		}
		
		// If no resources can be seized
		return false;
	}
	
	
	
	public static void SeizeResource(Resource_Types typeOfResourceNeeded, int capacityNeeded, int amountOfTimeNeeded, String processID)
	{
		for(Resource res : resources)
		{
			if(res.GetResourceType() == typeOfResourceNeeded)
			{
				if(res.CanSeize(capacityNeeded))
				{
					res.Seize(amountOfTimeNeeded, capacityNeeded);
					
					System.out.println("Process ID["+processID+"] "
							+ "seized resource of type["+typeOfResourceNeeded+"] "
							+ "for ["+ capacityNeeded+ "] capacity, "
							+ "for ["+amountOfTimeNeeded+"] timeunits." );
					
					// Stop loop
					break;
				}
			}
		}
	}
	
	private static boolean CheckIfAnyResourceCanBeReleased()
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
	
	private static void ReleaseResources()
	{
		for(Resource res : resources)
		{
			
			// If resource is not available but can release, release resource
			if(res.CanRelease() && (!res.IsAvailable())) 
			{
				res.Release();
				System.out.println("Resource ["+res.GetID()+"] has been released.");
			}
		}
	}
	
	
	
	
	
}

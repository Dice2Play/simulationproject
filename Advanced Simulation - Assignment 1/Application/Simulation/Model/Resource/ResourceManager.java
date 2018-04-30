package Simulation.Model.Resource;

import java.util.ArrayList;
import java.util.List;

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
			ReleaseResource();
		}
	}
	
	public static void AddResource(Resource[] resourcesToAdd)
	{
		for(Resource resource: resourcesToAdd)
		{
			resources.add(resource);
		}
		
	}
	
	private static boolean CheckIfAnyResourceCanBeReleased()
	{
		return false;
	}
	
	private static void ReleaseResource()
	{
		
	}
	
	
	
	
	
}

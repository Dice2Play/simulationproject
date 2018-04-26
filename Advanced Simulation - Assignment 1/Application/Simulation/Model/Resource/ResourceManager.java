package Simulation.Model.Resource;

import java.util.ArrayList;
import java.util.List;

import Simulation.Interfaces.Tick_Listener;
import Simulation.Model.Time.TimeManager;

public class ResourceManager implements Tick_Listener {

	private List<Resource> resources = new ArrayList<Resource>();
	private TimeManager timeManager;
	
	public ResourceManager(TimeManager timeManager)
	{
		// Set fields
		this.timeManager = timeManager;
		
		// Subscribe to event handlers
		timeManager.AddTickListener(this);
	}

	@Override
	public void Event_Tick(int timePassed) {
		// Check if any resource can be released
		
	}
	
	public void AddResource(Resource[] resourcesToAdd)
	{
		for(Resource resource: resourcesToAdd)
		{
			resources.add(resource);
		}
		
	}
	
	private void CheckIfResourceCanBeReleased()
	{
		
	}
	
	private void ReleaseResource()
	{
		
	}
	
	
	
	
	
}

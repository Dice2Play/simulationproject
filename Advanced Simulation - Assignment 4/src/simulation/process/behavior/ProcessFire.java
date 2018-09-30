package simulation.process.behavior;

import java.util.ArrayList;

import simulation.process.commands.Release_Entity_From_ProcessCommand;
import simulation.resource.Cleaner;
import simulation.resource.Resource;
import simulation.time.Event_Type;
import simulation.time.TimeEvent;
import simulation.time.TimeManager;

public class ProcessFire extends FireBehavior{

	private simulation.process.Process process;
	
	public ProcessFire(simulation.process.Process process)
	{
		this.process = process;
	}
	
	@Override
	public void Fire() {
		
		// Seize
		process.Seize();
		
		// Set delayed release
		double timeForRelease = TimeManager.GetInstance().GetCurrentTime() + process.GetProcessTime();
		boolean doesTimeForReleaseSurpassesClosingTime = CheckWhetherTimeForReleaseSurpassesClosingTime(timeForRelease);
		timeForRelease = doesTimeForReleaseSurpassesClosingTime ? AdjustTimeForNonAvailabilityOfResources(timeForRelease) : timeForRelease;
		
		
		// Generate TimeEvent for release
		TimeManager.GetInstance().AddTimeEvent(new TimeEvent(timeForRelease, new Release_Entity_From_ProcessCommand(process), String.format("Process %s has released entity&process", process.GetID()), Event_Type.GENERAL));
		
		
		
	}
	
	/*
	 * Checks whether time for release surpasses closing time (19.00 - 19.30) and whether required resources are available then.
	 */
	private boolean CheckWhetherTimeForReleaseSurpassesClosingTime(double timeForRelease)
	{
		
		if(timeForRelease > 12.00)
		{
			return true;
		}
		
		return false;
	}
	
	/*
	 * Adjust time for non-availability of resources
	 */
	private double AdjustTimeForNonAvailabilityOfResources(double timeWhichHasToBeAdjusted)
	{
		// Also check if one of the required resources is a cleaner (which can't work 19.00 - 19.30)
		ArrayList<Resource> assignedResources = process.GetReservedEntity().GetAssignedResources();
		for(Resource resource : assignedResources)
		{
			if(resource.getClass().equals(Cleaner.class))
			{
				return timeWhichHasToBeAdjusted += 0.5;
			}
		}
		
		return timeWhichHasToBeAdjusted;
	}


}

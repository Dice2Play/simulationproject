package simulation.time;

import java.util.ArrayList;
import java.util.LinkedList;


import simulation.interfaces.Tick_Listener;

public class TimeManager {

	static int currentDay;
	static double currentTime;
	static LinkedList<TimeEvent> plannedTimeEvents = new LinkedList<TimeEvent>();
	static ArrayList<Tick_Listener> tickListeners = new ArrayList<Tick_Listener>();
	
	final static double START_TIME = 0;
	final static double END_TIME = 12.5;

	
	
	
	// Increment and execute time event which has to occur on this time
	public static void Tick()
	{
		// Get next time event
		TimeEvent nextTimeEvent = plannedTimeEvents.getFirst();
		
		// Increase time to next event
		IncrementTime(nextTimeEvent);
		
		// Execute next time event
		ExecuteNextTimeEvent(nextTimeEvent);
		
		// Remove time event from 'plannedTimeEvents'
		plannedTimeEvents.remove(nextTimeEvent);
		
		// Fire listeners
		tickListeners.forEach(x -> x.On_Tick());
	}
	
	
	/**
	 * Description:	Execute's the command stored in the timeEvent object, which is passed through the parameter 
	 * Assumption: 	Time is incremented before this method is executed.
	 * @param timeEventToExecute: timeEvent object which command is being executed.
	 */
	public static void ExecuteNextTimeEvent(TimeEvent timeEventToExecute)
	{
		System.out.println(String.format("TIME MANAGER: EVENT %s fired on %d", timeEventToExecute.GetDescription(), GetCurrentTime()));
	}
	
	/**
	 * Description: Sets the current time to the time specified in the 'timeEvent' object, GetTimeOnWhichEventOccurs method.
	 * However if the new time value surpasses the end time value, add the remainder after surpassing the end time value to the start time value, and also increment the current day;
	 * @param timeEvent: object where the new time value is retrieved from.
	 */
	public static void IncrementTime(TimeEvent timeEvent)
	{
		// Check if next time value surpasses the end time, if so, increment day, and add the remainder to the start time.
		if(timeEvent.GetTimeOnWhichEventOccurs() > END_TIME)
		{
			// Increment day
			IncrementDay();
			
			// Set new time value to remainder
			double remainder = (timeEvent.GetTimeOnWhichEventOccurs() - END_TIME);
			SetCurrentTime(remainder);
			
		}
		
		
		else SetCurrentTime(timeEvent.GetTimeOnWhichEventOccurs());
		
	}
	
	private static void IncrementDay()
	{
		currentDay++;
	}
	
	public static void AddTickListener(Tick_Listener tickListenerToAdd)
	{
		tickListeners.add(tickListenerToAdd);
	}
	
	public static void AddTimeEvent(TimeEvent timeEvent)
	{
		plannedTimeEvents.add(timeEvent);
	}
	
	public static int GetCurrentDay()
	{
		return currentDay;
	}
	
	public static double GetCurrentTime()
	{
		return currentTime;
	}
	
	private static void SetCurrentTime(double newTimeValue)
	{
		currentTime = newTimeValue;
	}
	
}

package simulation.time;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;


import simulation.interfaces.Tick_Listener;

public class TimeManager {

	static TimeManager timeManager;
	int currentDay;
	double currentTime;
	LinkedList<TimeEvent> plannedTimeEvents = new LinkedList<TimeEvent>();
	ArrayList<Tick_Listener> tickListeners = new ArrayList<Tick_Listener>();
	
	final double START_TIME = 0;
	final static double END_TIME = 12.5;
	final int AMOUNT_OF_DISCRETE_DAILY_TIME_EVENTS = 25;
	
	private TimeManager()
	{
		// On creation, generate time events for first day
		GenerateDiscreteTimeUnits();
		
	}
	
	
	private void GenerateDiscreteTimeUnits()
	{
		double timeNextDiscreteEvent = 0;
		double intervalBetweenDiscreteEvent = END_TIME / AMOUNT_OF_DISCRETE_DAILY_TIME_EVENTS;
			
		for(int ii = 0; ii <= AMOUNT_OF_DISCRETE_DAILY_TIME_EVENTS; ii++)
		{				
			AddTimeEvent(new TimeEvent(timeNextDiscreteEvent,"Discrete Time Event", Event_Type.GENERAL));
			timeNextDiscreteEvent += intervalBetweenDiscreteEvent;
		}
		
	}
	
	// Increment and execute time event which has to occur on this time
	public void Tick()
	{
		// Get next time event
		OrderPlannedTimeEventsListByTime();
		TimeEvent nextTimeEvent = GetNextTimeEvent();
		
		// Increase time to next event
		IncrementTime(nextTimeEvent);
		
		// Execute next time event
		ExecuteNextTimeEvent(nextTimeEvent);
		
		// Check if next time value surpasses the end time, if so, increment day
		if(nextTimeEvent.GetTimeOnWhichEventOccurs() == END_TIME)
		{
			// Increment day
			IncrementDay();
			
			// Generate new discrete time values for (new) current day
			GenerateDiscreteTimeUnits();
			
		}
		

		// Fire listeners
		tickListeners.forEach(x -> x.On_Tick(nextTimeEvent.GetTypeOfEvent()));
		
		// Remove time event from 'plannedTimeEvents'
		plannedTimeEvents.remove(nextTimeEvent);
		
	}
	
	
	/**
	 * Description:	Execute's the command stored in the timeEvent object, which is passed through the parameter 
	 * Assumption: 	Time is incremented before this method is executed.
	 * @param timeEventToExecute: timeEvent object which command is being executed.
	 */
	public void ExecuteNextTimeEvent(TimeEvent timeEventToExecute)
	{
		timeEventToExecute.ExecuteTimeEvent();
		System.out.println(String.format("TIME MANAGER:[ %s ] EVENT %s ",  GetCurrentTime(), timeEventToExecute.GetDescription()));
	}
	
	/**
	 * Description: Sets the current time to the time specified in the 'timeEvent' object, GetTimeOnWhichEventOccurs method.
	 * However if the new time value surpasses the end time value, increment the current day;
	 * @param timeEvent: object where the new time value is retrieved from.
	 */
	public void IncrementTime(TimeEvent timeEvent)
	{
		
		SetCurrentTime(timeEvent.GetTimeOnWhichEventOccurs());
		
	
		
		
	}
	
	/**
	 * Assumption: The time event list 'plannedTimeEvents' is ordered by time.
	 * Returns the next available time event
	 */
	private TimeEvent GetNextTimeEvent()
	{
		for(int index = 0; index < plannedTimeEvents.size(); index++)
		{
			if(plannedTimeEvents.get(index).IsAvailable()) { return plannedTimeEvents.get(index);} 
		}
		
		return null;
	}
	
	private void IncrementDay()
	{
		currentDay++;
		System.out.println(String.format("TIME MANAGER: Day incremented to %s", currentDay));		
	}
	
	public void AddTickListener(Tick_Listener tickListenerToAdd)
	{
		tickListeners.add(tickListenerToAdd);
	}

	private void OrderPlannedTimeEventsListByTime()
	{
		// Order time events by time
		plannedTimeEvents.sort(new TimeEventComparator());
	}
	
	public void AddTimeEvent(TimeEvent timeEvent)
	{
		plannedTimeEvents.add(timeEvent);

	}
	
	public int GetCurrentDay()
	{
		return currentDay;
	}
	
	public double GetCurrentTime()
	{
		return currentTime;
	}
	
	private void SetCurrentTime(double newTimeValue)
	{
		currentTime = newTimeValue;
	}
	
	public static TimeManager GetInstance()
	{
		if(timeManager == null)
		{
			timeManager = new TimeManager();
		}
		
		return timeManager;
	}
	
	public double GetEndTime()
	{
		return END_TIME;
	}
	
	private static class TimeEventComparator implements Comparator<TimeEvent>
	{
		@Override
		public int compare(TimeEvent timeEvent1, TimeEvent timeEvent2) {
			double timeOfEvent1 = timeEvent1.GetTimeOnWhichEventOccurs();
			double timeOfEvent2 = timeEvent2.GetTimeOnWhichEventOccurs();
			
			if(timeOfEvent2 < timeOfEvent1) {return 1;}
			if(timeOfEvent2 > timeOfEvent1) {return -1;}
			
			// If equal
			return 0;
		}
	}
	
	
}

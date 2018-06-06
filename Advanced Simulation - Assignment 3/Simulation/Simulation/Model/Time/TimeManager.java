package Simulation.Model.Time;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Simulation.Enums.TimeManager_State;
import Simulation.Enums.TimeManager_Subscriber;
import Simulation.Interfaces.*;
import Simulation.Model.Queue.Queue;


public class TimeManager  {

	
	private static List<Tick_Listener> tickListeners = new ArrayList<Tick_Listener>();
	private static double timeUnitsPassed = 0;
	private static LinkedList<TimeEvent> events = new LinkedList<TimeEvent>();
	private static TimeManager_State state = TimeManager_State.FIRE_ALL;

	
	
	public static void Tick()
	{
		// Check whether discrete event occurs before continuous event,
		// if so, first increment to discrete event.
		if(HasNextEvent())
		{
			double timeOfNextContinuousEvent = events.getFirst().getTimeWhichEventOccursOn();
			double roundToNextNearestIntegerGreaterThanInput = getNextWholeNumber();
			
			if(roundToNextNearestIntegerGreaterThanInput < timeOfNextContinuousEvent)
			{
				// Since this is an artificial increment (not through a (continuous) random variable), suppress the tick event for Queue
				// Temp remove - tick - add again
				state = TimeManager_State.DONT_FIRE_QUEUE;
				IncrementToNextDiscreteNumber();
				state = TimeManager_State.FIRE_ALL;
			}
			
			else
			{
				IncrementToNextEvent();
			}
			
		}
		
		// if there are no continuous events planned
		else
		{
			IncrementToNextDiscreteNumber();
		}
		
	}
	
	private static double getNextWholeNumber()
	{
		double roundToNextNearestIntegerGreaterThanInput = Math.ceil(timeUnitsPassed); // Wont work if time is exactly 'x.0'
		double nextDiscreteNumber = 0.0 ;
		
		if(roundToNextNearestIntegerGreaterThanInput == timeUnitsPassed) { nextDiscreteNumber = timeUnitsPassed + 1;}
		else {nextDiscreteNumber = roundToNextNearestIntegerGreaterThanInput;}
		
		//System.out.println(String.format("Input %s, output %s", timeUnitsPassed, nextDiscreteNumber));
		
		return nextDiscreteNumber;
	}
	
	private static void IncrementToNextDiscreteNumber()
	{	
		setTimeUnitsPassed(getNextWholeNumber());
	}
	
	private static void setTimeUnitsPassed(double newValue)
	{
		timeUnitsPassed = newValue;
		PrintAmountOfTimePassed();
		FireTickListeners(); 
	}
	
	public static void AddEvent(TimeEvent event)
	{
		events.add(event);
	}
	
	private static boolean HasNextEvent()
	{
		return !events.isEmpty();
	}
	
	private static void IncrementToNextEvent()
	{
		TimeEvent event = events.getFirst();
		setTimeUnitsPassed(event.getTimeWhichEventOccursOn()); // Set time on moment when event occurs
		System.out.println("TIME MANAGER: " + event.getEventDescription());
		events.remove(event);
	}
	
	public static void Reset()
	{
		tickListeners = new ArrayList<Tick_Listener>();
		timeUnitsPassed = 0;
	}
	
	// Add subscriber to tick event
	public static void AddTickListener(Tick_Listener listener)
	{
		tickListeners.add(listener);
	}
	
	// Remove subscriber from tick event
	public static void RemoveTickListener(Tick_Listener listener)
	{
		tickListeners.remove(listener);
	}
	
	// Notify subscribers
	private static void FireTickListeners()
	{
		for(Tick_Listener listener : tickListeners)
		{
			if(!CanTickListenerFire(listener)) {break;}
			
			listener.Event_Tick(timeUnitsPassed);
		}
	}
	
	// Return amount of time passed
	public static double GetTimeUnitsPassed()
	{
		return timeUnitsPassed;
	}
	
	// Print amount of timeUnitsPassed
	public static void PrintAmountOfTimePassed()
	{
		System.out.println("TIME MANAGER: Amount of time units passed ["+ timeUnitsPassed + "]");
	}
	
	private static boolean CanTickListenerFire(Tick_Listener listener)
	{
		switch(state)
		{
		case FIRE_ALL:
			return true;
		
		case DONT_FIRE_QUEUE:
			
			
			if(listener.GetSubscriberType() == TimeManager_Subscriber.QUEUE){return false;}
			else return true;
				
		
			default:
				return true;
		}
	}
	
}

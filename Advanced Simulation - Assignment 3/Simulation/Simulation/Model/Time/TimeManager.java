package Simulation.Model.Time;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Simulation.Interfaces.*;


public class TimeManager  {

	
	private static List<Tick_Listener> tickListeners = new ArrayList<Tick_Listener>();
	private static double timeUnitsPassed = 0;
	private static LinkedList<TimeEvent> events = new LinkedList<TimeEvent>();

	
	
	public static void Tick()
	{
		// Check if there are any continuous events planned between this and the next tick
		if(HasNextEvent()){RunTillNextEvent();}
		else {IncrementToNextDiscreteNumber();}
	}
	
	private static void IncrementToNextDiscreteNumber()
	{
		double roundToNextNearestIntegerGreaterThanInput = Math.ceil(timeUnitsPassed); // Wont work if time is exactly 'x.0'
		double nextDiscreteNumber = 0.0 ;
		
		if(roundToNextNearestIntegerGreaterThanInput == timeUnitsPassed) { nextDiscreteNumber = timeUnitsPassed + 1;}
		else {nextDiscreteNumber = roundToNextNearestIntegerGreaterThanInput;}
		
	
		setTimeUnitsPassed(nextDiscreteNumber);
	}
	
	private static void setTimeUnitsPassed(double newValue)
	{
		timeUnitsPassed = newValue;
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
	
	private static void RunTillNextEvent()
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
	
	
}

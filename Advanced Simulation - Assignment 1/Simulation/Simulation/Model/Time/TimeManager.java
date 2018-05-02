package Simulation.Model.Time;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Simulation.Interfaces.*;


public class TimeManager  {

	
	private static List<Tick_Listener> tickListeners = new ArrayList<Tick_Listener>();
	private static int timeUnitsPassed = 0;
	
	
	public static void Tick()
	{
		// Increment time unit by one
		timeUnitsPassed = timeUnitsPassed + 1;

		// Fire listeners
		FireTickListeners(); 
	}
	
	public static void Reset()
	{
		timeUnitsPassed = 0;
	}
	
	
	// Add subscriber to tick event
	public static void AddTickListener(Tick_Listener listener)
	{
		tickListeners.add(listener);
	}
	
	// Remove subscriber to tick event
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
	public static int GetTimeUnitsPassed()
	{
		return timeUnitsPassed;
	}
	
	// Print amount of timeUnitsPassed
	public static void PrintAmountOfTimePassed()
	{
		System.out.println("TIME MANAGER: Amount of time units passed ["+ GetTimeUnitsPassed() + "]");
	}
	
	
}

package Simulation.Model.Time;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Simulation.Interfaces.*;


public class TimeManager  {

	
	private static List<Tick_Listener> tickListeners = new ArrayList<Tick_Listener>();
	private static int timeUnitsPassed = 0;
	
	
	// Increment time unit by one
	// Fire listeners
	public static void Tick()
	{
		System.out.println("Fired from timeManager...");
		timeUnitsPassed = timeUnitsPassed + 1;
		FireTickListeners(); // Fire listeners
	}
	
	// Add subscriber to tick event
	public static void AddTickListener(Tick_Listener listener)
	{
		tickListeners.add(listener);
	}
	
	// Notify subscribers
	private static void FireTickListeners()
	{
		for(Tick_Listener listener : tickListeners)
		{
			System.out.println(timeUnitsPassed);
			listener.Event_Tick(timeUnitsPassed);
		}
	}
	
	
}

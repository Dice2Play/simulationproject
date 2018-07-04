package simulation.time;

import java.util.ArrayList;
import java.util.List;

import simulation.interfaces.Tick_Listener;

public class TimeManager {

	static int currentDay;
	static int currentTime;
	static List<TimeEvent> plannedTimeEvents = new ArrayList<TimeEvent>();
	static List<Tick_Listener> tickListeners = new ArrayList<Tick_Listener>();
	
	// Increase time to next event
	public static void Tick()
	{
		
		// Fire listeners
		tickListeners.forEach(x -> x.On_Tick());
	}
	
	public static void AddTickListener(Tick_Listener tickListenerToAdd)
	{
		tickListeners.add(tickListenerToAdd);
	}
	
	public static void AddTimeEvent()
	{
		
	}
	
	public static int GetCurrentDay()
	{
		return currentDay;
	}
	
	public static int GetCurrentTime()
	{
		return currentTime;
	}
	
}

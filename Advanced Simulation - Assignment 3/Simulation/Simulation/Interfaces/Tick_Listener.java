package Simulation.Interfaces;

import Simulation.Enums.TimeManager_Subscriber;

public interface Tick_Listener {

	void Event_Tick(double timeUnitsPassed);
	TimeManager_Subscriber GetSubscriberType();
	
}

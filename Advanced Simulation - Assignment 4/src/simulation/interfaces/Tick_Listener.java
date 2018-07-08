package simulation.interfaces;

import simulation.time.Event_Type;

public interface Tick_Listener {

	void On_Tick(Event_Type eventType);
	
}

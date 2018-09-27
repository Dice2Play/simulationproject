package simulation.time.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import simulation.time.Event_Type;
import simulation.time.TimeEvent;
import simulation.time.TimeManager;



/*
 * TESTSCRIPT: Check whether TICK method operates correctly:
 * 	- Test whether an added TimeEvent is being fired by the TimeManager
 * 
 */

class TimeManager_Tick {

	
	@Test
	void TestWhetherTimeEventIsBeingFired() {
		
		// Set + Add TimeEvent
		double timeOnWhichEventMustOccur = 2.6;
		TimeEvent event = new TimeEvent(timeOnWhichEventMustOccur, "Custom Time event" , Event_Type.GENERAL);
		TimeManager.GetInstance().AddTimeEvent(event);
		
		// Tick for 1 day and check whether 'Custom Time event' has been fired
		boolean hasCustomTimeEventFired = false;
		
		for(int amountOfTimesTicked = 0; amountOfTimesTicked < 25; amountOfTimesTicked++ )
		{
			TimeManager.GetInstance().Tick();
			
			if(TimeManager.GetInstance().GetCurrentTime() == timeOnWhichEventMustOccur)
			{
				hasCustomTimeEventFired = true;
			}
		}
		
		
		assertTrue(hasCustomTimeEventFired);
		
	}
	

}

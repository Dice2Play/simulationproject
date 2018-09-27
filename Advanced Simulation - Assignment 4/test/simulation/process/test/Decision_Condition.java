package simulation.process.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import simulation.entity.EntityManager;
import simulation.interfaces.BooleanCommand;
import simulation.interfaces.Command;
import simulation.process.DecisionBasedOnChance;
import simulation.process.DecisionBasedOnCondition;
import simulation.process.Process;
import simulation.process.Process_Priority;
import simulation.process.Termination;
import simulation.queue.Queue;
import simulation.time.TimeManager;

class Decision_Condition {

	
	/*
	 * Tests when a decision path is given a condition if this path is taken yes/no 
	 */
	@Test
	void TestDecisionBasedOnCondition() {

		// Terminators
		Termination termination1 = new Termination("End of the line baby");
		Queue queue3 = new Queue("Termination QUEUE");
		termination1.SetQueue(queue3);
		
		
		// Processes
		Process process1 = new Process("SHORT CLEANING CAR", Process_Priority.Normal,0);
		Process process2 = new Process("LONG CLEANING CAR", Process_Priority.Normal, 0);
		
		// Queue's
		Queue queue1 = new Queue("DECISION: LONG OR SHORT CLEANING QUEUE?");
		Queue queue2 = new Queue("CLEAN CAR QUEUE SHORT");
		Queue queue4 = new Queue("CLEAN CAR QUEUE LONG");
		
		// Decision
		DecisionBasedOnCondition shortOrLongCleaning = new DecisionBasedOnCondition("DECISION: LONG OR SHORT CLEANING?", new AlwaysTrueCommand());
		
		
		// Set decisions
		shortOrLongCleaning.AddNextSequenceLink(process2);
		shortOrLongCleaning.AddNextSequenceLink(process1);
		shortOrLongCleaning.SetQueue(queue1);
		
		// Set processes
		process1.SetQueue(queue2);
		process1.AddNextSequenceLink(termination1);
		
		
		process2.SetQueue(queue4);
		process2.AddNextSequenceLink(termination1);
		
		// Entity manager
		// Set starting process
		EntityManager.GetInstance().SetStartingSequenceObject(shortOrLongCleaning);
		EntityManager.GetInstance().StartGenerating();
		
		boolean hasLongProcessFired = false;
		boolean hasShortProcessFired = false;
		
		
		// Let TimeManager tick for a few days to make sure that an entity is generated
		for(int amountOfDaysPassed = 0; amountOfDaysPassed <= 3; amountOfDaysPassed++)
		{
			for(int amountOfTimesTicked = 0; amountOfTimesTicked <= 25; amountOfTimesTicked++)
			{
				if(shortOrLongCleaning.CanFire()) { shortOrLongCleaning.Fire();}
				if(process1.CanFire()) { process1.Fire(); hasShortProcessFired = true;}
				if(process2.CanFire()) { process2.Fire(); hasLongProcessFired = true;}
				
				TimeManager.GetInstance().Tick();
			
			}
		}
		
		// Checks whether the long process has fired, and short one hasn't
		if(hasLongProcessFired && !hasShortProcessFired)
		{
			assertTrue(true);
		}
		
		else fail("Short process has fired while it shouldn't or long process hasn't fired.");
		
	}
	
	
	private class AlwaysTrueCommand implements BooleanCommand
	{

		@Override
		public boolean Execute() {
			return true;
		}
		
	}

}

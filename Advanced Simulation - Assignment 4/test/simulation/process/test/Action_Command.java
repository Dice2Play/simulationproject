package simulation.process.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import simulation.entity.EntityManager;
import simulation.process.Action;
import simulation.process.ProcessManager;
import simulation.process.Termination;
import simulation.process.commands.IncrementAmountOfRejects;
import simulation.queue.Queue;
import simulation.queue.QueueManager;
import simulation.resource.ResourceManager;
import simulation.time.TimeManager;


class Action_Command {


	/*
	 * Test whether action command works by measuring the amount of cars left after executing action command.
	 */

	@Test
	void TestActionIncrementAmountOfCarsLeft() {
		
		
		
		// Terminators
		Termination termination1 = new Termination("End of the line baby");
		Queue queue3 = new Queue("Termination QUEUE");
		termination1.SetQueue(queue3);
		
		
		// Set actions
		Action setEntityToRejected = new Action("Set entity to rejected action", new IncrementAmountOfRejects());
		Queue queue5 = new Queue("Set entity to rejected action QUEUE ");
		setEntityToRejected.AddNextSequenceLink(termination1);
		setEntityToRejected.SetQueue(queue5);
		
		// Entity manager
		// Set starting process
		EntityManager.GetInstance().SetStartingSequenceObject(setEntityToRejected);
		EntityManager.GetInstance().StartGenerating();
		
		
		// Let TimeManager tick for a few days to make sure that an entity is generated
		for(int amountOfDaysPassed = 0; amountOfDaysPassed <= 3; amountOfDaysPassed++)
		{
			for(int amountOfTimesTicked = 0; amountOfTimesTicked <= 25; amountOfTimesTicked++)
			{
				if(setEntityToRejected.CanFire()) { setEntityToRejected.Fire();}
				
				TimeManager.GetInstance().Tick();
			
			}
		}
		
		
		if(EntityManager.GetInstance().GetAmountOfRejects() > 0 ) 
		{
			System.out.println(String.format("Amount of rejects is %s", EntityManager.GetInstance().GetAmountOfRejects()));
			assertTrue(true);
		} 
		else { fail("Amount of rejects is 0");  }
		
		
		
		
		
	}
	


}

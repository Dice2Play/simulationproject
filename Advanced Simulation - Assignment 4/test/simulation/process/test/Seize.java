package simulation.process.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import simulation.entity.EntityManager;
import simulation.process.Action;
import simulation.process.Process_Priority;
import simulation.process.Termination;
import simulation.process.commands.IncrementAmountOfRejects;
import simulation.queue.Queue;
import simulation.resource.ResourceManager;
import simulation.resource.Resource_Type;
import simulation.time.TimeManager;

class Seize {

	
	@BeforeEach
	void Setup()
	{
		ResourceManager.GetInstance().Reset();
		TimeManager.GetInstance().Reset();
	}
	
	/*
	 * Test whether seize sequenceobject works by seizing some resources and check whether these resources are seized.
	 */
	@Test
	void TestWhetherResourcesAreSeized() {

		// Terminators
		Termination termination1 = new Termination("End of the line baby");
		
		
		// Seize
		simulation.process.Seize seize_process1 = new simulation.process.Seize("SEIZE Some resources");
		seize_process1.AddNextSequenceLink(termination1);
		seize_process1.AddRequiredResource(Resource_Type.CASH_REGISTER);
		seize_process1.AddRequiredResource(Resource_Type.CLEANING_SPOT);
		seize_process1.AddRequiredResource(Resource_Type.EMPLOYEE_CLEANER);
		

		// Resource Manager
		ResourceManager.GetInstance().SetAmountOfCashRegisters(1);
		ResourceManager.GetInstance().SetAmountOfCleaningSpots(1);
		ResourceManager.GetInstance().SetAmountOfCleaners(1);
		
		// Entity manager
		// Set starting process
		EntityManager.GetInstance().SetStartingSequenceObject(seize_process1);
		EntityManager.GetInstance().StartGenerating();
		
		
		// Let TimeManager tick for a few days to make sure that an entity is generated
		
		
		for(int amountOfDaysPassed = 0; amountOfDaysPassed <= 3; amountOfDaysPassed++)
		{
			for(int amountOfTimesTicked = 0; amountOfTimesTicked <= 25; amountOfTimesTicked++)
			{
				if(seize_process1.CanFire()) { seize_process1.Fire();}
				
				TimeManager.GetInstance().Tick();
			
			}
		}
		
		// Check whether all resources of 'seize_process 1' are seized
		if(ResourceManager.GetInstance().CheckForAvailableResource(Resource_Type.CASH_REGISTER) ||
				ResourceManager.GetInstance().CheckForAvailableResource(Resource_Type.CLEANING_SPOT) || 
				ResourceManager.GetInstance().CheckForAvailableResource(Resource_Type.EMPLOYEE_CLEANER))
		{
			fail("Not all required resources are seized");
		}
		
		else
		{
			assertTrue(true);
		}
		
	}

}

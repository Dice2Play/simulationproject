package simulation.process.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import simulation.entity.EntityManager;
import simulation.process.Process_Priority;
import simulation.process.Termination;
import simulation.queue.Queue;
import simulation.resource.ResourceManager;
import simulation.resource.Resource_Type;
import simulation.time.TimeManager;

class Release {


	@BeforeEach
	void Setup()
	{
		ResourceManager.GetInstance().Reset();
		TimeManager.GetInstance().Reset();
	}
	
	/*
	 * Test whether release sequenceobject works by seizing some resources and check whether these resources are released.
	 */
	@Test
	void TestWhetherResourcesAreReleased() {
		
		// Terminators
		Termination termination1 = new Termination("End of the line baby");
	
			
		// Release 
		simulation.process.Release release_process1 = new simulation.process.Release("RELEASE Some resources");
		release_process1.AddNextSequenceLink(termination1);

		
		
		// Seize
		simulation.process.Seize seize_process1 = new simulation.process.Seize("SEIZE Some resources");
		seize_process1.AddNextSequenceLink(release_process1);
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
		boolean hasSeizeFired = false; // Only let Seize fire once
		
		for(int amountOfDaysPassed = 0; amountOfDaysPassed <= 3; amountOfDaysPassed++)
		{
			for(int amountOfTimesTicked = 0; amountOfTimesTicked <= 25; amountOfTimesTicked++)
			{
				if(seize_process1.CanFire() && !hasSeizeFired) { seize_process1.Fire(); hasSeizeFired = true;}
				if(release_process1.CanFire()) { release_process1.Fire();}
				
				TimeManager.GetInstance().Tick();
			
			}
		}
		
		// Check whether all resources of 'release_process 1' are released
		if(ResourceManager.GetInstance().CheckForAvailableResource(Resource_Type.CASH_REGISTER) &&
				ResourceManager.GetInstance().CheckForAvailableResource(Resource_Type.CLEANING_SPOT) && 
				ResourceManager.GetInstance().CheckForAvailableResource(Resource_Type.EMPLOYEE_CLEANER))
		{
			assertTrue(true);
		}
		
		else
		{
			fail("Not all required resources are released");
		}
			
				
	}

}

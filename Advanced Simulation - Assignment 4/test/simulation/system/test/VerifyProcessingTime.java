package simulation.system.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import simulation.entity.Entity;
import simulation.process.ProcessManager;
import simulation.process.Process_Priority;
import simulation.process.Release;
import simulation.process.Seize;
import simulation.process.Termination;
import simulation.time.TimeManager;

class VerifyProcessingTime {

	@BeforeEach
	void setUp() throws Exception {
	}

	
	/*
	 * Check whether the output processing time from a single process is correct.
	 * - Check for single entity
	 */
	@Test
	void TestSingleProcess() {
		
		// Set processing time
		double processingTime = 10.0;
		
		// Create/set entity
		Entity entity1 = new Entity("Car_x");
		Entity entity2 = new Entity("Car_y");
		Entity entity3 = new Entity("Car_z");
		ArrayList<Entity> entitiesToTest = new ArrayList<Entity>();
		entitiesToTest.add(entity1);
		entitiesToTest.add(entity2);
		entitiesToTest.add(entity3);
		
		
		
		// Set references 
		Seize seize_1 = new Seize("Seize 1", Process_Priority.Normal);
		Release release_1 = new Release("Release 1", Process_Priority.Normal);
		simulation.process.Process process_1 = new simulation.process.Process("Process 1", Process_Priority.High,processingTime);
		Termination terminate_1 = new Termination("Terminate 1");
		
		// Add entities to first sequence object
		seize_1.AddEntityToQueue(entity1);
		seize_1.AddEntityToQueue(entity2);
		seize_1.AddEntityToQueue(entity3);
		
		
		// Set sequence objects
		seize_1.AddNextSequenceLink(process_1);
		process_1.AddNextSequenceLink(release_1);
		release_1.AddNextSequenceLink(terminate_1);
		
		
		// Run process for a few days
		int AMOUNT_OF_DAYS_TO_RUN = 5;
		while(TimeManager.GetInstance().GetCurrentDay() < AMOUNT_OF_DAYS_TO_RUN)
		{
			while(ProcessManager.GetInstance().CanFire())
			{
				try
				{
					ProcessManager.GetInstance().Fire();
				}
				
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
			}
			
			TimeManager.GetInstance().Tick();
		}
		
		
		// Check for all entities whether their 'total processing time' matches manual set 'processingTime'
		for(Entity entity : entitiesToTest)
		{
			if(entity.GetProcessingTime() != processingTime)
			{
				fail("processing time of entity isn't the same as the specified one.");
			}
		}
		
		// If fail hasn't fired, return success
		assertTrue(true);
		
	}
	
	/*
	 * Check whether the output processing time from multiple sequential processes is correct.
	 * - Check for single entity
	 */
	@Test
	void TestMultipleSequentialProcesses() {
		
		
	}
	

	/*
	 * Check whether the output processing time from multiple parallel processes is correct.
	 * - Check for single entity
	 * - Check mean
	 */
	@Test
	void TestMultipleParallelProcesses() {
		
		
	}
}

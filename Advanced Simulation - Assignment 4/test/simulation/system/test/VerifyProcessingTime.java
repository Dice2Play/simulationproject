package simulation.system.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import simulation.entity.Entity;
import simulation.entity.EntityManager;
import simulation.process.ProcessManager;
import simulation.process.Process_Priority;
import simulation.process.Release;
import simulation.process.Seize;
import simulation.process.Termination;
import simulation.resource.ResourceManager;
import simulation.resource.Resource_Type;
import simulation.time.TimeManager;

class VerifyProcessingTime {

	@BeforeEach
	void setUp() throws Exception {
		ResourceManager.GetInstance().Reset();
		EntityManager.GetInstance().Reset();
		ProcessManager.GetInstance().Reset();
		TimeManager.GetInstance().Reset();
		
	}

	
	/*
	 * Check whether the output processing time from a single process is correct.
	 * - Check for single entity
	 */
	@Test
	void TestSingleProcess() {
		
		
		// Set resources
		ResourceManager.GetInstance().SetAmountOfCleaners(1);
		
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
		Seize seize_1 = new Seize("Seize 1");
		Release release_1 = new Release("Release 1");
		simulation.process.Process process_1 = new simulation.process.Process("Process 1",processingTime);
		Termination terminate_1 = new Termination("Terminate 1");
		
		// Add entities to first sequence object
		seize_1.AddEntityToQueue(entity1);
		seize_1.AddEntityToQueue(entity2);
		seize_1.AddEntityToQueue(entity3);
		
		
		// Set sequence objects
		seize_1.AddNextSequenceLink(process_1);
		seize_1.AddRequiredResource(Resource_Type.EMPLOYEE_CLEANER);
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
	 * Check whether the output processing time from multiple parallel processes is correct.
	 * - Check for single entity
	 * - Check mean
	 */
	@Test
	void TestMultipleParallelProcesses() {
		
		// Set resources
				ResourceManager.GetInstance().SetAmountOfCleaners(1);
				
				// Set processing time
				double processingTime = 10.0;
				
				// Create/set entity
				Entity entity1 = new Entity("Car_x");
				Entity entity2 = new Entity("Car_y");
				Entity entity3 = new Entity("Car_z");
				Entity entity4 = new Entity("Car_z");
				Entity entity5 = new Entity("Car_z");
				Entity entity6 = new Entity("Car_z");
				Entity entity7 = new Entity("Car_z");
				Entity entity8 = new Entity("Car_z");
				ArrayList<Entity> entitiesToTest = new ArrayList<Entity>();
				entitiesToTest.add(entity1);
				entitiesToTest.add(entity2);
				entitiesToTest.add(entity3);
				entitiesToTest.add(entity4);
				entitiesToTest.add(entity5);
				entitiesToTest.add(entity6);
				entitiesToTest.add(entity7);
				entitiesToTest.add(entity8);
				
				
				
				// Set references 
				Seize seize_1 = new Seize("Seize 1");
				Release release_1 = new Release("Release 1");
				simulation.process.Process process_1 = new simulation.process.Process("Process 1",processingTime);
				simulation.process.Process process_2 = new simulation.process.Process("Process 2",processingTime);
				simulation.process.Process process_3 = new simulation.process.Process("Process 3",processingTime);
				simulation.process.Process process_4 = new simulation.process.Process("Process 4",processingTime);
				simulation.process.Process process_5 = new simulation.process.Process("Process 5",processingTime);
				simulation.process.Process process_6 = new simulation.process.Process("Process 6",processingTime);
				simulation.process.Process process_7 = new simulation.process.Process("Process 7",processingTime);
				Termination terminate_1 = new Termination("Terminate 1");
				
				// Add entities to first sequence object
				seize_1.AddEntityToQueue(entity1);
				seize_1.AddEntityToQueue(entity2);
				seize_1.AddEntityToQueue(entity3);
				seize_1.AddEntityToQueue(entity4);
				seize_1.AddEntityToQueue(entity5);
				seize_1.AddEntityToQueue(entity6);
				seize_1.AddEntityToQueue(entity7);
				seize_1.AddEntityToQueue(entity8);
				
				
				// Set sequence objects
				seize_1.AddNextSequenceLink(process_1);
				seize_1.AddNextSequenceLink(process_2);
				seize_1.AddNextSequenceLink(process_3);
				seize_1.AddNextSequenceLink(process_4);
				seize_1.AddNextSequenceLink(process_5);
				seize_1.AddNextSequenceLink(process_6);
				seize_1.AddNextSequenceLink(process_7);
					
				seize_1.AddRequiredResource(Resource_Type.EMPLOYEE_CLEANER);
				process_1.AddNextSequenceLink(release_1);
				release_1.AddNextSequenceLink(terminate_1);
				
				
				// Run process for a few days
				int AMOUNT_OF_DAYS_TO_RUN = 10;
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
}

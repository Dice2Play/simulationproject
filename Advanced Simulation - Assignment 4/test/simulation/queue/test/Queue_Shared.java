package simulation.queue.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import simulation.entity.EntityManager;
import simulation.process.Action;
import simulation.process.Process;
import simulation.process.ProcessManager;
import simulation.process.Process_Priority;
import simulation.process.Termination;
import simulation.process.commands.GenerateProcessingTimeAccordingToDistributionCommand;
import simulation.process.commands.IncrementAmountOfRejects;
import simulation.queue.Queue;
import simulation.resource.ResourceManager;
import simulation.resource.Resource_Type;
import simulation.time.TimeManager;

class Queue_Shared {


	/*
	 * Test for any exceptions during multiple processes retrieving entities from the same queue
	 */
	@Test
	void TestOneQueueMultipleProcesses() {
		
		// Terminators
		Termination termination1 = new Termination("End of the line baby");
		Queue terminateQueue = new Queue("Termination QUEUE");
		termination1.SetQueue(terminateQueue);
		
		
		// Release 
		simulation.process.Release release_process1 = new simulation.process.Release("RELEASE Some resources", Process_Priority.Normal);
		Queue releaseQueue = new Queue("Queue for release process_1");
		release_process1.SetQueue(releaseQueue);
		release_process1.AddNextSequenceLink(termination1);
		
		// Processes
		Queue processSharedQueue = new Queue("Queue for cleaning spots");
		
		Process process1 = new Process("Process cleaning spot 1", Process_Priority.Normal,10);
		process1.SetQueue(processSharedQueue);
		process1.AddNextSequenceLink(release_process1);
		
		
		// Seize
		simulation.process.Seize seize_process1 = new simulation.process.Seize("SEIZE Some resources", Process_Priority.Normal);
		Queue queue1 = new Queue("Queue for seize process_1");
		seize_process1.SetQueue(queue1);
		seize_process1.AddNextSequenceLink(release_process1);
		seize_process1.AddRequiredResource(Resource_Type.CASH_REGISTER);
		seize_process1.AddRequiredResource(Resource_Type.CLEANING_SPOT);
		seize_process1.AddRequiredResource(Resource_Type.EMPLOYEE_CLEANER);
		
		
		// Resource Manager
		ResourceManager.GetInstance().SetAmountOfCashRegisters(10);
		ResourceManager.GetInstance().SetAmountOfCleaningSpots(10);
		ResourceManager.GetInstance().SetAmountOfCleaners(10);
		
		
		// Entity manager
		// Set starting process
		EntityManager.GetInstance().SetStartingSequenceObject(seize_process1);
		EntityManager.GetInstance().StartGenerating();
		
		
		// Let TimeManager tick for a few days to make sure that an entity is generated
		for(int amountOfDaysPassed = 0; amountOfDaysPassed <= 3; amountOfDaysPassed++)
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
		
		
	}

}

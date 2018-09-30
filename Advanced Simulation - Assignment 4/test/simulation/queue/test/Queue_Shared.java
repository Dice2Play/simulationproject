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
		
		
		// Release 
		simulation.process.Release release_process1 = new simulation.process.Release("RELEASE Some resources");
		release_process1.AddNextSequenceLink(termination1);
		
		// Processes
		Process process1 = new Process("Process cleaning spot 1",10);
		process1.AddNextSequenceLink(release_process1);
		
		Process process2 = new Process("Process cleaning spot 2",10);
		process2.SetSharedQueue(process1);
		process2.AddNextSequenceLink(release_process1);
		
		Process process3 = new Process("Process cleaning spot 3",10);
		process3.SetSharedQueue(process1);
		process3.AddNextSequenceLink(release_process1);
		
		Process process4 = new Process("Process cleaning spot 4",10);
		process4.SetSharedQueue(process1);
		process4.AddNextSequenceLink(release_process1);
		
		Process process5 = new Process("Process cleaning spot 5",10);
		process5.SetSharedQueue(process1);
		process5.AddNextSequenceLink(release_process1);
		
		Process process6 = new Process("Process cleaning spot 6",10);
		process6.SetSharedQueue(process1);
		process6.AddNextSequenceLink(release_process1);
		
		
		// Seize
		simulation.process.Seize seize_process1 = new simulation.process.Seize("SEIZE Some resources");
		seize_process1.AddNextSequenceLink(process1);
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
		
		
	}

}

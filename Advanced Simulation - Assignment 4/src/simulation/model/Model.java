package simulation.model;

import simulation.queue.Queue;
import simulation.queue.QueueManager;
import simulation.resource.Assistant;
import simulation.resource.CashRegister;
import simulation.resource.Cleaner;
import simulation.resource.CleaningSpot;
import simulation.resource.ParkingSpot;
import simulation.resource.ResourceManager;
import simulation.resource.Resource_Type;
import simulation.time.TimeEvent;
import simulation.time.TimeManager;
import simulation.entity.EntityManager;
import simulation.interfaces.Command;
import simulation.interfaces.Tick_Listener;
import simulation.process.Decision;
import simulation.process.Process;
import simulation.process.ProcessManager;
import simulation.process.Termination;
import simulation.process.behavior.NextSequence;
import simulation.process.behavior.NextSequenceWithChance;
public class Model {

	int amountOfDaysToRun;

	
	public Model(int amountOfDaysToRun)
	{
		this.amountOfDaysToRun = amountOfDaysToRun;
		
		// Generate model objects
		GenerateResources();
		GenerateProcesses();
	}
	
	public void GenerateResources()
	{
		// Add cash registers
		for(int index = 1; index < 4; index++) {new CashRegister(String.format("CASH_REGISTER_%d", index));}
				
		// Add cleaning spots
		for(int index = 1; index < 11; index++) {new CleaningSpot(String.format("CLEANING_SPOT_%d", index));}
						
		// Add parking spots
		for(int index = 1; index < 26; index++) {new ParkingSpot(String.format("PARKING_SPOT_%d", index));}

		// Add employees
		for(int index = 1; index < 4; index++) {new Assistant(String.format("ASSISTANT_%d", index));}
		for(int index = 1; index < 4; index++) {new Cleaner(String.format("CLEANER_%d", index));}
		
	}
		
	public void GenerateProcesses()
	{
		double PROBABILITY_SHORT_CLEANING = 0.293;
		double PROBABILITY_LONG_CLEANING = 0.707;
		
		// Create references
		Queue queue1, queue2, queue3;
		
			// Decisions
		Decision shortOrLongCleaning = new Decision("DECISION: LONG OR SHORT CLEANING");
		
			// Processes
		Process process1,process2;
		process1 = new Process("LONG CLEANING CAR", 10.0/60.0);
		process2 = new Process("SHORT CLEANING CAR", 20.0/60.0);
		
			// Terminators
		Termination termination1 = new Termination("End of the line baby");
		
		
			// Queue's
		queue1 = new Queue("DECISION: LONG OR SHORT CLEANING QUEUE");
		queue2 = new Queue("CLEAN CAR QUEUE");
		queue3 = new Queue("Termination queue");
		
		// Set decisions
		shortOrLongCleaning.AddNextSequenceLink(new NextSequenceWithChance(process1, PROBABILITY_SHORT_CLEANING));
		shortOrLongCleaning.AddNextSequenceLink(new NextSequenceWithChance(process2, PROBABILITY_LONG_CLEANING));
		shortOrLongCleaning.SetQueue(queue1);
		
		// Set processes
		process1.AddRequiredResource(Resource_Type.EMPLOYEE_ASSISTANT);
		process1.AddRequiredResource(Resource_Type.CLEANING_SPOT);
		process1.SetQueue(queue2);
		process1.AddNextSequenceLink(new NextSequence(termination1));
		
		
		process2.AddRequiredResource(Resource_Type.EMPLOYEE_ASSISTANT);
		process2.AddRequiredResource(Resource_Type.CLEANING_SPOT);
		process2.SetQueue(queue2);
		process2.AddNextSequenceLink(new NextSequence(termination1));
		
		// Set terminators
		termination1.SetQueue(queue3);
		
		// Entity manager
		// Set starting process
		EntityManager.GetInstance().SetStartingSequenceObject(shortOrLongCleaning);
		EntityManager.GetInstance().StartGenerating();
		
		

		
	}
	
	private void Report()
	{
		
	}
	
	public void Run()
	{
		System.out.print("MODEL: RUN STARTED \n");
		
		while(TimeManager.GetInstance().GetCurrentDay() < amountOfDaysToRun)
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
			
			/**
			 * Report after all possible processes have fired. 
			 */
			Report();
			
			/** If no processes can fire tell the TimeManager to tick.
			 * - Will generate new entities when TimeEvent Event-Type is 'ARRIVAL'.
			 * - Will cause a process to call it's 'End_Delay' method, releasing related resources and entities.
			 */
			TimeManager.GetInstance().Tick();
		} 
		
			
	}

	public void Reset()
	{
		// Reset all managers
		ProcessManager.GetInstance().Reset();
		QueueManager.GetInstance().Reset();
		ResourceManager.GetInstance().Reset();
		TimeManager.GetInstance().Reset();
		EntityManager.GetInstance().Reset();
		
	}
	
}

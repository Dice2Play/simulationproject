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
import simulation.process.behavior.NextSequence;
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
		for(int index = 1; index < 4; index++) {ResourceManager.GetInstance().AddResource(new CashRegister(String.format("CASH_REGISTER_%d", index)));}
				
		// Add cleaning spots
		for(int index = 1; index < 11; index++) {ResourceManager.GetInstance().AddResource(new CleaningSpot(String.format("CLEANING_SPOT_%d", index)));}
						
		// Add parking spots
		for(int index = 1; index < 26; index++) {ResourceManager.GetInstance().AddResource(new ParkingSpot(String.format("PARKING_SPOT_%d", index)));}

		// Add employees
		for(int index = 1; index < 4; index++) {ResourceManager.GetInstance().AddResource(new Assistant(String.format("ASSISTANT_%d", index)));}
		for(int index = 1; index < 4; index++) {ResourceManager.GetInstance().AddResource(new Cleaner(String.format("CLEANER_%d", index)));}
		
	}
	
	
	
	public void GenerateProcesses()
	{
		// Create references
		Queue queue1;
		Decision shortOrLongCleaning;
		Process process1,process2;
		process1 = process2 = null;
		
		
		// Create queue's
		queue1 = new Queue("CLEAN CAR QUEUE");
		
		// Set decisions
		shortOrLongCleaning = new Decision("DECISION: LONG OR SHORT CLEANING");
		shortOrLongCleaning.AddNextSequenceLink(new NextSequence(process1));
		shortOrLongCleaning.AddNextSequenceLink(new NextSequence(process2));
		
		// Create processes
		
		process1 = new Process("LONG CLEANING CAR", 10.0/60.0);
		process1.AddRequiredResource(Resource_Type.EMPLOYEE_ASSISTANT);
		process1.AddRequiredResource(Resource_Type.CLEANING_SPOT);
		process1.SetQueue(queue1);
		
		process2 = new Process("SHORT CLEANING CAR", 20.0/60.0);
		process2.AddRequiredResource(Resource_Type.EMPLOYEE_ASSISTANT);
		process2.AddRequiredResource(Resource_Type.CLEANING_SPOT);
		process2.SetQueue(queue1);
		
		// Entity manager
		// Register starting process
		EntityManager.GetInstance().SetStartingSequenceObject(shortOrLongCleaning);
		EntityManager.GetInstance().StartGenerating();
		
		
		// QueueManager
		// Register queue's
		QueueManager.RegisterQueue(queue1);
		
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
			
			/** If no processes can fire tell the TimeManager to tick.
			 * - Will generate new entities when TimeEvent Event-Type is 'ARRIVAL'.
			 * - Will cause a process to call it's 'End_Delay' method, releasing related resources and entities.
			 */
			TimeManager.GetInstance().Tick();
		} 
		
		

		
	}

}

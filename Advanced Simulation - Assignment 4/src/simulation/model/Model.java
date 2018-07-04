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
import simulation.time.TimeManager;
import simulation.process.Process;
import simulation.process.ProcessManager;
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
		for(int index = 1; index < 4; index++) {ResourceManager.AddResource(new CashRegister(String.format("CASH_REGISTER_%d", index)));}
				
		// Add cleaning spots
		for(int index = 1; index < 11; index++) {ResourceManager.AddResource(new CleaningSpot(String.format("CLEANING_SPOT_%d", index)));}
						
		// Add parking spots
		for(int index = 1; index < 26; index++) {ResourceManager.AddResource(new ParkingSpot(String.format("PARKING_SPOT_%d", index)));}

		// Add employees
		for(int index = 1; index < 4; index++) {ResourceManager.AddResource(new Assistant(String.format("ASSISTANT_%d", index)));}
		for(int index = 1; index < 4; index++) {ResourceManager.AddResource(new Cleaner(String.format("CLEANER_%d", index)));}
		
	}
	
	
	
	public void GenerateProcesses()
	{
		// Create queue's
		Queue queue1 = new Queue("SEIZE ASSISTANT AND CLEANING SPOT QUEUE");
		
	
		
		// Create processes
		Process process1 = new Process("SEIZE ASSISTANT AND CLEANING SPOT");
		process1.AddRequiredResource(Resource_Type.EMPLOYEE_ASSISTANT);
		process1.AddRequiredResource(Resource_Type.CLEANING_SPOT);
		
		process1.SetQueue(queue1);
		
		// Entity manager
		// Register starting process
		
		
		
		// QueueManager
		// Register queue's
		QueueManager.RegisterQueue(queue1);
		
	}
	
	
	
	public void Run()
	{
		System.out.print("MODEL: Start run");
		
		while(TimeManager.GetCurrentDay() < amountOfDaysToRun)
		{
			while(ProcessManager.CanFire())
			{
				ProcessManager.Fire();
			}
		}
		
		
	}
}

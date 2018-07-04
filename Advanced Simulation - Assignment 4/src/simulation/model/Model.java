package simulation.model;

import simulation.process.ProcessManager;
import simulation.resource.Assistant;
import simulation.resource.CashRegister;
import simulation.resource.Cleaner;
import simulation.resource.CleaningSpot;
import simulation.resource.ParkingSpot;
import simulation.resource.ResourceManager;
import simulation.time.TimeManager;

public class Model {

	int amountOfDaysToRun;
	
	public Model(int amountOfDaysToRun)
	{
		this.amountOfDaysToRun = amountOfDaysToRun;
		
		// Generate model objects
		GenerateResources();
		GenerateProcesses();
		
		// Run model
		Run();
	}
	
	public void GenerateResources()
	{
		// Add cash registers
		for(int index = 1; index < 4; index++) {ResourceManager.AddResource(new CashRegister(String.format("CASH_REGISTER_%d", index)));}
				
		// Add cleaning spots
		for(int index = 1; index < 4; index++) {ResourceManager.AddResource(new CleaningSpot(String.format("CLEANING_SPOT_%d", index)));}
						
		// Add parking spots
		for(int index = 1; index < 4; index++) {ResourceManager.AddResource(new ParkingSpot(String.format("PARKING_SPOT_%d", index)));}

		// Add employees
		for(int index = 1; index < 4; index++) {ResourceManager.AddResource(new Assistant(String.format("ASSISTANT_%d", index)));}
		for(int index = 1; index < 4; index++) {ResourceManager.AddResource(new Cleaner(String.format("CLEANER_%d", index)));}
		
	}
	
	public void GenerateProcesses()
	{
		
	}
	
	
	
	public void Run()
	{
	
		while(TimeManager.GetCurrentDay() < amountOfDaysToRun)
		{
			while(ProcessManager.CanFire())
			{
				ProcessManager.Fire();
			}
		}
		
		
	}
}

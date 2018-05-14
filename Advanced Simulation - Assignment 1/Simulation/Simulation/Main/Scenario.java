package Simulation.Main;


import Simulation.Model.*;
import Simulation.Results.ResultManager;

public class Scenario {
	
	private int timeUnitsToRun;
	private int amountOfSimulationsToRun;
	private boolean useSingleQueue;

	public Scenario(int timeUnitsToRun, int amountOfSimulationsToRun, boolean useSingleQueue)
	{
		// Set fields
		this.timeUnitsToRun = timeUnitsToRun;
		this.amountOfSimulationsToRun = amountOfSimulationsToRun;
		this.useSingleQueue = useSingleQueue;

	}
	
	public void Run()
	{
		for(int amountOfReplicationsRan = 1; amountOfReplicationsRan <= amountOfSimulationsToRun; amountOfReplicationsRan++)
		{
			// Set ResultManager
			ResultManager.SetCurrentReplication(amountOfReplicationsRan);
			
			// Create/Run/Reset Model
			Model model = new Model(timeUnitsToRun, useSingleQueue);
			model.Run();
			model.Reset();
			
			// Show replication summary		
			ResultManager.ShowSummary();
			//ResultManager.ShowAllResults();
			
			
			// Clear results of ResultManager
			ResultManager.ClearResults();
		}
	}
	
}

package Simulation.Main;


import Simulation.Model.*;
import Simulation.Results.ResultManager;

public class Scenario {
	
	private int timeUnitsToRun;
	private int amountOfSimulationsToRun;
	
	public Scenario(int timeUnitsToRun, int amountOfSimulationsToRun)
	{
		// Set fields
		this.timeUnitsToRun = timeUnitsToRun;
		this.amountOfSimulationsToRun = amountOfSimulationsToRun;

	}
	
	public void Run()
	{
		for(int amountOfReplicationsRan = 1; amountOfReplicationsRan <= amountOfSimulationsToRun; amountOfReplicationsRan++)
		{
			// Set ResultManager
			ResultManager.SetCurrentReplication(amountOfReplicationsRan);
			
			// Create/Run/Reset Model
			Model model = new Model(timeUnitsToRun);
			model.Run();
			model.Reset();
			
			// Show replication summary		
			ResultManager.ShowSummary();
			
			// Clear results of ResultManager
			ResultManager.ClearResults();
		}
	}
	
}

package Simulation.Main;


import Simulation.Model.*;
import Simulation.Results.ResultManager;

public class Scenario {
	
	private int timeUnitsToRun;
	private int amountOfSimulationsToRun;
	/** 
	 * @param timeUnitsToRun set up how long per simulation will be 
	 * @param amountOfSimulationsToRun set up how many times cases we would run
	 */
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
			ResultManager.ShowAllResults();
			ResultManager.ShowSummary();
			
			// Export replication summary to csv
			ResultManager.ExportSummaryToCSV();
			
			// Clear results of ResultManager
			ResultManager.ClearResults();
		}
	}
	
}

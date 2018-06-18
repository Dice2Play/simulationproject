package Simulation.Main;


import java.util.List;

import Simulation.Model.*;
import Simulation.Model.Queue.Queue;
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
			ResultManager.ShowAllResults();
			ResultManager.ShowSummary();
			
			// Export replication summary to csv
			ResultManager.ExportSummaryToCSV();
			
			// Clear results of ResultManager
			ResultManager.ClearResults();
		}
		//show the result of waiting time of one simulation with multi runs
	List<Double> mylist = Queue.getWaitingTimeRecord();
	  double avgWaitingtime = mylist.stream().mapToDouble(val -> val).average().orElse(0);
	  System.out.println("final avg waiting time test (jennifer): "+ avgWaitingtime);
	}
	
}

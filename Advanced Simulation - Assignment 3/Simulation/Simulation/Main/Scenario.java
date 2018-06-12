package Simulation.Main;


import java.util.Arrays;

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
		System.out.print("****** Table statistics*************"+"\n");
		System.out.print("ExpectedValueXg: " + ResultManager.getExpectedValueXg()+"\n");
		System.out.print("ExpectedValueXg2nd: " + ResultManager.getExpectedValueXg2nd()+"\n");
		System.out.print("getExpectedValueX0: " + ResultManager.getExpectedValueX0()+"\n");
		System.out.print("ExpectedValueofAvgQueuelength: " + ResultManager.getExpectedValueofAvgQueuelength()+"\n");
		System.out.print("ExpectedDelay: " + ResultManager.getExpectedDelay()+"\n");
		System.out.print("ExpectedDelay2nd: " + ResultManager.getExpectedDelay2nd()+"\n");
		System.out.print("ProbabilityX0is0: " + ResultManager.getProbabilityX0is0()+"\n");
		System.out.print("ProbabilityXgis0: " + ResultManager.getProbabilityXgis0()+"\n");
		System.out.print("*********Variance******************* " +"\n");
		System.out.print("Variance of Xg: " + ResultManager.getVarianceXg()+"\n");
		System.out.print("Variance of X0: " + ResultManager.getVarianceX0()+"\n");
		System.out.print("Variance of X: " + ResultManager.getVarianceX()+"\n");
		System.out.print("Variance of Delay: " + ResultManager.getVarianceDelay()+"\n");
		System.out.print("****** upper and lower bound of 95% confidence interval**** " +"\n");
		System.out.print("*************Upper************Lower************** " +"\n");
		//xg 95
		System.out.println("Xg: "+Arrays.toString(ResultManager.get95ConfidenceXg()));
		//x0 95
		System.out.println("X0: "+Arrays.toString(ResultManager.get95ConfidenceX0()));
		//X avg 95
		System.out.println("X average: "+Arrays.toString(ResultManager.get95ConfidenceX()));
		//Delay 95
		System.out.println("Delay: "+Arrays.toString(ResultManager.get95ConfidenceDelay()));
		
		
	}
	
}

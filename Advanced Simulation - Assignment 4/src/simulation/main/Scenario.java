package simulation.main;

import simulation.model.Model;
import simulation.result.ResultManager;

public class Scenario {

	private int amountOfDaysToRun;
	private int amountOfReplications;
	
	
	public Scenario(int amountOfDaysToRun, int amountOfReplications)
	{
		this.amountOfDaysToRun = amountOfDaysToRun;
		this.amountOfReplications = amountOfReplications;
	}
	
	public void Run()
	{
		for(int index=0; index < amountOfReplications; index++)
		{
			// Run/Reset Model
			Model modelToRun = new Model(amountOfDaysToRun);
			modelToRun.Run();
			modelToRun.Report(); // Writes all results to ResultManager
			modelToRun.Reset();
			
			// Print results
			ResultManager.GetInstance().PrintResults();
				
			// Reset resultManager
			ResultManager.GetInstance().Reset();
		}
		
		
	}
	
	
}

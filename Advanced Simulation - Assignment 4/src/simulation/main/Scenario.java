package simulation.main;

import simulation.model.Model;

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
			
			Model modelToRun = new Model(amountOfDaysToRun);
			//modelToRun.Run();
		}
		
		
	}
	
	
}

package Simulation.Main;


import Simulation.Model.*;

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
		for(int amountOfReplicationsRan = 0; amountOfReplicationsRan < amountOfSimulationsToRun; amountOfReplicationsRan++)
		{
			Model model = new Model(timeUnitsToRun);
			model.Run();
			model.Reset();
			System.out.println("End of simulation ["+amountOfReplicationsRan+"]");
			
			
		}
	}
	
}

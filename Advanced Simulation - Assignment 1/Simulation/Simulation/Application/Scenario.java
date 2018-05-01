package Simulation.Application;
import java.util.ArrayList;
import java.util.List;

import Simulation.Model.*;

public class Scenario {
	
	private int timeUnitsToRun;
	private int amountOfSimulationsToRun;
	private List<Model> replications = new ArrayList<Model>();
	
	public Scenario(int timeUnitsToRun, int amountOfSimulations)
	{
		// Set fields
		this.timeUnitsToRun = timeUnitsToRun;
		this.amountOfSimulationsToRun = amountOfSimulations;

	}
	
	public void Run()
	{
		for(int amountOfReplicationsRan = 0; amountOfReplicationsRan < amountOfSimulationsToRun; amountOfReplicationsRan++)
		{
			Model model = new Model(timeUnitsToRun);
			model.Run();
			replications.add(model);
		}
	}
	
}

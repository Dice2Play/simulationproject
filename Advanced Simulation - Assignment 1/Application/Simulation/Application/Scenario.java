package Simulation.Application;
import java.util.ArrayList;
import java.util.List;

import Simulation.Model.*;

public class Scenario {
	
	private int timeUnitsToRun;
	private int amountOfSimulations;
	private List<Model> replications = new ArrayList<Model>();
	
	public Scenario(int timeUnitsToRun, int amountOfSimulations)
	{
		// Set fields
		this.timeUnitsToRun = timeUnitsToRun;
		this.amountOfSimulations = amountOfSimulations;

	}
	
	public void Run()
	{
		for(int amountOfReplicationsRun = 0; amountOfReplicationsRun < amountOfSimulations; amountOfReplicationsRun++)
		{
			Model model = new Model(timeUnitsToRun);
			model.Run();
			replications.add(model);
		}
	}
	
}

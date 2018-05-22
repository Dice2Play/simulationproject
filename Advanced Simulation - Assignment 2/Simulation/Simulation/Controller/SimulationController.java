package Simulation.Controller;

import Simulation.Model.SimulationModel;
import Simulation.View.SimulationView;

public class SimulationController {

	private final SimulationModel simModel;
	private final SimulationView simView;
	
	public SimulationController(SimulationModel simModel, SimulationView simView )
	{
		this.simModel = simModel;
		this.simView = simView;
	}
	
}

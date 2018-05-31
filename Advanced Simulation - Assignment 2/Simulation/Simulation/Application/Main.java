package Simulation.Application;

import Simulation.Controller.SimulationController;
import Simulation.Model.SimulationModel;
import Simulation.View.SimulationView;

public class Main {

	public static void main(String[] args) {
		
		SimulationView simView =  new SimulationView();
		SimulationModel simModel = new SimulationModel();
		SimulationController simController = new SimulationController(simModel, simView);
                
                
                
                
		simView.setVisible(true);
		
	}

}

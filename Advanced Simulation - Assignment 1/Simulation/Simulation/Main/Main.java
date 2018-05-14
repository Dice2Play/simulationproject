package Simulation.Main;

import Simulation.Results.ResultManager;

public class Main {


	public static void main(String[] args) {

		boolean useSingleQueue = true;
		Scenario scenario_1 = new Scenario(10000, 3, useSingleQueue);
		scenario_1.Run();
		
		
		ResultManager.ShowAllResults();	

	}

}

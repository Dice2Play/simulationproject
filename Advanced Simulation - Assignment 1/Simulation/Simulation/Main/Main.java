package Simulation.Main;

import Simulation.Results.ResultManager;

public class Main {


	public static void main(String[] args) {

		Scenario scenario_1 = new Scenario(60,2);
		scenario_1.Run();
		
		
		ResultManager.ShowAllResults();	

	}

}

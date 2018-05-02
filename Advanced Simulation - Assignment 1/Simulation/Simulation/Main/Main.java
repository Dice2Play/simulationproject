package Simulation.Main;

import Simulation.Results.Results;

public class Main {


	public static void main(String[] args) {

		Scenario scenario_1 = new Scenario(60,1);
		scenario_1.Run();
		
		
		Results.ShowResults();
		
		System.out.println("End");
		
		

	}

}

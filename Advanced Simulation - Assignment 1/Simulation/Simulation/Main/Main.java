package Simulation.Main;

import java.util.Random;

import Simulation.Results.ResultManager;
import Statistics.ExponentialDistribution;

public class Main {


	public static void main(String[] args) {


		Scenario scenario_1 = new Scenario(100,1);
		scenario_1.Run();
		
		
		ResultManager.ShowAllResults();	

	}

}

package Simulation.Main;

import java.util.Random;

import Simulation.Results.ResultManager;
import Statistics.ExponentialDistribution;

public class Main {


	public static void main(String[] args) {

		double sum = 0;
		
		for(int index = 0; index < 15; index++)
		{
			sum = sum + Probability.Probability.GetDistributionResult(new ExponentialDistribution(15,new Random()));
		}
		
		System.out.println(sum);
		Scenario scenario_1 = new Scenario(100,1);
		scenario_1.Run();
		
		
		ResultManager.ShowAllResults();	

	}

}

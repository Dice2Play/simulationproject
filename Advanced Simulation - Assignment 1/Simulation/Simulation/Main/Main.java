package Simulation.Main;

public class Main {

	final Results results = new Results();
			
	
	
	public static void main(String[] args) {

		Scenario scenario_1 = new Scenario(60,1);
		scenario_1.Run();
		
		
		System.out.println("End");

	}

}

package simulation.time.test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import simulation.time.TimeManager;

class Simulation_Time_Test_GetDifference {

	
	private static boolean hasSetupAlreadyRan =  false;
	
	@BeforeEach
	void setUp() throws Exception {
		
		if(hasSetupAlreadyRan) {return;}
		
		// Increment time to day 5

		for(int i = 0, amountOfDaysToRun = 5; i < amountOfDaysToRun; i++)
		{
			
			for(int ii = 0; ii <= TimeManager.GetInstance().Get_AMOUNT_OF_DISCRETE_DAILY_TIME_EVENTS(); ii++)
			{
				TimeManager.GetInstance().Tick();
			}	
		}
		
		
		hasSetupAlreadyRan = true;
		
	}

	
	
	
	@Test
	void test_zero_difference() {
		
		double inputTime = 0;
		int inputDay = 5;
		
		double difference = TimeManager.GetInstance().GetDifference(inputTime, inputDay);
		
		assertEquals(0,difference);
		
	}
	
	@Test
	void test_negative_difference() {
		
		double inputTime = 6;
		int inputDay = 6;
		
		double difference = TimeManager.GetInstance().GetDifference(inputTime, inputDay);
		
		assertEquals(-18,difference);
		
	}
	
	@Test
	void test_positive_difference() {
		
		double inputTime = 4;
		int inputDay = 4;
		
		double difference = TimeManager.GetInstance().GetDifference(inputTime, inputDay);
		
		assertEquals(8,difference);
		
	}

}

package simulation.statistics.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import statistics.PoissonDistribution;
import statistics.Statistics;

class PoissonArrivalDistribution {



	@Test
	void test() {
		
		double poissonResult = Statistics.GetDistributionResult(new PoissonDistribution(5.067, new Random()));
		
		if(poissonResult > 0 )
		{
			System.out.println(String.format("Value was greater than 0 [%s]", poissonResult));
			System.out.println(String.format("Next car will arrive in [%s] minutes", (60/poissonResult)));
			System.out.println(String.format("Next car will arrive in [%s] hours", (60/poissonResult)/60));
			assertTrue(true);
		}
		
		else if(poissonResult == Integer.MAX_VALUE)
		{
			fail("A value of infinity has been generated");
		}
		
		else
			
		{
			fail("Something has gone wrong with generating poissondistribution value");
		}
		
	}

}

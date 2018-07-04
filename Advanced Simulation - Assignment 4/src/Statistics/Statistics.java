package Statistics;

public class Statistics {

	// Get mean
	public static double GetMean(Object[] values)
	{
		double sum = 0;
		
		// Calculate sum
		for(Object val: values){sum = sum + (double)val;}
		
		// Divide the sum by the amount of values and return
		return (sum/values.length);
	}


	public static double GetDistributionResult(Distribution distribution)
	{
		return distribution.nextRandom();
	}
	
	// Get CI
	
	
	
}

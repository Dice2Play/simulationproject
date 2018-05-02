package Statistics;

public class Statistics {

	// Get mean
	public static double GetMean(Double[] values)
	{
		double sum = 0;
		
		// Calculate sum
		for(double val: values)
		{
			sum = sum + val;
		}
		
		// Divide the sum by the amount of values and return
		return (sum/values.length);
	}
	
	// Get CI
	
	
	
}

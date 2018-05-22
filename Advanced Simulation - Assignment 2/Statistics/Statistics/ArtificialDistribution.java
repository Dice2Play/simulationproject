package Statistics;

public class ArtificialDistribution  extends Distribution {

	
	/*
	 * Parameters 
	 * result: user input of possible output values
	 * probability: the probability of each result, sum of probability cannot be higher than 1, any individual value cannot be lower than 0.
	 */
	
	private double[] results;
	private double[] probabilities;
	
	public ArtificialDistribution(double[] results, double[] probabilities)
	{
		this.results = results;
		this.probabilities = probabilities;
	}
	
	
	@Override
	public double expectation() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double variance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double nextRandom() {
		
		
		double randomDoubleValue = random.nextDouble(); // generates a double value 0 < x < 1 
		double cumulativeDoubleValue = 0;
		int counter = 0;
		for(double probability : probabilities)
		{
			cumulativeDoubleValue = cumulativeDoubleValue + probability;
			
			if(randomDoubleValue <= cumulativeDoubleValue)
			{
				return results[counter];
			}
			
			counter++;
		}
		
		
		
		return 0;
	}

}

package Probability;

public class Probability {
	static java.util.Random rnd = new java.util.Random();

	/** Randomly chooses a value with respect to an array of probabilities for each value (=index).
	 * @param probabilities: Array of probabilities, probabilities should sum up to 1 and should be positive.
	 *                 Each index corresponds to a particular value.
	 * @return randomly chosen value in range [0,probabilities.length> with respect to probabilities.
	 * @throws Exception
	 */
	public static int get_random_index_using_probabilities(double[] probabilities) throws Exception
	{
		double val = rnd.nextDouble();
		double cumulative = 0;
		int i;
		int index = -1;

		for (i = 0; i < probabilities.length; i++) {
			if (!(probabilities[i] >= 0)) {
				throw new Exception("Check the argument probabilities.");
			}
			cumulative += probabilities[i];
			/* if we have selected the resulting event_index, we will not do it again */
			if (cumulative >= val && index == -1) {
				/* note: max of cumulative and val are 1. */
				index = i;
			}
		}

		/* we also added up all probabilities to verify corrects of the input array */
		if(!(Math.abs(cumulative - 1) <= 0.0000001) && (index != -1)) {
			throw new Exception("Check the argument probabilities.");
		};
		return index;
	}

}

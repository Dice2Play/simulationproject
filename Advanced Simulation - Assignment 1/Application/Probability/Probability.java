package Probability;

import java.util.Random;

public class Probability {

	
	public static boolean GetProbability(double chance) throws Exception
	{
		if(chance > 1 || chance < 0) {throw new Exception("Chance cannot be bigger than 1 or smaller than 0.");}
		return (new Random().nextDouble() < chance);
	}
	
}

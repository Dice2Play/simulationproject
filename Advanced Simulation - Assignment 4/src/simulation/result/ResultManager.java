package simulation.result;

import java.util.ArrayList;

public class ResultManager {
	private static ResultManager resultManager = null;
	
	private double meanLeftRate;
	private double meanWaitingRateUnder6Hours;
	private double meanCallsUnder2TimesCleaningTime;
	private double meanProcessingTime;
	
	public static ResultManager GetInstance()
	{
		if(resultManager == null)
		{
			resultManager = new ResultManager(); 
		}
		
		return resultManager;
	}
	
	public void SetMeanLeftRate(double meanLeftRate)
	{
		this.meanLeftRate = meanLeftRate;
	}
	
	public void SetMeanWaitingRateUnder6Hours(double meanWaitingRateUnder6Hours)
	{
		this.meanWaitingRateUnder6Hours =meanWaitingRateUnder6Hours; 
	}
	
	public void SetMeanCallsUnder2TimesCleaningTime(double meanCallsUnder2TimesCleaningTime)
	{
		this.meanCallsUnder2TimesCleaningTime = meanCallsUnder2TimesCleaningTime;
	}
	
	public void SetMeanProcessingTime(double meanProcessingTime)
	{
		this.meanProcessingTime = meanProcessingTime;
	}
	
	

	public void PrintResults()
	{
		System.out.println("\n=================== PRINT RESULTS OF RUN =====================");
		
		System.out.printf("%1$-50s %2$.2f \n", "MEAN LEFT RATE", GetMeanLeftRate());
		System.out.printf("%1$-50s %2$.2f \n", "MEAN WAITING RATE UNDER 6 HOURS", GetMeanWaitingTimeUnder6Hours());
		System.out.printf("%1$-50s %2$.2f \n", "MEAN CALLS UNDER 2x CLEANING TIME", GetMeanCallsUnder2XCleaningTime());
		System.out.printf("%1$-50s %2$.2f \n", "MEAN PROCESSING TIME", GetMeanProcessingTimeOfCars());
		
		System.out.println("==============================================================\n");
	}
	
 
	/** All the method are per run!
	 * the people left divide the total entities appear in the system per run 
	 * @return
	 */
	 private double GetMeanLeftRate()
	 {
		 return meanLeftRate;
	 }
	 
	 /** the goal is at most 10% of the customers get the phone call more than 6 working hours after they brought the car in. 
	  * arrivalTime- call time(in 1 day)<6  in 2 days need to check as well, first check wether in 1 day or not.
	  * @return
	  */
	 private double GetMeanWaitingTimeUnder6Hours()
	 {
		 return meanWaitingRateUnder6Hours;
	 }
	 
	 /**tollerance : 10 % 
	  * if the cleaning time of car < (arrival time - call time)/2, add to the unsatisfied cs, otherwise add count to the satisfied cs.
	  * @return the rate. 
	  */
	 private double GetMeanCallsUnder2XCleaningTime()
	 {
		 return meanCallsUnder2TimesCleaningTime;
	 }
	 
	 /**
	  * For each car we record its stay time in the car wash shop, then we get the avg nr per run(need sum of car each run)
	  * by each run the avg nr we add to the double record list.
	  * the time from the customer drop the car till the car finished and left
	  * @return the list of record for each car
	  */
	 private double GetMeanProcessingTimeOfCars()
	 {
		 return meanProcessingTime;
	 }


	public void Reset() {
		resultManager = null;
	}
	 
}

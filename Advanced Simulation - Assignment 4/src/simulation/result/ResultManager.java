package simulation.result;

import java.util.ArrayList;

public class ResultManager {
	private static ResultManager resultManager = null;
	private ArrayList<Result> results = new ArrayList<Result>();
	
	
	public static ResultManager GetInstance()
	{
		if(resultManager == null)
		{
			resultManager = new ResultManager(); 
		}
		
		return resultManager;
	}
	
	
	public void AddResult(Result resultToAdd)
	{
		results.add(resultToAdd);
	}
	
	public void PrintResults()
	{
		System.out.println("=================== PRINT RESULTS OF RUN =====================");
		
		System.out.printf("%1$-50s %2$.2f \n", "MEAN LEFT RATE", MeanLeftRate());
		System.out.printf("%1$-50s %2$.2f \n", "MEAN WAITING RATE UNDER 6 HOURS", MeanWaitingTimeUnder6Hours());
		System.out.printf("%1$-50s %2$.2f \n", "MEAN CALLS UNDER 2x CLEANING TIME", MeanCallsUnder2XCleaningTime());
		System.out.printf("%1$-50s %2$.2f \n", "MEAN PROCESSING TIME", MeanProcessingTimeOfCars());
		
		System.out.println("==============================================================");
	}
	
 
	/** All the method are per run!
	 * the people left divide the total entities appear in the system per run 
	 * @return
	 */
	 public double MeanLeftRate()
	 {
		 return 0.5;
	 }
	 
	 /** the goal is at most 10% of the customers get the phone call more than 6 working hours after they brought the car in. 
	  * arrivalTime- call time(in 1 day)<6  in 2 days need to check as well, first check wether in 1 day or not.
	  * @return
	  */
	 public double MeanWaitingTimeUnder6Hours()
	 {
		 return 0.5;
	 }
	 
	 /**tollerance : 10 % 
	  * if the cleaning time of car < (arrival time - call time)/2, add to the unsatisfied cs, otherwise add count to the satisfied cs.
	  * @return the rate. 
	  */
	 public double MeanCallsUnder2XCleaningTime()
	 {
		 return 0.5;
	 }
	 
	 /**
	  * For each car we record its stay time in the car wash shop, then we get the avg nr per run(need sum of car each run)
	  * by each run the avg nr we add to the double record list.
	  * the time from the customer drop the car till the car finished and left
	  * @return the list of record for each car
	  */
	 public double MeanProcessingTimeOfCars()
	 {
		 return 0.5;
	 }


	public void Reset() {
		resultManager = null;
		results = new ArrayList<Result>();
	}
	 
}

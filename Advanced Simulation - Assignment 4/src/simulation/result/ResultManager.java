package simulation.result;

public class ResultManager {
	//initialize all the counters in stastic final mode 
	final static double[] processingTimeOfCarRecord = new double[1000000];
 public ResultManager()
 {
	 
 }
 
 /** 
  * the people left divide the total entities appear in the system per run 
  * @return
  */
 public double leftRate()
 {
	 return 0.5;
 }
 /** the goal is at most 10% of the customers get the phone call more than 6 working hours after they brought the car in. 
  * arrivalTime- call time(in 1 day)<6  in 2 days need to check as well, first check wether in 1 day or not.
  * @return
  */
 public double waitingRateUnder6hrs()
 {
	 return 0.5;
 }
 /**tollerance : 10 % 
  * if the cleaning time of car < (arrival time - call time)/2, add to the unsatisfied cs, otherwise add count to the satisfied cs.
  * @return the rate. 
  */
 public double cleaningTimeX2GetCalled()
 {
	 return 0.5;
 }
 /**
  * For each car we record its stay time in the car wash shop, the time from the customer drop the car till the car finished and left
  * @return the list of record for each car
  */
 public double[] getProcessingTimeOfCar()
 {
	 return processingTimeOfCarRecord;
 }
}

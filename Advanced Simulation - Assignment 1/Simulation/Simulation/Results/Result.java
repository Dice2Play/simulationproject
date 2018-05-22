package Simulation.Results;



public class Result {

	// General
	private final double waitingTimeArbitraryCustomer;
	private final double totalQueueLength;
	private final double boatOccupancy;
	
	
	public Result(	double waitingTimeArbitraryCustomer, 
					double totalQueueLength,
					double boatOccupancy)
	{
		this.waitingTimeArbitraryCustomer = waitingTimeArbitraryCustomer;
		this.totalQueueLength = totalQueueLength;
		this.boatOccupancy = boatOccupancy;
	}
	
	public String GetFields()
	{
		return  "waitingTimeArbitraryCustomer ["+waitingTimeArbitraryCustomer+"] " + 
				"totalQueueLength ["+totalQueueLength+"] " +
				"boatOccupancy ["+boatOccupancy+"] ";
	}

	public double GetWaitingTimeArbitraryCustomer() {
		return waitingTimeArbitraryCustomer;
	}

	public double GetTotalQueueLength() {
		return totalQueueLength;
	}

	public double GetBoatOccupancy() {
		return boatOccupancy;
	}
	

}



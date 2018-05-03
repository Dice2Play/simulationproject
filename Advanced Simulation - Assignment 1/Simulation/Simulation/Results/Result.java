package Simulation.Results;

public class Result {

	// General
	private final int waitingTimeArbitraryCustomer;
	private final int totalQueueLength;
	private final double boatOccupancy;
	
	
	public Result(	int waitingTimeArbitraryCustomer, 
					int totalQueueLength,
					double boatOccupancy)
	{
		this.waitingTimeArbitraryCustomer = waitingTimeArbitraryCustomer;
		this.totalQueueLength = totalQueueLength;
		this.boatOccupancy = boatOccupancy;
	}
	
	public void Print()
	{
		
	}

	public int GetWaitingTimeArbitraryCustomer() {
		return waitingTimeArbitraryCustomer;
	}

	public int GetTotalQueueLength() {
		return totalQueueLength;
	}

	public double GetBoatOccupancy() {
		return boatOccupancy;
	}
	
	
}



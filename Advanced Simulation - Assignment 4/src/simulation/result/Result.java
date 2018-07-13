package simulation.result;

public class Result {

	final double LEFT_RATE,
				 WAITING_TIME_UNDER_6_HOURS,
				 CALLS_UNDER_2X_CLEANING_TIME,
				 PROCESSING_TIME,
				 RECORDED_DAY,
				 RECORDED_TIME;
	
	public Result(double leftRate, double waitingTimeUnder6Hours, double callsUnder2xCleaningTime, double processingTime, double recordedDay, double recordedTime)
	{
		this.LEFT_RATE = leftRate;
		this.WAITING_TIME_UNDER_6_HOURS = waitingTimeUnder6Hours;
		this.CALLS_UNDER_2X_CLEANING_TIME = callsUnder2xCleaningTime;
		this.PROCESSING_TIME = processingTime;
		this.RECORDED_DAY = recordedDay;
		this.RECORDED_TIME = recordedTime;
	}
	
	public double GetLeftRate()
	{
		return LEFT_RATE;
	}
	
	public double GetWaitingTimeUnder6Hours()
	{
		return WAITING_TIME_UNDER_6_HOURS;
	}
	
	public double GetCallsUnder2xCleaningTime()
	{
		return CALLS_UNDER_2X_CLEANING_TIME;
	}
	
	public double GetProcessingTime()
	{
		return PROCESSING_TIME;
	}
	
	public double GetRecordedDay()
	{
		return RECORDED_DAY;
	}
	
	public double GetRecordedTime()
	{
		return RECORDED_TIME;
	}
}

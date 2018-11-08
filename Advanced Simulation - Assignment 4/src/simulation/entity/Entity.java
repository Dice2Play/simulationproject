package simulation.entity;

import simulation.process.SequenceObject;

public class Entity {

	private String ID;
	private SequenceObject currentSequenceObject;
	private boolean isAvailable = true;
	
	// Results
	private double arrivalTime;
	private double pickUpTime;
	private double calledTime;
	private double cleaningTime;
	private double finishTime;//terminating the process. could also be leaving the process directly.
	private boolean isFinished; // means it got fired by a terminate sequence object
	
	private double processingTime;
	private boolean isRejected;
	private boolean waitingRateUnder6Hours;
	private boolean calledUnderTwoTimesCleaningTime;
	
	
	public Entity(String ID)
	{
		this.ID = ID;
	}
	
	public String GetID()
	{
		return ID;
	}
	
	public SequenceObject GetCurrentSequenceObject()
	{
		return currentSequenceObject;
	}
	
	public void SetCurrentSequenceObject(SequenceObject currentSequenceObject)
	{
		this.currentSequenceObject = currentSequenceObject;
	}
	
	public boolean IsAvailable()
	{
		return isAvailable;
	}
	
	public void Seize()
	{
		isAvailable = false;
	}
	
	public void Release()
	{
		isAvailable = true;
	}
	
	public boolean IsRejected()
	{
		return isRejected;
	}
	
	public double GetProcessingTime()
	{
		return processingTime;
	}
	
	public boolean WaitingRateUnder6Hours()
	{
		return waitingRateUnder6Hours;
	}
	
	public boolean CalledUnderTwoTimesCleaningTime()
	{
		return calledUnderTwoTimesCleaningTime;
	}
	
	public void UpdateProcessingTime(double amountOfTimeToAdd)
	{
		processingTime+= amountOfTimeToAdd;
	}
	
	public void SetFinished()
	{
		isFinished = true;
	}
	
	public boolean IsFinished()
	{
		return isFinished;
	}
	public void setFinishTime(double TerminatedTime) {
		this.finishTime = TerminatedTime;
	}

	public double getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(double arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public double getPickUpTime() {
		return pickUpTime;
	}

	public void setPickUpTime(double pickUpTime) {
		this.pickUpTime = pickUpTime;
	}

	public double getCalledTime() {
		return calledTime;
	}

	public void setCalledTime(double calledTime) {
		this.calledTime = calledTime;
	}

	public double getCleaningTime() {
		return cleaningTime;
	}

	public void setCleaningTime(double cleaningTime) {
		this.cleaningTime = cleaningTime;
	}
	
}

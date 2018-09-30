package simulation.entity;

import java.util.ArrayList;

import simulation.process.SequenceObject;
import simulation.resource.Resource;
import simulation.time.TimeManager;

public class Entity {

	private String ID;
	private SequenceObject currentSequenceObject;
	private boolean isAvailable = true;
	private double startTimeSeize;
	private int startDaySeize;
	private ArrayList<Resource> assignedResources = new ArrayList<Resource>();
	
	// Results
	private double arrivalTime;
	private double calledTime;
	private double cleaningTime;
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
	
	private void UpdateProcessingTime(double amountOfTimeToAdd)
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

	public double GetArrivalTime() {
		return arrivalTime;
	}

	public void SetArrivalTime(double arrivalTime) {
		this.arrivalTime = arrivalTime;
	}


	public double GetCalledTime() {
		return calledTime;
	}

	public void SetCalledTime(double calledTime) {
		this.calledTime = calledTime;
	}

	public double GetCleaningTime() {
		return cleaningTime;
	}

	public void SetCleaningTime(double cleaningTime) {
		this.cleaningTime = cleaningTime;
	}

	public void AssignResource(Resource getAvailableResource) {
		assignedResources.add(getAvailableResource);
	}

	public int GetStartDaySeize() {
		return startDaySeize;
	}

	public void SetStartDaySeize(int startDaySeize) {
		this.startDaySeize = startDaySeize;
	}

	public double GetStartTimeSeize() {
		return startTimeSeize;
	}

	public void SetStartTimeSeize(double startTimeSeize) {
		this.startTimeSeize = startTimeSeize;
	}

	public ArrayList<Resource> GetAssignedResources() {
		return assignedResources;
	}

	public void Update() {
		double difference = TimeManager.GetInstance().GetDifference(startTimeSeize, startDaySeize);
		UpdateProcessingTime(difference);
		
	}
	
}

package Simulation.Model.Queue;

//import java.awt.geom.Arc2D.Double;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


import Simulation.Enums.Queue_Priority;
import Simulation.Enums.TimeManager_Subscriber;
import Simulation.Interfaces.Tick_Listener;
import Simulation.Model.Model;
import Simulation.Model.Queue.Behavior.IQueueGenerateBehavior;
import Simulation.Model.Time.TimeManager;
import Statistics.ArtificialDistribution;

public abstract class Queue implements Tick_Listener {

	private final Queue_Priority queueingPriority;
	protected final String queueID;
	protected IQueueGenerateBehavior generateQueueBehavior;
	protected LinkedList<QueueObject> queueObjects = new LinkedList<QueueObject>();
	protected TimeManager_Subscriber timeManagerSubscriberType = TimeManager_Subscriber.QUEUE;
	public static final List<Double> waitingTimeRecord = new ArrayList<Double>(); 
	
	public Queue(Queue_Priority queueingPriority, String queueID)
	{
		this.queueingPriority = queueingPriority;
		this.queueID = queueID;
		
		// Set listener
		TimeManager.AddTickListener(this);
	}
	
	void SeizeFirstQueueObject(int amountOfTimeToSeize)
	{
		// Get first object
		QueueObject firstQueueObject = FirstQueueObject();
		
		// Seize
		firstQueueObject.SeizeQueueObject(amountOfTimeToSeize);
		//assign the departure time of that object
		firstQueueObject.SetLeaveTime(TimeManager.GetTimeUnitsPassed());
		//Calautlate the waiting time of first object and record.
		double waitingTimeperCar = firstQueueObject.GetWaitingTime();
		// Remove from list
		//System.out.print("waiting time recored:"+ waitingTimeperCar+ "\n");
		Queue.waitingTimeRecord.add(waitingTimeperCar);
		//for varaince per run purpose:
		Model.delayRecordPerRun.add(waitingTimeperCar);
		queueObjects.remove(firstQueueObject);	
		
	}
	
	Queue_Priority GetQueuePriority()
	{
		return queueingPriority;
	}
	
	// Return size of NextQueueObject
	int GroupSizeNextQueueObject()
	{
		return queueObjects.getFirst().GetGroupSize();
	}
	
	// Retrieve first QueueObject
	QueueObject FirstQueueObject()
	{
		return queueObjects.getFirst();
	}
	
	// Return if queue has another queueobject
	boolean HasNextQueueObject()
	{
		return !queueObjects.isEmpty();
	}
	
	void GenerateQueueObjects()
	{
		generateQueueBehavior.GenerateQueueObjects();
	}
	
	
	String GetID()
	{
		return queueID;
	}
	
	public TimeManager_Subscriber GetSubscriberType()
	{
		return timeManagerSubscriberType;
	}

	@Override
	public void Event_Tick(double timePassed)
	{
		// Generate queue objects according to their behavior
		GenerateQueueObjects();	
	}
	

	LinkedList<QueueObject> GetQueueObjectList()
	{
		return queueObjects;
	}
	
	
}

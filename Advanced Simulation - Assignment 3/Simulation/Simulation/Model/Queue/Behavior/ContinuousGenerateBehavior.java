package Simulation.Model.Queue.Behavior;

import java.util.LinkedList;
import java.util.Random;

import Simulation.Enums.Queue_Priority;
import Simulation.Model.Queue.QueueObject;
import Simulation.Model.Time.TimeEvent;
import Simulation.Model.Time.TimeManager;
import Statistics.ExponentialDistribution;

public class ContinuousGenerateBehavior extends  QueueGenerateBehavior{

	private final int rate;
	
	public ContinuousGenerateBehavior(String queueID, String queueObjectName, int rate, LinkedList<QueueObject> queueObjects)
	{
		super(queueID, queueObjects, queueObjectName);
		this.rate = rate;
	}
    /**
     * Cards arrival in the continous time, we generate cars object in a continues generation way.
     */
	@Override
	public void GenerateQueueObjects() {
		
		double timeTillThisEventMustOccur = Probability.Probability.GetDistributionResult(new ExponentialDistribution(rate,new Random()));
		double timeOnWhichThisObjectMustArrive = TimeManager.GetTimeUnitsPassed() + timeTillThisEventMustOccur;
		queueObjects.add(new QueueObject(1, queueID, timeOnWhichThisObjectMustArrive));
		ShowQueueObjectsAdded(1, timeOnWhichThisObjectMustArrive);
		TimeManager.AddEvent(new TimeEvent("Arrival of " + queueObjectName, timeOnWhichThisObjectMustArrive));
	}

}

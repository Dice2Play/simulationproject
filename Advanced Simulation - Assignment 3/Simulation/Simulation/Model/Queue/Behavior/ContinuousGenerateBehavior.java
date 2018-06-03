package Simulation.Model.Queue.Behavior;

import java.util.LinkedList;
import java.util.Random;

import Simulation.Enums.Queue_Priority;
import Simulation.Interfaces.IQueueGenerateBehavior;
import Simulation.Model.Queue.QueueObject;
import Simulation.Model.Time.TimeEvent;
import Simulation.Model.Time.TimeManager;
import Statistics.ExponentialDistribution;

public class ContinuousGenerateBehavior extends  QueueGenerateBehavior{

	private final int rate;
	
	public ContinuousGenerateBehavior(String queueID, int rate, LinkedList<QueueObject> queueObjects)
	{
		super(queueID, queueObjects);
		this.rate = rate;
	}

	@Override
	public void GenerateQueueObjects() {
		
		double timeTillThisEventMustOccur = Probability.Probability.GetDistributionResult(new ExponentialDistribution(rate,new Random()));
		double timeOnWhichThisObjectMustArrive = TimeManager.GetTimeUnitsPassed() + timeTillThisEventMustOccur;

		TimeManager.AddEvent(new TimeEvent("Added new Queue-Object", timeOnWhichThisObjectMustArrive));
	}

}

package Simulation.Model.Queue;

import Simulation.Enums.Queue_Priority;
import Simulation.Model.Queue.Behavior.ContinuousGenerateBehavior;

public class ContinuousQueue extends Queue{

	public ContinuousQueue(Queue_Priority queueingPriority, int minGroupSize, double rate, String queueID, String queueObjectName) {
		super(queueingPriority,  queueID);
		generateQueueBehavior = new ContinuousGenerateBehavior(this.queueID, queueObjectName, rate,  queueObjects);
	}

}

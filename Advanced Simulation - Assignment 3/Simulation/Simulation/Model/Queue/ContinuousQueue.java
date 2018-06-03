package Simulation.Model.Queue;

import Simulation.Enums.Queue_Priority;
import Simulation.Model.Queue.Behavior.ContinuousGenerateBehavior;

public class ContinuousQueue extends Queue{

	public ContinuousQueue(Queue_Priority queueingPriority, int minGroupSize, int rate, String queueID) {
		super(queueingPriority,  queueID);
		generateQueueBehavior = new ContinuousGenerateBehavior(this.queueID, rate,  queueObjects);
	}

}

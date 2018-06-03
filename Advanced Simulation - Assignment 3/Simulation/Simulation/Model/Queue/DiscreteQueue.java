package Simulation.Model.Queue;

import java.util.LinkedList;

import Simulation.Enums.Queue_Priority;
import Simulation.Model.Queue.Behavior.DiscreteGenerateBehavior;

public class DiscreteQueue extends Queue{

	public DiscreteQueue(Queue_Priority queueingPriority, int minGroupSize, int maxGroupSize, String queueID) {
		super(queueingPriority,  queueID);
		generateQueueBehavior = new DiscreteGenerateBehavior(this.queueID, maxGroupSize,  queueObjects);
	}



}

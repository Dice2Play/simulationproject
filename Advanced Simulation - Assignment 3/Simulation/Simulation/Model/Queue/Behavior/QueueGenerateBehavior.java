package Simulation.Model.Queue.Behavior;

import java.util.LinkedList;

import Simulation.Interfaces.IQueueGenerateBehavior;
import Simulation.Model.Queue.QueueObject;

public abstract class QueueGenerateBehavior implements IQueueGenerateBehavior{

	protected final String queueID;
	protected LinkedList<QueueObject> queueObjects;
	
	public QueueGenerateBehavior(String queueID, LinkedList<QueueObject> queueObjects)
	{
		this.queueID = queueID;
		this.queueObjects = queueObjects;
	}
	
	void ShowQueueObjectsAdded(int amountOfQueueObjectsAdded)
	{
		System.out.println(queueID + ": Added " + amountOfQueueObjectsAdded + " Queue-Objects");
	}
	
}

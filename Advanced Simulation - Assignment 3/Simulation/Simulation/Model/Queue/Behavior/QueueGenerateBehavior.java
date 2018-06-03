package Simulation.Model.Queue.Behavior;

import java.util.LinkedList;

import Simulation.Model.Queue.QueueObject;

public abstract class QueueGenerateBehavior implements IQueueGenerateBehavior{

	protected final String queueID;
	protected LinkedList<QueueObject> queueObjects;
	protected final String queueObjectName;
	
	public QueueGenerateBehavior(String queueID, LinkedList<QueueObject> queueObjects, String queueObjectName)
	{
		this.queueID = queueID;
		this.queueObjects = queueObjects;
		this.queueObjectName = queueObjectName;
	}
	
	void ShowQueueObjectsAdded(int amountOfQueueObjectsAdded)
	{
		System.out.println(queueID + ": Added " + amountOfQueueObjectsAdded + " " + queueObjectName);
	}
	
}

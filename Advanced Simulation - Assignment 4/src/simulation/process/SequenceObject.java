package simulation.process;

import java.util.ArrayList;
import java.util.LinkedList;

import simulation.entity.Entity;
import simulation.process.behavior.CanFireBehavior;
import simulation.process.behavior.FireBehavior;
import simulation.process.behavior.NextSequenceBehavior;
import simulation.queue.Queue;
import simulation.queue.QueueManager;

public abstract class SequenceObject {

	private String ID;
	private Process_Priority processPriority;
	private Queue queue;
	private LinkedList<SequenceObject> linkedSequenceObjects = new LinkedList<SequenceObject>();
	protected boolean isSequenceObjectAvailable = true;
	
	
	protected CanFireBehavior canFireBehavior;
	protected FireBehavior fireBehavior;
	protected NextSequenceBehavior nextSequenceBehavior;
	

	
	public SequenceObject(String ID, Process_Priority processPriority)
	{
		this.ID = ID;
		this.processPriority = processPriority;
		
		// Create and set Queue
		Queue queue = new Queue(String.format("QUEUE - %s", ID));
		SetQueue(queue);
		
		// Register to Process Manager
		ProcessManager.GetInstance().RegisterSequenceObject(this);
	}
	
	public String GetID()
	{
		return ID;
	}
	
	public void SetSharedQueue(SequenceObject seqObjWhichQueueToUse)
	{
		// Get reference to current queue
		Queue currentQueue = GetQueue();
		
		// Set queue same as input sequenceObj one
		SetQueue(seqObjWhichQueueToUse.GetQueue());
		
		// Remove old queue from queue manager
		QueueManager.GetInstance().DeRegisterQueue(currentQueue);
	}
	
	public void AddEntityToQueue(Entity entityToAdd)
	{
		this.queue.AddEntity(entityToAdd);
	}
	
	public Process_Priority GetProcessPriority()
	{
		return processPriority;
	}
	
	private void SetQueue(Queue queue)
	{
		this.queue = queue;
	}
	
	/**
	 * Checks whether there is an entity is in the queue and whether or not this entity is meant for this process
	 * @return
	 */
	
	public boolean IsThereANextEntityFromQueueForCurrentProcess()
	{
		return queue.IsThereAnAvailableEntityInQueue();
	}
	
	
	/**
	 * Gets the first entity in queue 
	 * @return
	 * @throws Exception 
	 */
	public Entity GetFirstEntityFromQueue() throws Exception
	{
		return queue.GetFirstAvailableEntity();
	}
	
	void RemoveEntityFromQueue(Entity entityToRemove)
	{
		queue.RemoveEntity(entityToRemove);
	}

	
	public void AddNextSequenceLink(SequenceObject nextSeqLinkType)
	{
		linkedSequenceObjects.add(nextSeqLinkType);
	}
	

	public boolean CanFire()
	{
		return canFireBehavior.CanFire();
	}
	
	
	public void Fire()
	{
		fireBehavior.Fire();
	}
	
	public LinkedList<SequenceObject> GetLinkedSequenceObjects()
	{
		return linkedSequenceObjects;
	}

	public NextSequenceBehavior GetNextSequenceBehavior() {
		return nextSequenceBehavior;
	}
	
	public SequenceObject GetFirstAvailableSequenceObject() throws Exception
	{
		for(SequenceObject seqObj : linkedSequenceObjects)
		{
			if(seqObj.GetIsSequenceObjectAvailable())
			{
				return seqObj;
			}
		}
		
		throw new Exception("Assumption violated: No available sequenceobject.");
	}
	
	public boolean CheckForAvailableSequenceObject() 
	{
		for(SequenceObject seqObj : linkedSequenceObjects)
		{
			if(seqObj.GetIsSequenceObjectAvailable())
			{
				return true;
			}
		}
		
		return false;
	}


	public void RemoveFirstEntityFromQueue()
	{
		queue.RemoveFirstEntityFromQueue();
	}
	
	public Queue GetQueue()
	{
		return queue;
	}
	
	public boolean GetIsSequenceObjectAvailable()
	{
		return isSequenceObjectAvailable;
	}

	public abstract void Validate() throws Exception;
	

}

package simulation.process;

import java.util.ArrayList;
import java.util.LinkedList;

import simulation.entity.Entity;
import simulation.process.behavior.CanFireBehavior;
import simulation.process.behavior.NextSequence;
import simulation.queue.Queue;

public abstract class SequenceObject {

	private String ID;
	private Process_Priority processPriority;
	private Queue queue;
	LinkedList<NextSequence> linkedSequenceObjects = new LinkedList<NextSequence>();
	private CanFireBehavior canFireBehavior;


	
	public SequenceObject(String ID, Process_Priority processPriority)
	{
		this.ID = ID;
		this.processPriority = processPriority;
		
		// Register to Process Manager
		ProcessManager.GetInstance().RegisterSequenceObject(this);
	}
	
	public String GetID()
	{
		return ID;
	}
	
	public void AddEntityToQueue(Entity entityToAdd)
	{
		this.queue.AddEntity(entityToAdd);
	}
	
	public Process_Priority GetProcessPriority()
	{
		return processPriority;
	}
	
	public void SetQueue(Queue queue)
	{
		this.queue = queue;
	}
	
	boolean IsThereANextEntityFromQueue()
	{
		return queue.IsThereAnAvailableEntityInQueue();
	}
	
	
	/**
	 * Gets the next 
	 * @return
	 * @throws Exception 
	 */
	Entity GetNextEntityFromQueue() throws Exception
	{
		return queue.GetFirstAvailableEntity();
	}
	
	void RemoveEntityFromQueue(Entity entityToRemove)
	{
		queue.RemoveEntity(entityToRemove);
	}

	
	/**
	 * - Removes current entity from  queue
	 * - Adds current entity to next sequenceObject's queue
	 * 
	 */
	public abstract void SetNextSequenceObjectForEntity();
	
	
	public void AddNextSequenceLink(NextSequence nextSeqLinkType)
	{
		linkedSequenceObjects.add(nextSeqLinkType);
	}
	

	public abstract boolean CanFire();
	
	
	public void Fire() throws Exception
	{
		currentEntity = GetNextEntityFromQueue();
	}
	

}

package simulation.process;

import java.util.ArrayList;

import simulation.entity.Entity;
import simulation.process.behavior.NextSequence;
import simulation.queue.Queue;

public abstract class SequenceObject {

	private String ID;
	private Process_Priority processPriority;
	private Queue queue;
	ArrayList<NextSequence> linkedSequenceObjects = new ArrayList<NextSequence>();
	Entity currentEntity;

	
	public SequenceObject(String ID, Process_Priority processPriority)
	{
		this.ID = ID;
		this.processPriority = processPriority;
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
	 * - Removes entity from current queue
	 * - Adds entity to next sequenceObject's queue
	 * @param entity: entity which has to be transferred to next sequenceObject
	 */
	public abstract void SetNextSequenceObjectForEntity(Entity entity);
	
	
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

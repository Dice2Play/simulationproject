package simulation.process;

import java.util.ArrayList;

import simulation.entity.Entity;
import simulation.queue.Queue;

public abstract class SequenceObject {

	private String ID;
	private Process_Priority processPriority;
	private Queue queue;
	private ArrayList<SequenceObject> linkedSequenceObjects = new ArrayList<SequenceObject>();
	
	public SequenceObject(String ID, Process_Priority processPriority)
	{
		this.ID = ID;
		this.processPriority = processPriority;
	}
	
	public String GetID()
	{
		return ID;
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
		return queue.IsEntityInQueue();
	}
	
	Entity GetNextEntityFromQueue()
	{
		return queue.GetFirstEntity();
	}

	
	/**
	 * - Removes entity from current queue
	 * - Adds entity to next sequenceObject's queue
	 * @param entity: entity which has to be transferred to next sequenceObject
	 */
	abstract void SetNextSequenceObjectForEntity(Entity entity);
	
	public void AddNextSequenceLink(SequenceObject seqObject)
	{
		linkedSequenceObjects.add(seqObject);
	}

	public abstract boolean CanFire();
	
	public abstract void Fire();
}

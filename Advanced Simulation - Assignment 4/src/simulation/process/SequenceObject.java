package simulation.process;

import java.util.ArrayList;
import java.util.LinkedList;

import simulation.entity.Entity;
import simulation.process.behavior.CanFireBehavior;
import simulation.process.behavior.FireBehavior;
import simulation.process.behavior.NextSequenceBehavior;
import simulation.queue.Queue;

public abstract class SequenceObject {

	private String ID;
	private Process_Priority processPriority;
	private Queue queue;
	private LinkedList<SequenceObject> linkedSequenceObjects = new LinkedList<SequenceObject>();
	
	
	protected CanFireBehavior canFireBehavior;
	protected FireBehavior fireBehavior;
	protected NextSequenceBehavior nextSequenceBehavior;
	

	
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
	 * Gets the next entity in queue 
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
	

}

package simulation.queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import simulation.entity.Entity;
import simulation.process.ProcessManager;
import simulation.process.SequenceObject;

public class Queue {

	private LinkedList<Entity> entities = new LinkedList<Entity>();
	private String ID;
	
	public Queue(String ID)
	{
		this.ID = ID;
		
		// Register to Queue Manager
		QueueManager.GetInstance().RegisterQueue(this);
	}
	
	public void AddEntity(Entity entityToAdd)
	{
		this.entities.add(entityToAdd);
		System.out.println(String.format("Added entity %s to queue %s", entityToAdd.GetID(), GetID()));
	}
	
	public String GetID()
	{
		return ID;
	}
	
	public boolean IsThereAnAvailableEntityInQueue()
	{
		for(Entity entity : entities)
		{
			if(entity.IsAvailable()) {return true;}
		}
		
		// default value
		return false;
	}
	
	
	/**
	 * Assumption: there is at least one available entity in 'entities'
	 * @return
	 * @throws Exception 
	 */
	public Entity GetFirstAvailableEntityForSpecifiedSequenceObject() throws Exception
	{
		for(Entity entity : entities)
		{
			if(entity.IsAvailable()) {return entity;}
		}
		
		// Throw exception
		throw new Exception("Assumption has been violated, no available entity.");
	}
	
	public void RemoveEntity(Entity entityToRemove)
	{
		entities.remove(entityToRemove);
	}
	
	public void RemoveFirstEntityFromQueue()
	{
		entities.removeFirst();
	}
	

	
}

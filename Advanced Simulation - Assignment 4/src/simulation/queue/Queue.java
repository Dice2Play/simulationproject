package simulation.queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import simulation.entity.Entity;

public class Queue {

	private LinkedList<Entity> entities = new LinkedList<Entity>();
	private String ID;
	
	public Queue(String ID)
	{
		this.ID = ID;
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
	
	public boolean IsEntityInQueue()
	{
		return !entities.isEmpty();
	}
	
	public Entity GetFirstEntity()
	{
		return entities.getFirst();
	}
	
}

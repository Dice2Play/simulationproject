package simulation.queue;

import java.util.ArrayList;
import java.util.List;

import simulation.entity.Entity;

public class Queue {

	private List<Entity> entities = new ArrayList<Entity>();
	private String ID;
	
	public Queue(String ID)
	{
		this.ID = ID;
	}
	
	public void AddEntity(Entity entityToAdd)
	{
		this.entities.add(entityToAdd);
		System.out.println(String.format("Added entity %s to queue %s", entityToAdd.GetID()));
	}
	
	public String GetID()
	{
		return ID;
	}
}

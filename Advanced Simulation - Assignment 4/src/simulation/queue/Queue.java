package simulation.queue;

public class Queue {

	List<Entity> entities = new ArrayList<Entity>();
	
	public Queue(String ID)
	{
		
	}
	
	public void AddEntity(Entity entityToAdd)
	{
		this.entities.add(entityToAdd);
		System.out.println(String.format("Added entity %s to queue %s", args));
	}
	
}

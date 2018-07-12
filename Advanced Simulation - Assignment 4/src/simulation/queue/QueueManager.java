package simulation.queue;

import java.util.ArrayList;
import java.util.List;

public class QueueManager {

	List<Queue> queues = new ArrayList<Queue>();
	static QueueManager queueManager = null;
	
	public static QueueManager GetInstance()
	{
		if(queueManager == null)
		{
			queueManager = new QueueManager();
		}
		
		return queueManager;
	}
	
	public void RegisterQueue(Queue queueToRegister)
	{
		queues.add(queueToRegister);
		
		System.out.println(String.format("QUEUE MANAGER: Registered QUEUE %s",queueToRegister.GetID()));
	}
	
	public void Reset()
	{
		queueManager = null;
		queues = new ArrayList<Queue>();
	}
}

package simulation.queue;

import java.util.ArrayList;
import java.util.List;

public class QueueManager {

	static List<Queue> queues = new ArrayList<Queue>();
	
	
	public static void RegisterQueue(Queue queueToRegister)
	{
		queues.add(queueToRegister);
		
		System.out.println(String.format("QUEUE MANAGER: Registered %s",queueToRegister.));
	}
}

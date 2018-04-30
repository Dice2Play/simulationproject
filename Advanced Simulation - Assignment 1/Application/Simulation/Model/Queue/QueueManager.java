package Simulation.Model.Queue;

import java.util.ArrayList;
import java.util.List;

public class QueueManager {

	private static List<Queue> queues = new ArrayList<Queue>();
		
	public static void AddQueue(Queue[] queuesToAdd)
	{
		for(Queue q : queuesToAdd)
		{
			queues.add(q);
		}
	}
	
	
	
	
	
	
	
}

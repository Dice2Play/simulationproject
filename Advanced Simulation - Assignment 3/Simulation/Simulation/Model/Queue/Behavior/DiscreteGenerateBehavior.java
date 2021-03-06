package Simulation.Model.Queue.Behavior;

import java.util.LinkedList;

import Simulation.Model.Queue.QueueObject;
import Simulation.Model.Time.TimeManager;
import Statistics.ArtificialDistribution;

public class DiscreteGenerateBehavior extends  QueueGenerateBehavior{

	private final int maxGroupSize;
	
	public DiscreteGenerateBehavior(String queueID, String queueObjectName, int maxGroupSize, LinkedList<QueueObject> queueObjects ) {
		super(queueID, queueObjects, queueObjectName);
		this.maxGroupSize = maxGroupSize;
	}

	@Override
	public void GenerateQueueObjects() {
		// Check if group queue yes/no
				if(maxGroupSize > 1)
				{
					double[] amountOfPossibleGroups = {0,1,2};
					double[] probabilityAmountOfPossibleGroups = {0.2,0.6,0.2};
					
					int amountOfGroups = (int) Probability.Probability.GetDistributionResult(new ArtificialDistribution(amountOfPossibleGroups, probabilityAmountOfPossibleGroups)); 
								
					// For each group 	
					double[] possibleSizeOfGroups = {1,2,3,4,5};
					double[] probabilityPossibleSizeOfGroups = {0.2,0.2,0.2,0.2,0.2};
					
					for(int i = 0; i < amountOfGroups; i++)
					{
						int groupSize = (int) Probability.Probability.GetDistributionResult(new ArtificialDistribution(possibleSizeOfGroups, probabilityPossibleSizeOfGroups)); 
						queueObjects.add(new QueueObject(groupSize, queueID, TimeManager.GetTimeUnitsPassed()));
						ShowQueueObjectsAdded(groupSize,TimeManager.GetTimeUnitsPassed());
						
						
					}
				}
				
				else // Single rider queue
				{
					queueObjects.add(new QueueObject(1, queueID, TimeManager.GetTimeUnitsPassed()));
					ShowQueueObjectsAdded(1,TimeManager.GetTimeUnitsPassed());
				}
		
	}
 

}

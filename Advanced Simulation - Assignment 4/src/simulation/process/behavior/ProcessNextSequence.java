package simulation.process.behavior;

import Statistics.ArtificialDistribution;
import Statistics.Statistics;
import simulation.entity.Entity;
import simulation.process.DecisionBasedOnChance;
import simulation.process.SequenceObject;

public class ProcessNextSequence extends NextSequenceBehavior {

	public ProcessNextSequence(simulation.process.Process process) {
		super(process);
	}

	@Override
	public void SetNextSequenceObjectForEntity() {
		try
		{
			// Get next sequenceObject
			SequenceObject nextSequenceObject = currentSequenceObject.GetLinkedSequenceObjects().getFirst();
			
			// Set next sequenceObject for entity
			Entity currentEntity = ((simulation.process.Process)currentSequenceObject).GetSeizedEntity();
			currentEntity.SetCurrentSequenceObject(nextSequenceObject);
			
			// Adds entity to queue of next sequenceObject
			nextSequenceObject.AddEntityToQueue(currentEntity);
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}

}

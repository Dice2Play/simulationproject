package simulation.process.behavior;

import Statistics.ArtificialDistribution;
import Statistics.Statistics;
import simulation.entity.Entity;
import simulation.process.DecisionBasedOnChance;
import simulation.process.SequenceObject;

public class RegularNextSequence extends NextSequenceBehavior {

	public RegularNextSequence(SequenceObject currentSequenceObject) {
		super(currentSequenceObject);
	}

	@Override
	public void SetNextSequenceObjectForEntity() {
		try
		{
			// Get next sequenceObject
			SequenceObject nextSequenceObject = currentSequenceObject.GetLinkedSequenceObjects().getFirst();
			
			// Set next sequenceObject for entity
			Entity currentEntity = currentSequenceObject.GetNextEntityFromQueue();
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

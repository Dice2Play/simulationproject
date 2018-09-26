package simulation.process.behavior;

import simulation.entity.Entity;
import simulation.process.DecisionBasedOnChance;
import simulation.process.SequenceObject;
import statistics.ArtificialDistribution;
import statistics.Statistics;

public class RegularNextSequence extends NextSequenceBehavior {

	public RegularNextSequence(SequenceObject currentSequenceObject) {
		super(currentSequenceObject);
	}

	@Override
	public void SetNextSequenceObjectForEntity(Entity entityForNextSequence) {
		try
		{
			// Get next sequenceObject
			SequenceObject nextSequenceObject = currentSequenceObject.GetLinkedSequenceObjects().getFirst();
			
			// Set next sequenceObject for entity
			entityForNextSequence.SetCurrentSequenceObject(nextSequenceObject);
			
			// Adds entity to queue of next sequenceObject
			nextSequenceObject.AddEntityToQueue(entityForNextSequence);
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}

}

package simulation.process.behavior;

import java.util.ArrayList;

import simulation.entity.Entity;
import simulation.process.DecisionBasedOnChance;
import simulation.process.SequenceObject;
import statistics.ArtificialDistribution;
import statistics.Statistics;

public class NextSequenceBasedOnChance extends NextSequenceBehavior {

	public NextSequenceBasedOnChance(DecisionBasedOnChance decisionBasedOnChance) {
		super(decisionBasedOnChance);
	}

	@Override
	public void SetNextSequenceObjectForEntity(Entity entityForNextSequence) {
		
		try
		{
			// Artificial distribution parameters
			double[] possibleOutcomes = new double[] {0,1}; 
			double[] probabilityOfPossibleOutcomes = new double[] {((DecisionBasedOnChance) currentSequenceObject).GetChanceForFirstSequenceLink(), ((DecisionBasedOnChance) currentSequenceObject).GetchanceForSecondSequenceLink()};
					
			
			// Get index of sequenceObject that has been chosen.
			SequenceObject chosenSequenceObject = currentSequenceObject.GetLinkedSequenceObjects().get((int) Statistics.GetDistributionResult(new ArtificialDistribution(possibleOutcomes, probabilityOfPossibleOutcomes)));
			
			// Set next sequenceObject for entity
			entityForNextSequence.SetCurrentSequenceObject(chosenSequenceObject);
			
			// Adds entity to queue of next sequenceObject
			chosenSequenceObject.AddEntityToQueue(entityForNextSequence);
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		
		
	}

}

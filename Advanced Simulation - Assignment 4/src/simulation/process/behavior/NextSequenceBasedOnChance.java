package simulation.process.behavior;

import java.util.ArrayList;

import Statistics.ArtificialDistribution;
import Statistics.Statistics;
import simulation.entity.Entity;
import simulation.process.DecisionBasedOnChance;
import simulation.process.SequenceObject;

public class NextSequenceBasedOnChance extends NextSequenceBehavior {

	public NextSequenceBasedOnChance(DecisionBasedOnChance decisionBasedOnChance) {
		super(decisionBasedOnChance);
	}

	@Override
	public void SetNextSequenceObjectForEntity() {
		
		try
		{
			// Artificial distribution parameters
			double[] possibleOutcomes = new double[] {0,1}; 
			double[] probabilityOfPossibleOutcomes = new double[] {((DecisionBasedOnChance) currentSequenceObject).GetChanceForFirstSequenceLink(), ((DecisionBasedOnChance) currentSequenceObject).GetchanceForSecondSequenceLink()};
					
			
			// Get index of sequenceObject that has been chosen.
			SequenceObject chosenSequenceObject = currentSequenceObject.GetLinkedSequenceObjects().get((int) Statistics.GetDistributionResult(new ArtificialDistribution(possibleOutcomes, probabilityOfPossibleOutcomes)));
			
			// Set next sequenceObject for entity
			Entity currentEntity = currentSequenceObject.GetNextEntityFromQueue();
			currentEntity.SetCurrentSequenceObject(chosenSequenceObject);
			
			// Adds entity to queue of next sequenceObject
			chosenSequenceObject.AddEntityToQueue(currentEntity);
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		
		
	}

}

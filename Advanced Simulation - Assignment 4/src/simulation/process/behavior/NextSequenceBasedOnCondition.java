package simulation.process.behavior;

import simulation.entity.Entity;
import simulation.process.DecisionBasedOnCondition;
import simulation.process.SequenceObject;


public class NextSequenceBasedOnCondition extends NextSequenceBehavior{

	public NextSequenceBasedOnCondition(DecisionBasedOnCondition decisionBasedOnCondition) {
		super(decisionBasedOnCondition);
		
	}

	@Override
	public void SetNextSequenceObjectForEntity() {

		try
		{
			// If result is true take the first element of linked sequence objects, otherwise take the second one.
			SequenceObject chosenSequenceObject = ((DecisionBasedOnCondition)currentSequenceObject).GetConditionResult() ?
					currentSequenceObject.GetLinkedSequenceObjects().get(0) : currentSequenceObject.GetLinkedSequenceObjects().get(1); 
			
			
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

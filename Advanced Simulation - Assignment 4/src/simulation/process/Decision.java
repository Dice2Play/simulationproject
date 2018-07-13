package simulation.process;

import java.util.ArrayList;
import java.util.Random;

import Statistics.ArtificialDistribution;
import Statistics.NormalDistribution;
import Statistics.Statistics;
import simulation.entity.Entity;
import simulation.process.behavior.NextSequence;
import simulation.process.behavior.NextSequenceWithChance;
import simulation.time.TimeManager;

public class Decision extends SequenceObject{

	
	
	public Decision(String ID, Process_Priority processPriority) {
		super(ID, processPriority);
		
	}
	
	public Decision(String ID)
	{
		this(ID, Process_Priority.Normal);
	}



	@Override
	public boolean CanFire() {
		// TODO Auto-generated method stub
		return IsThereANextEntityFromQueue();
	}

	/**
	 * - Gets next entity from queue
	 * - Takes entity through process
	 * - Removes entity from this queue
	 * @throws Exception 
	 */
	@Override
	public void Fire() throws Exception {
		
		super.Fire();
		SetNextSequenceObjectForEntity();
		RemoveEntityFromQueue(currentEntity);
		SetCurrentEntityToNull();
	}

	/**
	 * Does the following:
	 * - the new currentProcess for entity
	 * - adds entity to queue of next sequence object
	 */
	public void SetNextSequenceObjectForEntity() {
		
		
		// Artificial distribution parameters
		ArrayList<Double> possibleOutcomesList = new ArrayList<Double>();
		ArrayList<Double> probabilityOfPossibleOutcomesList = new ArrayList<Double>();
		
		for(NextSequence nextSeq: linkedSequenceObjects)
		{
			// Cast to nextSequenceWithChance
			possibleOutcomesList.add((double) linkedSequenceObjects.indexOf(nextSeq));
			probabilityOfPossibleOutcomesList.add(((NextSequenceWithChance) nextSeq).GetChance());
		}
		
		double[] possibleOutcomes = possibleOutcomesList.stream().mapToDouble(d -> d).toArray();
		double[] probabilityOfPossibleOutcomes = probabilityOfPossibleOutcomesList.stream().mapToDouble(d -> d).toArray();
		
		
		// Get index of sequenceObject that has been chosen.
		SequenceObject chosenSequenceObject = linkedSequenceObjects.get((int) Statistics.GetDistributionResult(new ArtificialDistribution(possibleOutcomes, probabilityOfPossibleOutcomes))).GetNextSequenceObject();
		
		// Set next sequenceObject for entity
		currentEntity.SetCurrentSequenceObject(chosenSequenceObject);
		
		// Adds entity to queue of next sequenceObject
		chosenSequenceObject.AddEntityToQueue(currentEntity);
	}


	
	
}

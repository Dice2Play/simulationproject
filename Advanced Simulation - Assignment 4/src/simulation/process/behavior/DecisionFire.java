package simulation.process.behavior;

import simulation.entity.Entity;
import simulation.process.Decision;

public class DecisionFire extends FireBehavior{

	private Decision currentDecision;
	
	public DecisionFire(Decision decision)
	{
		this.currentDecision = decision;
	}

	@Override
	public void Fire() {
	
		try
		{
			// Get first entity from queue
			Entity firstEntityFromQueue = currentDecision.GetFirstEntityFromQueue();
			currentDecision.GetNextSequenceBehavior().SetNextSequenceObjectForEntity(firstEntityFromQueue);
			currentDecision.RemoveFirstEntityFromQueue();
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		
	}
	
	
}

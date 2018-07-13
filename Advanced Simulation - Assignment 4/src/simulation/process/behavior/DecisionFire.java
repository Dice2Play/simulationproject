package simulation.process.behavior;

import simulation.process.Decision;

public class DecisionFire extends FireBehavior{

	private Decision currentDecision;
	
	public DecisionFire(Decision decision)
	{
		this.currentDecision = decision;
	}

	@Override
	public void Fire() {
		currentDecision.GetNextSequenceBehavior().SetNextSequenceObjectForEntity();
		currentDecision.RemoveFirstEntityFromQueue();
	}
	
	
}

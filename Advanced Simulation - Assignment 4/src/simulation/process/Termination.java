package simulation.process;

import simulation.process.behavior.CanFireEntity;
import simulation.process.behavior.DecisionFire;
import simulation.process.behavior.NextSequenceBasedOnChance;
import simulation.process.behavior.NextSequenceTerminate;
import simulation.process.behavior.TerminateFire;

public class Termination extends SequenceObject {

	public Termination(String ID) {
		super(ID, Process_Priority.Normal);
		nextSequenceBehavior = new NextSequenceTerminate(this);
		canFireBehavior = new CanFireEntity(this);
		fireBehavior = new TerminateFire(this);
	}


}

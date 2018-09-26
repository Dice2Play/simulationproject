package simulation.process;

import simulation.entity.Entity;
import simulation.process.behavior.CanFireEntity;
import simulation.process.behavior.CanFireResourceAndEntity;
import simulation.process.behavior.RegularNextSequence;
import simulation.process.behavior.ReleaseFire;
import simulation.process.behavior.SeizeFire;
import simulation.resource.Resource;

public class Release extends SequenceObject{

	public Release(String ID, Process_Priority processPriority) {
		super(ID, processPriority);
		fireBehavior = new ReleaseFire(this);
		canFireBehavior = new CanFireEntity(this);
		nextSequenceBehavior = new RegularNextSequence(this);
	}

	@Override
	public void Validate() throws Exception {
		if(GetLinkedSequenceObjects().size() != 1){throw new Exception(String.format("VALIDATE MODEL ERROR: Exactly 1 Sequence Link need to be set for %s", this.GetID()));}
		
	}

	public void ReleaseResources(Entity entityToReleaseResourceFrom) throws Exception {
		for(Resource assignedResource : entityToReleaseResourceFrom.GetAssignedResources())
		{
			assignedResource.Release();
		}

	}

	public void UpdateEntity(Entity entityToUpdate) throws Exception {
		entityToUpdate.Update();
	}

}


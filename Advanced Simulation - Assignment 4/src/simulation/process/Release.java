package simulation.process;

import simulation.resource.Resource;

public class Release extends SequenceObject{

	public Release(String ID, Process_Priority processPriority) {
		super(ID, processPriority);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Validate() throws Exception {
		if(GetLinkedSequenceObjects().size() != 1){throw new Exception(String.format("VALIDATE MODEL ERROR: Exactly 1 Sequence Link need to be set for %s", this.GetID()));}
		
	}

	public void ReleaseResources() throws Exception {
		for(Resource assignedResource : GetNextEntityFromQueue().GetAssignedResources())
		{
			assignedResource.Release();
		}

	}

	public void UpdateEntity() throws Exception {
		GetNextEntityFromQueue().Update();
	}

}


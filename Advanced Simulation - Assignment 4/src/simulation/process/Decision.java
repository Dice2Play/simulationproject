package simulation.process;


public abstract class Decision extends SequenceObject{

	
	public Decision(String ID, Process_Priority processPriority) {
		super(ID, processPriority);	
	}
	
	public Decision(String ID)
	{
		this(ID, Process_Priority.Normal);
	}

	@Override
	public void Validate() throws Exception {
		if(GetLinkedSequenceObjects().size() != 2)
		{
			throw new Exception(String.format("VALIDATE MODEL ERROR: No two next SequenceLink has been set for %s", this.GetID()));
		}
		
		if(GetQueue() == null) {throw new Exception(String.format("VALIDATE MODEL ERROR: No Queue has been set for %s", this.GetID()));}
		
	}





	
	
}

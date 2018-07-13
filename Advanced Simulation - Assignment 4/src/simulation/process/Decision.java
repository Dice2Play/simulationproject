package simulation.process;


public class Decision extends SequenceObject{

	
	
	public Decision(String ID, Process_Priority processPriority) {
		super(ID, processPriority);
		
	}
	
	public Decision(String ID)
	{
		this(ID, Process_Priority.Normal);
	}





	
	
}

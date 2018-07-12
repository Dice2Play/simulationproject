package simulation.process;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ProcessManager {

	static ProcessManager processManager = null;
	LinkedList<SequenceObject> sequenceObjects = new LinkedList<SequenceObject>();
	
	
	private void OrderByPriorityCode()
	{
		sequenceObjects.sort(new SequenceObjectPriorityComparator());
	}
	
	public void Reset()
	{
		// Delete all sequenceObjects
		sequenceObjects = new LinkedList<SequenceObject>();
		
		// Set process manager to null
		processManager = null;
	}
	
	
	public static ProcessManager GetInstance()
	{
		if(processManager == null)
		{
			processManager = new ProcessManager();
		}
		
		return processManager;
	}
	
	void RegisterSequenceObject(SequenceObject sequenceObjectToAdd)
	{
		sequenceObjects.add(sequenceObjectToAdd);
		
		System.out.printf("PROCESS MANAGER: Registered SEQUENCEOBJECT %s \n", sequenceObjectToAdd.GetID());
	}

	
	
	public boolean CanFire() {
		for(SequenceObject sequenceObject: sequenceObjects)
		{
			if(sequenceObject.CanFire())
			{
				return true;
			}
		}
		return false;
	}

	public void Fire() throws Exception {
		// Sort by priority
		OrderByPriorityCode();
		
		// Get first sequence object that CAN fire, from list
		SequenceObject seqObject = GetFirstCanFireSequenceObject();
		seqObject.Fire();
		System.out.printf("Process %s has fired \n", seqObject.GetID());
				
	}
	
	/**
	 * Assumption1: There is an sequenceObject which can fire.
	 * Assumption2: sequenceObjects list is ordered by priority.
	 * @return
	 * @throws Exception 
	 */
	private SequenceObject GetFirstCanFireSequenceObject() throws Exception
	{
		for(SequenceObject sequenceObject: sequenceObjects)
		{
			if(sequenceObject.CanFire()) {return sequenceObject;}
		}
		
		throw new Exception("Assumption violated, there is no sequenceObject which can fire");
		
	}
	
	
	private class SequenceObjectPriorityComparator implements Comparator<SequenceObject>
	{

		@Override
		public int compare(SequenceObject seqObj1, SequenceObject seqObj2) {
			return (seqObj1.GetProcessPriority().getLevelCode() - seqObj2.GetProcessPriority().getLevelCode());
		}
		
	}
}

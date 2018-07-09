package simulation.process;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProcessManager {

	static ProcessManager processManager = null;
	List<SequenceObject> sequenceObjects = new ArrayList<SequenceObject>();
	
	
	private ProcessManager()
	{
		
	}
	
	private void OrderByPriorityCode()
	{
		sequenceObjects.sort(new SequenceObjectPriorityComparator());
	}
	
	public static ProcessManager GetInstance()
	{
		if(processManager == null)
		{
			processManager = new ProcessManager();
		}
		
		return processManager;
	}
	
	public void AddProcess(Process processToAdd)
	{
		sequenceObjects.add(processToAdd);
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

	public void Fire() {
		// Sort by priority
		OrderByPriorityCode();
		
		for(SequenceObject seqObj : sequenceObjects)
		{
			seqObj.Fire();
		}
		
	}
	
	private class SequenceObjectPriorityComparator implements Comparator<SequenceObject>
	{

		@Override
		public int compare(SequenceObject seqObj1, SequenceObject seqObj2) {
			return (seqObj1.GetProcessPriority().getLevelCode() - seqObj2.GetProcessPriority().getLevelCode());
		}
		
	}
}

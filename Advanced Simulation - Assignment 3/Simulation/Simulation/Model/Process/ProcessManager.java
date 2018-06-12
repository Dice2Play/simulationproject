package Simulation.Model.Process;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Simulation.Enums.Resource_Type;
import Simulation.Model.Time.TimeManager;

public class ProcessManager {

	private static LinkedList<Process> processes = new LinkedList<Process>();
	private static Process currentRunningProcess;

	
	
	public static boolean CanFire() throws Exception
	{
		// If no currentRunningProcess is defined yet, get first
		if(getCurrentRunningProcess() == null)
		{
			setCurrentRunningProcess(getNextProcessToRun());
			StartProcess(getCurrentRunningProcess());
		}
		
		return getCurrentRunningProcess().CanFire();
	}
	
	private static void StartProcess(Process processToStart)
	{
		System.out.println(String.format("PROCESS MANAGER: Process %s started at %s", processToStart.GetID(), TimeManager.GetTimeUnitsPassed()));
		processToStart.Start();
	}
	public Process GetCurrentProcess()
	{
		return ProcessManager.getCurrentRunningProcess();
	}
	
	public static void Fire() throws Exception
	{
		// If no currentRunningProcess is defined yet, get first
		// If no process is running, get next process
		if(getCurrentRunningProcess() == null)
		{
			setCurrentRunningProcess(getNextProcessToRun());
			StartProcess(getCurrentRunningProcess());
		}
		
		getCurrentRunningProcess().Fire();
		System.out.println(String.format("PROCESS MANAGER: Process %s fired at %s", getCurrentRunningProcess().GetID(), TimeManager.GetTimeUnitsPassed()));	
	}
	
	public static boolean CheckIfCurrentProcessCanFinish()
	{
		// Check if currentProcess can be finished
		if(getCurrentRunningProcess().CanFinish()) { return true;}
		else return false;
	}
	
	public static void FinishCurrentProcess()
	{
		
		if(getCurrentRunningProcess().IsFinished()) {return;}
		
		
		System.out.println(String.format("PROCESS MANAGER: Process %s finished at %s", getCurrentRunningProcess().GetID(), TimeManager.GetTimeUnitsPassed()));
		
		//Stop and Reset process
		getCurrentRunningProcess().SetFinished();
		getCurrentRunningProcess().Stop();
		getCurrentRunningProcess().Reset();
		
		// Get next process
		try
		{
			setCurrentRunningProcess(getNextProcessToRun());
			StartProcess(getCurrentRunningProcess());
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	// Returns the next process to run
	private static Process getNextProcessToRun() throws Exception
	{
		// Check if empty, if so throw exception
		if(processes.isEmpty()) { throw new Exception("Their are no processes stored...");}
		
		
		// If there isn't any process currently running, get the first occurrence from list 'Processes'
		if(getCurrentRunningProcess() == null) { return processes.getFirst();}
		
		// If there is a process currently running then,
		// get index of last element in 'processes' list,
		// if this index is out of bounds, return the first occurrence from the list 'Processes'
		int indexOfCurrentRunningProcess = processes.indexOf(getCurrentRunningProcess());
		
		if((indexOfCurrentRunningProcess + 2) > processes.size())
		{ 
			return processes.getFirst();
		}
		
		else
		{
			return  processes.get(indexOfCurrentRunningProcess + 1);
		}
	}

	
	public static void AddProcess(Process process)
	{
			processes.add(process);
	}
	
	public static void Reset()
	{
		processes = new LinkedList<Process>();
		setCurrentRunningProcess(null);
	}

	public static Process getCurrentRunningProcess() {
		return currentRunningProcess;
	}

	public static void setCurrentRunningProcess(Process currentRunningProcess) {
		ProcessManager.currentRunningProcess = currentRunningProcess;
	}
	
}

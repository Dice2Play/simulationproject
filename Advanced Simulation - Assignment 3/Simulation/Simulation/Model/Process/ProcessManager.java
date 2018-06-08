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
		if(currentRunningProcess == null)
		{
			currentRunningProcess = getNextProcessToRun();
			StartProcess(currentRunningProcess);
		}
		
		return currentRunningProcess.CanFire();
	}
	
	private static void StartProcess(Process processToStart)
	{
		System.out.println(String.format("PROCESS MANAGER: Process %s started at %s", processToStart.GetID(), TimeManager.GetTimeUnitsPassed()));
		processToStart.Start();
	}
	
	public static void Fire() throws Exception
	{
		// If no currentRunningProcess is defined yet, get first
		// If no process is running, get next process
		if(currentRunningProcess == null)
		{
			currentRunningProcess = getNextProcessToRun();
			StartProcess(currentRunningProcess);
		}
		
		currentRunningProcess.Fire();
		System.out.println(String.format("PROCESS MANAGER: Process %s fired at %s", currentRunningProcess.GetID(), TimeManager.GetTimeUnitsPassed()));	
	}
	
	public static boolean CheckIfCurrentProcessCanFinish()
	{
		// Check if currentProcess can be finished
		if(currentRunningProcess.CanFinish()) { return true;}
		else return false;
	}
	
	public static void FinishCurrentProcess()
	{
		
		if(currentRunningProcess.IsFinished()) {return;}
		
		
		System.out.println(String.format("PROCESS MANAGER: Process %s finished at %s", currentRunningProcess.GetID(), TimeManager.GetTimeUnitsPassed()));
		
		//Stop and Reset process
		currentRunningProcess.SetFinished();
		currentRunningProcess.Stop();
		currentRunningProcess.Reset();
		
		// Get next process
		try
		{
			currentRunningProcess = getNextProcessToRun();
			StartProcess(currentRunningProcess);
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
		if(currentRunningProcess == null) { return processes.getFirst();}
		
		// If there is a process currently running then,
		// get index of last element in 'processes' list,
		// if this index is out of bounds, return the first occurrence from the list 'Processes'
		int indexOfCurrentRunningProcess = processes.indexOf(currentRunningProcess);
		
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
		currentRunningProcess = null;
	}
	
}

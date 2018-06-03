package Simulation.Model.Process.Behavior;

import Simulation.Model.Process.Process;

public class MultipleFireBehavior implements IProcessFireBehavior {

	private Process[] subProcesses;
	
	
	public MultipleFireBehavior(Process[] subProcesses)
	{
		this.subProcesses = subProcesses;
	}
	
	@Override
	public void Fire() {
		for(Process process : subProcesses)
		{
			process.Fire();
		}
		
	}
	@Override
	public boolean CanFire() {
		for(Process process : subProcesses)
		{
			// If one of the processes can't fire, return false
			if(!process.CanFire()) { return false;}
		}
		
		// If all processes can fire, return true
		return true;
		
	}
}

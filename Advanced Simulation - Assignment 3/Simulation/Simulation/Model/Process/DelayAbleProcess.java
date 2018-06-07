package Simulation.Model.Process;

import Simulation.Enums.Resource_Type;
import Simulation.Model.Process.Behavior.DelayAbleFireBehavior;

public class DelayAbleProcess extends Process{

	
	public DelayAbleProcess(String ID, int processTime, Resource_Type type, double delayTime) {
		super(ID, processTime, type);
		fireBehavior = new DelayAbleFireBehavior(delayTime, processTime, type);
	}
	
}

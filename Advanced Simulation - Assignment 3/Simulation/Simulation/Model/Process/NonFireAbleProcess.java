package Simulation.Model.Process;

import Simulation.Enums.Resource_Type;
import Simulation.Model.Process.Behavior.NonFireAbleBehavior;

public class NonFireAbleProcess extends Process{

	public NonFireAbleProcess(String ID, int processTime, Resource_Type type) {
		super(ID, processTime, type);
		fireBehavior = new NonFireAbleBehavior();
	}
	
	

}

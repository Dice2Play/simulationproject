package Simulation.Model.Process;

import java.util.ArrayList;

import Simulation.Enums.Resource_Type;
import Simulation.Model.Process.Behavior.IProcessFireBehavior;
import Simulation.Model.Process.Behavior.MultipleFireBehavior;

public class CompositeProcess extends Process{

	
	public CompositeProcess(String ID, int processTime, Resource_Type type, Process[] subProcesses) {
		super(ID, processTime, type);
		//fireBehavior = new MultipleFireBehavior() 
	}
	
	



}

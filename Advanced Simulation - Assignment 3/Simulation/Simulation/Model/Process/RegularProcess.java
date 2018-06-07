package Simulation.Model.Process;

import Simulation.Enums.Resource_Type;
import Simulation.Model.Process.Behavior.RegularFireBehavior;


public class RegularProcess  extends Process{ 
	
	public RegularProcess(String ID, int processTime, Resource_Type type) {
		super(ID, processTime, type);
		fireBehavior = new RegularFireBehavior(processTime,ID, type);
	}


	

}

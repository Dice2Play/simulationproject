package Simulation.Model.Resource;

import Simulation.Enums.Resource_Type;

public class NonSeizableResource extends Resource{

	NonSeizableResource(String ID, Resource_Type type) {super(ID,type);	}

	@Override
	boolean IsAvailable() {
		return true;
	}

	@Override
	boolean CanSeize() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	void Seize(int timeUnitsRequired, int capacityNeeded) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void Release() {
		// TODO Auto-generated method stub
		
	}
	
	

}

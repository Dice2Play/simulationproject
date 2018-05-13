package Simulation.Model.Resource.Behavior;

import Simulation.Enums.Resource_Type;


public class StaticSeizableResource  implements ISeizable{

	

	@Override
	public boolean IsAvailable() {
		return true;
	}

	@Override
	public boolean CanSeize() {
		return true;
	}

	@Override
	public void Seize(int timeUnitsRequired, int capacityNeeded) {}

	@Override
	public void Release() {	}


	

}

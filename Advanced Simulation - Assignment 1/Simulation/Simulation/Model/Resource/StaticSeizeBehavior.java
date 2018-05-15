package Simulation.Model.Resource;

public class StaticSeizeBehavior implements ISeizeBehavior{


	public double GetOccupancy() {return 1;}
	public void Release() {}
	public void Seize(int capacityNeeded) {	}

}

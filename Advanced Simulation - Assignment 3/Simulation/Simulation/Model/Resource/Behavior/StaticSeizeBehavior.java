package Simulation.Model.Resource.Behavior;

public class StaticSeizeBehavior implements IResourceSeizeBehavior{


	public double GetOccupancy() {return 1;}
	public void Release() {}
	public void Seize(int capacityNeeded) {	}

}

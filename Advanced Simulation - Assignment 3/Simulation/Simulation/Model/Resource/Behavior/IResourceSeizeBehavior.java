package Simulation.Model.Resource.Behavior;

public interface IResourceSeizeBehavior {

	
	double GetOccupancy();
	void Seize(int capacityNeeded);
	void Release();
	
	
	
}

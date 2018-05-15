package Simulation.Model.Resource.Behavior;

public interface ISeizeBehavior {

	
	double GetOccupancy();
	void Seize(int capacityNeeded);
	void Release();
	
	
	
}

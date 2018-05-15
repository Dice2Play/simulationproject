package Simulation.Model.Resource;

public interface ISeizeBehavior {

	
	double GetOccupancy();
	void Seize(int capacityNeeded);
	void Release();
	
	
	
}

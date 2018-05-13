package Simulation.Model.Resource.Behavior;

// Whether or not a resource is Seizable
public interface ISeizable {

	boolean IsAvailable();
	boolean CanSeize();
	void Seize();
	void Release();
	
}

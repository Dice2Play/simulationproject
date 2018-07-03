package simulation.resource;

public abstract class Employee extends Resource {

	double hourlyRate;
	
	Employee(int ID, Resource_Type type, double hourlyRate) {
		super(ID, type);
		this.hourlyRate = hourlyRate;
	}

	
	
	
	
}

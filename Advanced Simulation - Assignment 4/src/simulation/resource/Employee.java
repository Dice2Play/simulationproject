package simulation.resource;

public abstract class Employee extends Resource {

	double hourlyRate;
	
	public Employee(String ID, Resource_Type type, double hourlyRate) {
		super(ID, type);
		this.hourlyRate = hourlyRate;
	}
	
	public double GetHourlyRate()
	{
		return hourlyRate;
	}

	
	
	
	
}

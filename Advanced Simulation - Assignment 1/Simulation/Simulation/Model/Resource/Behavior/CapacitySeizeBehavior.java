package Simulation.Model.Resource.Behavior;

public class CapacitySeizeBehavior implements ISeizeBehavior {

	final double CAPACITY;
	double amountOfCapacityTaken;
	
	public CapacitySeizeBehavior(double capacity ){this.CAPACITY = capacity;}
	public double GetOccupancy() {return (amountOfCapacityTaken/CAPACITY);}
	public void Release() {amountOfCapacityTaken = 0;}
	public void Seize(int capacityNeeded) {amountOfCapacityTaken = amountOfCapacityTaken + capacityNeeded;}	
}

package Simulation.Results;

public class DoubleResultAttribute extends  ResultAttribute {


	public DoubleResultAttribute(Double value, String ID) {
		super(value, ID);
	}

	@Override
	public Double getValue() {
		return (Double) value;
	}

	

}

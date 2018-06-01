package Simulation.Results;

import Simulation.Interfaces.IResultAttribute;

public abstract class ResultAttribute implements IResultAttribute{

	protected final Object value;
	protected final String ID;
	
	protected ResultAttribute(Object value, String ID)
	{
		this.value = value;
		this.ID = ID;
	}
	
	public String getID() {
		return ID;
	}

	
}

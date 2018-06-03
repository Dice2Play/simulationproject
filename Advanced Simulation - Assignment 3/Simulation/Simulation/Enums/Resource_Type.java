package Simulation.Enums;

public enum Resource_Type {
	BOAT(8),
	NONE(0);
	
	private final int capacity;
	
	Resource_Type(int capacity)
	{
		this.capacity = capacity;
	}
	
	public int GetCapacity()
	{
		return this.capacity;
	}
}

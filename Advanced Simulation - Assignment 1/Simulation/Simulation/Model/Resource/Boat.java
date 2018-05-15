package Simulation.Model.Resource;

import Simulation.Enums.Resource_Type;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Boat extends Resource{

	public Boat(String ID)
	{
		super(ID, Resource_Type.BOAT);
		seizeBehavior = new CapacitySeizeBehavior(8);
	}
}

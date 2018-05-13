package Simulation.Model.Resource;

import Simulation.Enums.Resource_Type;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class Boat extends Resource{

	final int capacity = 8;
	int amountOfPlacesTaken = 0;
	
	
	public Boat(String ID)
	{
		super(ID, Resource_Type.BOAT);	
	}
	

}

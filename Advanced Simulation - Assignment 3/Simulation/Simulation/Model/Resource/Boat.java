package Simulation.Model.Resource;

import Simulation.Enums.Resource_Type;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import Simulation.Model.Resource.Behavior.CapacitySeizeBehavior;

public class Boat extends Resource{

	public Boat(String ID)
	{
		super(ID, Resource_Type.BOAT);
		seizeBehavior = new CapacitySeizeBehavior(Resource_Type.BOAT.GetCapacity());
	}
}

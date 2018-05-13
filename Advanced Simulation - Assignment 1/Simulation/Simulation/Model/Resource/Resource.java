package Simulation.Model.Resource;

import Simulation.Enums.Resource_Type;
import Simulation.Model.Time.TimeManager;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

abstract class Resource {

	
	private String ID;
	private Resource_Type type;
	
	abstract boolean IsAvailable();
	abstract boolean CanSeize();
	abstract void Seize(int timeUnitsRequired, int capacityNeeded);
	abstract void Release();
	
	
	Resource(String ID, Resource_Type type)
	{
		this.ID = ID;
		this.type = type;
	}
	
	Resource_Type GetResourceType()
	{
		return type;
	}
	
	
	String GetID()
	{
		return ID;
	}

	
	

	
	
}

package Simulation.Model.Resource;

import Simulation.Enums.Resource_Type;
import Simulation.Model.Resource.Behavior.ISeizable;

//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

class Resource {

	
	private String ID;
	private Resource_Type type;
	private ISeizable seizable;
		
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

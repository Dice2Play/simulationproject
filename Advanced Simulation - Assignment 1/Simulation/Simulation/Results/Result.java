package Simulation.Results;

import java.util.ArrayList;
import java.util.List;

import Simulation.Interfaces.IResultAttribute;

public class Result {

	List<IResultAttribute> attributes = new ArrayList<IResultAttribute>();
	
	
	public void AddAttribute(IResultAttribute attribute)
	{
		attributes.add(attribute);
	}
	
	public List<IResultAttribute> getAttributes()
	{
		return attributes;
	}
	
	String getAttributeValues()
	{
		StringBuilder stringToPrint =  new StringBuilder();
		
		for(IResultAttribute attribute : attributes)
		{
			stringToPrint.append(String.format(" %s : %.6f " , attribute.getID(), attribute.getValue()));
		}
		
		return stringToPrint.toString();
	}
	

}



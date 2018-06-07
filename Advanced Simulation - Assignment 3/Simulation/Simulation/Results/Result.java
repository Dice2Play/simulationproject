package Simulation.Results;

import java.util.ArrayList;
import java.util.List;

import Simulation.Interfaces.IResultAttribute;
import Simulation.Model.Time.TimeManager;

public class Result {

	List<IResultAttribute> attributes = new ArrayList<IResultAttribute>();
	private double timeStamp;
	
	public Result()
	{
		timeStamp = TimeManager.GetTimeUnitsPassed();
	}
	
	public void AddAttribute(IResultAttribute attribute)
	{
		attributes.add(attribute);
	}
	
	public List<IResultAttribute> GetAttributes()
	{
		return attributes;
	}
	
	String GetAttributeValues()
	{
		StringBuilder stringToPrint =  new StringBuilder();
		
		for(IResultAttribute attribute : attributes)
		{
			stringToPrint.append(String.format(" %s : %.4f " , attribute.getID(), attribute.getValue()));
		}
		
		return stringToPrint.toString();
	}
	
	double GetTimeStamp()
	{
		return timeStamp;
	}
	

}



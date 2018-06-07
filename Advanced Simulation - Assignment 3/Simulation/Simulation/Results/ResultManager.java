package Simulation.Results;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.csvreader.CsvWriter;

import Simulation.Interfaces.IResultAttribute;
import Simulation.Model.Time.TimeManager;

public class ResultManager {

	private static List<Result> results = new ArrayList<Result>(); 	
	private static List<Double> replicationSummary = new ArrayList<Double>(); // csv uses
	
	private static int currentReplication;
	//private static final String outputDirectory;
	
	
	public static void SetCurrentReplication(int newCurrentReplication)
	{
		currentReplication = newCurrentReplication;
	}
	
	public static void ClearResults()
	{
		results = new ArrayList<Result>(); 	
	}
	
	public static void AddResults(Result result)
	{
		results.add(result);
	}
	
	public static void ShowAllResults()
	{
		for(Result result : results)
		{
			System.out.println("TimeUnit [" + result.GetTimeStamp() +"] " + result.GetAttributeValues()); 
		}
	}
	
	
	private static HashMap<String, List<Object>> CumulateAllAttributeValues()
	{
		// HashTable for storing the key+values
		HashMap<String, List<Object>> attributeValues = new HashMap<String, List<Object>>();
				
		for(Result result :results)
		{
					
			for(IResultAttribute attribute : result.GetAttributes())
			{
				// Check if key already exists
				if(attributeValues.containsKey(attribute.getID()))
				{
					// Get List<Object> from attributeValues
					List<Object> values = attributeValues.get(attribute.getID());
							
					// Add new value
					values.add(attribute.getValue());
				}
						
				else // If no key already exists
				{
					List<Object> values = new ArrayList<Object>();
					values.add(attribute.getValue());
					attributeValues.put(attribute.getID(), values);
				}
						
						
			}
					
					
					
		}
		
		return attributeValues;
	}
	
	public static void ShowSummary()
	{
		
		
		// Check if array not is empty
		if(results.isEmpty()) {return;}

		// HashTable for storing the key+values
		HashMap<String, List<Object>> attributeValues = CumulateAllAttributeValues();
		
		// Add one empty row, summary header and current replication
		System.out.println("\n===== SUMMARY =====");
		System.out.println("Replication " + currentReplication);
		
		// Iterate through hashmap
	    Set set = attributeValues.entrySet();
	    Iterator i = set.iterator();
	    
	    
		while(i.hasNext())
		{
			// Add key
			Map.Entry me = (Map.Entry)i.next();
	        System.out.print("MEAN " + me.getKey() + ": ");
	        
	        // Get/Add value
	        List<Object> valuesOfKey = (List<Object>) me.getValue();
	        double meanValue = Statistics.Statistics.GetMean(valuesOfKey.toArray());
	        System.out.println(meanValue);
	        
	        // Add to replicationSummary
	        replicationSummary.add(meanValue);
	        
		}
		
	
		
		
	}
	
	

	
	/*****Write results to csv******/
	public static void ExportSummaryToCSV()
	{
		// Check if there is any replication data to write, otherwise cancel method
		if(replicationSummary.isEmpty()) {return;}
		
	   String outputFile = "Result.csv";
	   // check if the file already exists
	   boolean alreadyexists = new File(outputFile).exists();
	   
	   try {
		   // use FileWriter constructor that specifies open for appending
           CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');

           // if the file didn't already exist then we need to write out the header line
           if (!alreadyexists)
           {
        	   
        	   
        	   //Retrieve result attribute ID's
       		   HashMap<String, List<Object>> attributeValues = CumulateAllAttributeValues();
       		   Set set = attributeValues.entrySet();
       		   Iterator i = set.iterator();
       		   
       		   // Set replication 
       		   csvOutput.write("Replication");
       		   
       		 
       		   while(i.hasNext())
       		   {
       			   Map.Entry me = (Map.Entry)i.next();
       			   csvOutput.write(me.getKey().toString());
       		   }
       		   
       		   csvOutput.endRecord();
       		  	
               
           }
           // else assume that the file already has the correct header line

		csvOutput.write(String.valueOf(currentReplication));
		
		
	    for (Double data : replicationSummary)
	    {
	    	csvOutput.write(String.valueOf(data));	
	   
	    }
	    
	    replicationSummary.clear();
	
        csvOutput.endRecord();
		
		// Add empty line
		System.out.println();
           
           csvOutput.close();

		   
	   }catch (Exception e )
	   {
		   e.printStackTrace();
	   }
	}
	
}



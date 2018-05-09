package Simulation.Results;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvWriter;

public class ResultManager {

	private static List<Result> results = new ArrayList<Result>(); 	
	private static List<Double> fillingdata = new ArrayList<Double>(); // csv uses
	
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
		results.forEach(x -> x.Print());
	}
	
	public static void ShowSummary()
	{
		
		
		// Check if array not is empty
		if(results.isEmpty()) {return;}

		try {
				// From each result class, retrieve attributes and attributes names
				Field[] resultFields = results.get(0).getClass().getDeclaredFields();
				
				// Add one empty row, summary header and current replication
				System.out.println();
				System.out.println("===== SUMMARY =====");
				System.out.println("Replication " + currentReplication);
				
				// Print ( resultname {resultMean})
				for (Field f : resultFields) {
				
					System.out.println("MEAN " + f.getName() + " " + CalculateMean(f.getName()));
					fillingdata.add(CalculateMean(f.getName()));
					
				} 
			
				
				// Add empty line
				System.out.println();
			
				Export2CSV();
		}
		catch (Exception e) {e.printStackTrace();}
		
		
	}
	
	
	// Calculate's the mean from the specified attribute name of the Result object
	private static double CalculateMean(String attributeName) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		List<Double> valuesFromResultsList = new ArrayList<Double>();

		// Get the value from specified 'attributeName'
		for(Result result : results)
		{
			// Set correct getMethodName
			String getMethodName = "Get"+attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
			
			try {
				
				// Try to fire method of result class
				Double valGetMethod = (Double) result.getClass().getMethod(getMethodName).invoke(result);
				
				// Add to return list
				valuesFromResultsList.add((Double) valGetMethod);
				
			}
			
			
			catch (NoSuchMethodException | SecurityException e) {e.printStackTrace();}
		}
		

	
		return Statistics.Statistics.GetMean(valuesFromResultsList.toArray());
	}
	
	/*****Write results to csv******/
	public static void Export2CSV()
	{
	   String outputFile = "result.csv";
	   // check if the file already exists
	   boolean alreadyexists = new File(outputFile).exists();
	   
	   try {
		   // use FileWriter constructor that specifies open for appending
           CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');

           // if the file didn't already exist then we need to write out the header line
           if (!alreadyexists)
           {
               csvOutput.write("Replication id");
               csvOutput.write(" waitingTime Of Customer");
               csvOutput.write("Total Queue length");
               csvOutput.write("Boat Occupancy time");
               csvOutput.endRecord();
           }
           // else assume that the file already has the correct header line

		csvOutput.write(String.valueOf(currentReplication));
		
		System.out.println("Lenth of filling data " + fillingdata.size());
		
	    for (Double data : fillingdata)
	    {
	    	csvOutput.write(String.valueOf(data));	
	   
	    }
	    
	    fillingdata.clear();
	
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



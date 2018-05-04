package Simulation.Results;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ResultManager {

	private static List<Result> results = new ArrayList<Result>(); 	
	private static int currentReplication;
	
	
	public static void SetCurrentReplication(int newCurrentReplication)
	{
		currentReplication = newCurrentReplication;
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
				} 
				
			
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
		

	
		return Statistics.Statistics.GetMean((Double[]) valuesFromResultsList.toArray());
	}
	
	public static void ExportToCSV()
	{
		
	}
}



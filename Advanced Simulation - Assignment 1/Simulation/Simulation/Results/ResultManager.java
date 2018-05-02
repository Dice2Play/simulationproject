package Simulation.Results;
import java.lang.reflect.Field;
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
		
		// From each result class, retrieve attributes and attributes names
		Field[] resultFields = results.get(0).getClass().getDeclaredFields();
		
		// Add one empty row and summary header
		System.out.println();
		System.out.println("===== SUMMARY =====");
		
		for(Field f : resultFields)
		{
			System.out.println("MEAN " +f.getName()+" ");
		}
		
		// print ( resultname {resultMean})
	}
	
	private static double CalculateMean(String attributeName)
	{
		List<Double> valuesFromResultsList = new ArrayList<Double>();
		
		results.forEach(x -> {
			
			// Get the GetMethod for attributeName
			try {valuesFromResultsList.add(x.getClass().getMethod("Get"+attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1)));} 
			catch (NoSuchMethodException | SecurityException e) {e.printStackTrace();}
		});
		
		return Statistics.Statistics.GetMean(Double[])valuesFromResultsList.toArray());
	}
	
	public static void ExportToCSV()
	{
		
	}
}



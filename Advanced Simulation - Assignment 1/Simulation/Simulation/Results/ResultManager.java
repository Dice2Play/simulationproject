package Simulation.Results;
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
		
	}
	
	public static void ExportToCSV()
	{
		
	}
}



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
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

import com.csvreader.CsvWriter;

import Simulation.Interfaces.IResultAttribute;
import Simulation.Model.Queue.Queue;
import Simulation.Model.Time.TimeManager;

public class ResultManager {

	private static List<Result> results = new ArrayList<Result>(); 	
	private static List<Double> replicationSummary = new ArrayList<Double>(); // csv uses
	//for table use:
	public static final List<Integer> xgRecord = new ArrayList<Integer>();
	public static final List<Integer> x0Record = new ArrayList<Integer>();
	public static final List<Integer> xfullRecord = new ArrayList<Integer>();
	private static int counter0;
	private static int counterg;
	
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
		
	//	printCounterG(counterg);
	//	printCounter0(counter0);
		
		
		
	}
	public static void printCounterG(int counter)
	{
		System.out.print("counter Xg is "+ counter+"\n");
	}
	public static void printCounter0(int counter)
	{
		System.out.print("counter X0 is "+ counter +"\n");
	}
	
	
	public static void setCounter0(int c)
	{
		counter0 = c;
	}
	public static void setCounterg(int c)
	{
		counterg = c;
	}
   //get the average waiting time the whole simulation
	//duplicated method, used for testing 
	public static Double calculateAvgWaitingTime()
	{                       
	  return Queue.waitingTimeRecord.stream().mapToDouble(val -> val).average().orElse(0);
	}
	/******************************Table of expected values and probability*******************/
	//Expected Xg
	public static Double getExpectedValueXg()
	{
		return xgRecord.stream().mapToDouble(val -> val).average().orElse(0);
	}
	//Expected Expected g ^2
	public static Double getExpectedValueXg2nd()
	{
		Function<Integer, Integer> square = x -> x * x;

		return xgRecord.stream().map(square).mapToDouble(val -> val).average().orElse(0);
	}
	//Expected value of X0
	public static Double getExpectedValueX0()
	{
		return x0Record.stream().mapToDouble(val -> val).average().orElse(0);
	}
	//E[x-]
	public static Double getExpectedValueofAvgQueuelength()
	{
		return xfullRecord.stream().mapToDouble(val -> val).average().orElse(0);
	}
	public static Double getExpectedDelay() 
	{
		return Queue.waitingTimeRecord.stream().mapToDouble(val -> val).average().orElse(0);
	}
	public static Double getExpectedDelay2nd()
	{
		Function<Double, Double> square = x -> x * x;

		return Queue.waitingTimeRecord.stream().map(square).mapToDouble(val -> val).average().orElse(0);
	}
	public static Double getProbabilityX0is0()
	{    double counter = 0;
		 counter =  x0Record.stream().filter(record ->record.equals(0)).count();		 
		return counter/x0Record.size();
	}
	public static Double getProbabilityXgis0()
	{
		double counter = 0;
		counter =  xgRecord.stream().filter(record ->record.equals(0)).count();		 
		return counter/xgRecord.size();
	}
	/******************************Variance and half-width*******************/
	public static double getVarianceXg()
	{
		Function<Integer, Double> differnce = x -> x- getExpectedValueXg();
		DoubleUnaryOperator square = x -> x * x;
		Double top = xgRecord.stream().map(differnce).mapToDouble(val -> val).map(square).sum();
		return top/(xgRecord.size()-1);
	}
	public static double getVarianceX0()
	{
		Function<Integer, Double> differnce = x -> x- getExpectedValueX0();
		DoubleUnaryOperator square = x -> x * x;
		Double top = x0Record.stream().map(differnce).mapToDouble(val -> val).map(square).sum();
		return top/(x0Record.size()-1);
	}
	public static double getVarianceX()
	{
		Function<Integer, Double> differnce = x -> x- getExpectedValueofAvgQueuelength();
		DoubleUnaryOperator square = x -> x * x;
		Double top = xfullRecord.stream().map(differnce).mapToDouble(val -> val).map(square).sum();
		return top/(xfullRecord.size()-1);
	}
	public static double getVarianceDelay()
	{
		Function<Double, Double> differnce = x -> x- getExpectedDelay();
		DoubleUnaryOperator square = x -> x * x;
		Double top = Queue.waitingTimeRecord.stream().map(differnce).mapToDouble(val -> val).map(square).sum();
		return top/(Queue.waitingTimeRecord.size()-1);
	}
	/**
	 * Get half width formula
	 * @param varaince of each value
	 * @param N is the total smaple number
	 * @return upper and lower bound 95% confidence 
	 */
	public static double[] getlowerHigherBond(double mean, double varaince, double N)
	{
		double c = 1.96;
		double half_width = c*Math.sqrt(varaince/N);
		double upper = mean+half_width;
		double lower = mean-half_width;
		double[] myList = {upper, lower};
	  return myList;
	}
	public static double[] get95ConfidenceX0()
	{
		return getlowerHigherBond(getExpectedValueX0(),getVarianceX0(),x0Record.size());
	}
	public static double[] get95ConfidenceXg()
	{
		return getlowerHigherBond(getExpectedValueXg(),getVarianceXg(),xgRecord.size());
	}
	public static double[] get95ConfidenceX()
	{
		return getlowerHigherBond(getExpectedValueofAvgQueuelength(),getVarianceX(),xfullRecord.size());
	}
	public static double[] get95ConfidenceDelay()
	{
		return getlowerHigherBond(getExpectedDelay(),getVarianceDelay(),Queue.waitingTimeRecord.size());
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



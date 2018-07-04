package simulation.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Statistics.ArtificialDistribution;
import Statistics.Distribution;
import Statistics.NormalDistribution;
import Statistics.Statistics;
import simulation.interfaces.Command;
import simulation.interfaces.Tick_Listener;

public class EntityManager implements Tick_Listener{

	static List<Entity> entities = new ArrayList<Entity>();
	static Process startingProcess;
	
	// Probability of customer takes Short or Long cleaning
	final static double PROBABILITY_SHORT_CLEANING = 0.293;
	final static double PROBABILITY_LONG_CLEANING = 0.707;
	
	
	// Short cleaning normal distribution parameters
	final static double LONG_CLEANING_MEAN = 0;
	final static double LONG_CLEANING_VARIANCE = 0;
	
	// Long cleaning chi-square distribution parameters
	final static double SHORT_CLEANING_MEAN = 0;
	final static double SHORT_CLEANING_VARIANCE = 0;	
	
	
	/**
	 * Generate entity according to the 'generateRate'
	 * Use the result from the normal distribution to determine when this entity arrives, pass this result to the TimeManager
	 */
	private static void GenerateEntity()
	{
		// Time at which next car should arrive
		double timeOnWhichNextCarArrives;
		
		// Artificial distribution parameters
		double[] possibleOutcomes = new double[] {0,1};
		double[] probabilityOfPossibleOutcomes = new double[] {PROBABILITY_SHORT_CLEANING,PROBABILITY_LONG_CLEANING};
		
		
		// If outcomeToChoose 
		// 0: Get short cleaning 
		// 1: Get long cleaning
		double outcomeToChoose = Statistics.GetDistributionResult(new ArtificialDistribution(possibleOutcomes, probabilityOfPossibleOutcomes));
		
		
		if(outcomeToChoose == 0)
		{
			timeOnWhichNextCarArrives = Statistics.GetDistributionResult(new NormalDistribution(0,0,new Random()));
		}
		
		if(outcomeToChoose == 1)
		{
			timeOnWhichNextCarArrives = Statistics.GetDistributionResult(new NormalDistribution(0,0,new Random()));
		}
		
		// Add time event which will generate an entity when its being executed
		Entity newEntity = new Entity(String.format("CAR_%d", entities.size() + 1));
		entities.add(newEntity);
		GenerateEntity generateEntity = new GenerateEntity(newEntity, )
			
	}
	
	public static void AddEntity(Entity entityToAdd)
	{
		entities.add(entityToAdd);
	}
	
	
	/**
	 * 
	 * @param newStartingProcess
	 */
	public static void SetStartingProcess(Process newStartingProcess)
	{
		startingProcess = newStartingProcess;
	}

	public void On_Tick() {
		GenerateEntity();
	}
	
	private class GenerateEntityCommand implements Command
	{

		Entity entityToAdd;
		simulation.process.Process processWhereEntityNeedsToBeAddedTo;		
		
		public GenerateEntityCommand(Entity entityToAdd, simulation.process.Process processWhereEntityNeedsToBeAddedTo)
		{
			this.entityToAdd = entityToAdd;
			this.processWhereEntityNeedsToBeAddedTo = processWhereEntityNeedsToBeAddedTo;
		}
		
		@Override
		public void Execute() {
			// QUEUE , PROCESS, ENTITYMANAGER
			
			processWhereEntityNeedsToBeAddedTo.AddEntityToQueue(entityToAdd);
			
		}
		
	}
}

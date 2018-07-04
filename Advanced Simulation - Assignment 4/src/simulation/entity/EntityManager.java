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
import simulation.time.TimeEvent;
import simulation.time.TimeManager;

public class EntityManager implements Tick_Listener{

	static EntityManager entityManager = null;
	
	List<Entity> entities = new ArrayList<Entity>();
	simulation.process.Process startingProcess;
	
	// Probability of customer takes Short or Long cleaning
	final  double PROBABILITY_SHORT_CLEANING = 0.293;
	final  double PROBABILITY_LONG_CLEANING = 0.707;
	
	
	// Short cleaning normal distribution parameters
	final  double LONG_CLEANING_MEAN = 0;
	final  double LONG_CLEANING_VARIANCE = 0;
	
	// Long cleaning chi-square distribution parameters
	final  double SHORT_CLEANING_MEAN = 0;
	final  double SHORT_CLEANING_VARIANCE = 0;	
	
	
	private EntityManager()
	{
		
	}
	
	
	public static EntityManager GetInstance()
	{
		if(entityManager == null)
		{
			entityManager = new EntityManager();
		}
		
		return entityManager;
	}
	
	/**
	 * Generate entity according to the 'generateRate'
	 * Use the result from the normal distribution to determine when this entity arrives, pass this result to the TimeManager
	 */
	private void GenerateEntity()
	{
		// Time at which next car should arrive
		double timeOnWhichNextEntityArrives = 0;
		
		// Artificial distribution parameters
		double[] possibleOutcomes = new double[] {0,1};
		double[] probabilityOfPossibleOutcomes = new double[] {PROBABILITY_SHORT_CLEANING,PROBABILITY_LONG_CLEANING};
		
		
		// If outcomeToChoose 
		// 0: Get short cleaning 
		// 1: Get long cleaning
		double outcomeToChoose = Statistics.GetDistributionResult(new ArtificialDistribution(possibleOutcomes, probabilityOfPossibleOutcomes));
		
		
		if(outcomeToChoose == 0)
		{
			timeOnWhichNextEntityArrives = TimeManager.GetCurrentTime() + Statistics.GetDistributionResult(new NormalDistribution(0,0,new Random()));
		}
		
		if(outcomeToChoose == 1)
		{
			timeOnWhichNextEntityArrives = TimeManager.GetCurrentTime() + Statistics.GetDistributionResult(new NormalDistribution(0,0,new Random()));
		}
		
		// Add time event which will generate an entity when its being executed
		Entity newEntity = new Entity(String.format("CAR_%d", entities.size() + 1));
		GenerateEntityCommand generateEntity = new GenerateEntityCommand(newEntity, startingProcess);
		
		TimeManager.AddTimeEvent(new TimeEvent(timeOnWhichNextEntityArrives, generateEntity, String.valueOf("Entity %s has arrived")));
			
	}
	
	
	public void AddEntity(Entity entityToAdd)
	{
		entities.add(entityToAdd);
	}
	
	
	/**
	 * 
	 * @param newStartingProcess
	 */
	public void SetStartingProcess(simulation.process.Process newStartingProcess)
	{
		startingProcess = newStartingProcess;
	}

	public void On_Tick() {
		GenerateEntity();
	}
	

		
	


}

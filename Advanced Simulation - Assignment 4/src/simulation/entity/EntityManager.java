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
import simulation.time.Event_Type;
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
	final  double SHORT_CLEANING_MEAN = 70.3;
	final  double SHORT_CLEANING_SD_DEVIATION = 11.7;
	
	// Long cleaning chi-square distribution parameters
	final  double LONG_CLEANING_MEAN = 191;
	final  double LONG_CLEANING_SD_DEVIATION  = 24;	
	
	// Transform time unit constant
	final double TIME_UNIT_TRANSFORM_FACTOR = (1.0/60.0);
	
	

	private EntityManager()
	{
		// Set listeners
		TimeManager.GetInstance().AddTickListener(this);
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
	 * Call this method at the beginning to start creating time events generating entities.
	 */
	public void StartGenerating()
	{
		GenerateEntity();
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
			timeOnWhichNextEntityArrives = TimeManager.GetInstance().GetCurrentTime() + (Statistics.GetDistributionResult(new NormalDistribution(SHORT_CLEANING_MEAN,SHORT_CLEANING_SD_DEVIATION,new Random()))* TIME_UNIT_TRANSFORM_FACTOR);
		}
		
		if(outcomeToChoose == 1)
		{
			timeOnWhichNextEntityArrives = TimeManager.GetInstance().GetCurrentTime() + (Statistics.GetDistributionResult(new NormalDistribution(LONG_CLEANING_MEAN,LONG_CLEANING_SD_DEVIATION,new Random())) * TIME_UNIT_TRANSFORM_FACTOR);
		}
		
		// Add time event which will generate an entity when its being executed
		Entity newEntity = new Entity(String.format("CAR_%d", entities.size() + 1));
		GenerateEntityCommand generateEntity = new GenerateEntityCommand(newEntity, startingProcess);
		
		TimeManager.GetInstance().AddTimeEvent(new TimeEvent(timeOnWhichNextEntityArrives, generateEntity, String.format("Entity %s has arrived", newEntity.GetID()), Event_Type.ARRIVAL));
			
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


	@Override
	public void On_Tick(Event_Type eventType) {
		if(eventType == Event_Type.ARRIVAL) 	{GenerateEntity();}
	}
	

		
	


}

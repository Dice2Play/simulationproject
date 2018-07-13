package simulation.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Statistics.ArtificialDistribution;
import Statistics.Distribution;
import Statistics.NormalDistribution;
import Statistics.PoissonDistribution;
import Statistics.Statistics;
import simulation.interfaces.Command;
import simulation.interfaces.Tick_Listener;
import simulation.process.SequenceObject;
import simulation.time.Event_Type;
import simulation.time.TimeEvent;
import simulation.time.TimeManager;

public class EntityManager implements Tick_Listener{

	static EntityManager entityManager = null;
	
	List<Entity> entities = new ArrayList<Entity>();
	SequenceObject startingSequenceObject;
	
	// Probability of customer takes Short or Long cleaning

	
	
	// Short cleaning normal distribution parameters
	//final  double SHORT_CLEANING_MEAN = 70.3;
	//final  double SHORT_CLEANING_SD_DEVIATION = 11.7;
	
	// Long cleaning chi-square distribution parameters
	//final  double LONG_CLEANING_MEAN = 191;
	//final  double LONG_CLEANING_SD_DEVIATION  = 24;	
	
	// Transform time unit constant
	final double TIME_UNIT_TRANSFORM_FACTOR = (1.0/60.0);
	
	// Set schedule
	ArrayList<Double> poissonArrivalRates = new ArrayList<Double>();
	
	final double POISSON_ARRIVAL_RATE_0 = 5.067; 
	final double POISSON_ARRIVAL_RATE_1 = 4.111;
	final double POISSON_ARRIVAL_RATE_2 = 3.244;
	final double POISSON_ARRIVAL_RATE_3 = 2.067;
	final double POISSON_ARRIVAL_RATE_4 = 1.578;
	final double POISSON_ARRIVAL_RATE_5 = 1.267;
	final double POISSON_ARRIVAL_RATE_6 = 0.978;
	final double POISSON_ARRIVAL_RATE_7 = 1.556;
	final double POISSON_ARRIVAL_RATE_8 = 2.511;
	final double POISSON_ARRIVAL_RATE_9 = 4.733;
	final double POISSON_ARRIVAL_RATE_10 = 4.400;
	final double POISSON_ARRIVAL_RATE_11 = 3.222;
	final double POISSON_ARRIVAL_RATE_12 = 0;
	
	

	private EntityManager()
	{
		poissonArrivalRates.add(POISSON_ARRIVAL_RATE_0);
		poissonArrivalRates.add(POISSON_ARRIVAL_RATE_1);
		poissonArrivalRates.add(POISSON_ARRIVAL_RATE_2);
		poissonArrivalRates.add(POISSON_ARRIVAL_RATE_3);
		poissonArrivalRates.add(POISSON_ARRIVAL_RATE_4);
		poissonArrivalRates.add(POISSON_ARRIVAL_RATE_5);
		poissonArrivalRates.add(POISSON_ARRIVAL_RATE_6);
		poissonArrivalRates.add(POISSON_ARRIVAL_RATE_7);
		poissonArrivalRates.add(POISSON_ARRIVAL_RATE_8);
		poissonArrivalRates.add(POISSON_ARRIVAL_RATE_9);
		poissonArrivalRates.add(POISSON_ARRIVAL_RATE_10);
		poissonArrivalRates.add(POISSON_ARRIVAL_RATE_11);
		poissonArrivalRates.add(POISSON_ARRIVAL_RATE_12);
		
		// Set listeners
		TimeManager.GetInstance().AddTickListener(this);
	}
		
	public void Reset()
	{
		entityManager = null;
		entities = new ArrayList<Entity>();
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
	 */
	private void GenerateEntity()
	{
		// Time at which next entity should arrive
		double currentHour = Math.floor(TimeManager.GetInstance().GetCurrentTime());
		double timeOnWhichNextEntityArrives = TimeManager.GetInstance().GetCurrentTime() + Statistics.GetDistributionResult(new PoissonDistribution(poissonArrivalRates.get((int)currentHour), new Random()));
		
		
		// Add time event which will generate an entity when its being executed
		Entity newEntity = new Entity(String.format("CAR_%d", entities.size() + 1));
		GenerateEntityCommand generateEntity = new GenerateEntityCommand(newEntity, startingSequenceObject);
		
		TimeManager.GetInstance().AddTimeEvent(new TimeEvent(timeOnWhichNextEntityArrives, generateEntity, String.format("Entity %s has arrived", newEntity.GetID()), Event_Type.GENERATE));
			
	}

	
	public void DeRegisterEntity(Entity entityToDeRegister)
	{
		entities.remove(entityToDeRegister);
	}
	
	
	public void RegisterEntity(Entity entityToRegister)
	{
		entities.add(entityToRegister);
	}
	
	
	/**
	 * 
	 * @param newStartingProcess
	 */
	public void SetStartingSequenceObject(SequenceObject newStartingSequenceObject)
	{
		startingSequenceObject = newStartingSequenceObject;
	}


	@Override
	public void On_Tick(Event_Type eventType) {
		if(eventType == Event_Type.GENERATE) 	{GenerateEntity();}
	}
	

		
	


}

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
	private double amountOfRejects;

	
	// Transform time unit constant
	final double TIME_UNIT_TRANSFORM_FACTOR = (1.0/60.0);
	
	// Set schedule
	ArrayList<Double> poissonArrivalRates = new ArrayList<Double>();
	
	final double POISSON_ARRIVAL_RATE_0 = 1/5.067; 
	final double POISSON_ARRIVAL_RATE_1 = 1/4.111;
	final double POISSON_ARRIVAL_RATE_2 = 1/3.244;
	final double POISSON_ARRIVAL_RATE_3 = 1/2.067;
	final double POISSON_ARRIVAL_RATE_4 = 1/1.578;
	final double POISSON_ARRIVAL_RATE_5 = 1/1.267;
	final double POISSON_ARRIVAL_RATE_6 = 1/0.978;
	final double POISSON_ARRIVAL_RATE_7 = 1/1.556;
	final double POISSON_ARRIVAL_RATE_8 = 1/2.511;
	final double POISSON_ARRIVAL_RATE_9 = 1/4.733;
	final double POISSON_ARRIVAL_RATE_10 = 1/4.400;
	final double POISSON_ARRIVAL_RATE_11 = 1/3.222;
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
		double generatedValueForNextEntity = Statistics.GetDistributionResult(new PoissonDistribution(poissonArrivalRates.get((int)currentHour), new Random()));
		double timeOnWhichNextEntityArrives = TimeManager.GetInstance().GetCurrentTime() + generatedValueForNextEntity;
		
		
		// Add time event which will generate an entity when its being executed
		Entity newEntity = new Entity(String.format("CAR_%d", entities.size() + 1));
		GenerateEntityCommand generateEntity = new GenerateEntityCommand(newEntity, startingSequenceObject);
		
		TimeManager.GetInstance().AddTimeEvent(new TimeEvent(timeOnWhichNextEntityArrives, generateEntity, String.format("Entity %s has arrived", newEntity.GetID()), Event_Type.GENERATE));
		newEntity.SetArrivalTime(timeOnWhichNextEntityArrives);
		System.out.println("[Register arrival time of car]: "+ newEntity.GetID()+ "  "+newEntity.GetArrivalTime());
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

	public double GetLeftRate()
	{
		return (amountOfRejects/entities.size());
	}
	
	public double GetWaitingTimeUnder6Hours()
	{
		return 0;
	}
	
	public double GetCallsUnder2TimesCleaningTime()
	{
		return 0;
	}
	
	public void IncrementAmountOfRejects()
	{
		amountOfRejects++;
	}
	
	
	public double GetProcessingTime()
	{
		double totalProcessingTime = 0;
		double amountOfFinishedEntities = 0;
		
		for(Entity entity : entities)
		{
			if(entity.IsFinished())
			{
				totalProcessingTime += entity.GetProcessingTime();
				amountOfFinishedEntities++;
			}
			
		}
		
		return (totalProcessingTime/amountOfFinishedEntities);
	}
	
	// Checks whether StartSequenceObject is set
	public void Validate() throws Exception
	{
		if(startingSequenceObject == null) { throw new Exception("VALIDATE MODEL ERROR: Starting sequence object hasn't been set for EntityManager.");}
	}
	
	@Override
	public void On_Tick(Event_Type eventType) {
		if(eventType == Event_Type.GENERATE) 	{GenerateEntity();}
	}
	

		
	


}

package simulation.model;

import simulation.queue.Queue;
import simulation.queue.QueueManager;
import simulation.resource.Assistant;
import simulation.resource.CashRegister;
import simulation.resource.Cleaner;
import simulation.resource.CleaningSpot;
import simulation.resource.ParkingSpotNotReserved;
import simulation.resource.ParkingSpotReserved;
import simulation.resource.ResourceManager;
import simulation.resource.Resource_Type;
import simulation.result.ResultManager;
import simulation.time.TimeEvent;
import simulation.time.TimeManager;
import statistics.Distribution;
import statistics.GammaDistribution;
import statistics.NormalDistribution;
import statistics.PoissonDistribution;

import java.util.Random;

import simulation.entity.EntityManager;
import simulation.interfaces.Command;
import simulation.interfaces.Tick_Listener;
import simulation.process.Action;
import simulation.process.Decision;
import simulation.process.DecisionBasedOnChance;
import simulation.process.DecisionBasedOnCondition;
import simulation.process.Process;
import simulation.process.ProcessManager;
import simulation.process.Process_Priority;
import simulation.process.Release;
import simulation.process.Seize;
import simulation.process.Termination;
import simulation.process.commands.IsParkingSpotFullBooleanCommand;
import simulation.process.commands.GenerateProcessingTimeAccordingToDistributionCommand;
import simulation.process.commands.IncrementAmountOfRejects;


public class Model {

	final int AMOUNT_OF_DAYS_TO_RUN;
	final int AMOUNT_OF_CASH_REGISTERS = 3;
	final int AMOUNT_OF_CLEANING_SPOTS = 10;
	final int AMOUNT_OF_NOT_RESERVED_PARKING_SPOT = 20;
	final int AMOUNT_OF_RESERVED_PARKING_SPOT = 5;
	final int AMOUNT_OF_CLEANERS = 3;
	final int AMOUNT_OF_ASSISTANTS = 3;
	
	final double PROBABILITY_SHORT_CLEANING = 0.293; 
	final double PROBABILITY_LONG_CLEANING = 0.707;
	
	
	// Short cleaning normal distribution parameters
	final  double SHORT_CLEANING_NORMAL_SD_DEVIATION = 11.7;
	final  double SHORT_CLEANING_NORMAL_MEAN = 11.7;
	final  Distribution SHORT_CLEANING_NORMAL_DISTRIBUTION = new NormalDistribution(SHORT_CLEANING_NORMAL_SD_DEVIATION, SHORT_CLEANING_NORMAL_MEAN, new Random());
	
	
	// Long cleaning chi-square distribution parameters
	final  double LONG_CLEANING_GAMMA_MEAN = 191;
	final  double LONG_CLEANING_GAMMA_DEVIATION  = 24;	
	final  Distribution LONG_CLEANING_NORMAL_DISTRIBUTION = new GammaDistribution(LONG_CLEANING_GAMMA_MEAN, LONG_CLEANING_GAMMA_DEVIATION, new Random());
	
	
	public Model(int amountOfDaysToRun)
	{
		this.AMOUNT_OF_DAYS_TO_RUN = amountOfDaysToRun;
		
		// Generate model objects
		GenerateResources();
		GenerateProcesses();
		
		// Validate model
		Validate();
	}
	
	private void Validate() {
		
		try
		{
			EntityManager.GetInstance().Validate();
			ProcessManager.GetInstance().Validate();
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}

	public void GenerateResources()
	{
		ResourceManager.GetInstance().SetAmountOfCashRegisters(AMOUNT_OF_CASH_REGISTERS);
		ResourceManager.GetInstance().SetAmountOfCleaningSpots(AMOUNT_OF_CLEANING_SPOTS);
		ResourceManager.GetInstance().SetAmountOfReservedParkingSpots(AMOUNT_OF_NOT_RESERVED_PARKING_SPOT);
		ResourceManager.GetInstance().SetAmountOfNotReservedParkingSpots(AMOUNT_OF_RESERVED_PARKING_SPOT);
		ResourceManager.GetInstance().SetAmountOfAssistants(AMOUNT_OF_ASSISTANTS);
		ResourceManager.GetInstance().SetAmountOfCleaners(AMOUNT_OF_CLEANERS);
	}
		
	public void GenerateProcesses()
	{
		
		/**
		 * NOTE:
		 * - When using condition decisions, the first set sequenceLink is used when the condition evaluates to true  
		 * - When using the defined probabilities below, add the processes (short and long) in the same order.
		 * - For merging sequenceobjects queue, use the SharedQueue() function.
		 */
		

		/**
		 * =======================================================================================================
		 * 											EXAMPLE BELOW
		 * =======================================================================================================
		 */
		
		// Create references
				
			// Decisions
			DecisionBasedOnCondition isParkingLotFull = new DecisionBasedOnCondition("DECISION: IS PARKINGLOT FULL?", new IsParkingSpotFullBooleanCommand());
			DecisionBasedOnChance shortOrLongCleaning = new DecisionBasedOnChance("DECISION: LONG OR SHORT CLEANING?", PROBABILITY_SHORT_CLEANING, PROBABILITY_LONG_CLEANING);
		
			// Processes
//			Process process1 = new Process("SHORT CLEANING CAR", Process_Priority.Normal,new GenerateProcessingTimeAccordingToDistributionCommand(SHORT_CLEANING_NORMAL_DISTRIBUTION));
//			Process process2 = new Process("LONG CLEANING CAR", Process_Priority.Normal, new GenerateProcessingTimeAccordingToDistributionCommand(LONG_CLEANING_NORMAL_DISTRIBUTION));
		
			Process process1 = new Process("SHORT CLEANING CAR",5 );
			Process process2 = new Process("LONG CLEANING CAR", 10);
			
			// Terminators
			Termination termination1 = new Termination("End of the line baby (process1)");			
			Termination termination2 = new Termination("End of the line baby (process2)");	
			Termination termination3 = new Termination("End of the line baby (setEntityToRejected)");	
			
			// Actions
			Action setEntityToRejected = new Action("Set entity to rejected action", new IncrementAmountOfRejects());
			
			// Release
			Release release_process1 = new Release("RELEASE FOR PROCESS 1");
			Release release_process2 = new Release("RELEASE FOR PROCESS 2");
			
			// Seize
			Seize seize_process1 = new Seize("SEIZE FOR PROCESS 1");
			Seize seize_process2 = new Seize("SEIZE FOR PROCESS 2");
			
		
		// Set decisions
		isParkingLotFull.AddNextSequenceLink(setEntityToRejected);
		isParkingLotFull.AddNextSequenceLink(shortOrLongCleaning);
		
		
		shortOrLongCleaning.AddNextSequenceLink(seize_process1);
		shortOrLongCleaning.AddNextSequenceLink(seize_process2);
		
		
		// Set processes
		process1.AddNextSequenceLink(release_process1);
		process2.AddNextSequenceLink(release_process2);
		
		// Set Seize
		seize_process1.AddNextSequenceLink(process1);
		seize_process2.AddNextSequenceLink(process2);
		
		// Set Release
		release_process1.AddNextSequenceLink(termination1);
		release_process2.AddNextSequenceLink(termination2);
		
		// Set actions
		setEntityToRejected.AddNextSequenceLink(termination3);

		
		// Entity manager
		// Set starting process
		EntityManager.GetInstance().SetStartingSequenceObject(isParkingLotFull);
		EntityManager.GetInstance().StartGenerating();		
	}
	
	/**
	 * Call this method when the run is finished, it will retrieve the statistics about the run.
	 */
	public void Report()
	{
		// Retrieve info from entity manager
		double meanLeftRate = EntityManager.GetInstance().GetLeftRate();
		double meanWaitingRateUnder6Hours = EntityManager.GetInstance().GetWaitingTimeUnder6Hours();
		double meanCallsUnder2TimesCleaningTime = EntityManager.GetInstance().GetCallsUnder2TimesCleaningTime();
		double meanProcessingTime = EntityManager.GetInstance().GetProcessingTime();
		
		
		ResultManager.GetInstance().SetMeanCallsUnder2TimesCleaningTime(meanCallsUnder2TimesCleaningTime);
		ResultManager.GetInstance().SetMeanLeftRate(meanLeftRate);
		ResultManager.GetInstance().SetMeanWaitingRateUnder6Hours(meanWaitingRateUnder6Hours);
		ResultManager.GetInstance().SetMeanProcessingTime(meanProcessingTime);
	}
	
	public void Run()
	{
		System.out.print("MODEL: RUN STARTED \n");
		
		while(TimeManager.GetInstance().GetCurrentDay() < AMOUNT_OF_DAYS_TO_RUN)
		{
			while(ProcessManager.GetInstance().CanFire())
			{
				try
				{
					ProcessManager.GetInstance().Fire();
				}
				
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
			}
			

			/** If no processes can fire tell the TimeManager to tick.
			 * - Will generate new entities when TimeEvent Event-Type is 'ARRIVAL'.
			 * - Will cause a process to call it's 'End_Delay' method, releasing related resources and entities.
			 */
			TimeManager.GetInstance().Tick();
		} 
		
			
	}

	public void Reset()
	{
		// Reset all managers
		ProcessManager.GetInstance().Reset();
		QueueManager.GetInstance().Reset();
		ResourceManager.GetInstance().Reset();
		TimeManager.GetInstance().Reset();
		EntityManager.GetInstance().Reset();
		
	}
	
}

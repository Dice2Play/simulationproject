package Simulation.Model;

import Simulation.Enums.Queue_Priority;
import Simulation.Enums.Resource_Type;
import  Simulation.Interfaces.*;
import Simulation.Model.Resource.ResourceManager;
import Simulation.Model.Time.TimeManager;
import Simulation.Results.Result;
import Simulation.Results.ResultManager;
import Simulation.Model.Process.*;
import Simulation.Model.Process.Process;
import Simulation.Model.Queue.*;
import Simulation.Interfaces.*;
import Simulation.Model.Resource.*;

public class Model implements Tick_Listener {

	private final int amountOfTimeUnitsToRun;
	
	private int amountOfTimeUnitsPassed;
	
	private Queue queue_group;
	private Queue queue_single;

	private boolean useSingleQueue = false;

	public Model(int amountOfTimeUnitsToRun, boolean useSingleQueue)
	{
		// Set fields
		this.amountOfTimeUnitsToRun = amountOfTimeUnitsToRun;
		this.useSingleQueue = useSingleQueue;

		// Create model objects
		Create();
		
		// Set listeners
		TimeManager.AddTickListener(this);

	}
	
	private void Create()
	{
		
		// Create Resources/Queue/Processes
		// Resources
		ResourceManager.AddResource(new Boat("BOAT_1"));
		ResourceManager.AddResource(new Boat("BOAT_2"));
		
		// Queue's
		queue_group = new Queue(Queue_Priority.High, 1,8, "BOAT_GROUP_QUEUE");
		queue_single = new Queue(Queue_Priority.Low, 1,1, "BOAT_SINGLE_QUEUE");
		QueueManager.AddQueue(queue_group);
		QueueManager.AddQueue(queue_single);
		
		// Processes
		ProcessManager.AddProcess(new Process("Boattrip", 4 , Resource_Type.BOAT));
	}
	
	public void Run()
	{
		while(amountOfTimeUnitsPassed < amountOfTimeUnitsToRun)
		{
			// Print amount of time units passed
			TimeManager.PrintAmountOfTimePassed();
			
			// Save results from previous timeUnit
			Report();
			
			// Check if ProcessManager can fire any process
			// If so, fire processes
			if(ProcessManager.CanFire()){ ProcessManager.Fire();}
			
			// increment timeUnit, such that subscribed resourceManager can release resources.
			TimeManager.Tick();
			
		}
		
	}
	
	private void SetTimePassed(int timeValue)
	{
		amountOfTimeUnitsPassed = timeValue;	
	}
	
	/* an alternative design is that a separate queue and process is created, in order
	 * to spread to people across group queue and single queue.
	 */
	private void AddPeopleToQueues() throws Exception
	{
		double propabilities_k_groups_in_time_slot[] = new double[] {0.2, 0.6, 0.2}; /* 0, 1, 2 GROUPS */
		double propabilities_m_persons_in_group[] = new double[] {0.2, 0.2, 0.2, 0.2, 0.2}; /* 1, 2, 3, 4, 5 PERSONS */

		int nofGroups = Probability.Probability.get_random_index_using_probabilities(
				propabilities_k_groups_in_time_slot);

		for(int i = 0; i < nofGroups; i++) {
			int nofPersons;
			Queue queue;

			nofPersons = Probability.Probability.get_random_index_using_probabilities(
				propabilities_m_persons_in_group) + 1;  /* index 0, means 1 person */

			if (nofPersons == 1 && useSingleQueue) {
				queue = queue_single;
			} else {
				queue = queue_group;
			}
			queue.Add(new QueueObject(nofPersons, queue.GetID()));
		}
	}

	@Override
	public void Event_Tick(int timePassed) {
		try {
			AddPeopleToQueues();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SetTimePassed(timePassed);	
		
		if(ResourceManager.CheckIfAnyResourceCanBeReleased()) { ResourceManager.ReleaseResources();}
	
	}

	public void Reset() {
		QueueManager.Reset();
		ResourceManager.Reset();
		TimeManager.Reset();
		ProcessManager.Reset();
	}
	
	private void Report()
	{
		// Nothing of interest at timeUnit 0, so skip.
		if(amountOfTimeUnitsPassed > 0)
		{
			double meanBoatOccupancy = ResourceManager.GetResourceOccupancy();
			double waitingTimeArbitraryCustomer = QueueManager.GetWaitingTimeArbitraryCustomer();
			double totalQueuelength =  QueueManager.GetTotalQueueLength(); // Total number of people waiting
			ResultManager.AddResults(new Result(waitingTimeArbitraryCustomer,totalQueuelength,meanBoatOccupancy));
		}
	}
}

package Simulation.Model;
import  Simulation.Interfaces.*;
import Simulation.Model.Process.ProcessManager;
import Simulation.Model.Queue.QueueManager;
import Simulation.Model.Resource.Boat;
import Simulation.Model.Resource.ResourceManager;
import Simulation.Model.Time.TimeManager;
import Simulation.Interfaces.*;
import Simulation.Model.Resource.*;

public class Model implements Tick_Listener {

	private final TimeManager timeManager = new TimeManager();
	private final QueueManager queueManager = new QueueManager();
	private final ResourceManager resourceManager = new ResourceManager();
	private final ProcessManager processManager = new ProcessManager();

	private int amountOfTimeUnitsToRun;
	private int amountOfTimeUnitsPassed;
	
	public Model(int amountOfTimeUnitsToRun)
	{
		// Set fields
		this.amountOfTimeUnitsToRun = amountOfTimeUnitsToRun;
		
		// Create model objects
		Create();
		
		// Event handlers
		timeManager.AddTickListener(this);
	}
	
	private void Create()
	{
		
		// Create Resources/Queue/Processes
		resourceManager.AddResource(new Boat[] {new Boat("1"),
												new Boat("2"),
												new Boat("3"),
												new Boat("4"),
												new Boat("5"),
												new Boat("6"),
												new Boat("7"),
												new Boat("8"),
												new Boat("9"),
												new Boat("10")});
		
		
		
		
	}
	
	public void Run()
	{
		while(amountOfTimeUnitsPassed < amountOfTimeUnitsToRun)
		{
			// Print amount of timeUnitsPassed
			System.out.println(amountOfTimeUnitsPassed);
			System.out.println("Amount of time units passed :" + amountOfTimeUnitsPassed);
			
			// Check if ProcessManager can fire any process
			// If so, fire processes
			if(processManager.CanFire()){ processManager.Fire();}
			
			// Else increment timeUnit, such that subscribed resourceManager can release resources.
			else timeManager.Tick();
			
		}
		
	}
	
	

	@Override
	public void Event_Tick(int timePassed) {
		amountOfTimeUnitsPassed = timePassed;	
	}
	
	
}

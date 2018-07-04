package simulation.entity;

import java.util.ArrayList;
import java.util.List;

import simulation.interfaces.Tick_Listener;

public class EntityManager implements Tick_Listener{

	static List<Entity> entities = new ArrayList<Entity>();
	static Process startingProcess;
	
		
	private static void GenerateEntity()
	{
		
	}
	
	public static void SetStartingProcess(Process newStartingProcess)
	{
		startingProcess = newStartingProcess;
	}

	public void On_Tick() {
		GenerateEntity();
	}
	
	
}

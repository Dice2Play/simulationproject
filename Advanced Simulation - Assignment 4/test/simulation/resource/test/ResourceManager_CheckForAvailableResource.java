package simulation.resource.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import simulation.entity.EntityManager;
import simulation.process.ProcessManager;
import simulation.queue.QueueManager;
import simulation.resource.CashRegister;
import simulation.resource.ResourceManager;
import simulation.resource.Resource_Type;
import simulation.time.TimeManager;

class ResourceManager_CheckForAvailableResource {

	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Checks whether no resources are available on instantiate(NULL case)
	 */
	@Test
	void TestWhetherNoResourcesAreAvailableOnInstantiate() {

		boolean isResourceAvailable = ResourceManager.GetInstance().CheckForAvailableResource(Resource_Type.CASH_REGISTER);
		assertEquals(false, isResourceAvailable);
	}
	
	/**
	 * Create N amount of resources, seize N-1, check whether there is a resource available
	 */
	@Test
	void TestWhetherResourcesAreAvailableAfterSeizingAllButOne()
	{
		Random rand = new Random();
		int N = rand.nextInt(100);
		
		for(int i = 0; i < N; i++) {new CashRegister(String.format("CASH REGISTER %d", i));}
		
		try 
		{
			for(int ii = 0; ii < N - 1; ii++) { ResourceManager.GetInstance().GetAvailableResource(Resource_Type.CASH_REGISTER).Seize();}
		}
		
		catch(Exception ex) { ex.printStackTrace(); }
		
		
		boolean isThereAnAvailableResource = ResourceManager.GetInstance().CheckForAvailableResource(Resource_Type.CASH_REGISTER);
		
		assertEquals(true,isThereAnAvailableResource);
		
	}
	
	/**
	 * Create N amount of resources, seize N, check whether no resources are available
	 */
	@Test
	void TestWhetherResourcesAreAvailableAfterSeizingAll()
	{
		Random rand = new Random();
		int N = rand.nextInt(100);
		
		for(int i = 0; i < N; i++) {new CashRegister(String.format("CASH REGISTER %d", i));}
		
		try 
		{
			for(int ii = 0; ii < N; ii++) { ResourceManager.GetInstance().GetAvailableResource(Resource_Type.CASH_REGISTER).Seize();}
		}
		
		catch(Exception ex) { ex.printStackTrace(); }
		
		
		boolean isThereAnAvailableResource = ResourceManager.GetInstance().CheckForAvailableResource(Resource_Type.CASH_REGISTER);
		
		assertEquals(false,isThereAnAvailableResource);
	}
	
	

	
	
	

}

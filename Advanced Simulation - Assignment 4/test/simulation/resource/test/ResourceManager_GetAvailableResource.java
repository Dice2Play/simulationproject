package simulation.resource.test;

import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.jupiter.api.function.*;

import simulation.entity.EntityManager;
import simulation.process.ProcessManager;
import simulation.queue.QueueManager;
import simulation.resource.ParkingSpotReserved;
import simulation.resource.Resource;
import simulation.resource.ResourceManager;
import simulation.resource.Resource_Type;
import simulation.time.TimeManager;

class ResourceManager_GetAvailableResource {

		

	@BeforeEach
	void ResetManagers()
	{
		ResourceManager.GetInstance().Reset();
	}
	
	/**
	 * NULL case, create no resources, try to get a resource_type
	 * @throws Exception 
	 */
	@Test
	void TestWhetherResourceIsAvailableOnInstantiate() 
	{
		Assertions.assertThrows(Exception.class, () -> ResourceManager.GetInstance().GetAvailableResource(Resource_Type.CASH_REGISTER));
	}
	
	/**
	 * NULL case, create resources except the one specified resource_type
	 */
	@Test
	void TestWhetherResourceIsAvailableNoneCreated()
	{
		
		Resource resource = new ParkingSpotReserved("Parking spot 1");
		Assertions.assertThrows(Exception.class, () -> ResourceManager.GetInstance().GetAvailableResource(Resource_Type.CASH_REGISTER));
	}
	
	/**
	 * Positive case, create specified resource and return it
	 */
	@Test
	void TestWhetherResourceIsAvailable()
	{
		
		try {
			Resource resource = new ParkingSpotReserved("Parking spot 1");
			Assertions.assertEquals(resource, ResourceManager.GetInstance().GetAvailableResource(Resource_Type.PARKING_SPOT_AVAILABLE_AND_RESERVED));
		} 
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
}

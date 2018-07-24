package simulation.resource.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import simulation.process.Process_Priority;
import simulation.resource.ParkingSpotReserved;
import simulation.resource.ResourceManager;

class ResourceManager_MaintenanceProcess {

	@BeforeEach
	void setUp()  {
		
		ParkingSpotReserved parkingSpot = new ParkingSpotReserved("Parking spot");
		simulation.process.Process someProcessUsingAParkingSpot = new simulation.process.Process("I NEED A PARKING SPOT PROCESS", Process_Priority.Normal, 1);
		
		
		
		
	}

	@Test
	void CheckWhetherMaintenanceProcessIsGeneratedAfterTenUses() {
		fail("Not yet implemented");
	}

	@Test
	void CheckWhetherMaintenanceProcessIsGeneratedAfterTenUsesAndParkingSpotCantBeUsed() {
		fail("Not yet implemented");
	}
	
	@Test
	void CheckWhetherAfterMaintenanceProcessParkingSpotIsAvailableAgain() {
		fail("Not yet implemented");
	}
	
}

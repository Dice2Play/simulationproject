package simulation.resource.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

class ResourceManager_GetAvailableResource {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@BeforeEach
	void setUp() throws Exception {
	}

	

	/**
	 * NULL case, create no resources, try to get one of a random resource_type
	 */
	@Test
	void TestWhetherResourceIsAvailableOnInstantiate()
	{
		exception.expect(Exception.class);
		fail("Not yet implemented");
	}
	
	/**
	 * NULL case, create resources except the one specified resource_type
	 */
	@Test
	void TestWhetherResourceIsAvailableNoneCreated()
	{
		fail("Not yet implemented");
	}
	
	/**
	 * Positive case, create specified resource and return it
	 */
	@Test
	void TestWhetherResourceIsAvailable()
	{
		fail("Not yet implemented");
	}
	
	
	
}

package simulation.process.commands;

import simulation.interfaces.BooleanCommand;
import simulation.resource.ResourceManager;
import simulation.resource.Resource_Type;

public class IsParkingSpotFullBooleanCommand implements BooleanCommand{


	@Override
	public boolean Execute() {
				
		return !ResourceManager.GetInstance().CheckForAvailableResource(Resource_Type.PARKING_SPOT_AVAILABLE_AND_NOT_RESERVED);
	}

	
}

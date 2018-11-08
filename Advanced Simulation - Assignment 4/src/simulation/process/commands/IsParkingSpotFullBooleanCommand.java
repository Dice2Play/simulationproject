package simulation.process.commands;

import simulation.interfaces.BooleanCommand;
import simulation.resource.ResourceManager;
import simulation.resource.Resource_Type;
import simulation.entity.EntityManager;

public class IsParkingSpotFullBooleanCommand implements BooleanCommand{


	@Override
	public boolean Execute() {
		boolean checkingStatus = false;
		if(!ResourceManager.GetInstance().CheckForAvailableResource(Resource_Type.PARKING_SPOT_AVAILABLE_AND_NOT_RESERVED))
		{
			checkingStatus = false;
			System.out.println( "Sorry, at this moment the parking sprot is not avaliable, we are full" );
			EntityManager.GetInstance().IncrementAmountOfRejects();
		}else
		{
			checkingStatus = true;
			System.out.println( "Welcome! please park your car at the spot" );
		}
				
		return checkingStatus;
	}

	
}

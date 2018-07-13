package simulation.entity;

import simulation.interfaces.Command;
import simulation.process.SequenceObject;


public class GenerateEntityCommand implements Command
{
		Entity entityToAdd;
		SequenceObject startingSequenceObjectWhereEntityNeedsToBeAddedTo;		
		
		public GenerateEntityCommand(Entity entityToAdd, SequenceObject startingSequenceObjectWhereEntityNeedsToBeAddedTo)
		{
			this.entityToAdd = entityToAdd;
			this.startingSequenceObjectWhereEntityNeedsToBeAddedTo = startingSequenceObjectWhereEntityNeedsToBeAddedTo;
		}
		
		@Override
		public void Execute() {
			
			
			// Register entity to ENTITYMANAGER
			EntityManager.GetInstance().RegisterEntity(entityToAdd);
			
			// Add entity to PROCESS
			startingSequenceObjectWhereEntityNeedsToBeAddedTo.AddEntityToQueue(entityToAdd);
			
		}
	
}

package simulation.entity;

import simulation.interfaces.Command;


public class GenerateEntityCommand implements Command
{
		Entity entityToAdd;
		simulation.process.Process processWhereEntityNeedsToBeAddedTo;		
		
		public GenerateEntityCommand(Entity entityToAdd, simulation.process.Process processWhereEntityNeedsToBeAddedTo)
		{
			this.entityToAdd = entityToAdd;
			this.processWhereEntityNeedsToBeAddedTo = processWhereEntityNeedsToBeAddedTo;
		}
		
		@Override
		public void Execute() {
			
			
			// Register entity to ENTITYMANAGER
			EntityManager.GetInstance().AddEntity(entityToAdd);
			
			// Add entity to PROCESS
			processWhereEntityNeedsToBeAddedTo.AddEntityToQueue(entityToAdd);
			
		}
	
}

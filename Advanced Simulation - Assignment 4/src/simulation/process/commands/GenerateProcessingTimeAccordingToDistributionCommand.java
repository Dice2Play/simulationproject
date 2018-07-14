package simulation.process.commands;

import Statistics.Distribution;
import simulation.interfaces.DoubleCommand;

public class GenerateProcessingTimeAccordingToDistributionCommand implements DoubleCommand {

	private Distribution distributionUsedForGeneratingProcesstime;
	
	public GenerateProcessingTimeAccordingToDistributionCommand(Distribution distributionUsedForGeneratingProcesstime)
	{
		this.distributionUsedForGeneratingProcesstime = distributionUsedForGeneratingProcesstime;
	}
	
	@Override
	public double Execute() {
		return Statistics.Statistics.GetDistributionResult(distributionUsedForGeneratingProcesstime);
	}

}

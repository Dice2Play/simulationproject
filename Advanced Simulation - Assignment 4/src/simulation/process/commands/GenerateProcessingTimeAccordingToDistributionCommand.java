package simulation.process.commands;

import simulation.interfaces.DoubleCommand;
import statistics.Distribution;

public class GenerateProcessingTimeAccordingToDistributionCommand implements DoubleCommand {

	private Distribution distributionUsedForGeneratingProcesstime;
	
	public GenerateProcessingTimeAccordingToDistributionCommand(Distribution distributionUsedForGeneratingProcesstime)
	{
		this.distributionUsedForGeneratingProcesstime = distributionUsedForGeneratingProcesstime;
	}
	
	@Override
	public double Execute() {
		return statistics.Statistics.GetDistributionResult(distributionUsedForGeneratingProcesstime);
	}

}

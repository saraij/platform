package com.sarai.platform.aggregator;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

import com.sarai.platform.domain.Ballot;

public class AggregationTask implements Callable<Map<String,Integer>>
{
	private final Set<Ballot> countables;
	
	public AggregationTask(Set<Ballot> countables)
	{
		this.countables = countables;
	}
	
	public Map<String, Integer> call() throws Exception 
	{
		Aggregator<Ballot> aggregator = new Aggregator<Ballot>();
		
		return aggregator.aggregateWithCandidateIteration(countables);
	}
}

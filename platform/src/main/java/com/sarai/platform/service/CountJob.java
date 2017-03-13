package com.sarai.platform.service;

import java.util.Map;
import java.util.Set;

import com.sarai.platform.aggregator.Aggregator;
import com.sarai.platform.domain.Ballot;

public class CountJob extends SimpleJob
{
	public CountJob(int identifier) 
	{
		super(identifier);
	}

	private final Aggregator<Ballot> aggregator = new Aggregator<Ballot>();
	
	public Action getAction() 
	{
		return Action.COUNT;
	}
	
	public Map<String,Integer> count(Set<Ballot> ballots)
	{
		return aggregator.aggregateWithCandidateIteration(ballots);
	}
}

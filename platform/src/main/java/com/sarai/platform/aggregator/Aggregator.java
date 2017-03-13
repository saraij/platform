package com.sarai.platform.aggregator;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.sarai.platform.domain.Ballot;
import com.sarai.platform.domain.Candidate;
import com.sarai.platform.domain.Countable;

public class Aggregator<T extends Countable<String,Integer>> 
{
	/* worker implementation for summing votes according to candidate name using various strategies */
	
	public Map<String,Integer> aggregate(Set<T> countables)
	{
		Map<String,Integer> aggregateMap = new HashMap<String,Integer>();
		
		for (Countable<String,Integer> countable : countables)
		{
			Map<String,Integer> countableMap = countable.count();
			
			for (Map.Entry<String, Integer> entry : countableMap.entrySet())
			{
				if (!aggregateMap.containsKey(entry.getKey()))
				{	
					aggregateMap.put(entry.getKey(), entry.getValue());
				}
				
				int count = aggregateMap.get(entry.getKey());
				
				aggregateMap.put(entry.getKey(), count + entry.getValue());
			}
		}
		
		return aggregateMap;
	}
	
	public Map<String,Integer> aggregateWithCandidateIteration(Set<Ballot> ballots)
	{
		Map<String,Integer> aggregateMap = new HashMap<String,Integer>();
		
		for (Ballot ballot : ballots)
		{
			for (Candidate nominee : ballot.getNominations())
			{
				if (!aggregateMap.containsKey(nominee.getName()))
				{
					aggregateMap.put(nominee.getName(), 0);
				}
				
				int count = aggregateMap.get(nominee.getName()).intValue();
				
				aggregateMap.put(nominee.getName(), count + 1);
			}
		}
		
		return aggregateMap;
	}
	
	public Map<String,Integer> aggregateWithCandidateArray(Candidate[] candidates, Set<Ballot> ballots)
	{
		int[] candidateCount = new int[candidates.length];
		
		Map<Candidate,Integer> candidateMap = new LinkedHashMap<Candidate,Integer>();
		
		for (int i = 0; i < candidates.length; i++)
		{
			candidateMap.put(candidates[i], i);
			
			candidateCount[i] = 0;
		}
		
		Map<String,Integer> aggregateMap = new HashMap<String,Integer>();
		
		for (Ballot ballot : ballots)
		{
			for (Candidate nominee : ballot.getNominations())
			{
				candidateCount[candidateMap.get(nominee)] += 1;
			}
		}
		
		for (int i = 0; i < candidates.length; i++)
		{
			aggregateMap.put(candidates[i].getName(), candidateCount[i]);
		}
		
		return aggregateMap;
	}
	
	public Map<String,Integer> aggregateWithBallotDelegation(Set<Ballot> ballots)
	{
		Map<String,Integer> aggregateMap = new HashMap<String,Integer>();
		
		for (Ballot ballot : ballots)
		{
			Map<String,Integer> tally = ballot.count();
			
			for (Map.Entry<String, Integer> entry : tally.entrySet())
			{
				if (!aggregateMap.containsKey(entry.getKey()))
				{
					aggregateMap.put(entry.getKey(), 0);
				}
				
				int count = aggregateMap.get(entry.getKey()).intValue();
				
				aggregateMap.put(entry.getKey(), count + 1);
			}
		}
		
		return aggregateMap;
	}
}

package com.sarai.platform.aggregator;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.sarai.platform.domain.Ballot;
import com.sarai.platform.domain.Candidate;

public final class BallotAllocator
{
	/* creates a set of Ballot objects for a prescribed pair of candidate and allocation arrays and given population */
	
	public Set<Ballot> allocateConstantCandidate(Candidate[] candidates, double[] allocation, int population)
	{
		Map<Candidate,Integer> absoluteAllocationMap = new LinkedHashMap<Candidate,Integer>();
		
		for (int i = 0; i < candidates.length; i++)
		{
			Double votes = allocation[i] * population;
			
			absoluteAllocationMap.put(candidates[i], votes.intValue());
		}
		
		Set<Ballot> ballots = new HashSet<Ballot>();
		
		int id = 1;
		
		for (Candidate candidate : candidates)
		{	
			int candidateCount = 0;
			
			for (int i = 0; i < absoluteAllocationMap.get(candidate); i++)
			{
				Ballot ballot = new Ballot(id);
				
				for (int j = 0 ; j < 3; j++)
				{
					if (candidateCount < absoluteAllocationMap.get(candidate))
					{	
						ballot.nominate(candidate);
						
						candidateCount++;
					}
				}
				
				ballots.add(ballot);
				
				id++;
				
				if (candidateCount == absoluteAllocationMap.get(candidate))
				{
					i = absoluteAllocationMap.get(candidate);
				}
			}
		}
		
		return ballots;
	}
	
	public Set<Ballot> allocateIteratingCandidate(Candidate[] candidates, double[] allocation, int population)
	{
		Map<Candidate,Integer> absoluteAllocationMap = new LinkedHashMap<Candidate,Integer>();
		
		for (int i = 0; i < candidates.length; i++)
		{
			Double votes = allocation[i] * population;
			
			absoluteAllocationMap.put(candidates[i], votes.intValue());
		}
		
		Set<Ballot> ballots = new HashSet<Ballot>();
		
		int id = 0;
		
		int candidateCounter[] = new int[candidates.length];
		
		for (int i = 0; i < candidates.length; i++)
		{	
			candidateCounter[i] = 0;
		}
		
		for (int i = 0; i < population; i++)
		{
			Ballot ballot = new Ballot(id);
			
			int j = 0;
			
			for (Candidate candidate : candidates)
			{
				if (candidateCounter[j] < absoluteAllocationMap.get(candidate))
				{
					boolean hasCast = ballot.nominate(candidate);
					
					if (hasCast)
					{
						int currentCount = 0;
						
						for (int k = 0; k < candidates.length; k++)
						{	
							currentCount += candidateCounter[k];
						}
						
						i = currentCount;
						
						candidateCounter[j]++;
					}
				}
				
				j++;
			}
			
			ballots.add(ballot);
			
			id++;
		}
		
		return ballots;
	}
}

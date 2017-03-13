package com.sarai.platform.aggregator;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.sarai.platform.domain.Ballot;
import com.sarai.platform.domain.Candidate;

public final class BallotBox
{
	private final Map<Integer,Ballot> map;
	
	public BallotBox()
	{
		map = new ConcurrentHashMap<Integer, Ballot>();
	}
	
	public void addBallot(Ballot ballot)
	{
		if (!map.containsKey(ballot.getVoterIdentifier()))
		{
			map.put(ballot.getVoterIdentifier(), ballot);
		}
		else
		{
			for (Candidate candidate : ballot.getNominations())
			{
				map.get(ballot.getVoterIdentifier()).nominate(candidate);
			}
		}
	}
	
	public Map<String,Integer> declareResult()
	{
		return tally(map.values());
	}
	
	public Set<Ballot> getBallots()
	{
		return Collections.unmodifiableSet(new HashSet<Ballot>(map.values()));
	}
	
	private Map<String,Integer> tally(Collection<Ballot> ballots)
	{
		Map<String,Integer> tally = new HashMap<String,Integer>();
		
		for (Ballot ballot : ballots)
		{
			for (Candidate nominee : ballot.getNominations())
			{
				if (!tally.containsKey(nominee.getName()))
				{
					tally.put(nominee.getName(), 0);
				}

				int count = tally.get(nominee.getName()).intValue();
				
				tally.put(nominee.getName(), count + 1);
			}
		}
		
		return Collections.unmodifiableMap(tally);
	}
	
	public void empty()
	{
		map.clear();
	}
	
	@Override
	public String toString()
	{
		return "ballot box of size " + map.size();
	}
}

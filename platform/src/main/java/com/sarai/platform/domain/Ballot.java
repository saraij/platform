package com.sarai.platform.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Ballot implements Countable<String,Integer>
{
	/* container for voter identifier and up to three nominations */
	
	private final int voterIdentifier;
	
	private final List<Candidate> nominations;
	
	public Ballot(int voterIdentifier)
	{
		this.voterIdentifier = voterIdentifier;
		this.nominations = new ArrayList<Candidate>(3);
	}
	
	public int getVoterIdentifier() 
	{
		return voterIdentifier;
	}

	public List<Candidate> getNominations() 
	{
		return nominations;
	}

	public boolean nominate(Candidate candidate)
	{
		if (nominations.size() == 3)
		{
			return false;
		}
		
		return nominations.add(candidate);
	}

	public Map<String,Integer> count() 
	{
		Map<String,Integer> countMap = new HashMap<String,Integer>();
		
		for (Candidate nominee : nominations)
		{
			if (!countMap.containsKey(nominee.getName()))
			{
				countMap.put(nominee.getName(), 0);
			}

			int count = countMap.get(nominee.getName()).intValue();
			
			countMap.put(nominee.getName(), count + 1);
		}
		
		return countMap;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nominations == null) ? 0 : nominations.hashCode());
		result = prime * result + voterIdentifier;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Ballot other = (Ballot) obj;
		if (nominations == null) {
			if (other.nominations != null) {
				return false;
			}
		} else if (!nominations.equals(other.nominations)) {
			return false;
		}
		if (voterIdentifier != other.voterIdentifier) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString()
	{
		return "voterIdentifier=" + voterIdentifier + ", nominations=" + nominations;
	}
}

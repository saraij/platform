package com.sarai.platform.aggregator;

import java.util.HashSet;
import java.util.Set;

public final class SubsetFactory 
{
	public static <T> Set<Set<T>> createSubSets(Set<T> set, Integer subSetSize)
	{
		Set<Set<T>> subSets = new HashSet<Set<T>>();
		
		int counter = 0;
		
		Set<T> subSet = new HashSet<T>(subSetSize);
		
		for (T element : set)
		{
			subSet.add(element);
			
			counter++;
			
			if (counter == subSetSize)
			{
				Set<T> aSubSet = new HashSet<T>(subSet);
				
				subSets.add(aSubSet);
				
				subSet.clear();
				
				counter = 0;
			}
		}
		
		if (!subSet.isEmpty())
		{
			subSets.add(subSet);
		}
		
		return subSets;
	}
}

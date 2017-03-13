package com.sarai.platform.aggregator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.sarai.platform.domain.Ballot;

public class AggregationManager
{
	public Map<String,Integer> aggregate(Set<Set<Ballot>> countables)
	{
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		
		final Map<String,Integer> aggregateMap = new HashMap<String,Integer>();
		
		List<Future<Map<String,Integer>>> results = new ArrayList<Future<Map<String,Integer>>>();
		
		for (Set<Ballot> ballots : countables)
		{
			AggregationTask associationTask = new AggregationTask(ballots);
			
			Future<Map<String,Integer>> future = executorService.submit(associationTask);
			
			results.add(future);
		}
		
		for (Future<Map<String,Integer>> result : results)
		{
			try
			{
				for (Map.Entry<String, Integer> entry : result.get().entrySet())
				{
					if (!aggregateMap.containsKey(entry.getKey()))
					{	
						aggregateMap.put(entry.getKey(), 0);
					}
					
					int count = aggregateMap.get(entry.getKey());
					
					aggregateMap.put(entry.getKey(), count + entry.getValue());
				}
			}
			catch (Exception exception)
			{
				throw new RuntimeException("Failed to retrieve result.");
			}
		}
		
		executorService.shutdown();
		
		return aggregateMap;
	}
}

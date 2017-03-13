package com.sarai.platform.aggregator;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.sarai.platform.domain.Ballot;
import com.sarai.platform.domain.Candidate;

public final class AggregatorTest
{
	private final Candidate candidateOne = new Candidate((byte)1, "one");
	private final Candidate candidateTwo = new Candidate((byte)2, "two");
	private final Candidate candidateThree = new Candidate((byte)3, "three");
	private final Candidate candidateFour = new Candidate((byte)4, "four");
	private final Candidate candidateFive = new Candidate((byte)5, "five");
	
	private final Candidate[] candidates = new Candidate[]{candidateOne, candidateTwo, candidateThree, candidateFour, candidateFive};
	
	private final int population = 10000000;
	
	private double[] allocation = new double[]{0.05, 0.10, 0.20, 0.25, 0.40};
	
	@Test
	public void aggregate()
	{
		/* satisfies performance benchmark of < 1000 millis invoked on AMD A8-6410 APU 2.0 GHz processor */
		
		BallotAllocator allocator = new BallotAllocator();
		
		Set<Ballot> ballots = allocator.allocateIteratingCandidate(candidates, allocation, population);
		
		assertTrue(ballots.size() == 5000000);
		
		Aggregator<Ballot> aggregator = new Aggregator<Ballot>();
		
		long start = System.currentTimeMillis();
		
		Map<String, Integer> result = aggregator.aggregateWithCandidateArray(candidates, ballots);
		
		long stop = System.currentTimeMillis();
		
		System.out.println(stop - start);
		
		System.out.println(result);
		
		assertEquals(result.get("one"), new Integer(500000));
		assertEquals(result.get("two"), new Integer(1000000));
		assertEquals(result.get("three"), new Integer(2000000));
		assertEquals(result.get("four"), new Integer(2500000));
		assertEquals(result.get("five"), new Integer(4000000));
	}
	
	@Test
	public void testConcurrentAggregate() 
	{
		/* not optimized */
		
		BallotAllocator allocator = new BallotAllocator();
		
		Set<Ballot> ballots = allocator.allocateIteratingCandidate(candidates, allocation, population);
		
		System.out.println(ballots.size());
		
		Set<Set<Ballot>> ballotSets = SubsetFactory.createSubSets(ballots, 100000);
		
		AggregationManager manager = new AggregationManager();
		
		long start = System.currentTimeMillis();
		
		Map<String, Integer> result = manager.aggregate(ballotSets);
		
		long stop = System.currentTimeMillis();
		
		System.out.println(stop - start);
		
		System.out.println(result);
	}
}

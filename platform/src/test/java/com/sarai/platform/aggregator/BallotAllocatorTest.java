package com.sarai.platform.aggregator;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.sarai.platform.domain.Ballot;
import com.sarai.platform.domain.Candidate;

public final class BallotAllocatorTest
{
	private final BallotAllocator allocator = new BallotAllocator();
	
	private final Aggregator<Ballot> aggregator = new Aggregator<Ballot>();
	
	@Test
	public void testAllocateConstantCandidate() 
	{
		Candidate candidateA = new Candidate((byte)1, "a");
		Candidate candidateB = new Candidate((byte)2, "b");
		
		int population = 10;
		
		double[] allocation = new double[]{0.1, 0.9};
		
		Set<Ballot> ballots = allocator.allocateConstantCandidate(new Candidate[]{candidateA, candidateB}, allocation, population);
		
		assertTrue(ballots.size() == 4);
		
		Map<String,Integer> countMap = aggregator.aggregateWithCandidateArray(new Candidate[]{candidateA, candidateB}, ballots);
		
		assertEquals(countMap.get("a"), new Integer(1));
		assertEquals(countMap.get("b"), new Integer(9));
	}

	@Test
	public void testAllocateIteratingCandidate() 
	{
		Candidate candidateA = new Candidate((byte)1, "a");
		Candidate candidateB = new Candidate((byte)2, "b");
		Candidate candidateC = new Candidate((byte)3, "c");
		
		int population = 10;
		
		double[] allocation = new double[]{0.1, 0.4, 0.5};
		
		Set<Ballot> ballots = allocator.allocateIteratingCandidate(new Candidate[]{candidateA, candidateB, candidateC}, allocation, population);
		
		assertTrue(ballots.size() == 5);
		
		Map<String,Integer> countMap = aggregator.aggregateWithCandidateArray(new Candidate[]{candidateA, candidateB, candidateC}, ballots);
		
		assertEquals(countMap.get("a"), new Integer(1));
		assertEquals(countMap.get("b"), new Integer(4));
		assertEquals(countMap.get("c"), new Integer(5));
	}
}

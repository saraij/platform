package com.sarai.platform.domain;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public final class BallotTest 
{
	@Test
	public void testGetVoterIdentifier()
	{
		Ballot ballot = new Ballot(1);
		
		assertTrue(ballot.getVoterIdentifier() == 1);
	}

	@Test
	public void testGetNominations() 
	{
		Ballot ballot = new Ballot(1);
		
		assertTrue(ballot.getNominations().isEmpty());
		
		Candidate candidate = new Candidate((byte) 1, "name");
		
		ballot.nominate(candidate);
		
		assertTrue(ballot.getNominations().size() == 1);
	}

	@Test
	public void testNominate() 
	{
		Ballot ballot = new Ballot(1);
		
		assertTrue(ballot.getNominations().isEmpty());
		
		Candidate candidateOne = new Candidate((byte) 1, "a");
		
		ballot.nominate(candidateOne);

		Candidate candidateTwo = new Candidate((byte) 2, "b");
		
		ballot.nominate(candidateTwo);
		
		Candidate candidateThree = new Candidate((byte) 3, "c");
		
		ballot.nominate(candidateThree);
		
		assertTrue(ballot.getNominations().size() == 3);
		
		assertFalse(ballot.nominate(new Candidate((byte) 4, "d")));
	}

	@Test
	public void testCount()
	{
		Ballot ballot = new Ballot(1);
		
		assertTrue(ballot.getNominations().isEmpty());
		
		Candidate candidateOne = new Candidate((byte) 1, "a");
		
		ballot.nominate(candidateOne);

		Candidate candidateTwo = new Candidate((byte) 2, "b");
		
		ballot.nominate(candidateTwo);
		
		Candidate candidateThree = new Candidate((byte) 3, "c");
		
		ballot.nominate(candidateThree);
		
		Map<String,Integer> map = ballot.count();
		
		assertTrue(map.size() == 3);
		
		assertEquals(map.get("a"), new Integer(1));
		assertEquals(map.get("b"), new Integer(1));
		assertEquals(map.get("c"), new Integer(1));
	}
}

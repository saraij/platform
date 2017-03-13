package com.sarai.platform.service;

import com.sarai.platform.domain.Ballot;
import com.sarai.platform.domain.Candidate;

public class VoteJob extends SimpleJob
{
	private Ballot ballot;
	
	public VoteJob(int voterIdentifier, Candidate candidate)
	{
		super(voterIdentifier);
		
		this.ballot = new Ballot(voterIdentifier);
		
		ballot.nominate(candidate);
	}
	
	public int getIdentifier() 
	{
		return 0;
	}

	public Action getAction() 
	{
		return Action.VOTE;
	}

	public Ballot getBallot() 
	{
		return ballot;
	}
}

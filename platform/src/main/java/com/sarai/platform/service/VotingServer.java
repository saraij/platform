package com.sarai.platform.service;

import java.util.Map;

import com.sarai.platform.aggregator.BallotBox;
import com.sarai.platform.domain.Ballot;

public final class VotingServer implements VotingService
{
	private final BallotBox ballotBox = new BallotBox();
	
	public JobResult process(Job job)
	{
		switch (job.getAction())
		{
			case COUNT : return processCountJob(job);
			case VOTE : return processVoteJob(job);
			default : throw new IllegalArgumentException("Invalid job submission " + job.getAction());
		}
	}
	
	private JobResult processCountJob(Job job)
	{
		CountJob aJob = (CountJob)job;
		
		Map<String,Integer> resultMap = aJob.count(ballotBox.getBallots());
		
		return new CountResult(job.getIdentifier(), resultMap);
	}
	
	private JobResult processVoteJob(Job job)
	{
		VoteJob aJob = (VoteJob)job;
		
		Ballot ballot = aJob.getBallot();
		
		ballotBox.addBallot(ballot);
		
		return new VotingResult(job.getIdentifier());
	}
}

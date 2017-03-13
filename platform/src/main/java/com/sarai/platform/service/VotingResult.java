package com.sarai.platform.service;

public class VotingResult extends VotingJobResult<Boolean>
{
	public VotingResult(int identifier) 
	{
		super(identifier);
	}

	@Override
	protected Boolean getResponse()
	{
		return true;
	}
}

package com.sarai.platform.service;

public abstract class VotingJobResult<T> implements JobResult
{
	private final int identifier;
	
	public VotingJobResult(int identifier)
	{
		this.identifier = identifier;
	}
	
	public int getIdentifier()
	{
		return identifier;
	}
	
	protected abstract T getResponse();
}

package com.sarai.platform.service;

public abstract class SimpleJob implements Job
{
	private final int identifier;
	
	public SimpleJob(int identifier)
	{
		this.identifier = identifier;
	}
	
	public int getIdentifier() 
	{
		return identifier;
	}
}

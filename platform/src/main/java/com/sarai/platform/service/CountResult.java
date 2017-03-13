package com.sarai.platform.service;

import java.util.Map;

public class CountResult extends VotingJobResult<Map<String,Integer>>
{
	private Map<String,Integer> resultMap;
	
	public CountResult(int identifier, Map<String,Integer> resultMap) 
	{
		super(identifier);
		
		this.resultMap = resultMap;
	}

	@Override
	protected Map<String,Integer> getResponse() 
	{
		return resultMap;
	}
}

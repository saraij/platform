package com.sarai.platform.domain;

public final class Candidate 
{
	private final byte identifier;
	private final String name;
	
	public Candidate(byte identifier, String name)
	{
		this.identifier = identifier;
		this.name = name;
	}

	public byte getIdentifier() 
	{
		return identifier;
	}

	public String getName() 
	{
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + identifier;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Candidate other = (Candidate) obj;
		if (identifier != other.identifier)
			return false;
		return true;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}

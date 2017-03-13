package com.sarai.platform.aggregator;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public final class SubsetFactoryTest 
{
	@Test
	public void testCreateSubSet() 
	{
		Set<Integer> integerSet = new HashSet<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
		
		Set<Set<Integer>> subSets = SubsetFactory.createSubSets(integerSet, 2);
		
		assertEquals(5, subSets.size());
	}
}

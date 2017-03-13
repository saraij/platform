package com.sarai.platform.domain;

import java.util.Map;

public interface Countable<K,V> 
{
	Map<K,V> count();
}

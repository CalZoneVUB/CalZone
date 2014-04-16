package com.vub.scheduler;

/**
 * The pair datastructure. (This is a helper class.)
 * 
 * @author pieter
 * 
 * @param <T>
 *            Type of the first value.
 * @param <V>
 *            Type of the second value.
 */
public class Pair<T, V> {
	public T first;
	public V second;

	/**
	 * Creates a new pair instance.
	 * 
	 * @param first
	 *            The first value.
	 * @param second
	 *            The second value.
	 */
	public Pair(T first, V second) {
		this.first = first;
		this.second = second;
	}
}

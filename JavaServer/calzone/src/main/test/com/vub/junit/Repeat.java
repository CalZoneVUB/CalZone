package com.vub.junit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Implementation of an extra annotation 'Repeat' for the unit testing.
 * 
 * <p>
 * Annotation that allows the execution of a unit test multiple times.
 * </p>
 * 
 * @author pieter
 * 
 * @see <a
 *      href="http://codehowtos.blogspot.be/2011/04/run-junit-test-repeatedly.html">
 *      http://codehowtos.blogspot.be/2011/04/run-junit-test-repeatedly.html</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Repeat {

	int value();

}

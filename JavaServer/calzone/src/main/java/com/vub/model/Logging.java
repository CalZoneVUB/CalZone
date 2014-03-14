package com.vub.model;

import org.apache.log4j.PropertyConfigurator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logging {
	final Logger logger = LoggerFactory.getLogger(getClass());
	public void f(){
		//PropertyConfigurator.configure("log4j.properties"); //TODO: check whether this call is necessary
		logger.info("Hello World");
		logger.info("This is a logging test");
		logger.debug("This is a debug log with a parameter at the end: {}", 5);
		logger.error("This is an error message with parameter at the end: {}", 6);
	}
}

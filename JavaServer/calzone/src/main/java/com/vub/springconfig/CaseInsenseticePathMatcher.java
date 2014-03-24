package com.vub.springconfig;

import java.util.Map;

import org.springframework.util.AntPathMatcher;

/**
 * Class for case insensitive url matchting.
 * 
 * Source: http://stackoverflow.com/questions/4150039/how-can-i-have-case-insensitive-urls-in-spring-mvc-with-annotated-mappings
 * 
 * @author pieter
 *
 */
public class CaseInsenseticePathMatcher extends AntPathMatcher {
	@Override
	protected boolean doMatch(String pattern, String path, boolean fullMatch,
			Map<String, String> uriTemplateVariables) {
		System.err.println(pattern + " -- " + path);
		return super.doMatch(pattern.toLowerCase(), path.toLowerCase(),
				fullMatch, uriTemplateVariables);
	}
}

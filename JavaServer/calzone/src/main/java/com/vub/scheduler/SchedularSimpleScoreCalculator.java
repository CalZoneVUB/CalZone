package com.vub.scheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.impl.score.director.simple.SimpleScoreCalculator;

import com.vub.model.CourseComponent;
import com.vub.model.Entry;

/**
 * Class that calculates the score of a current solution.
 * 
 * This way is simple, but is not recommended due to too slow and not scalable.
 * 
 * Use drools rule engine instead.
 * 
 * @author pieter
 * 
 */
public class SchedularSimpleScoreCalculator implements
		SimpleScoreCalculator<Schedular> {

	public SimpleScore calculateScore(Schedular solution) {
		int score = 0;
		List<Entry> entryList = solution.getEntryList();
		List<Pair<Date, String>> agendaTeacher = new ArrayList<Pair<Date, String>>();

		for (Entry e : entryList) {
				CourseComponent courseComponent = e.getCourseComponent();
				for (int j = 0; j < courseComponent.getTeachers().size(); j++) {
					String teacherName = courseComponent.getTeachers().get(j)
							.getUser().getUsername();
					if (agendaTeacher.contains(new Pair<Date, String>(e
							.getStartDate(), teacherName))) {
						score--;
					} else {
						agendaTeacher.add(new Pair<Date, String>(e
								.getStartDate(), teacherName));
					}
				}
		}

		return SimpleScore.valueOf(score);
	}

	private class Pair<T, V> {
		public T first;
		public V second;

		public Pair(T first, V second) {
			this.first = first;
			this.second = second;
		}
		
		@Override
		public boolean equals(Object o) {
			@SuppressWarnings("unchecked")
			Pair<T,V> other = (Pair<T,V>) o;
			return first == other.first && second == other.second;
		}
	}
}
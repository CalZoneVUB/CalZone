package com.vub.scheduler;

import org.optaplanner.core.impl.heuristic.selector.common.decorator.SelectionFilter;
import org.optaplanner.core.impl.score.director.ScoreDirector;

import com.vub.model.Entry;

/**
 * This class represents an implementation of the {@link SelectionFilter} interface of OptaPlanner.
 * This class will be used by OptaPlanner during (re)scheduling to check whether an {@link Entry} can be moved or not.
 * 
 * For more info, please check: https://docs.jboss.org/drools/release/6.0.1.Final/optaplanner-docs/html_single/index.html#immovablePlanningEntities
 * @author youri
 *
 */
public class MovableEntrySelectionFilter implements SelectionFilter<Entry> {

	@Override
	public boolean accept(ScoreDirector scoreDirector, Entry selection) {
		//returns true when an Entry is movable and false when it's immovable
		return !selection.isFrozen();
	}

}

package com.vub.scheduler;

import java.util.ArrayList;
import java.util.List;

import com.vub.model.Entry;

public class SchedulerHelper {
	public static long checkSpareHoursAndNoonBreak(List<Entry> orderedEntryList) {
		long totalDurationOfDay;
		long hoursOfClass;
		List<Pair<Integer, Integer> > listOfSpareHours;
		boolean spareHourAtNoon = false;
		long spareHours;
			
		listOfSpareHours = new ArrayList<Pair<Integer, Integer>>();
		Entry lastEntry = orderedEntryList.get(orderedEntryList.size()-1);
		Entry firstEntry = orderedEntryList.get(0);
		totalDurationOfDay = Entry.calcEndDate(lastEntry).getTime() - firstEntry.getStartDate().getTime();
			
		hoursOfClass = (long) firstEntry.getCourseComponent().getDuration();
			
		Entry previousEntry = firstEntry;
		orderedEntryList.remove(0); // since we don't want firstEntry in the coming for-loop
		for(Entry e : orderedEntryList){
			int endedAt = Entry.calcEndDate(previousEntry).getHours();
			int startingAt = e.getStartDate().getHours();
			if(startingAt - endedAt != 0)
				listOfSpareHours.add(new Pair<Integer,Integer>(endedAt,startingAt));
			hoursOfClass += (long) e.getCourseComponent().getDuration();
			previousEntry = e;
		}
			
		// Since totalDurationOfDay is in milliseconds, we need to convert it to hours
		totalDurationOfDay /= 60*60*1000;
		spareHours = totalDurationOfDay - hoursOfClass;
		if(Entry.calcEndDate(lastEntry).getHours() <= 12 || firstEntry.getStartDate().getHours() >= 14 ){
			if(spareHours > 0)
				return -(spareHours - 1); 
			else
				return 0;
		} else {
			for(Pair<Integer,Integer> s : listOfSpareHours){
				int middle = (s.first + s.second)/2;
				if((middle >= 12) && (middle <= 14)){
					spareHourAtNoon = true;
					break;
				}
			}
			
			if(spareHourAtNoon){
				if (spareHours == 1){
					return 0;
				}
				else
					return -(spareHours - 1);
			} else 
				return -5 * spareHours;
		}
	}
}

package com.vub.scheduler;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.zohhak.api.Coercion;
import com.googlecode.zohhak.api.TestWith;
import com.googlecode.zohhak.api.runners.ZohhakRunner;
import com.vub.model.Entry;

/**
 * 
 * @author Pieter Meiresone
 * 
 */
@RunWith(ZohhakRunner.class)
public class SchedulerHelperTest {
	final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Test method for the method
	 * {@link SchedulerHelper#checkSpareHoursAndNoonBreak(List)}. This test is
	 * several times repeated with different input data, these represent the
	 * different cases in {@link SchedulerHelper#checkSpareHoursAndNoonBreak(List)}.
	 * 
	 * @param entryList
	 *            The list of entries.
	 * @param testCase
	 *            The description of the testcase.
	 * @param expectedScore
	 *            The expected score of the test case.
	 */
	@TestWith(coercers = SchedulerHelperTest.class, value = {
			"2014/1/10-08:00@2|2014/1/10-10:00@2, 'Case 1 subcase 1: Only class before noon and no spare hours.', 0",
			"2014/1/10-09:00@2|2014/1/10-11:00@2, 'Case 1 subcase 1: Only class before noon and no spare hours.', 0",
			"2014/1/10-08:00@2|2014/1/10-11:00@2, 'Case 1 subcase 2: Only class before noon and 1 spare hour.', -1",
			"2014/1/10-13:00@2|2014/1/10-15:00@2, 'Case 2 subcase 1: Only class after noon and no spare hours.', 0",
			"2014/1/10-13:00@2|2014/1/10-16:00@2, 'Case 2 subcase 2: Only class after noon and 1 spare hours.', -1",
			"2014/1/10-08:00@2|2014/1/10-10:00@2|2014/1/10-13:00@2|2014/1/10-15:00@2, 'Case 3 subcase 1: All day of class with one spare hour during noon.', 0",
			"2014/1/10-08:00@2|2014/1/10-10:00@2|2014/1/10-14:00@2|2014/1/10-16:00@2, 'Case 3 subcase 2: All day of class with two spare hours during noon.', -1",
			"2014/1/10-08:00@2|2014/1/10-10:00@2|2014/1/10-16:00@2, 'Case 3 subcase 2: All day of class with four spare hours during noon.', -3",
			"2014/1/10-08:00@2|2014/1/10-10:00@2|2014/1/10-12:00@2|2014/1/10-14:00@2, 'Case 3 subcase 3: class all day without spare hours', -5",
			"2014/1/10-08:00@2|2014/1/10-10:00@2|2014/1/10-12:00@2|2014/1/10-14:00@2|2014/1/10-17:00@1, 'Case 3 subcase 3: class all day without spare hours', -10",
			"2014/1/10-12:00@2, 'Case 4 subcase 1: first class starts at noon and ends just before noon.', 0",
			"2014/1/10-12:00@1|2014/1/10-14:00@2, 'Case 4 subcase 2 subcase 1', 0",
			"2014/1/10-12:00@1|2014/1/10-14:00@1|2014/1/10-16:00@2, 'Case 4 subcase 2 subcase 2.', -1",
			"2014/1/10-12:00@2|2014/1/10-14:00@2|2014/1/10-16:00@2, 'Case 4 subcase 2 subcase 3', -5",
			"2014/1/10-12:00@2|2014/1/10-15:00@2, 'Case 4 subcase 2 subcase 3', -10"})
	public void checkSpareHoursAndNoonBreak(ArrayList<Entry> entryList,
			String testCase, int expectedScore) {
		Collections.sort(entryList);
		long result = SchedulerHelper.checkSpareHoursAndNoonBreak(entryList);
		assertEquals(testCase, expectedScore, result);
	}

	/**
	 * 
	 * @param entries
	 *            Different entries must be seperated by a "|" character. A
	 *            string-entry consists of the start date and duration,
	 *            separated by a "@" character. The date must be in the format
	 *            yyyy/MM/dd-HH:mm
	 * @return a list of entries.
	 */
	@Coercion
	public ArrayList<Entry> parseEntries(String entries) {
		String[] listOfStringEntries = entries.split("\\|");
		ArrayList<Entry> result = new ArrayList<Entry>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd-HH:mm");

		for (String s : listOfStringEntries) {
			String[] strEntry = s.split("@");
			try {
				Date date = formatter.parse(strEntry[0]);
				int duration = Integer.parseInt(strEntry[1]);
				Entry entry = Helper.createEntry(date, duration);
				result.add(entry);
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}

		}

		return result;
	}

}

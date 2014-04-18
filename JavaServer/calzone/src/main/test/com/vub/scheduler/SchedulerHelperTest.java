package com.vub.scheduler;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.vub.model.Entry;

public class SchedulerHelperTest {
	/**
	 * Test method for the method
	 * {@link SchedulerHelper#checkSpareHoursAndNoonBreak(List)}
	 */
	@Test
	@Ignore
	public void checkSpareHoursAndNoonBreak() {
		// Case 1 : Subcase 1
		{
			Entry entry1 = createEntry(new Date(2014, 1, 10, 8, 0), 2);
			Entry entry2 = createEntry(new Date(2014, 1, 10, 10, 0), 2);
			List<Entry> entryList = Arrays.asList(entry1, entry2);
			Collections.sort(entryList);

			long result = SchedulerHelper
					.checkSpareHoursAndNoonBreak(entryList);
			String message = "Case 1, subcase 1: Only class before noon and no spare hours.";
			assertEquals(message, 0, result);
		}
		
		// Case 1 : Subcase 1 (Bis)
				{
					Entry entry1 = createEntry(new Date(2014, 1, 10, 9, 0), 2);
					Entry entry2 = createEntry(new Date(2014, 1, 10, 11, 0), 2);
					List<Entry> entryList = Arrays.asList(entry1, entry2);
					Collections.sort(entryList);

					long result = SchedulerHelper
							.checkSpareHoursAndNoonBreak(entryList);
					String message = "Case 1, subcase 1: Only class before noon and no spare hours.";
					assertEquals(message, 0, result);
				}

		// Case 1 : Subcase 2
		{
			Entry entry1 = createEntry(new Date(2014, 1, 10, 8, 0), 2);
			Entry entry2 = createEntry(new Date(2014, 1, 10, 11, 0), 2);
			List<Entry> entryList = Arrays.asList(entry1, entry2);
			Collections.sort(entryList);

			long result = SchedulerHelper
					.checkSpareHoursAndNoonBreak(entryList);
			String message = "Case 1, subcase 2: Only class before noon and 1 spare hour.";
			assertEquals(message, -1, result);
		}

		// Case 2 : Subcase 1
		{
			Entry entry1 = createEntry(new Date(2014, 1, 10, 13, 0), 2);
			Entry entry2 = createEntry(new Date(2014, 1, 10, 15, 0), 2);
			List<Entry> entryList = Arrays.asList(entry1, entry2);
			Collections.sort(entryList);

			long result = SchedulerHelper
					.checkSpareHoursAndNoonBreak(entryList);
			String message = "Case 2, subcase 1: Only class after noon and no spare hours.";
			assertEquals(message, 0, result);
		}

		// Case 2 : Subcase 2
		{
			Entry entry1 = createEntry(new Date(2014, 1, 10, 13, 0), 2);
			Entry entry2 = createEntry(new Date(2014, 1, 10, 16, 0), 2);
			List<Entry> entryList = Arrays.asList(entry1, entry2);
			Collections.sort(entryList);

			long result = SchedulerHelper
					.checkSpareHoursAndNoonBreak(entryList);
			String message = "Case 2, subcase 2: Only class after noon and 1 spare hours.";
			assertEquals(message, -1, result);
		}

		// Case 3 : Subcase 1
		{
			Entry entry1 = createEntry(new Date(2014, 1, 10, 8, 0), 2);
			Entry entry2 = createEntry(new Date(2014, 1, 10, 10, 0), 2);
			Entry entry3 = createEntry(new Date(2014, 1, 10, 13, 0), 2);
			Entry entry4 = createEntry(new Date(2014, 1, 10, 15, 0), 2);
			List<Entry> entryList = Arrays.asList(entry1, entry2, entry3,
					entry4);
			Collections.sort(entryList);

			long result = SchedulerHelper
					.checkSpareHoursAndNoonBreak(entryList);
			String message = "Case 3, subcase 1: All day of class with one spare hour during noon.";
			assertEquals(message, 0, result);
		}

		// Case 3 : Subcase 2
		{
			Entry entry1 = createEntry(new Date(2014, 1, 10, 8, 0), 2);
			Entry entry2 = createEntry(new Date(2014, 1, 10, 10, 0), 2);
			Entry entry3 = createEntry(new Date(2014, 1, 10, 14, 0), 2);
			Entry entry4 = createEntry(new Date(2014, 1, 10, 16, 0), 2);
			List<Entry> entryList = Arrays.asList(entry1, entry2, entry3,
					entry4);
			Collections.sort(entryList);

			long result = SchedulerHelper
					.checkSpareHoursAndNoonBreak(entryList);
			String message = "Case 3, subcase 2: All day of class with two spare hours during noon.";
			assertEquals(message, -1, result);
		}

		// Case 3 : Subcase 2 (Bis)
		{
			Entry entry1 = createEntry(new Date(2014, 1, 10, 8, 0), 2);
			Entry entry2 = createEntry(new Date(2014, 1, 10, 10, 0), 2);
			Entry entry3 = createEntry(new Date(2014, 1, 10, 16, 0), 2);
			List<Entry> entryList = Arrays.asList(entry1, entry2, entry3);
			Collections.sort(entryList);

			long result = SchedulerHelper
					.checkSpareHoursAndNoonBreak(entryList);
			String message = "Case 3, subcase 2: All day of class with four spare hours during noon.";
			assertEquals(message, -3, result);
		}

		// Case 3 : Subcase 3
		{
			Entry entry1 = createEntry(new Date(2014, 1, 10, 8, 0), 2);
			Entry entry2 = createEntry(new Date(2014, 1, 10, 10, 0), 2);
			Entry entry3 = createEntry(new Date(2014, 1, 10, 16, 0), 2);
			List<Entry> entryList = Arrays.asList(entry1, entry2, entry3);
			Collections.sort(entryList);

			long result = SchedulerHelper
					.checkSpareHoursAndNoonBreak(entryList);
			String message = "Case 3, subcase 3: All day of class with two spare hours during noon.";
			assertEquals(message, -3, result);
		}

		// Class during entire day

		Entry enty1 = createEntry(new Date(2014, 1, 10, 8, 0), 2);
	}
}

package com.jpmc.model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class ArabiaWorkingDaysTest {
	private WorkingDays workingDays;

	@Before
	public void setUp() throws Exception {
		workingDays = ArabiaWorkingDays.getInstance();
	}

	@Test
	public void testFindFirstWorkingDate_Sunday() throws Exception {
		final LocalDate aSunday = LocalDate.of(2019, 05, 05);

		assertEquals(aSunday, workingDays.findFirstWorkingDate(aSunday)); // true for Arab
	}

	@Test
	public void testFindFirstWorkingDate_Thursday() throws Exception {
		final LocalDate aThursday = LocalDate.of(2019, 05, 02);

		assertEquals(aThursday, workingDays.findFirstWorkingDate(aThursday)); // true for Arab
	}

	@Test
	public void testFindFirstWorkingDate_Friday() throws Exception {
		final LocalDate aFriday = LocalDate.of(2018, 10, 12);

		assertEquals(LocalDate.of(2018, 10, 14), workingDays.findFirstWorkingDate(aFriday)); // true next working day
																							// Arab
	}

}
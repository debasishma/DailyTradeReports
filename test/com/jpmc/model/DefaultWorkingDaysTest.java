package com.jpmc.model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class DefaultWorkingDaysTest {

	private WorkingDays workingDays;

	@Before
	public void setUp() throws Exception {
		workingDays = DefaultWorkingDays.getInstance();
	}

	@Test
	public void testFindFirstWorkingDate_Monday() throws Exception {
		final LocalDate aMonday = LocalDate.of(2019, 05, 06);

		assertEquals(aMonday, workingDays.findFirstWorkingDate(aMonday));
	}

	@Test
	public void testFindFirstWorkingDate_Friday() throws Exception {
		final LocalDate aFriday = LocalDate.of(2019, 05, 03);

		assertEquals(aFriday, workingDays.findFirstWorkingDate(aFriday));
	}

	@Test
	public void testFindFirstWorkingDate_Saturday() throws Exception {
		final LocalDate aSaturday = LocalDate.of(2018, 10, 13);

		assertEquals(LocalDate.of(2018, 10, 15), workingDays.findFirstWorkingDate(aSaturday));
	}

	@Test
	public void testFindFirstWorkingDate_Sunday() throws Exception {
		final LocalDate aSunday = LocalDate.of(2018, 10, 14);

		assertEquals(LocalDate.of(2018, 10, 15), workingDays.findFirstWorkingDate(aSunday));
	}
}
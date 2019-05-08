package com.jpmc.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Finding working days for different region based on currency
 */
public abstract class WorkingDays {
	protected Map<DayOfWeek, Boolean> workingdays = new HashMap<>();

	protected abstract void setupWorkingDays();

	public WorkingDays() {
		setupWorkingDays();
	}

	/**
	 * Method to calculate first working day for a particular region
	 * 
	 * @param date
	 *            object with settlement date
	 */
	public LocalDate findFirstWorkingDate(LocalDate date) {

		// Check for weekday availability for the settlement date
		if (workingdays.values().stream().noneMatch(b -> b)) {
			return null;
		}

		// find first working day
		return getFirstWorkingDayofWeek(date);
	}

	private LocalDate getFirstWorkingDayofWeek(LocalDate date) {
		final DayOfWeek dayOfWeek = date.getDayOfWeek();

		// check if day of week is working day
		if (workingdays.get(dayOfWeek)) {
			return date;
		} else {
			// Else calculate working day for next day of week
			return getFirstWorkingDayofWeek(date.plusDays(1));
		}
	}
}

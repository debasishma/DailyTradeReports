package com.jpmc.model;

import java.time.DayOfWeek;

/**
 * Working days of Arab region AED Friday and Saturday weekend Rest is Weekdays
 * including Sunday
 */
public class ArabiaWorkingDays extends WorkingDays {

	private static ArabiaWorkingDays instance = null;

	public static ArabiaWorkingDays getInstance() {
		if (instance == null) {
			instance = new ArabiaWorkingDays();
		}
		return instance;
	}

	private ArabiaWorkingDays() {
		super();
	}

	@Override
	protected void setupWorkingDays() {
		this.workingdays.put(DayOfWeek.SUNDAY, true);
		this.workingdays.put(DayOfWeek.MONDAY, true);
		this.workingdays.put(DayOfWeek.TUESDAY, true);
		this.workingdays.put(DayOfWeek.WEDNESDAY, true);
		this.workingdays.put(DayOfWeek.THURSDAY, true);
		this.workingdays.put(DayOfWeek.FRIDAY, false);
		this.workingdays.put(DayOfWeek.SATURDAY, false);
	}
}

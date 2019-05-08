package com.jpmc.model;

import java.time.DayOfWeek;

/**
 * Working days of General region except Arab Saturday and Sunday weekend Rest
 * is Weekdays
 * 
 */
public class DefaultWorkingDays extends WorkingDays {

	private static DefaultWorkingDays instance = null;

	public static DefaultWorkingDays getInstance() {
		if (instance == null) {
			instance = new DefaultWorkingDays();
		}
		return instance;
	}

	private DefaultWorkingDays() {
		super();
	}

	@Override
	protected void setupWorkingDays() {
		this.workingdays.put(DayOfWeek.MONDAY, true);
		this.workingdays.put(DayOfWeek.TUESDAY, true);
		this.workingdays.put(DayOfWeek.WEDNESDAY, true);
		this.workingdays.put(DayOfWeek.THURSDAY, true);
		this.workingdays.put(DayOfWeek.FRIDAY, true);
		this.workingdays.put(DayOfWeek.SATURDAY, false);
		this.workingdays.put(DayOfWeek.SUNDAY, false);
	}
}

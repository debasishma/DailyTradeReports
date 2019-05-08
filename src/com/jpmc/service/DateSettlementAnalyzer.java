package com.jpmc.service;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Set;

import com.jpmc.model.ArabiaWorkingDays;
import com.jpmc.model.DefaultWorkingDays;
import com.jpmc.model.Instruction;
import com.jpmc.model.WorkingDays;

/**
 * Finding Settlement days as per instruction set based on region
 */
public class DateSettlementAnalyzer {

	private static final String UAE_CURRENCY_AED = "AED";
	private static final String SAUDI_CURRENCY_SAR = "SAR";

	/**
	 * Method for finding settlement date with working days for region for each
	 * instruction
	 * 
	 * @param set
	 *            of instructions with settlement days
	 */
	public static void calculateSettlementDates(Set<Instruction> instructions) {
		instructions.forEach(DateSettlementAnalyzer::calculateSettlementDate);
	}

	/**
	 * Method to find settlement days for a region as per input instruction
	 * 
	 * @param instance
	 *            of instruction
	 */
	public static void calculateSettlementDate(Instruction instruction) {
		// Region wise working day logic
		final WorkingDays workingDaysMechanism = getWorkingDaysStrategy(instruction.getCurrency());

		final LocalDate newSettlementDate = workingDaysMechanism.findFirstWorkingDate(instruction.getSettlementDate());

		if (newSettlementDate != null) {
			instruction.setSettlementDate(newSettlementDate);
		}
	}

	/**
	 * Choose working days strategy based on currency
	 * 
	 * @param currency
	 * @return working days for the currency passed
	 */
	private static WorkingDays getWorkingDaysStrategy(Currency currency) {
		if ((currency.getCurrencyCode().equals(UAE_CURRENCY_AED))
				|| (currency.getCurrencyCode().equals(SAUDI_CURRENCY_SAR))) {
			return ArabiaWorkingDays.getInstance();
		}
		return DefaultWorkingDays.getInstance();
	}
}

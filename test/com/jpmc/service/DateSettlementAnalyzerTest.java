package com.jpmc.service;

import org.junit.Test;

import com.jpmc.model.Instruction;
import com.jpmc.model.InstructionData;
import com.jpmc.model.InstructionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import static org.junit.Assert.assertEquals;

public class DateSettlementAnalyzerTest {
	@Test
	public void calculateSettlementDate_default_Friday() throws Exception {
		final LocalDate initialSettlementDate = LocalDate.of(2019, 5, 17); // Friday

		final BigDecimal agreedFx = BigDecimal.valueOf(0.20);
		final BigDecimal pricePerUnit = BigDecimal.valueOf(98.11);
		final int units = 120;

		final Instruction dummyIns = new Instruction("Amazon", InstructionType.BUY, LocalDate.of(2019, 5, 9),
				LocalDate.of(2019, 5, 17),
				new InstructionData(Currency.getInstance("SGD"), agreedFx, units, pricePerUnit));

		DateSettlementAnalyzer.calculateSettlementDate(dummyIns);

		assertEquals(initialSettlementDate, dummyIns.getSettlementDate()); // true
	}

	@Test
	public void calculateSettlementDate_default_Sunday() throws Exception {
		final LocalDate initialSettlementDate = LocalDate.of(2019, 5, 19); // Sunday

		final BigDecimal agreedFx = BigDecimal.valueOf(0.20);
		final BigDecimal pricePerUnit = BigDecimal.valueOf(98.11);
		final int units = 120;

		final Instruction dummyIns = new Instruction("Amazon", InstructionType.BUY, LocalDate.of(2019, 5, 9),
				LocalDate.of(2019, 5, 20),
				new InstructionData(Currency.getInstance("SGD"), agreedFx, units, pricePerUnit));

		DateSettlementAnalyzer.calculateSettlementDate(dummyIns);

		// Monday
		assertEquals(LocalDate.of(2019, 5, 20), dummyIns.getSettlementDate());
	}

	@Test
	public void calculateSettlementDate_arabia_Friday() throws Exception {
		final LocalDate initialSettlementDate = LocalDate.of(2017, 3, 24); // Friday

		final Instruction dummyIns = new Instruction("E1", InstructionType.BUY, LocalDate.of(2017, 3, 10),
				initialSettlementDate, new InstructionData(Currency.getInstance("AED"), BigDecimal.valueOf(0.50), 200,
						BigDecimal.valueOf(100.25)));

		DateSettlementAnalyzer.calculateSettlementDate(dummyIns);

		// Sunday
		assertEquals(LocalDate.of(2017, 3, 26), dummyIns.getSettlementDate());
	}

	@Test
	public void calculateSettlementDate_arabia_Sunday() throws Exception {
		final LocalDate initialSettlementDate = LocalDate.of(2017, 3, 26); // Sunday

		final Instruction dummyIns = new Instruction("E1", InstructionType.BUY, LocalDate.of(2017, 3, 10),
				initialSettlementDate, new InstructionData(Currency.getInstance("SAR"), BigDecimal.valueOf(0.50), 200,
						BigDecimal.valueOf(100.25)));

		DateSettlementAnalyzer.calculateSettlementDate(dummyIns);

		assertEquals(initialSettlementDate, dummyIns.getSettlementDate()); // true
	}
}
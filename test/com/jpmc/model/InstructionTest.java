package com.jpmc.model;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import static org.junit.Assert.assertEquals;

public class InstructionTest {

	@Test
	public void testTradeAmountCalc() throws Exception {
		final BigDecimal agreedFx = BigDecimal.valueOf(0.20);
		final BigDecimal pricePerUnit = BigDecimal.valueOf(98.11);
		final int units = 120;

		final Instruction fakeInstruction = new Instruction("Amazon", InstructionType.BUY, LocalDate.of(2019, 05, 2),
				LocalDate.of(2019, 05, 6),
				new InstructionData(Currency.getInstance("SGD"), agreedFx, units, pricePerUnit));

		assertEquals(agreedFx, fakeInstruction.getFxRate());
		assertEquals(pricePerUnit, fakeInstruction.getPricePerUnit());
		assertEquals(units, fakeInstruction.getUnits());

		final BigDecimal correct = pricePerUnit.multiply(agreedFx).multiply(BigDecimal.valueOf(units)).setScale(2,
				BigDecimal.ROUND_HALF_EVEN);
		assertEquals(correct, fakeInstruction.getTradeAmount());
	}
}
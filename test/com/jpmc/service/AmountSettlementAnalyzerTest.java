package com.jpmc.service;

import com.jpmc.model.Rank;
import com.jpmc.model.Instruction;
import com.jpmc.model.InstructionData;
import com.jpmc.model.InstructionType;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class AmountSettlementAnalyzerTest {

	private static final LocalDate MONDAY = LocalDate.of(2018, 5, 21);
	private static final LocalDate TUESDAY = LocalDate.of(2018, 5, 22);
	private static final LocalDate WEDNESDAY = LocalDate.of(2018, 5, 23);
	private static final LocalDate SATURDAY = LocalDate.of(2018, 5, 19);
	private static final LocalDate SUNDAY = LocalDate.of(2018, 5, 20);

	private static Set<Instruction> getSampleInstuction() {
		final Set<Instruction> instructions = new HashSet<>();

		// Monday settle
		instructions.add(new Instruction("Amazon", InstructionType.BUY, LocalDate.of(2019, 5, 10), MONDAY,
				new InstructionData(Currency.getInstance("SGD"), BigDecimal.valueOf(1), 100, BigDecimal.valueOf(1))));

		instructions.add(new Instruction("Barclays", InstructionType.BUY, LocalDate.of(2019, 5, 10), MONDAY,
				new InstructionData(Currency.getInstance("SGD"), BigDecimal.valueOf(1), 200, BigDecimal.valueOf(1))));

		instructions.add(new Instruction("CISCO", InstructionType.BUY, LocalDate.of(2019, 5, 10), SATURDAY,
				new InstructionData(Currency.getInstance("SGD"), BigDecimal.valueOf(1), 300, BigDecimal.valueOf(1))));

		instructions.add(new Instruction("TIBCO", InstructionType.SELL, LocalDate.of(2017, 5, 10), SUNDAY,
				new InstructionData(Currency.getInstance("SGD"), BigDecimal.valueOf(1), 200, BigDecimal.valueOf(1))));

		DateSettlementAnalyzer.calculateSettlementDates(instructions);

		return instructions;
	}

	@Test
	public void testDailyIncomingAmount() throws Exception {
		final Map<LocalDate, BigDecimal> dailyIncomingAmount = AmountSettlementAnalyzer
				.findDailyIncomingAmount(getSampleInstuction());

		assertEquals(1, dailyIncomingAmount.size());
		assertTrue(Objects.equals(dailyIncomingAmount.get(MONDAY),
				BigDecimal.valueOf(200.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
		}

	@Test
	public void testDailyOutgoingAmount() throws Exception {
		final Map<LocalDate, BigDecimal> dailyOutgoingAmount = AmountSettlementAnalyzer
				.findDailyOutgoingAmount(getSampleInstuction());

		assertEquals(1, dailyOutgoingAmount.size());
		assertTrue(Objects.equals(dailyOutgoingAmount.get(MONDAY),
				BigDecimal.valueOf(600.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
		
	}

	@Test
	public void testDailyIncomingRanking() throws Exception {
		final Map<LocalDate, LinkedList<Rank>> dailyIncomingRanking = AmountSettlementAnalyzer
				.findDailyIncomingRanking(getSampleInstuction());

		assertEquals(1, dailyIncomingRanking.size());

		assertEquals(1, dailyIncomingRanking.get(MONDAY).size());


	}

	@Test
	public void testDailyOutgoingRanking() throws Exception {
		final Map<LocalDate, LinkedList<Rank>> dailyOutgoingRanking = AmountSettlementAnalyzer
				.findDailyOutgoingRanking(getSampleInstuction());

		assertEquals(1, dailyOutgoingRanking.size());

		assertEquals(3, dailyOutgoingRanking.get(MONDAY).size());
		assertFalse(dailyOutgoingRanking.get(MONDAY).contains(new Rank(1, "Abcd", MONDAY)));
		assertFalse(dailyOutgoingRanking.get(MONDAY).contains(new Rank(2, "TIBCO", MONDAY)));
		assertFalse(dailyOutgoingRanking.get(MONDAY).contains(new Rank(3, "BTCI", MONDAY)));


	}
}
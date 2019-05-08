package com.jpmc.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import com.jpmc.model.Instruction;
import com.jpmc.model.InstructionType;
import com.jpmc.model.Rank;

import static java.util.stream.Collectors.*;

/**
 * Segregate the daily outgoing and incoming instructions based on date and
 * BUY/SELL
 */
public class AmountSettlementAnalyzer {

	// Create a predicate for outgoing
	private final static Predicate<Instruction> outgoingPredicate = instruction -> instruction.getInstructionType()
			.equals(InstructionType.BUY);

	// Create a predicate for incoming
	private final static Predicate<Instruction> incomingPredicate = instruction -> instruction.getInstructionType()
			.equals(InstructionType.SELL);

	/**
	 * Find daily outgoing amount for BUY trades
	 * 
	 * @param instruction
	 *            set received in input
	 * @return collection of date mapped to its total BUY mount
	 */
	public static Map<LocalDate, BigDecimal> findDailyOutgoingAmount(Set<Instruction> instructions) {
		return calculateDailyAmount(instructions, outgoingPredicate);
	}

	/**
	 * Find daily incoming amount for SELL trades
	 * 
	 * @param instruction
	 *            set received in input
	 * @return collection of date mapped to its total SELL amount
	 */
	public static Map<LocalDate, BigDecimal> findDailyIncomingAmount(Set<Instruction> instructions) {
		return calculateDailyAmount(instructions, incomingPredicate);
	}

	/**
	 * Find daily outgoing ranking for BUY trades
	 * 
	 * @param instruction
	 *            set received in input
	 * @return collection of date mapped to its rank
	 */
	public static Map<LocalDate, LinkedList<Rank>> findDailyOutgoingRanking(Set<Instruction> instructions) {
		return calculateRanking(instructions, outgoingPredicate);
	}

	/**
	 * Find daily outgoing ranking for SELL trades
	 * 
	 * @param instruction
	 *            set received in input
	 * @return collection of date mapped to its rank
	 */
	public static Map<LocalDate, LinkedList<Rank>> findDailyIncomingRanking(Set<Instruction> instructions) {
		return calculateRanking(instructions, incomingPredicate);
	}

	private static Map<LocalDate, BigDecimal> calculateDailyAmount(Set<Instruction> instructions,
			Predicate<Instruction> predicate) {
		return instructions.stream().filter(predicate).collect(groupingBy(Instruction::getSettlementDate,
				mapping(Instruction::getTradeAmount, reducing(BigDecimal.ZERO, BigDecimal::add))));
	}

	private static Map<LocalDate, LinkedList<Rank>> calculateRanking(Set<Instruction> instructions,
			Predicate<Instruction> predicate) {
		final Map<LocalDate, LinkedList<Rank>> ranking = new HashMap<>();

		instructions.stream().filter(predicate).collect(groupingBy(Instruction::getSettlementDate, toSet()))
				.forEach((date, dailyInstructionSet) -> {
					final AtomicInteger rank = new AtomicInteger(1);

					final LinkedList<Rank> ranks = dailyInstructionSet.stream()
							.sorted((a, b) -> b.getTradeAmount().compareTo(a.getTradeAmount()))
							.map(instruction -> new Rank(rank.getAndIncrement(), instruction.getEntity(), date))
							.collect(toCollection(LinkedList::new));

					ranking.put(date, ranks);
				});

		return ranking;
	}
}

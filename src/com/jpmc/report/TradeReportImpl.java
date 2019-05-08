package com.jpmc.report;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import com.jpmc.model.Instruction;
import com.jpmc.model.Rank;
import com.jpmc.service.AmountSettlementAnalyzer;
import com.jpmc.service.DateSettlementAnalyzer;

public class TradeReportImpl implements TradeReport {
	private StringBuilder outputPrint = new StringBuilder(); // output string to print report

	/*
	 * 
	 * Method to create the report string for console print . Daily Outgoing amount
	 * Daily Incoming Amount Daily Outgoing entity ranking Daily Incoming entity
	 * ranking
	 *
	 */
	@Override
	public String createTradeReport(Set<Instruction> instructions) {

		DateSettlementAnalyzer.calculateSettlementDates(instructions);

		// output string for console
		return reportDailyOutgoingRanking(instructions,
				reportDailyIncomingRanking(instructions,
						reportDailyIncomingAmount(instructions, reportDailyOutgoingAmount(instructions, outputPrint))))
								.toString();
	}

	private static StringBuilder reportDailyOutgoingAmount(Set<Instruction> instructions, StringBuilder outputPrint) {
		final Map<LocalDate, BigDecimal> dailyOutgoingAmount = AmountSettlementAnalyzer
				.findDailyOutgoingAmount(instructions);

		outputPrint.append("\n---------------------------------------------\n")
				.append("Amount in USD settled incoming everyday           \n")
				.append("---------------------------------------------\n")
				.append("Settlement Date  |    Trade Amount(USD)      \n")
				.append("---------------------------------------------\n");

		for (LocalDate date : dailyOutgoingAmount.keySet()) {
			outputPrint.append(date).append("       |      ").append(dailyOutgoingAmount.get(date)).append("\n");
		}

		return outputPrint;
	}

	private static StringBuilder reportDailyIncomingAmount(Set<Instruction> instructions, StringBuilder outputPrint) {
		final Map<LocalDate, BigDecimal> dailyOutgoingAmount = AmountSettlementAnalyzer
				.findDailyIncomingAmount(instructions);

		outputPrint.append("\nAmount in USD settled outgoing everyday           \n")
				.append("---------------------------------------------\n")
				.append("Settlement Date  |    Trade Amount(USD)      \n")
				.append("---------------------------------------------\n");

		for (LocalDate date : dailyOutgoingAmount.keySet()) {
			outputPrint.append(date).append("       |      ").append(dailyOutgoingAmount.get(date)).append("\n");
		}

		return outputPrint;
	}

	private static StringBuilder reportDailyOutgoingRanking(Set<Instruction> instructions, StringBuilder outputPrint) {
		final Map<LocalDate, LinkedList<Rank>> dailyOutgoingRanking = AmountSettlementAnalyzer
				.findDailyOutgoingRanking(instructions);

		outputPrint.append("\n---------------------------------------------\n")
				.append("Ranking of entities based on everyday outgoing          \n")
				.append("---------------------------------------------\n")
				.append("     Date    |     Rank   |   Entity     \n")
				.append("---------------------------------------------\n");

		for (LocalDate date : dailyOutgoingRanking.keySet()) {
			for (Rank rank : dailyOutgoingRanking.get(date)) {
				outputPrint.append(date).append("   |      ").append(rank.getRank()).append("     |    ")
						.append(rank.getEntity()).append("\n");
			}
		}

		return outputPrint;
	}

	private static StringBuilder reportDailyIncomingRanking(Set<Instruction> instructions, StringBuilder outputPrint) {
		final Map<LocalDate, LinkedList<Rank>> dailyIncomingRanking = AmountSettlementAnalyzer
				.findDailyIncomingRanking(instructions);

		outputPrint.append("\n---------------------------------------------\n")
				.append("Ranking of entities based on everyday incoming          \n")
				.append("---------------------------------------------\n")
				.append("     Date    |     Rank   |   Entity     \n")
				.append("---------------------------------------------\n");

		for (LocalDate date : dailyIncomingRanking.keySet()) {
			for (Rank rank : dailyIncomingRanking.get(date)) {
				outputPrint.append(date).append("   |      ").append(rank.getRank()).append("     |    ")
						.append(rank.getEntity()).append("\n");
			}
		}

		return outputPrint;
	}
}
